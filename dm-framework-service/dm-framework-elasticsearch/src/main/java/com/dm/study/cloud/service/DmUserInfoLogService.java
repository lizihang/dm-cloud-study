package com.dm.study.cloud.service;

import com.dm.study.cloud.po.DmUserInfoLog;

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
 * <p>类全名：com.dm.study.cloud.service.DmUserInfoLogService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface DmUserInfoLogService {
	List<DmUserInfoLog> queryList();

	List<DmUserInfoLog> queryListByParam() throws IOException;
}
