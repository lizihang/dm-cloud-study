package com.dm.study.cloud.vo;

import lombok.Getter;
import lombok.Setter;

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
 * <p>创建日期：2023年04月04日 15:02</p>
 * <p>类全名：com.dm.study.cloud.vo.IdParam</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
public class IdParam implements Serializable {
	private static final long   serialVersionUID = 3855823567136116247L;
	/* id */
	@NotNull
	private              String id;
}
