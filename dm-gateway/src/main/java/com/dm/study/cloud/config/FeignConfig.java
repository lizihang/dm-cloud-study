package com.dm.study.cloud.config;

import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年05月02日 23:20</p>
 * <p>类全名：com.dm.study.cloud.config.FeignConfig</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Configuration
public class FeignConfig {
    @Bean
    public Decoder decoder(ObjectProvider<HttpMessageConverterCustomizer> customizers) {
        return new ResponseEntityDecoder(new SpringDecoder(feignHttpMessageConverter(), customizers));
    }

    private ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
        HttpMessageConverters httpMessageConverters = new HttpMessageConverters
                (new MappingJackson2HttpMessageConverter());
        return () -> httpMessageConverters;
    }
}
