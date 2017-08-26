package com.my.jpa.spring;



import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.jpa.service.PersonService;
import com.atguigu.jpa.spring.entities.Person;

public class testJPA {

	private ApplicationContext ctx = null;
	private PersonService personService;
	
	{
		
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		personService = ctx.getBean(PersonService.class);
	}
	
	
	@Test
	public void testPersonService(){
		Person p1 = new Person();
		p1.setAge(22);
		p1.setEmail("tina@qq.com");
		p1.setLastName("Tina");
		
		Person p2 = new Person();
		p2.setLastName("Sofy");
		p2.setEmail("sofy@qq.com");
		p2.setAge(23);
		System.out.println(personService.getClass().getName());
		personService.savePersonz(p1, p2);
		
		
	}
	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}

}
