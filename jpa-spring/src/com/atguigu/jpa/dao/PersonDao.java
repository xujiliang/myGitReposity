package com.atguigu.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.atguigu.jpa.spring.entities.Person;

@Repository
public class PersonDao {
	
	//如何获取和当前事务绑定的EntityManager对象？
	//通过@PersistenceContext注解来标记成员变量
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(Person person){
		entityManager.persist(person);
	}
}
