package com.dm.study.cloud.util;

import java.lang.reflect.Field;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年07月19日 16:33</p>
 * <p>类全名：com.dm.study.cloud.util.EncryptUtil</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface EncryptUtil {
	/**
	 *
	 * @param aesFields paramsObject所申明的字段
	 * @param paramsObject mapper中paramsType的实例
	 * @param <T>
	 * @return
	 * @throws IllegalAccessException 字段不可访问的异常
	 * 这里为了写这个接口为了以后可以拓展掐他的加密类型
	 */
	<T> T aesEncrypt(Field[] aesFields, T paramsObject) throws IllegalAccessException;
}
