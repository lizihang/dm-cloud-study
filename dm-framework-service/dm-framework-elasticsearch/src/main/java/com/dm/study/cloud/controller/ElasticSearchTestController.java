package com.dm.study.cloud.controller;

import com.dm.study.cloud.service.DmUserInfoLogService;
import com.dm.study.cloud.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年06月13日 15:52</p>
 * <p>类全名：com.dm.study.cloud.controller.ElasticSearchTestController</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@RestController
@RequestMapping("/elasticSearch")
public class ElasticSearchTestController {
	@Resource
	DmUserInfoLogService logService;

	/**
	 * 查询所有
	 * @return
	 */
	@PostMapping("/queryDmUserLog")
	public Result queryDmUserLog() {
		logService.queryList();
		return Result.success("查询成功！");
	}
}
