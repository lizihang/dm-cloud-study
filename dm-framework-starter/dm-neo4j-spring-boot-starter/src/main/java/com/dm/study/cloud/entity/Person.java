package com.dm.study.cloud.entity;

import com.dm.study.cloud.relationship.PersonRelationship;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.neo4j.core.schema.*;

import java.io.Serializable;
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
 * <p>创建日期：2024年04月18日 14:28</p>
 * <p>类全名：com.dm.study.cloud.entity.Person</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Node(labels = "Person")
@Getter
@Setter
@ToString
public class Person implements Serializable {
	private static final long                     serialVersionUID = -4732228224584457412L;
	@Id
	@GeneratedValue
	private              Long                     id;
	@Property
	private              Integer                  born;
	@Property
	private              String                   name;
	// 关系属性
	@Relationship(type = "ACTED_IN")
	private              List<PersonRelationship> actedMovies;
	// // 关系属性
	// @Relationship(type = "DIRECTED")
	// private              List<Movie>              directedMovies;
	// @Relationship(type = "WROTE")
	// private              List<PersonRelationship> relationshipList;
}
