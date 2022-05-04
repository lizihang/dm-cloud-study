package com.dm.study.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年05月04日 19:50</p>
 * <p>类全名：com.dm.study.cloud.config.TokenStoreConfig</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Configuration
public class TokenStoreConfig {

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
}
