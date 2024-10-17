package com.dm.study.cloud.liteflow.core.nodeDefinition;

import com.dm.study.cloud.liteflow.core.enums.NodeEnum;
import lombok.NonNull;
/**
 * @Author: jiangtao
 * @Date 2024/4/7 11:33
 * @Description:
 */
public class WhenNode extends Node {

    public WhenNode(@NonNull String id, @NonNull String name) {
        super(id, name, NodeEnum.WHEN);
    }
}
