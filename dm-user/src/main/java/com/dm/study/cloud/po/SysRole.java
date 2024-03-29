package com.dm.study.cloud.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2022年09月16日 11:09</p>
 * <p>类全名：com.dm.study.cloud.po.SysRole</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
@ToString
@TableName("dm_sys_role")
public class SysRole extends BasePO {
	private static final long    serialVersionUID = -8611417340775332277L;
	/* 角色编码 */
	private              String  roleCode;
	/* 角色名称 */
	private              String  roleName;
	/* 状态 */
	private              Integer status;
}
