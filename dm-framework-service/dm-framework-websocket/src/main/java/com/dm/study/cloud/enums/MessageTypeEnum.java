package com.dm.study.cloud.enums;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年11月24日 14:54</p>
 * <p>类全名：com.dm.study.cloud.enums.MessageTypeEnum</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public enum MessageTypeEnum {
	SYSTEM(0, "系统消息"), //
	NORMAL(1, "普通消息"); //
	private final Integer code;
	private final String  msg;

	MessageTypeEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return this.code;
	}

	public String getMsg() {
		return this.msg;
	}
}
