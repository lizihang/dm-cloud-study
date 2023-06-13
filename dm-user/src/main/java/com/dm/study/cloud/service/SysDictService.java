package com.dm.study.cloud.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dm.study.cloud.param.SysDictDetailParams;
import com.dm.study.cloud.param.SysDictQueryParams;
import com.dm.study.cloud.po.SysDict;
import com.dm.study.cloud.po.SysDictDetail;
import com.dm.study.cloud.vo.IdParam;

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
 * <p>创建日期：2023年05月18日 15:52</p>
 * <p>类全名：com.dm.study.cloud.service.SysDictService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface SysDictService extends IService<SysDict> {
	Page<SysDict> queryDictPage(SysDictQueryParams params);

	List<SysDictDetail> queryDictDetail(SysDictDetailParams params);

	SysDict addDict(SysDict dict);

	void updateDict(SysDict dict);

	void deleteDict(IdParam param);
}
