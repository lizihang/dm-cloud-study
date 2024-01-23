package com.dm.study.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dm.study.cloud.dao.SysMenuMapper;
import com.dm.study.cloud.exception.DmException;
import com.dm.study.cloud.param.SysMenuQueryParams;
import com.dm.study.cloud.po.SysMenu;
import com.dm.study.cloud.service.SysMenuService;
import com.dm.study.cloud.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
		LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
		wrapper.like(StrUtil.isNotEmpty(params.getName()), SysMenu::getName, params.getName());
		List<SysMenu> list = list(wrapper);
		List<SysMenu> result = buildTree(list);
		return result;
	}

	@Override
	public SysMenu addMenu(SysMenu menu) {
		String router = menu.getRouter();
		LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(SysMenu::getRouter, router);
		SysMenu exist = getOne(wrapper);
		if (exist != null) {
			throw new DmException("菜单路由不能重复！");
		}
		if (StrUtil.isEmpty(menu.getParentId())) {
			menu.setParentId("-1");
		}
		baseMapper.insert(menu);
		return menu;
	}

	private List<SysMenu> buildTree(List<SysMenu> data) {
		Map<String,List<SysMenu>> collect = data.stream().collect(Collectors.groupingBy(SysMenu::getParentId));
		List<SysMenu> result = collect.get("-1");
		setSub(collect, result);
		return result;
	}

	private void setSub(Map<String,List<SysMenu>> collect, List<SysMenu> parent) {
		if (parent == null || parent.size() == 0) {
			return;
		}
		for (SysMenu menu : parent) {
			List<SysMenu> sub = collect.get(menu.getId());
			if (sub == null) {
				continue;
			}
			menu.setSubMenus(sub);
			setSub(collect, sub);
		}
	}
}
