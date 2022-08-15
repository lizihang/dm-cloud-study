package com.dm.study.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dm.study.cloud.dao.SysMenuMapper;
import com.dm.study.cloud.param.SysMenuQueryParams;
import com.dm.study.cloud.po.SysMenu;
import com.dm.study.cloud.service.SysMenuService;
import com.dm.study.cloud.util.StrUtil;
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
 * <p>创建日期：2022年08月15日 15:09</p>
 * <p>类全名：com.dm.study.cloud.service.impl.SysMenuServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper,SysMenu> implements SysMenuService {
	@Override
	public List<SysMenu> queryMenuList(SysMenuQueryParams params) {
		QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
		wrapper.orderBy(StrUtil.isNotEmpty(params.getOrderColumn()), "asc".equals(params.getRule()), params.getOrderColumn());
		return baseMapper.selectMenuList(params);
	}
}
