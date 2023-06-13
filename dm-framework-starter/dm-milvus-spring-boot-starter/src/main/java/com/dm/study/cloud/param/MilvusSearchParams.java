package com.dm.study.cloud.param;

import java.io.Serializable;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年06月13日 16:18</p>
 * <p>类全名：com.dm.study.cloud.param.MilvusSearchParams</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class MilvusSearchParams implements Serializable {
	private static final long   serialVersionUID = 6645154572856917269L;
	private              String collectionName;

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
}
