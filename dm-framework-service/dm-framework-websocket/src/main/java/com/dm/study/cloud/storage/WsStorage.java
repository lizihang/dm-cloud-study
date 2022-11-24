package com.dm.study.cloud.storage;

import com.dm.study.cloud.server.WebSocketServer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年11月24日 14:52</p>
 * <p>类全名：com.dm.study.cloud.storage.WsStorage</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class WsStorage {

	//
	public static final Map<String,WebSocketServer> socketMap = new ConcurrentHashMap<>();
}
