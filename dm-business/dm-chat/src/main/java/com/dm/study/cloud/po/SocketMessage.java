package com.dm.study.cloud.po;

import lombok.Getter;
import lombok.Setter;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年11月21日 15:12</p>
 * <p>类全名：com.dm.study.cloud.po.SocketMessage</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
public class SocketMessage {
	/**
	 * 消息类型
	 */
	private String  messageType;
	/**
	 * 消息发送者id
	 */
	private Integer userId;
	/**
	 * 消息接受者id或群聊id
	 */
	private Integer chatId;
	/**
	 * 消息内容
	 */
	private String  message;
}
