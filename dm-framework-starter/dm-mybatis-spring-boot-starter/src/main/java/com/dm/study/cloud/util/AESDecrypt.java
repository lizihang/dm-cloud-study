package com.dm.study.cloud.util;

import com.dm.study.cloud.annotation.SensitiveFiled;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
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
 * <p>创建日期：2022年07月19日 16:40</p>
 * <p>类全名：com.dm.study.cloud.util.AESDecrypt</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class AESDecrypt implements DecryptUtil {
	//
	private static final String key = "dm-decrypt";

	@Override
	public <T> T decrypt(T result) throws IllegalAccessException {
		//取出resultType的类
		Class<?> resultClass = result.getClass();
		Field[] declaredFields = resultClass.getDeclaredFields();
		for (Field declaredField : declaredFields) {
			//去除所有被EncryptDecryptFiled注解的字段
			SensitiveFiled sensitiveFiled = declaredField.getAnnotation(SensitiveFiled.class);
			if (!Objects.isNull(sensitiveFiled)) {
				//将此对象的 accessible 标志设置为指示的布尔值。值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。
				declaredField.setAccessible(true);
				//这里的result就相当于是字段的访问器
				Object object = declaredField.get(result);
				//只支持String解密
				if (object instanceof String) {
					String value = (String) object;
					//对注解在这段进行逐一解密
					declaredField.set(result, AESUtil.decrypt(value, key));
				}
			}
		}
		return result;
	}
}
