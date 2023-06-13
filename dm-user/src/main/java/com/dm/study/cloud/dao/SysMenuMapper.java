package com.dm.study.cloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dm.study.cloud.param.SysMenuQueryParams;
import com.dm.study.cloud.po.SysMenu;
import com.dm.study.cloud.util.MybatisRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
 * <p>创建日期：2022年08月15日 15:10</p>
 * <p>类全名：com.dm.study.cloud.dao.SysMenuMapper</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
	List<SysMenu> selectMenuList(@Param("p") SysMenuQueryParams params);
}
