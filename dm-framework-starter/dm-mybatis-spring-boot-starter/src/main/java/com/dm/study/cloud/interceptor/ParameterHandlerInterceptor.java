package com.dm.study.cloud.interceptor;

import com.dm.study.cloud.annotation.SensitiveData;
import com.dm.study.cloud.util.EncryptUtil;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.Map;
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
 * <p>创建日期：2022年07月19日 16:19</p>
 * <p>类全名：com.dm.study.cloud.interceptor.ParameterHandlerInterceptor</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
@Intercepts({ @Signature(type = ParameterHandler.class, method = "setParameters", args = PreparedStatement.class) })
public class ParameterHandlerInterceptor implements Interceptor {
	@Resource
	EncryptUtil encryptUtil;

	@SuppressWarnings("unchecked")
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// @Signature 指定了 type= parameterHandler 后，这里的 invocation.getTarget() 便是parameterHandler
		ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();
		// 取出实例
		Object parameterObject = parameterHandler.getParameterObject();
		if (parameterObject != null) {
			if (parameterObject instanceof Map) {
				Map<String,Object> parameterMap = (Map<String,Object>) parameterObject;
				for (String key : parameterMap.keySet()) {
					Object param = parameterMap.get(key);
					Class<?> paramClass = param.getClass();
					//校验该实例的类是否被@SensitiveData所注解
					SensitiveData sensitiveData = AnnotationUtils.findAnnotation(paramClass, SensitiveData.class);
					if (Objects.nonNull(sensitiveData)) {
						// 取出当前类的所有字段，传入加密方法
						Field[] declaredFields = paramClass.getDeclaredFields();
						encryptUtil.aesEncrypt(declaredFields, param);
					}
				}
			} else {
				Class<?> parameterObjectClass = parameterObject.getClass();
				//校验该实例的类是否被@SensitiveData所注解
				SensitiveData sensitiveData = AnnotationUtils.findAnnotation(parameterObjectClass, SensitiveData.class);
				if (Objects.nonNull(sensitiveData)) {
					// 取出当前类的所有字段，传入加密方法
					Field[] declaredFields = parameterObjectClass.getDeclaredFields();
					encryptUtil.aesEncrypt(declaredFields, parameterObject);
				}
			}
		}
		// 获取原方法的返回值
		return invocation.proceed();
	}
}
