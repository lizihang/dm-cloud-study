package com.dm.study.cloud.liteflow.node;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;
/**
 * @Author: jiangtao
 * @Date 2024/4/2 19:46
 * @Description:
 */
@Component
public class D extends NodeComponent {
    @Override
    public void process() throws Exception {
        Thread.sleep(333);
        System.out.println("执行d节点");
    }
}
