package com.dm.study.cloud.param;

import com.dm.study.cloud.vo.QueryParams;
import lombok.Getter;
import lombok.Setter;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年09月16日 11:24</p>
 * <p>类全名：com.dm.study.cloud.param.SysRoleQueryParams</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
public class SysRoleQueryParams extends QueryParams {
	private static final long   serialVersionUID = 1317206478005860425L;
	/* 角色名称 */
	private              String roleName;
}
