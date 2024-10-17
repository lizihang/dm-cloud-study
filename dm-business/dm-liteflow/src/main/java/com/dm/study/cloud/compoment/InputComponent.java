package com.dm.study.cloud.compoment;

import com.alibaba.fastjson.JSON;
import com.dm.study.cloud.content.TestContent;
import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024年09月11日 13:46</p>
 * <p>类全名：com.dm.study.cloud.compoment.InputComponent</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class InputComponent extends NodeComponent {
	@Override
	public void process() throws Exception {

		TestContent contextBean = this.getContextBean(TestContent.class);
		String name = this.getName();
		String nodeId = this.getNodeId();
		String chainId = this.getChainId();
		Object requestData = this.getRequestData();
		String tag = this.getTag();
		System.out.println("this is input component");
		System.out.println("name:" + name);
		System.out.println("nodeId:" + nodeId);
		System.out.println("chainId:" + chainId);
		System.out.println("requestData:" + JSON.toJSONString(requestData));
		System.out.println("tag:" + tag);
		// 保存结果
		Map<String,Map<String,Object>> cmpResult = contextBean.getCmpResult();
		// 将本节点结果放进去
		Map<String,Object> result = cmpResult.computeIfAbsent(tag, k -> new HashMap<>());
		result.put("name", "input name");
	}
}
