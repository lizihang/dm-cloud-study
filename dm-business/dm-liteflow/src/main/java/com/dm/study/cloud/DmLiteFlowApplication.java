package com.dm.study.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024年09月11日 13:42</p>
 * <p>类全名：com.dm.study.cloud.DmLiteFlowApplication</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@SpringBootApplication
@ComponentScan(value = {"com.dm.study.cloud.liteflow.node","com.dm.study.cloud"})
public class DmLiteFlowApplication {
	public static void main(String[] args) {
		SpringApplication.run(DmLiteFlowApplication.class, args);
	}
}
