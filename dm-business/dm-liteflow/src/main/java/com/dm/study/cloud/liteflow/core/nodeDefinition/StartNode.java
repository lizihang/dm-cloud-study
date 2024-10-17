package com.dm.study.cloud.liteflow.core.nodeDefinition;

import com.dm.study.cloud.liteflow.core.enums.NodeEnum;
import lombok.NonNull;
/**
 * @Author: jiangtao
 * @Date 2024/5/7 20:50
 * @Description:
 */
public class StartNode extends Node {

    public StartNode(@NonNull String id, @NonNull String name) {
        super(id, name, NodeEnum.START);
    }
}
