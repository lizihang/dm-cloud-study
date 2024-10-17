package com.dm.study.cloud.liteflow.core.nodeDefinition;

import com.dm.study.cloud.liteflow.core.enums.NodeEnum;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.NonNull;

import java.util.Map;
@Getter
public class SwitchNode extends Node {

    private final Map<Node, String> nodeTagMap = Maps.newHashMap();

    public SwitchNode(@NonNull String id, @NonNull String name) {
        super(id, name, NodeEnum.SWITCH);
    }

    public void putNodeTag(Node node, String tag) {
        nodeTagMap.put(node, tag);
        super.addNextNode(node);
    }
}
