package com.dm.study.cloud.runner;

import com.dm.study.cloud.service.impl.NettyServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

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
 * <p>创建日期：2023年11月21日 15:17</p>
 * <p>类全名：com.dm.study.cloud.runner.NettyInitRunner</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
// @Component
public class NettyInitRunner implements CommandLineRunner {
	//springboot服务端口
	@Value("${server.port}")
	Integer     serverPort;
	@Resource
	NettyServer server;

	@Override
	public void run(String... args) throws Exception {
		try {
			System.out.println("nettyServer starting ...");
			System.out.println("http://127.0.0.1:" + serverPort + "/login");
			server.start();
		} catch (Exception e) {
			System.out.println("NettyServerError:" + e.getMessage());
		}
	}
}
