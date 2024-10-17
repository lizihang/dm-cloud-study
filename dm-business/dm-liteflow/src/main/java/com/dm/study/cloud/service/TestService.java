package com.dm.study.cloud.service;

import com.dm.study.cloud.param.ChainParam;
import com.dm.study.cloud.param.PublishParam;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024年09月11日 13:54</p>
 * <p>类全名：com.dm.study.cloud.service.TestService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface TestService {
	void testChain1(ChainParam params);

	void testPublish(PublishParam param);

	void testBuildEl();
}
