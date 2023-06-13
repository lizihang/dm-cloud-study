package com.dm.study.cloud.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
/**
 * <p>标题：系统字典详情</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年05月18日 15:49</p>
 * <p>类全名：com.dm.study.cloud.po.SysDictDetail</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
@TableName("dm_sys_dict_detail")
public class SysDictDetail implements Serializable {
	private static final long    serialVersionUID = -1535591959491308054L;
	// id
	@TableId
	private              Integer id;
	// 字典id
	private              String  dictId;
	// 字典KEY
	private              String  dictKey;
	// 字典VALUE
	private              String  dictValue;
}
