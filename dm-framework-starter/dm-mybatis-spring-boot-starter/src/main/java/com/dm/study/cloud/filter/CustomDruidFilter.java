package com.dm.study.cloud.filter;

import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.DataSourceProxy;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Properties;
/**
 * <p>标题：自定义druid过滤器</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：打印
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年02月13日 9:17</p>
 * <p>类全名：com.dm.study.cloud.filter.CustomDruidFilter</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class CustomDruidFilter extends FilterEventAdapter {
	private static final Logger logger = LoggerFactory.getLogger(CustomDruidFilter.class);

	@Override
	public void init(DataSourceProxy dataSource) {
		String name = dataSource.getName();
		String dbType = dataSource.getDbType();
		String url = dataSource.getUrl();
		String rawJdbcUrl = dataSource.getRawJdbcUrl();
		Properties connectProperties = dataSource.getConnectProperties();
		logger.info("name:{}", name);
		logger.info("dbType:{}", dbType);
		logger.info("url:{}", url);
		logger.info("rawJdbcUrl:{}", rawJdbcUrl);
		logger.info("connectProperties:{}", JSON.toJSONString(connectProperties));
	}
}
