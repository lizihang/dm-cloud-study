package com.dm.study.cloud.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dm.study.cloud.po.SocketMessage;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.stereotype.Component;

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
 * <p>创建日期：2023年11月20日 11:07</p>
 * <p>类全名：com.dm.study.cloud.config.NettyServerHandler</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
	//GlobalEventExecutor.INSTANCE 是全局的事件执行器，是一个单例
	private static final ChannelGroup                         channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	/**
	 * 存储用户id和用户的channelId绑定
	 */
	public static        ConcurrentHashMap<Integer,ChannelId> userMap      = new ConcurrentHashMap<>();

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("客户端链接完成");
		//添加到group
		channelGroup.add(ctx.channel());
		ctx.channel().id();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("收到客户端消息");
		//首次连接是fullHttprequest，，把用户id和对应的channel对象存储起来
		if (msg instanceof FullHttpRequest) {
			FullHttpRequest request = (FullHttpRequest) msg;
			//获取用户参数
			Integer userId = getUrlParams(request.uri());
			//保存到登录信息map
			userMap.put(userId, ctx.channel().id());
			//如果url包含参数，需要处理
			if (request.uri().contains("?")) {
				String newUri = request.uri().substring(0, request.uri().indexOf("?"));
				request.setUri(newUri);
			}
		} else if (msg instanceof TextWebSocketFrame) {
			//正常的text类型
			TextWebSocketFrame frame = (TextWebSocketFrame) msg;
			System.out.println("消息内容" + frame.text());
			//转换实体类
			SocketMessage socketMessage = JSON.parseObject(frame.text(), SocketMessage.class);
			if ("group".equals(socketMessage.getMessageType())) {
				//推送群聊信息
				//groupMap.get(socketMessage.getChatId()).writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(socketMessage)));
				System.out.println("推送群聊消息");
			} else {
				//处理私聊的任务，如果对方也在线,则推送消息
				ChannelId channelId = userMap.get(socketMessage.getChatId());
				if (channelId != null) {
					Channel ct = channelGroup.find(channelId);
					if (ct != null) {
						ct.writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(socketMessage)));
					}
				}
			}
		}
		super.channelRead(ctx, msg);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("与客户端断开");
		//移除channelGroup 通道组
		channelGroup.remove(ctx.channel());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
	}

	private static Integer getUrlParams(String url) {
		if (!url.contains("=")) {
			return null;
		}
		String userId = url.substring(url.indexOf("=") + 1);
		return Integer.parseInt(userId);
	}
}
