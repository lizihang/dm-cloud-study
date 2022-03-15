package com.dm.study.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DmUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(DmUserApplication.class, args);
    }
}
