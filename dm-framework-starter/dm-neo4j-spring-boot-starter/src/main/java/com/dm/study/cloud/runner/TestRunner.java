package com.dm.study.cloud.runner;

import com.dm.study.cloud.entity.Person;
import com.dm.study.cloud.servie.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
 * <p>创建日期：2024年04月18日 15:14</p>
 * <p>类全名：com.dm.study.cloud.runner.TestRunner</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class TestRunner implements CommandLineRunner {
	@Resource
	PersonService personService;

	@Override
	public void run(String... args) throws Exception {
		// Person person = personService.findPersonByName("Kevin Pollak");
		// System.out.println(person);
		// List<Movie> movieList = person.getActedMovies();
		// for (Movie m : movieList) {
		// 	System.out.println(m);
		// }
		System.out.println("==============================================");
		List<Person> personList = personService.findAll();
		for (Person p : personList) {
			System.out.println(p);
		}
	}
}
