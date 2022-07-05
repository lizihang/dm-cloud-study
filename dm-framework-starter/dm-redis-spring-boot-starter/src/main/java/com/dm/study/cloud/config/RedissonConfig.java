package com.dm.study.cloud.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
     */
    @Value("${spring.redisson.type}")
    private String redisType;

    @Bean
    RedissonClient redisson() {
        logger.info("redisType:" + redisType);
        RedissonClient redisson;
        switch (redisType) {
            case "master":
                // MasterSlaveServersConfig
                redisson = Redisson.create(getMasterSlaveServersConfig());
                break;
            case "sentinel":
                // SentinelServersConfig
                redisson = Redisson.create(getSentinelServersConfig());
                break;
            case "cluster":
                // ClusterServersConfig
                redisson = Redisson.create(getClusterServersConfig());
                break;
            default:
                // SingleServerConfig
                redisson = Redisson.create(getSingleServerConfig());
                break;
        }
        return redisson;
    }

    /**
     * 单机模式配置
     * @return
     */
    private Config getSingleServerConfig() {
        // 1.代码方式
		/*
		Config config = new Config();
		config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(1);
		return config;
		*/
        // 2.配置文件方式
        Config config = null;
        try {
            config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson-single.yml"));
            config.useSingleServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    /**
     * 主从模式
     * @return
     */
    private Config getMasterSlaveServersConfig() {
        Config config = null;
        try {
            config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson-master.yml"));
            config.useMasterSlaveServers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    /**
     * 哨兵模式
     * @return
     */
    private Config getSentinelServersConfig() {
        Config config = null;
        try {
            config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson-sentinel.yml"));
            config.useSentinelServers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    /**
     * 集群模式
     * @return
     */
    private Config getClusterServersConfig() {
        Config config = null;
        try {
            config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson-cluster.yml"));
            config.useClusterServers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }
}
