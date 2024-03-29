package com.dm.study.cloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dm.study.cloud.po.SysDictDetail;

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
 * <p>创建日期：2023年05月18日 21:49</p>
 * <p>类全名：com.dm.study.cloud.service.SysDictDetailService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public interface SysDictDetailService extends IService<SysDictDetail> {
	List<SysDictDetail> selectDictDetailByIds(List<String> ids);
}
