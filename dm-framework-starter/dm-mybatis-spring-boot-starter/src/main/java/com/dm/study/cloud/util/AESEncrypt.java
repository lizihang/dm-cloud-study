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
 * <p>创建日期：2022年07月19日 16:38</p>
 * <p>类全名：com.dm.study.cloud.util.AESEncrypt</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class AESEncrypt implements EncryptUtil {
	//
	private static final String key = "dm-encrypt";

	@Override
	public <T> T aesEncrypt(Field[] aesFields, T paramsObject) throws IllegalAccessException {
		for (Field aesField : aesFields) {
			//取出所有被EncryptDecryptFiled注解的字段
			SensitiveFiled filed = aesField.getAnnotation(SensitiveFiled.class);
			if (!Objects.isNull(filed)) {
				//将此对象的 accessible 标志设置为指示的布尔值。值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。
				aesField.setAccessible(true);
				Object object = aesField.get(paramsObject);
				//这里暂时只对String类型来加密
				if (object instanceof String) {
					String value = (String) object;
					//开始对字段加密使用自定义的AES加密工具
					aesField.set(paramsObject, AESUtil.encrypt(value, key));
				}
			}
		}
		return paramsObject;
	}
}
