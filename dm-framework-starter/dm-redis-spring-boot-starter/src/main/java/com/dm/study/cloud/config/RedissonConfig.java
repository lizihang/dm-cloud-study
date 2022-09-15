package com.dm.study.cloud.config;

import com.dm.study.cloud.properties.ClusterServerProperties;
import com.dm.study.cloud.properties.MasterSlaveServerProperties;
import com.dm.study.cloud.properties.SentinelServerProperties;
import com.dm.study.cloud.properties.SingleServerProperties;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
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
 * <p>创建日期：2022年04月28日 15:16</p>
 * <p>类全名：com.dm.study.cloud.config.RedissonConfig</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Configuration
public class RedissonConfig {
	final static Logger logger = LoggerFactory.getLogger(RedissonConfig.class);
	/**
	 * single：单机模式
	 * master：主从模式
	 * sentinel：哨兵模式
	 * cluster：集群模式
	 * 默认单机模式
	 */
	@Value("${spring.redisson.type:single}")
	private      String redisType;
	/**
	 * code:代码
	 * file:配置文件
	 * 默认为file
	 */
	@Value("${spring.redisson.build.type:file}")
	private      String redisBuildType;
	@Resource
	ClusterServerProperties     clusterServerProperties;
	@Resource
	MasterSlaveServerProperties masterSlaveServerProperties;
	@Resource
	SentinelServerProperties    sentinelServerProperties;
	@Resource
	SingleServerProperties      singleServerProperties;

	@Bean
	RedissonClient redisson() {
		logger.info("redisType:{};redisBuildType:{}", redisType, redisBuildType);
		RedissonClient redisson;
		switch (redisType) {
		case "master":
			// MasterSlaveServersConfig
			redisson = Redisson.create(getMasterSlaveServersConfig(redisBuildType));
			break;
		case "sentinel":
			// SentinelServersConfig
			redisson = Redisson.create(getSentinelServersConfig(redisBuildType));
			break;
		case "cluster":
			// ClusterServersConfig
			redisson = Redisson.create(getClusterServersConfig(redisBuildType));
			break;
		default:
			// SingleServerConfig
			redisson = Redisson.create(getSingleServerConfig(redisBuildType));
			break;
		}
		return redisson;
	}

	/**
	 * 集群模式
	 * @return
	 */
	private Config getClusterServersConfig(String redisBuildType) {
		Config config = null;
		if ("code".equals(redisBuildType)) {
			// 1.代码方式
			config = new Config();
			config.useClusterServers() //
					.setPassword(clusterServerProperties.getPassword()) //
					.setNodeAddresses(clusterServerProperties.getNodeAddresses());
			config.setCodec(new JsonJacksonCodec());
		} else {
			// 2.配置文件方式
			try {
				config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson-cluster.yml"));
				config.useClusterServers();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return config;
	}

	/**
	 * 主从模式
	 * @return
	 */
	private Config getMasterSlaveServersConfig(String redisBuildType) {
		Config config = null;
		if ("code".equals(redisBuildType)) {
			// 1.代码方式
			config = new Config();
			config.useMasterSlaveServers() //
					.setPassword(masterSlaveServerProperties.getPassword()) //
					.setDatabase(masterSlaveServerProperties.getDatabase()) //
					.setMasterAddress(masterSlaveServerProperties.getMasterAddress()) //
					.setSlaveAddresses(masterSlaveServerProperties.getSlaveAddresses());
			config.setCodec(new JsonJacksonCodec());
		} else {
			// 2.配置文件方式
			try {
				config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson-master.yml"));
				config.useMasterSlaveServers();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return config;
	}

	/**
	 * 哨兵模式
	 * @return
	 */
	private Config getSentinelServersConfig(String redisBuildType) {
		Config config = null;
		if ("code".equals(redisBuildType)) {
			// 1.代码方式
			config = new Config();
			config.useSentinelServers() //
					.setPassword(sentinelServerProperties.getPassword()) //
					.setDatabase(sentinelServerProperties.getDatabase()) //
					.setSentinelAddresses(sentinelServerProperties.getSentinelAddresses());
			config.setCodec(new JsonJacksonCodec());
		} else {
			// 2.配置文件方式
			try {
				config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson-sentinel.yml"));
				config.useSentinelServers();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return config;
	}

	/**
	 * 单机模式配置
	 * @return
	 */
	private Config getSingleServerConfig(String redisBuildType) {
		Config config = null;
		if ("code".equals(redisBuildType)) {
			// 1.代码方式
			config = new Config();
			config.useSingleServer() //
					.setAddress(singleServerProperties.getAddress()) //
					.setPassword(singleServerProperties.getPassword()) //
					.setDatabase(singleServerProperties.getDatabase());
			config.setCodec(new JsonJacksonCodec());
		} else {
			// 2.配置文件方式
			try {
				config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson-single2.yaml"));
				config.useSingleServer();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return config;
	}
}
