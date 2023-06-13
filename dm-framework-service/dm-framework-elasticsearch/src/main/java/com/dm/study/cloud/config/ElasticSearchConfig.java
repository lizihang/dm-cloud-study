package com.dm.study.cloud.config;

import com.dm.study.cloud.properties.ElasticSearchProperties;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

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
 * <p>创建日期：2023年06月13日 15:47</p>
 * <p>类全名：com.dm.study.cloud.config.ElasticSearchConfig</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Configuration
@ConditionalOnProperty(prefix = "dm.elasticsearch", name = "enable", havingValue = "true")
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {
	@Resource
	ElasticSearchProperties properties;

	@Override
	public RestHighLevelClient elasticsearchClient() {
		System.out.println("ElasticSearchConfig:" + properties.isEnable());
		String hostAndPort = properties.getIp() + ":" + properties.getPort();
		final ClientConfiguration clientConfiguration = ClientConfiguration.builder() //
				.connectedTo(hostAndPort) //
				.build();
		return RestClients.create(clientConfiguration).rest();
	}
}
