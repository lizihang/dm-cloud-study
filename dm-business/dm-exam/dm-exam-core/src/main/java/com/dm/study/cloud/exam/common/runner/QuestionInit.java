package com.dm.study.cloud.exam.common.runner;

import org.redisson.api.RedissonClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
/**
 * <p>标题：试题管理初始化</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年07月05日 19:01</p>
 * <p>类全名：com.dm.study.cloud.exam.common.runner.QuestionInit</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class QuestionInit implements CommandLineRunner {
    @Resource
    RedissonClient client;

    @Override
    public void run(String... args) throws Exception {
    }
}
