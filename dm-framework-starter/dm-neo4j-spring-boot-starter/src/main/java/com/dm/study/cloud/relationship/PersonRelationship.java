package com.dm.study.cloud.relationship;

import com.dm.study.cloud.entity.Movie;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.neo4j.core.schema.*;

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
 * <p>创建日期：2024年04月18日 15:45</p>
 * <p>类全名：com.dm.study.cloud.relationship.PersonRelationship</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@RelationshipProperties()
@Getter
@Setter
@ToString
public class PersonRelationship {
	@Id
	@GeneratedValue
	private Long        id;
	@Property
	private String      type;
	@Property
	private String      roles;
	@TargetNode
	private List<Movie> movies;
}
