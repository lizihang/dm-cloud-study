package com.dm.study.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dm.study.cloud.dao.NmapScanRecordMapper;
import com.dm.study.cloud.param.NmapScanQueryParams;
import com.dm.study.cloud.po.NmapScanRecord;
import com.dm.study.cloud.service.NmapScanRecordService;
import org.springframework.stereotype.Service;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024年02月02日 15:08</p>
 * <p>类全名：com.dm.study.cloud.service.impl.NmapScanRecordServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service
public class NmapScanRecordServiceImpl extends ServiceImpl<NmapScanRecordMapper,NmapScanRecord> implements NmapScanRecordService {
	@Override
	public Page<NmapScanRecord> queryPage(NmapScanQueryParams params) {
		Page<NmapScanRecord> page = new Page<>(params.getPageNum(), params.getPageSize());
		LambdaQueryWrapper<NmapScanRecord> wrapper = buildQueryWrapper(params);
		return baseMapper.selectPage(page, wrapper);
	}

	/**
	 * 构建查询wrapper
	 * @param params 参数
	 * @return wrapper
	 */
	private LambdaQueryWrapper<NmapScanRecord> buildQueryWrapper(NmapScanQueryParams params) {
		LambdaQueryWrapper<NmapScanRecord> wrapper = new LambdaQueryWrapper<>();
		return wrapper;
	}
}
