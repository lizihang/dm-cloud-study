package com.dm.study.cloud.liteflow.core.nodeDefinition;

import com.dm.study.cloud.liteflow.core.enums.NodeEnum;
import lombok.NonNull;
/**
 * @Author: jiangtao
 * @Date 2024/5/7 20:54
 * @Description:
 */
public class EndNode extends Node {

    public EndNode(@NonNull String id, @NonNull String name) {
        super(id, name, NodeEnum.END);
    }
}
