package com.dm.study.cloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dm.study.cloud.content.TestContent;
import com.dm.study.cloud.content.TestContentObj;
import com.dm.study.cloud.param.ChainParam;
import com.dm.study.cloud.param.PublishParam;
import com.dm.study.cloud.schema.edge.EdgeSchema;
import com.dm.study.cloud.schema.node.NodeSchema;
import com.dm.study.cloud.service.TestService;
import com.yomahub.liteflow.builder.el.ELBus;
import com.yomahub.liteflow.builder.el.LiteFlowChainELBuilder;
import com.yomahub.liteflow.builder.el.ThenELWrapper;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import com.yomahub.liteflow.flow.entity.CmpStep;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024年09月11日 13:55</p>
 * <p>类全名：com.dm.study.cloud.service.impl.TestServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service
public class TestServiceImpl implements TestService {
	@Resource
	FlowExecutor flowExecutor;

	@Override
	public void testChain1(ChainParam params) {
		LiteflowResponse response = flowExecutor.execute2Resp(params.getChainId(), "arg", createTestContent());
		System.out.println(response.getMessage());
		Map<String,List<CmpStep>> executeSteps = response.getExecuteSteps();
		String executeStepStr = response.getExecuteStepStr();
		System.out.println("executeStepStr:" + executeStepStr);
	}

	@Override
	public void testPublish(PublishParam param) {
		String json = param.getJson();
		JSONObject jsonObject = JSON.parseObject(json);
		JSONObject workflowSchema = jsonObject.getJSONObject("workflow_schema");
		JSONArray nodes = workflowSchema.getJSONArray("nodes");
		List<NodeSchema> nodeSchemas = JSON.parseArray(nodes.toJSONString(), NodeSchema.class);
		Map<String,String> nodeNameMap = nodeSchemas.stream().collect(Collectors.toMap(NodeSchema::getId, NodeSchema::getName));
		// test build el
		JSONArray edges = workflowSchema.getJSONArray("edges");
		List<EdgeSchema> edgeSchemas = JSON.parseArray(edges.toJSONString(), EdgeSchema.class);
		// 当前节点有几个下级节点
		Map<String,List<String>> targetMap = new HashMap<>();
		// 当前节点有几个上级节点
		Map<String,List<String>> sourceMap = new HashMap<>();
		// 当前节点flag
		Map<String,Boolean> nodeFlag = new HashMap<>();
		for (EdgeSchema edge : edgeSchemas) {
			String source_node_id = edge.getSource_node_id();
			String target_node_id = edge.getTarget_node_id();
			List<String> targetList = targetMap.computeIfAbsent(source_node_id, k -> new ArrayList<>());
			targetList.add(target_node_id);
			//
			List<String> sourceList = sourceMap.computeIfAbsent(target_node_id, k -> new ArrayList<>());
			sourceList.add(source_node_id);
		}
		// 获取开始节点，从nodes里边找
		String startId = "f9ad9110a59d455a961ea38a8ac33851";
		String el = "THEN(";
		List<String> nextNodes = targetMap.get(startId);
		if (nextNodes.size() == 1) {
			el += nodeNameMap.get(startId) + ".tag(\"" + startId + "\"";
		} else {
			nodeFlag.put(startId, true);
			el += nodeNameMap.get(startId) + ".tag(\"" + startId + "\"";
			el += ",WHEN(";
		}
		for (String nextId : nextNodes) {
			dealNextNode(nextId, targetMap, sourceMap, nodeFlag, el, nodeNameMap);
		}
		el += ")";
		System.out.println("here");
	}

	private void dealNextNode(String nodeId, Map<String,List<String>> targetMap, Map<String,List<String>> sourceMap, Map<String,Boolean> nodeFlag, String el, Map<String,String> nodeNameMap) {
		//	1.如果本节点入度为1，出度为1，则直接写
		//	2.如果本节点入度>1，结束上一层的括号
		//	3.如果本节点出度>1，开始下一层
		// List<String> targetList = targetMap.get(nodeId);
		// List<String> sourceList = sourceMap.get(nodeId);
		// if (targetList.size() == 1 && sourceList.size() == 1) {
		// 	el += "," + nodeNameMap.get(nodeId) + ".tag(\"" + nodeId + "\"";
		// }
		// else if (sourceList.size() > 1) {
		// 	el += ")";
		// }
		// else if (targetList.size() > 1) {
		// 	el += "WHEN(" + nodeNameMap.get(nodeId) + ".tag(\"" + startId + "\",";
		// }
		// for (String nextId : nextNodes) {
		// 	dealNextNode(nextId, targetMap, sourceMap, nodeFlag, el, nodeNameMap);
		// }
	}

	@Override
	public void testBuildEl() {
		// 组装EL表达式
		ThenELWrapper el = ELBus.then(ELBus.node("inputComponent").tag("input01"), ELBus.when(ELBus.node("searchComponent").tag("111"), ELBus.node("searchComponent").tag("222")));
		ThenELWrapper main1 = ELBus.then(ELBus.node("inputComponent").tag("input01"), "chain1");
		// 格式化输出
		System.out.println(el.toEL(true));
		System.out.println(main1.toEL(true));
		String sqlElStr = el.toEL();
		String main1Str = main1.toEL();
		LiteFlowChainELBuilder.createChain().setChainId("chain").setEL(sqlElStr).build();
		LiteFlowChainELBuilder.createChain().setChainId("main1Str").setEL(main1Str).build();
		LiteflowResponse response = flowExecutor.execute2Resp("main1Str", "arg", createTestContent());
	}

	private TestContent createTestContent() {
		TestContent content = new TestContent();
		Map<String,TestContentObj> cmpContentMap = new HashMap<>();
		TestContentObj obj1 = new TestContentObj();
		// obj1.setText(liteflow"你好");
		// obj1.setRecallNum(2);
		// TestContentObj obj2 = new TestContentObj();
		// obj2.setText("我好");
		// obj2.setRecallNum(5);
		// contentParam.put("111", obj1);
		// contentParam.put("222", obj2);
		// content.setSearchParam(contentParam);
		Map<String,Map<String,Object>> cmpResult = new HashMap<>();
		content.setCmpResult(cmpResult);
		return content;
	}
}
