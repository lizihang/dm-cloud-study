package com.dm.study.cloud.compoment;

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
 * <p>创建日期：2024年09月11日 13:45</p>
 * <p>类全名：com.dm.study.cloud.compoment.SearchComponent</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class SearchComponent extends NodeComponent {

	@Override
	public void process() throws Exception {

		//
		String tag = this.getTag();
		String nodeId = this.getNodeId();
		TestContent contextBean = this.getContextBean(TestContent.class);
		// 1.获取本节点input
		// SearchContentObj searchContentObj = contextBean.getSearchParam().get(tag);
		// List<Map<String,Object>> input = searchContentObj.getInput();
		// TODO 假设name从input节点的结果中获取
		String nameCmpId = "input01";
		// 2.根据input从上级节点获取output
		Map<String,Map<String,Object>> cmpResult = contextBean.getCmpResult();
		String name = (String) cmpResult.get(nameCmpId).get("name");
		System.out.println("从上级节点输出中获取到的参数名：" + "name");
		System.out.println("从上级节点输出中获取到的参数值：" + name);
		// 3.处理业务
		// System.out.println("上下文：" + JSON.toJSONString(searchContentObj));
		System.out.println("nodeId:" + nodeId);
		System.out.println("search from milvus");
		System.out.println("search from es");
		System.out.println("search end");
		// 4.本节点output存入上下文
		Map<String,Object> result = cmpResult.computeIfAbsent(tag, k -> new HashMap<>());
		result.put("dddd", "dddd");
	}

	@Override
	public void beforeProcess() {
		super.beforeProcess();
		TestContent contextBean = this.getContextBean(TestContent.class);
	}

	@Override
	public void onSuccess() throws Exception {
		super.onSuccess();
	}
}
