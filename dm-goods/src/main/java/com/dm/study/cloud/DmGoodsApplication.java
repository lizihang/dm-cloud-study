package com.dm.study.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DmGoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(DmGoodsApplication.class, args);
    }
}
