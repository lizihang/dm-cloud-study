package com.dm.study.cloud.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dm.study.cloud.param.NmapScanQueryParams;
import com.dm.study.cloud.po.NmapScanRecord;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024年02月02日 15:07</p>
 * <p>类全名：com.dm.study.cloud.service.NmapScanRecordService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface NmapScanRecordService extends IService<NmapScanRecord> {
	Page<NmapScanRecord> queryPage(NmapScanQueryParams params);
}
