package com.dm.study.cloud.liteflow.web;

import com.alibaba.fastjson.JSONObject;
import com.dm.study.cloud.liteflow.core.ElHelper;
import com.dm.study.cloud.liteflow.core.model.FlowJsonParser;
import com.dm.study.cloud.liteflow.core.model.JsonAstModel;
import com.dm.study.cloud.liteflow.core.nodeDefinition.Node;
import com.yomahub.liteflow.builder.el.LiteFlowChainELBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Author: jiangtao
 * @Date 2024/4/2 21:18
 * @Description:
 */
@RestController
@RequestMapping("liteflow")
@Slf4j
public class Controller {

    @PostMapping("toEl")
    public Result<Boolean> toEl(@RequestBody JSONObject json) {
        try {
            JsonAstModel request = FlowJsonParser.json2jsonAstModel(json);
            // 1、用前端的json构建基于Node的抽象语法树
            Node head = ElHelper.Json2Node(request);
            // 2、将ast转为EL表达式
            String el = ElHelper.ast2El(head);
            System.out.println(el);
            // 3、校验EL表达式的有效性
            return Result.success(LiteFlowChainELBuilder.validate(el));
        } catch (Exception e) {
            log.error("[生成El表达式] 发生异常", e);
            return Result.error(e.getMessage(), Boolean.FALSE);
        }
    }
    @PostMapping("toElDemo")
    public Result<Boolean> toElDemo(@RequestBody JsonAstModel request) {
        try {
            // 1、用前端的json构建基于Node的抽象语法树
            Node head = ElHelper.Json2Node(request);
            // 2、将ast转为EL表达式
            String el = ElHelper.ast2El(head);
            System.out.println(el);
            // 3、校验EL表达式的有效性
            return Result.success(LiteFlowChainELBuilder.validate(el));
        } catch (Exception e) {
            log.error("[生成El表达式] 发生异常", e);
            return Result.error(e.getMessage(), Boolean.FALSE);
        }
    }
}
