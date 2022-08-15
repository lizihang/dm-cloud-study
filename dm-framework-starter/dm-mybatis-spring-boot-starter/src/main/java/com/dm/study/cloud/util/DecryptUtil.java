package com.dm.study.cloud.util;

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
 * <p>类全名：com.dm.study.cloud.util.DecryptUtil</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface DecryptUtil {
	/**
	 * 解密
	 *
	 * @param result
	 * @param <T>
	 * @return
	 * @throws IllegalAccessException
	 */
	<T> T decrypt(T result) throws IllegalAccessException;
}
