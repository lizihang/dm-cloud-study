package com.dm.study.cloud.util;

import com.dm.study.cloud.po.WsMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年11月24日 14:58</p>
 * <p>类全名：com.dm.study.cloud.util.WsMessageUtil</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class WsMessageUtil {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static WsMessage getWsMessage(String message) throws JsonProcessingException {
		return objectMapper.readValue(message, WsMessage.class);
	}

	public static String buildSendMessage(WsMessage wsMessage) throws JsonProcessingException {
		return objectMapper.writeValueAsString(wsMessage);
	}
}
