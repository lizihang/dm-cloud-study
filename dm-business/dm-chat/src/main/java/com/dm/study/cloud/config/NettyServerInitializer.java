package com.dm.study.cloud.config;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年11月20日 11:06</p>
 * <p>类全名：com.dm.study.cloud.config.NettyServerInitializer</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
	@Resource
	private NettyServerHandler nettyServerHandler;

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		//websocket协议本身是基于http协议的，所以这边也要使用http解编码器
		ch.pipeline().addLast(new HttpServerCodec());
		//以块的方式来写的处理器
		ch.pipeline().addLast(new ChunkedWriteHandler());
		ch.pipeline().addLast(new HttpObjectAggregator(8192));
		ch.pipeline().addLast(nettyServerHandler);//添加测试的聊天消息处理类
		ch.pipeline().addLast(new WebSocketServerProtocolHandler("/ws", null, true, 65536 * 10));
	}
}
