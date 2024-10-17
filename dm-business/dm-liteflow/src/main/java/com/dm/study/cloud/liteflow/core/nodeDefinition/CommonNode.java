package com.dm.study.cloud.liteflow.core.nodeDefinition;

import com.dm.study.cloud.liteflow.core.enums.NodeEnum;
import lombok.NonNull;
public class CommonNode extends Node {

    public CommonNode(@NonNull String id, @NonNull String name) {
        super(id, name, NodeEnum.COMMON);
    }
}
