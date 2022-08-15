package com.dm.study.cloud.interceptor;

import com.dm.study.cloud.annotation.SensitiveData;
import com.dm.study.cloud.util.AESDecrypt;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年07月19日 16:42</p>
 * <p>类全名：com.dm.study.cloud.interceptor.ResultSetHandlerInterceptor</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
@Intercepts({ @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
public class ResultSetHandlerInterceptor implements Interceptor {
	@Resource
	AESDecrypt aesDecrypt;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		//取出查询的结果
		Object resultObject = invocation.proceed();
		if (Objects.isNull(resultObject)) {
			return null;
		}
		//基于selectList
		if (resultObject instanceof ArrayList) {
			ArrayList resultList = (ArrayList) resultObject;
			if (!CollectionUtils.isEmpty(resultList)) {
				for (Object result : resultList) {
					if (needToDecrypt(result)) {
						//逐一解密
						aesDecrypt.decrypt(result);
					}
				}
			}
			//基于selectOne
		} else {
			if (needToDecrypt(resultObject)) {
				aesDecrypt.decrypt(resultObject);
			}
		}
		return resultObject;
	}

	/**
	 * 对单个结果集判空的一个方法
	 * @param object
	 * @return
	 */
	private boolean needToDecrypt(Object object) {
		Class<?> objectClass = object.getClass();
		SensitiveData sensitiveData = AnnotationUtils.findAnnotation(objectClass, SensitiveData.class);
		return Objects.nonNull(sensitiveData);
	}
}
