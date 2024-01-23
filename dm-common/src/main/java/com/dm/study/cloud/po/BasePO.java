package com.dm.study.cloud.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年03月15日 16:48</p>
 * <p>类全名：com.dm.study.cloud.po.BasePO</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class BasePO implements Serializable {
	private static final long   serialVersionUID = -2928960545586924970L;
	/* id */
	@TableId(type = IdType.AUTO)
	private              String id;
	/* 创建人 */
	@TableField(fill = FieldFill.INSERT)
	private              String createUser;
	/* 创建时间 */
	@TableField(fill = FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private              Date   createTime;
	/* 修改人 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private              String modifyUser;
	/* 修改时间 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private              Date   modifyTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "BasePO{" + "id=" + id + ", createUser='" + createUser + '\'' + ", createTime=" + createTime + ", modifyUser='" + modifyUser + '\'' + ", modifyTime=" + modifyTime + '}';
	}
}
