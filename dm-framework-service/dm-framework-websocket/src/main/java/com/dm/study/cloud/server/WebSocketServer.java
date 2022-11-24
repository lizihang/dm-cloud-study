package com.dm.study.cloud.server;

import com.dm.study.cloud.enums.MessageTypeEnum;
import com.dm.study.cloud.po.WsMessage;
import com.dm.study.cloud.storage.WsStorage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年11月24日 14:36</p>
 * <p>类全名：com.dm.study.cloud.server.WebSocketServer</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
@ServerEndpoint(value = "/websocket/{userId}")
public class WebSocketServer {
	private static final Logger       logger       = LoggerFactory.getLogger(WebSocketServer.class);
	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private              Session      session;
	// userId
	private              String       userId;
	//
	private final        ObjectMapper objectMapper = new ObjectMapper();

	@OnOpen
	public void onOpen(Session session, @PathParam("userId") String userId) {
		this.session = session;
		this.userId = userId;
		// 存放到onlineUsers中保存
		WsStorage.socketMap.put(userId, this);
		//
		for (String key : WsStorage.socketMap.keySet()) {
			logger.info("在线用户<" + key + ">");
		}
		//
		logger.info("用户<" + userId + ">建立websocket连接！");
	}

	/**
	 * 接收到客户端发送的数据时调用.
	 * @param message 客户端发送的数据
	 * @param session session对象
	 * @return void
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		logger.info("websocket收到消息！");
		try {
			// 1.转换WsMessage对象
			WsMessage msg = objectMapper.readValue(message, WsMessage.class);
			// 2.逻辑处理
			// 消息发送方
			String username = msg.getSendName();
			// 消息接收方
			String toName = msg.getReceiveName();
			msg.setType(MessageTypeEnum.NORMAL.getCode());
			msg.setToSelf(username.equals(toName));
			// 3.发送消息
			// 封装发送的消息
			String sendMsg = null;
			try {
				sendMsg = objectMapper.writeValueAsString(msg);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			//发送消息
			WebSocketServer target = WsStorage.socketMap.get(toName);
			if (target == null) {
				WsMessage m = new WsMessage();
				m.setContent("对方不在线");
				this.session.getBasicRemote().sendText(objectMapper.writeValueAsString(m));
			} else {
				target.session.getBasicRemote().sendText(sendMsg);
			}
		} catch (JsonProcessingException e) {
			logger.error("接收客户端的消息，转换出错了！");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		// 移除
		WebSocketServer remove = WsStorage.socketMap.remove(this.userId);
		//
		for (String key : WsStorage.socketMap.keySet()) {
			logger.info("在线用户<" + key + ">");
		}
		// 打印日志
		logger.info("用户<" + this.userId + ">关闭websocket连接！");
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		logger.error("onError方法，session id = {},message = {}", session.getId(), throwable.getStackTrace());
	}
}
