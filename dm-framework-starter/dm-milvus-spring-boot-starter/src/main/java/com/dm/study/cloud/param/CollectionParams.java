package com.dm.study.cloud.param;

import java.io.Serializable;
import java.util.List;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年06月13日 16:16</p>
 * <p>类全名：com.dm.study.cloud.param.CollectionParams</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class CollectionParams implements Serializable {
	private static final long                  serialVersionUID = 8419160289181327510L;
	private              String                collectionName;
	private              String                description;
	private              Integer               shardsNum;
	private              List<FieldTypeParams> filedTypeList;

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getShardsNum() {
		return shardsNum;
	}

	public void setShardsNum(Integer shardsNum) {
		this.shardsNum = shardsNum;
	}

	public List<FieldTypeParams> getFiledTypeList() {
		return filedTypeList;
	}

	public void setFiledTypeList(List<FieldTypeParams> filedTypeList) {
		this.filedTypeList = filedTypeList;
	}
}
