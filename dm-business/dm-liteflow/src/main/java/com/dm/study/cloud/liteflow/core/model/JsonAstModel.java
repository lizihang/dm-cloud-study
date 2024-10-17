package com.dm.study.cloud.liteflow.core.model;

import lombok.Data;

import java.util.List;
import java.util.Map;
/**
 * @Author: jiangtao
 * @Date 2024/4/3 13:07
 * @Description:
 */
@Data
public class JsonAstModel {

    private List<NodeEntity> nodeEntities;

    private List<NodeEdge> nodeEdges;

    @Data
    public static class NodeEntity {

        // 节点唯一id
        private String id;

        // 节点名称，对应的LIteFlow的节点名称，也就是bean名称
        private String name;

        // 前端展示用
        private String label;

        // 节点类型，有COMMON、SWITCH和SUMMARY
        private String nodeType;

        private String type;

        private Map<String,String> data;
        private int level;
    }

    @Data
    public static class NodeEdge {

        // 源节点
        private String source;

        // 目标节点
        private String target;

        /**
         * 冗余给switch使用，如果节点类型是common、summary、if，则为空
         */
        private String tag;
    }
}
