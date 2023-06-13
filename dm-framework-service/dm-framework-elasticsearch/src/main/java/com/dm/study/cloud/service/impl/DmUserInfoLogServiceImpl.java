package com.dm.study.cloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.dm.study.cloud.constant.Constants;
import com.dm.study.cloud.mapper.DmUserInfoLogMapper;
import com.dm.study.cloud.po.DmUserInfoLog;
import com.dm.study.cloud.service.DmUserInfoLogService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
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
 * <p>创建日期：2023年06月13日 15:53</p>
 * <p>类全名：com.dm.study.cloud.service.impl.DmUserInfoLogServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service
public class DmUserInfoLogServiceImpl implements DmUserInfoLogService {
	@Resource
	RestHighLevelClient esClient;
	@Resource
	DmUserInfoLogMapper logMapper;

	@Override
	public List<DmUserInfoLog> queryList() {
		Iterable<DmUserInfoLog> all = logMapper.findAll();
		all.forEach(log -> {
			if (log.getMessage().contains(Constants.LOG_START_FLAG)) {
				System.out.println(log.getId());
				System.out.println(log.getMessage());
			}
		});
		List<DmUserInfoLog> list = logMapper.findByMessage(Constants.LOG_START_FLAG);
		for (DmUserInfoLog log : list) {
			System.out.println(log.getMessage());
		}
		return null;
	}

	@Override
	public List<DmUserInfoLog> queryListByParam() throws IOException {
		SearchRequest request = new SearchRequest("dm-user2-info-2023.05.22");
		SearchSourceBuilder builder = new SearchSourceBuilder();
		builder.query(QueryBuilders.termQuery("message", "spring"));
		request.source(builder);
		SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
		SearchHits hits = response.getHits();
		for (SearchHit hit : hits) {
			String source = hit.getSourceAsString();
			//System.out.println(source);
			DmUserInfoLog log = JSON.parseObject(source, DmUserInfoLog.class);
			System.out.println(log);
		}
		return null;
	}
}
