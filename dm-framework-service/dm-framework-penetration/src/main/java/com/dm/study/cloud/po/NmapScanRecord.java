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
 * <p>创建日期：2024年02月02日 14:37</p>
 * <p>类全名：com.dm.study.cloud.po.NmapScanRecord</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
@ToString
@TableName("safe_nmap_record")
public class NmapScanRecord extends BasePO {
	private static final long    serialVersionUID = -8733387246545223547L;
	// 输出文件
	private              String  outFile;
	// 是否解析
	private              Integer analysis;
}
