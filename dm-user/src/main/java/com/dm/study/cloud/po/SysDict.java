package com.dm.study.cloud.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;
/**
 * <p>标题：系统字典</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年05月18日 15:48</p>
 * <p>类全名：com.dm.study.cloud.po.SysDict</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
@TableName("dm_sys_dict")
public class SysDict extends BasePO {
	private static final long                serialVersionUID = 3261109147839025322L;
	// 字典编号
	@NotEmpty
	private              String              dictCode;
	// 字典名称
	@NotEmpty
	private              String              dictName;
	// 状态
	private              Integer             status;
	// 系统标记（1：禁止删除，禁止编辑，禁止修改状态）
	private              Integer             sysFlag;
	// 备注
	private              String              remark;
	// 字典详情
	@TableField(exist = false)
	private              List<SysDictDetail> dictDetailList;
}
