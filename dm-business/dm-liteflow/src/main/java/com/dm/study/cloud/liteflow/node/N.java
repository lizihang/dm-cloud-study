package com.dm.study.cloud.liteflow.node;

import com.yomahub.liteflow.core.NodeSwitchComponent;
import org.springframework.stereotype.Component;
/**
 * @Author: jiangtao
 * @Date 2024/4/2 19:46
 * @Description:
 */
@Component
public class N  extends NodeSwitchComponent {

    @Override
    public String processSwitch() throws Exception {
        System.out.println("执行n节点");
        return "true";
    }



}
