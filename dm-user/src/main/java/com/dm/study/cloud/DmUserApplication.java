package com.dm.study.cloud;

import com.dm.study.cloud.util.SystemUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年07月05日 18:46</p>
 * <p>类全名：com.dm.study.cloud.DmUserApplication</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class DmUserApplication {
	public static void main(String[] args) {
		SpringApplication.run(DmUserApplication.class, args);
		SystemUtil.getSystemInfo();
	}
}
