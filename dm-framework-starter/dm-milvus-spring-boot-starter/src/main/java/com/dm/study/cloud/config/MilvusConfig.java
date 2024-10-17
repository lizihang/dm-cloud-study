package com.dm.study.cloud.config;

import io.milvus.client.MilvusServiceClient;
import io.milvus.param.ConnectParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年06月13日 16:11</p>
 * <p>类全名：com.dm.study.cloud.config.MilvusConfig</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Configuration
public class MilvusConfig {
	//milvus所在服务器地址
	@Value("${milvus.uri}")
	private String uri;
	//milvus端口
	@Value("${milvus.username}")
	private String username;
	//milvus端口
	@Value("${milvus.password}")
	private String password;

	@Bean
	public MilvusServiceClient milvusServiceClient() {
		ConnectParam connectParam = ConnectParam.newBuilder() //
				.withUri(uri) //
				.withAuthorization(username, password) //
				.build();
		return new MilvusServiceClient(connectParam);
	}
}
