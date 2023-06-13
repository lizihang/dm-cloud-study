package com.dm.study.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dm.study.cloud.dao.SysDictMapper;
import com.dm.study.cloud.exception.DmException;
import com.dm.study.cloud.param.SysDictDetailParams;
import com.dm.study.cloud.param.SysDictQueryParams;
import com.dm.study.cloud.po.SysDict;
import com.dm.study.cloud.po.SysDictDetail;
import com.dm.study.cloud.service.SysDictDetailService;
import com.dm.study.cloud.service.SysDictService;
import com.dm.study.cloud.vo.IdParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
 * <p>创建日期：2023年05月18日 15:52</p>
 * <p>类全名：com.dm.study.cloud.service.impl.SysDictServiceImpl</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper,SysDict> implements SysDictService {
	@Resource
	SysDictDetailService detailService;

	@Override
	public Page<SysDict> queryDictPage(SysDictQueryParams params) {
		Page<SysDict> page = new Page<>(params.getPageNum(), params.getPageSize());
		LambdaQueryWrapper<SysDict> wrapper = buildQueryWrapper(params);
		page = baseMapper.selectPage(page, wrapper);
		List<SysDict> records = page.getRecords();
		if (records != null && records.size() > 0) {
			List<String> ids = records.stream().map(SysDict::getId).collect(Collectors.toList());
			List<SysDictDetail> detailList = detailService.selectDictDetailByIds(ids);
			Map<String,List<SysDictDetail>> detailMap = detailList.stream().collect(Collectors.groupingBy(SysDictDetail::getDictId));
			for (SysDict dict : records) {
				List<SysDictDetail> sysDictDetails = detailMap.get(dict.getId());
				dict.setDictDetailList(sysDictDetails);
			}
		}
		return page;
	}

	@Override
	public List<SysDictDetail> queryDictDetail(SysDictDetailParams params) {
		String dictId = params.getDictId();
		return baseMapper.selectDictDetailList(dictId);
	}

	@Override
	public SysDict addDict(SysDict dict) {
		checkNameDump(null, dict.getDictName());
		baseMapper.insert(dict);
		return dict;
	}

	@Override
	public void updateDict(SysDict dict) {
		checkCanUpdate(dict.getId());
		checkNameDump(dict.getId(), dict.getDictName());
		baseMapper.updateById(dict);
	}

	@Override
	public void deleteDict(IdParam param) {
		String id = param.getId();
		SysDict sysDict = baseMapper.selectById(id);
		if (sysDict.getSysFlag() == 1) {
			throw new DmException("系统默认字典禁止删除！");
		}
		baseMapper.deleteById(id);
	}

	/**
	 * 检查字典名称是否重复
	 * @param id 字典ID，新增为null
	 * @param dictName 字典名称
	 */
	private void checkNameDump(String id, String dictName) {
		LambdaQueryWrapper<SysDict> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(SysDict::getDictName, dictName);
		// update
		if (StringUtils.isNotEmpty(id)) {
			wrapper.ne(SysDict::getId, id);
		}
		int num = baseMapper.selectCount(wrapper);
		if (num > 0) {
			throw new DmException("字典名称重复！");
		}
	}

	/**
	 * 检查字典是否可以修改
	 * @param id
	 */
	private void checkCanUpdate(String id) {
		SysDict sysDict = baseMapper.selectById(id);
		if (sysDict.getSysFlag() == 1) {
			throw new DmException("系统默认字典禁止编辑！");
		}
	}

	/**
	 * 构造查询条件
	 * @param params
	 * @return
	 */
	private LambdaQueryWrapper<SysDict> buildQueryWrapper(SysDictQueryParams params) {
		LambdaQueryWrapper<SysDict> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(StringUtils.isNotEmpty(params.getDictCode()), SysDict::getDictCode, params.getDictCode());
		wrapper.like(StringUtils.isNotEmpty(params.getDictName()), SysDict::getDictName, params.getDictName());
		wrapper.eq(params.getStatus() != null, SysDict::getStatus, params.getStatus());
		return wrapper;
	}
}
