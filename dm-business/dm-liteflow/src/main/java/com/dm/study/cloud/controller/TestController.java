package com.dm.study.cloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.dm.study.cloud.content.TestContent;
import com.dm.study.cloud.content.TestContentObj;
import com.dm.study.cloud.liteflow.core.ElHelper;
import com.dm.study.cloud.liteflow.core.model.FlowJsonParser;
import com.dm.study.cloud.liteflow.core.model.JsonAstModel;
import com.dm.study.cloud.liteflow.core.nodeDefinition.Node;
import com.dm.study.cloud.param.ChainParam;
import com.dm.study.cloud.param.PublishParam;
import com.dm.study.cloud.service.TestService;
import com.dm.study.cloud.vo.Result;
import com.yomahub.liteflow.builder.el.LiteFlowChainELBuilder;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
 * <p>创建日期：2024年09月11日 13:51</p>
 * <p>类全名：com.dm.study.cloud.controller.TestController</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@RestController
@RequestMapping("/test")
public class TestController {
	@Resource
	TestService  testService;
	@Resource
	FlowExecutor flowExecutor;

	@PostMapping("/chain1")
	public Result testChain1(ChainParam params) {
		testService.testChain1(params);
		return Result.success("success");
	}

	@PostMapping("/testPublish")
	public Result testPublish(PublishParam param) {
		testService.testPublish(param);
		return Result.success("success");
	}

	@PostMapping("/testBuildEl")
	public Result testBuildEl() {
		testService.testBuildEl();
		return Result.success("success");
	}

	@PostMapping("toEl")
	public Result toEl(@RequestBody JSONObject json) {
		try {
			JsonAstModel request = FlowJsonParser.json2jsonAstModel(json);
			// 1、用前端的json构建基于Node的抽象语法树
			Node head = ElHelper.Json2Node(request);
			// 2、将ast转为EL表达式
			String el = ElHelper.ast2El(head);
			System.out.println(el);
			//
			LiteFlowChainELBuilder.createChain().setChainId("chain").setEL(el).build();
			LiteflowResponse response = flowExecutor.execute2Resp("chain", "arg", createTestContent());
			// 3、校验EL表达式的有效性
			LiteFlowChainELBuilder.validateWithEx(el);
			String executeStepStr = response.getExecuteStepStrWithTime();
			return Result.success("成功", executeStepStr);
		} catch (Exception e) {
			// log.error("[生成El表达式] 发生异常", e);
			e.printStackTrace();
			return Result.error(e.getMessage());
		}
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
