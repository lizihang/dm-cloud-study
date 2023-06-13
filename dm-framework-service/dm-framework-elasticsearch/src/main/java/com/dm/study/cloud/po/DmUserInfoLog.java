package com.dm.study.cloud.po;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2023年06月13日 15:54</p>
 * <p>类全名：com.dm.study.cloud.po.DmUserInfoLog</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Getter
@Setter
@Document(indexName = "dm-user2-info-2023.05.22", createIndex = false)
public class DmUserInfoLog {
	@Id
	@Field(type = FieldType.Keyword)
	private String id;
	@Field(type = FieldType.Text)
	private String message;
}
