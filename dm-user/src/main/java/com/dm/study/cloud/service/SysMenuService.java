package com.dm.study.cloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dm.study.cloud.param.SysMenuQueryParams;
import com.dm.study.cloud.po.SysMenu;

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
 * <p>创建日期：2022年08月15日 15:01</p>
 * <p>类全名：com.dm.study.cloud.service.SysMenuService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface SysMenuService extends IService<SysMenu> {
	List<SysMenu> queryMenuList(SysMenuQueryParams params);

	SysMenu addMenu(SysMenu menu);
}
