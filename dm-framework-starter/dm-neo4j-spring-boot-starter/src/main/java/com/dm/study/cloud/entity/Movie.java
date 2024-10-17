package com.dm.study.cloud.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

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
 * <p>创建日期：2024年04月18日 15:31</p>
 * <p>类全名：com.dm.study.cloud.entity.Movie</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Node(labels = "Movie")
@Getter
@Setter
@ToString
public class Movie implements Serializable {
	private static final long    serialVersionUID = -5379414073985631341L;
	@Id
	@GeneratedValue
	private              Long    id;
	@Property
	private              Integer released;
	@Property
	private              String  tagline;
	@Property
	private              String  title;
}
