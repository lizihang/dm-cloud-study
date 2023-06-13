package com.dm.study.cloud.mapper;

import com.dm.study.cloud.po.DmUserInfoLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年06月13日 15:55</p>
 * <p>类全名：com.dm.study.cloud.mapper.DmUserInfoLogMapper</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface DmUserInfoLogMapper extends ElasticsearchRepository<DmUserInfoLog,String> {
	List<DmUserInfoLog> findByMessage(String message);
}
