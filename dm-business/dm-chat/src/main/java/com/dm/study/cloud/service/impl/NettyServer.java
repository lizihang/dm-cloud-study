package com.dm.study.cloud.service.impl;

import com.dm.study.cloud.config.NettyServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Value;

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
 * <p>创建日期：2023年11月20日 11:01</p>
 * <p>类全名：com.dm.study.cloud.service.impl.NettyServer</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
// @Component
public class NettyServer {
	@Value("${netty.port}")
	private int port;
	@Resource
	NettyServerInitializer nettyServerInitializer;

	public void start() throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap //
					.group(bossGroup, workerGroup) //
					.channel(NioServerSocketChannel.class) //
					.childHandler(nettyServerInitializer);
			// 绑定一个端口并且同步，生成一个ChannelFuture异步对象，通过isDone()等方法判断异步事件的执行情况
			// 启动服务器(并绑定端口)，bind是异步操作，sync方法是等待异步操作执行完毕
			ChannelFuture channelFuture = bootstrap.bind(port).sync();
			channelFuture.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture channelFuture) throws Exception {
					if (channelFuture.isSuccess()) {
						System.out.println("监听端口" + port + "成功");
					} else {
						System.out.println("监听端口" + port + "失败");
					}
				}
			});
			// 对通道关闭进行监听，closeFuture是异步操作,监听通道关闭
			// 通过sync方法同步等待通道关闭处理完毕，这里会阻塞等待通道关闭
			channelFuture.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
