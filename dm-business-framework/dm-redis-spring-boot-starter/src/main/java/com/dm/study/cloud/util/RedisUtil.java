package com.dm.study.cloud.util;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年05月02日 14:16</p>
 * <p>类全名：com.dm.study.cloud.util.RedisUtil</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class RedisUtil {

    @Resource
    private RedissonClient client;

    /**
     * 缓存基本的对象，Integer、String、实体类等
     * @param key   键
     * @param value 值
     * @param <T>   泛型
     */
    public <T> void setCacheObject(final String key, final T value) {
        RBucket<T> bucket = client.getBucket(key);
        bucket.set(value);
    }

    /**
     * 获得缓存的基本对象。
     * @param key 键
     * @param <T> 泛型
     * @return 值
     */
    public <T> T getCacheObject(final String key) {
        RBucket<T> bucket = client.getBucket(key);
        return bucket.get();
    }

}
