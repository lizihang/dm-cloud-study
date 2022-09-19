package com.dm.study.cloud.po;

import lombok.Getter;
import lombok.Setter;

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
 * <p>创建日期：2022年09月19日 9:30</p>
 * <p>类全名：com.dm.study.cloud.po.TestGroupingBy</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
public class TestGroupingBy extends BasePO {
	private static final long          serialVersionUID = -3169881024445790933L;
	private              List<SysUser> userList1;
	private              List<SysUser> userList2;
	private              List<SysUser> userList3;
	private              List<SysUser> allList;
}
