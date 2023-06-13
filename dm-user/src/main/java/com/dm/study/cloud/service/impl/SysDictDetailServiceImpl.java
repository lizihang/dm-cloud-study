package com.dm.study.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dm.study.cloud.dao.SysDictDetailMapper;
import com.dm.study.cloud.po.SysDictDetail;
import com.dm.study.cloud.service.SysDictDetailService;
import org.springframework.stereotype.Service;

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
 * <p>创建日期：2023年05月18日 21:50</p>
 * <p>类全名：com.dm.study.cloud.service.impl.SysDictDetailServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service
public class SysDictDetailServiceImpl extends ServiceImpl<SysDictDetailMapper,SysDictDetail> implements SysDictDetailService {
	@Override
	public List<SysDictDetail> selectDictDetailByIds(List<String> ids) {
		LambdaQueryWrapper<SysDictDetail> wrapper = new LambdaQueryWrapper<>();
		wrapper.in(SysDictDetail::getDictId, ids);
		return baseMapper.selectList(wrapper);
	}
}
