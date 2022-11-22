package com.dm.study.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：zipkin服务端，目前用官网jar包启动
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年11月22日 9:24</p>
 * <p>类全名：com.dm.study.cloud.DmZipkinApplication</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@SpringBootApplication
public class DmZipkinApplication {
	public static void main(String[] args) {
		SpringApplication.run(DmZipkinApplication.class, args);
	}
}
