package com.hibernate.join.union.subclass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HibernateTest {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void init(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = 
				new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				                            .buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	
	@After
	public void destroy(){
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	/**
	 * 缺点:
	 * 1. 使用了辨别者列.
	 * 2. 子类独有的字段不能添加非空约束.
	 * 3. 若继承层次较深, 则数据表的字段也会较多. 
	 */
	
	/**
	 * 查询:
	 * 1. 查询父类记录, 只需要查询一张数据表
	 * 2. 对于子类记录, 也只需要查询一张数据表
	 */
	
	@Test
	public void testSave(){
		
		Person person = new Person();
		person.setName("CC");
        person.setAge(28);		
        
        Student student = new Student();
        student.setName("DD");
        student.setAge(30);
        student.setSchool("dd");
        session.save(student);
        session.save(person);
	}

}
