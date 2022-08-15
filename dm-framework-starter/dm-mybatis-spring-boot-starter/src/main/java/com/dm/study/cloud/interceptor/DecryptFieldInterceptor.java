package com.dm.study.cloud.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dm.study.cloud.enums.EncryptField;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.util.*;
/**
 * <p>标题：解密拦截器</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年06月13日 19:31</p>
 * <p>类全名：com.dm.study.cloud.interceptor.DecryptFieldInterceptor</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
// @Component
// @Intercepts({
// 		// type 指定代理对象，method 指定代理方法，args 指定type代理类中method方法的参数
// 		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class }),
// 		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class })
// })
public class DecryptFieldInterceptor implements Interceptor {
	private static final Logger logger = LoggerFactory.getLogger(DecryptFieldInterceptor.class);

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		String sqlId = mappedStatement.getId();
		logger.info("==> intercept  SQL_ID: [{}]", sqlId);
		Object proceed = invocation.proceed();
		if (sqlId.contains("selectCount")) {
			logger.warn("==> SQL 语句类型是[selectCount] 直接返回结果");
			return proceed;
		}
		Object parameter = null;
		if (invocation.getArgs().length > 1) {
			parameter = invocation.getArgs()[1];
		}
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		Configuration configuration = mappedStatement.getConfiguration();
		String sql = genSql(configuration, boundSql);
		if (sql.contains("count") || sql.contains("COUNT") || sql.contains("Count")) {
			logger.warn("==> SQL 语句包含[count|COUNT|Count] 直接返回结果");
			return proceed;
		}
		String result = JSON.toJSONString(proceed);
		logger.info("==> SQL 查询结果 [{}]", result);
		JSONArray array = JSONArray.parseArray(result);
		logger.info("==> SQL 查询结果转换json [{}]", array);
		JSONArray retArray = new JSONArray();
		for (Object o : array) {
			JSONObject jsonObject = JSONObject.parseObject(o.toString());
			// 这种的最好还是统一
			if (jsonObject.containsKey(EncryptField.MOBILE.getCode())) {
				jsonObject.put(EncryptField.MOBILE.getCode(), jsonObject.getString(EncryptField.MOBILE.getCode()).substring(8));
			}
			if (jsonObject.containsKey(EncryptField.EMAIL.getCode())) {
				jsonObject.put(EncryptField.EMAIL.getCode(), jsonObject.getString(EncryptField.EMAIL.getCode()).substring(8));
			}
			retArray.add(JSON.parseObject(jsonObject.toJSONString(), ((ArrayList) proceed).get(0).getClass()));
		}
		return retArray;
	}

	private String genSql(Configuration configuration, BoundSql boundSql) {
		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		if (parameterMappings.size() > 0 && null != parameterObject) {
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				sql = sql.replaceFirst("\\?", getParameterVal(parameterObject));
			} else {
				MetaObject metaObject = configuration.newMetaObject(parameterObject);
				for (ParameterMapping parameterMapping : parameterMappings) {
					String propertyName = parameterMapping.getProperty();
					if (metaObject.hasGetter(propertyName)) {
						Object value = metaObject.getValue(propertyName);
						if (EncryptField.EMAIL.getCode().equalsIgnoreCase(propertyName) || EncryptField.MOBILE.getCode().equalsIgnoreCase(propertyName)) {
							logger.info("==> genSql before [{}]: [{}]", propertyName, value);
							// value = AesUtils.AESEncode(EncryptField.AES_KEY, value.toString());
							value = "encrypt-" + value.toString();
							logger.info("==> genSql after [{}]: [{}]", propertyName, value);
							metaObject.setValue(propertyName, value);
						}
						sql = sql.replaceFirst("\\?", getParameterVal(value));
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						Object value = boundSql.getAdditionalParameter(propertyName);
						sql = sql.replaceFirst("\\?", getParameterVal(value));
					}
				}
			}
		}
		return sql;
	}

	private String getParameterVal(Object obj) {
		String val;
		if (obj instanceof String) {
			val = "'" + obj.toString() + "'";
		} else if (obj instanceof Date) {
			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
			val = "'" + dateFormat.format(obj) + "'";
		} else {
			if (null != obj) {
				val = obj.toString();
			} else {
				val = "";
			}
		}
		return val;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}
}
