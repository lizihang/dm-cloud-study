package com.dm.study.cloud.servie;

import com.dm.study.cloud.entity.Person;
import com.dm.study.cloud.repository.PersonRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
 * <p>创建日期：2024年04月18日 15:10</p>
 * <p>类全名：com.dm.study.cloud.servie.PersonService</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Service
public class PersonService {
	@Resource
	PersonRepository repository;

	public Person createPerson(String name) {
		Person person = new Person();
		person.setName(name);
		person.setBorn(1970);
		return repository.save(person);
	}

	public Person findPersonByName(String name) {
		return repository.findByName(name);
	}

	public List<Person> findAll() {
		return repository.findAll();
	}
}
