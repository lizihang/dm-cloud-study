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
 * <p>创建日期：2023年06月13日 16:16</p>
 * <p>类全名：com.dm.study.cloud.param.FieldTypeParams</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class FieldTypeParams implements Serializable {
	private static final long    serialVersionUID = -156864511810206019L;
	private              String  name;
	private              Boolean primaryKey;
	private              String  description;
	private              Integer dataTypeValue;
	private              Boolean autoID;
	private              Integer dimension;
	// private final Map<String,String> typeParams = new HashMap<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDataTypeValue() {
		return dataTypeValue;
	}

	public void setDataTypeValue(Integer dataTypeValue) {
		this.dataTypeValue = dataTypeValue;
	}

	public Boolean getAutoID() {
		return autoID;
	}

	public void setAutoID(Boolean autoID) {
		this.autoID = autoID;
	}

	public Integer getDimension() {
		return dimension;
	}

	public void setDimension(Integer dimension) {
		this.dimension = dimension;
	}
}
