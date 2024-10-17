package com.dm.study.cloud.repository;

import com.dm.study.cloud.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2024年04月18日 14:54</p>
 * <p>类全名：com.dm.study.cloud.repository.PersonRepository</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Repository
public interface PersonRepository extends Neo4jRepository<Person,Long> {
	Person findByName(String name);
}
