package com.dm.study.cloud.exception;

import io.milvus.exception.MilvusException;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年06月13日 16:24</p>
 * <p>类全名：com.dm.study.cloud.exception.CustomMilvusException</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class CustomMilvusException extends MilvusException {
	private static final long serialVersionUID = 2078443427832519978L;

	public CustomMilvusException(String msg, Integer status) {
		super(msg, status);
	}
}
