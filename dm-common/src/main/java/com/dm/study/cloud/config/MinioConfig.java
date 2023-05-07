package com.dm.study.cloud.config;

import com.dm.study.cloud.properties.MinioProperties;
import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
 * <p>创建日期：2023年05月07日 12:18</p>
 * <p>类全名：com.dm.study.cloud.config.MinioConfig</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Configuration
public class MinioConfig {
    @Resource
    MinioProperties properties;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder() //
                .endpoint(properties.getEndpoint()) //
                .credentials(properties.getAccessKey(), properties.getSecretKey()) //
                .build();
    }
}
