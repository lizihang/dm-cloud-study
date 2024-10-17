package com.dm.study.cloud.util;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
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
	 * @param key 键
	 * @param value 值
	 * @param <T> 泛型
	 */
	public <T> void setCacheObject(final String key, final T value) {
		RBucket<T> bucket = client.getBucket(key);
		bucket.set(value);
	}

	/**
	 * 缓存基本的对象，Integer、String、实体类等
	 * @param key 键
	 * @param value 值
	 * @param timeToLive 超时时间，默认毫秒
	 * @param <T> 泛型
	 */
	public <T> void setCacheObject(final String key, final T value, long timeToLive) {
		setCacheObject(key, value, timeToLive, TimeUnit.MILLISECONDS);
	}

	/**
	 * 缓存基本的对象，Integer、String、实体类等
	 * @param key 键
	 * @param value 值
	 * @param timeToLive 超时时间
	 * @param timeUnit 时间单位
	 * @param <T> 泛型
	 */
	public <T> void setCacheObject(final String key, final T value, long timeToLive, TimeUnit timeUnit) {
		RBucket<T> bucket = client.getBucket(key);
		bucket.set(value, timeToLive, timeUnit);
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

	/**
	 * 根据key删除缓存
	 * @param key 键
	 * @param <T> 泛型
	 * @return 是否成功
	 */
	public <T> boolean deleteCacheObject(final String key) {
		RBucket<T> bucket = client.getBucket(key);
		return bucket.delete();
	}

	/**
	 * 判断是否存在
	 * @param key 键
	 * @return true:存在
	 */
	public boolean exist(final String key) {
		RBucket<Object> bucket = client.getBucket(key);
		return bucket.isExists();
	}

	/**
	 * 设置过期时间
	 * @param key 键
	 * @param timeToLive 过期时间
	 * @param timeUnit 时间单位
	 */
	public void setExpireTime(final String key, final long timeToLive, final TimeUnit timeUnit) {
		RBucket<Object> bucket = client.getBucket(key);
		bucket.expire(timeToLive, timeUnit);
	}

	/**
	 * 获取过期时间
	 * @param key 键
	 * @return 过期时间，单位毫秒
	 */
	public long getExpireTime(final String key) {
		RBucket<Object> bucket = client.getBucket(key);
		return bucket.remainTimeToLive();
	}

}
