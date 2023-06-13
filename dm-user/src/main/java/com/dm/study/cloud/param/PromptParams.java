package com.dm.study.cloud.param;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年05月10日 10:35</p>
 * <p>类全名：com.dm.study.cloud.param.PromptParams</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
public class PromptParams implements Serializable {
	private static final long    serialVersionUID = -1980476431673468904L;
	@NotEmpty
	private              String  id;
	@NotEmpty
	private              String  prompt;
	@NotNull
	private              Boolean stream;
	//
	private              String  url;
}
