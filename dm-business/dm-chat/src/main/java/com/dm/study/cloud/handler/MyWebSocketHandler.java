package com.dm.study.cloud.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年11月22日 15:38</p>
 * <p>类全名：com.dm.study.cloud.handler.MyWebSocketHandler</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class MyWebSocketHandler extends TextWebSocketHandler {
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 当连接建立时发送欢迎消息
		session.sendMessage(new TextMessage("Welcome to the WebSocket Server!"));
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 处理收到的消息
		String receivedMessage = message.getPayload();
		session.sendMessage(new TextMessage("Received message: " + receivedMessage));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 连接关闭时的处理
	}
}
