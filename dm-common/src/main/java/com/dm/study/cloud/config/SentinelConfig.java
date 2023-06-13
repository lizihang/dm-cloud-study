package com.dm.study.cloud.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年04月27日 9:21</p>
 * <p>类全名：com.dm.study.cloud.config.SentinelConfig</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Configuration
public class SentinelConfig {
	@Bean
	public SentinelResourceAspect sentinelResourceAspect() {
		return new SentinelResourceAspect();
	}

	/**
	 * 设置流控规则
	 */
	@PostConstruct
	public static void initFlowRules() {
		List<FlowRule> flowRules = new ArrayList<>();
		FlowRule flowRule = new FlowRule();
		// 设置受保护的资源
		flowRule.setResource("queryPort");
		// 设置流控规则 QPS
		flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		// 设置受保护的资源阈值
		// Set limit QPS to 20.
		flowRule.setCount(1);
		flowRules.add(flowRule);
		// 加载配置好的规则
		FlowRuleManager.loadRules(flowRules);
	}
}
