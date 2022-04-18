package com.dm.study.cloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dm.study.cloud.po.DmUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年04月18日 21:23</p>
 * <p>类全名：com.dm.study.cloud.dao.UserMapper</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Mapper
public interface UserMapper extends BaseMapper<DmUser> {
}
