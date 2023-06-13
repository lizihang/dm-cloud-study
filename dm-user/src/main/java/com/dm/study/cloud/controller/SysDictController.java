package com.dm.study.cloud.controller;

import com.dm.study.cloud.annotation.DmLog;
import com.dm.study.cloud.param.SysDictDetailParams;
import com.dm.study.cloud.param.SysDictQueryParams;
import com.dm.study.cloud.po.SysDict;
import com.dm.study.cloud.service.SysDictService;
import com.dm.study.cloud.vo.IdParam;
import com.dm.study.cloud.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年05月18日 15:46</p>
 * <p>类全名：com.dm.study.cloud.controller.SysDictController</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@RestController
@RequestMapping("/dict")
public class SysDictController {
	@Resource
	SysDictService service;

	/**
	 * 获取字典列表
	 * @return
	 */
	@DmLog
	@PostMapping("/queryDictPage")
	public Result queryDictList(@RequestBody @Valid SysDictQueryParams params) {
		return Result.success("查询字典列表成功", service.queryDictPage(params));
	}

	/**
	 * 获取字典详情
	 * @return
	 */
	@DmLog
	@PostMapping("/queryDictDetail")
	public Result queryDictDetail(@RequestBody @Valid SysDictDetailParams params) {
		return Result.success("查询字典信息成功", service.queryDictDetail(params));
	}

	/**
	 * 新增字典
	 * @return
	 */
	@DmLog
	@PostMapping("/addDict")
	public Result addDict(@RequestBody SysDict dict) {
		return Result.success("新增字典成功", service.addDict(dict));
	}

	/**
	 * 修改字典
	 * @return
	 */
	@DmLog
	@PostMapping("/updateDict")
	public Result updateDict(@RequestBody SysDict dict) {
		service.updateDict(dict);
		return Result.success("修改字典成功");
	}

	/**
	 * 删除字典
	 * @param param
	 * @return
	 */
	@DmLog
	@PostMapping("/deleteDict")
	public Result deleteDict(@RequestBody @Valid IdParam param) {
		service.deleteDict(param);
		return Result.success("删除字典成功！");
	}
}
