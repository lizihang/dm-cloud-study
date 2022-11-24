package com.dm.study.cloud.po;

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
 * <p>创建日期：2022年11月24日 14:46</p>
 * <p>类全名：com.dm.study.cloud.po.WsMessage</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class WsMessage implements Serializable {
	private static final long    serialVersionUID = -8828277452937181798L;
	/**
	 * 发送方名称
	 */
	private              String  sendName;
	/**
	 * 接收方名称
	 */
	private              String  receiveName;
	/**
	 * 发送时间
	 */
	private              Date    sendTime;
	/**
	 * 消息内容
	 */
	private              String  content;
	/**
	 * 消息类型
	 */
	private              Integer type;
	/**
	 * 是否发送给自己
	 */
	private              boolean toSelf;

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public boolean isToSelf() {
		return toSelf;
	}

	public void setToSelf(boolean toSelf) {
		this.toSelf = toSelf;
	}

	@Override
	public String toString() {
		return "WsMessage{" + "sendName='" + sendName + '\'' + ", receiveName='" + receiveName + '\'' + ", sendTime=" + sendTime + ", content='" + content + '\'' + ", type=" + type + ", toSelf=" + toSelf + '}';
	}
}
