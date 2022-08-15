package com.dm.study.cloud.po;

import com.baomidou.mybatisplus.annotation.TableId;
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
 * <p>创建日期：2022年08月15日 15:06</p>
 * <p>类全名：com.dm.study.cloud.po.SysMenu</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
public class SysMenu extends BasePO {
	private static final long          serialVersionUID = 7497267136149976058L;
	/**
	 * id
	 */
	@TableId
	private              Integer       id;
	/**
	 * 菜单名称
	 */
	private              String        name;
	/**
	 * 菜单路由
	 */
	private              String        router;
	/**
	 * 菜单图标
	 */
	private              String        icon_class;
	/**
	 * 菜单分组
	 */
	private              String        group;
	/**
	 * 排序号
	 */
	private              Integer       idx;
	/**
	 * 菜单级次
	 */
	private              Integer       level;
	/**
	 * 父菜单id
	 */
	private              Integer       parent_id;
	/**
	 * 子菜单
	 */
	private              List<SysMenu> subMenus;
}
