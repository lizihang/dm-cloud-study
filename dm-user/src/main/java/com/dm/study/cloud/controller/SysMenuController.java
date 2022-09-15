package com.dm.study.cloud.controller;

import com.dm.study.cloud.annotation.DmLog;
import com.dm.study.cloud.param.SysMenuQueryParams;
import com.dm.study.cloud.service.SysMenuService;
import com.dm.study.cloud.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年08月15日 14:54</p>
 * <p>类全名：com.dm.study.cloud.controller.SysMenuController</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@CrossOrigin
@RestController
@RequestMapping("/menu")
public class SysMenuController {
	@Autowired
	SysMenuService menuService;

	/**
	 * @param params
	 * @return
	 */
	@DmLog
	@GetMapping("/queryMenuList")
	public Result queryMenuList(SysMenuQueryParams params) {
		return Result.success("查询菜单列表成功！", menuService.queryMenuList(params));
	}
}