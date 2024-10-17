package com.dm.study.cloud.liteflow.core.nodeDefinition;

import com.dm.study.cloud.liteflow.core.enums.NodeEnum;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class Node {
    // node的唯一id
    private final String     id;
    // node名称，对应LIteFlow的Bean名称
    private final String     name;
    // 入度
    private       List<Node> pre = Lists.newArrayList();
    // 节点类型
    private final NodeEnum   nodeEnum;
    // 出度
    private       List<Node> next = Lists.newArrayList();

    protected Node(String id, String name, NodeEnum nodeEnum) {
        this.id = id;
        this.name = name;
        this.nodeEnum = nodeEnum;
    }
    public void addNextNode(Node node) {
        next.add(node);
    }
    public void addPreNode(Node preNode) {
        pre.add(preNode);
    }


}