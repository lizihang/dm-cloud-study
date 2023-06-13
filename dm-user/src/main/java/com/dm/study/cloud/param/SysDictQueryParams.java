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
 * <p>创建日期：2023年05月18日 15:54</p>
 * <p>类全名：com.dm.study.cloud.param.SysDictQueryParams</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
public class SysDictQueryParams extends QueryParams {
	private static final long    serialVersionUID = 5763875511299480200L;
	// 字典编号
	private              String  dictCode;
	// 字典名称
	private              String  dictName;
	// 状态
	private              Integer status;
}
