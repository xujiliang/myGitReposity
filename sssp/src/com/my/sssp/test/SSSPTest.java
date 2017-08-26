package com.my.sssp.test;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.my.sssp.entities.Department;
import com.my.sssp.repository.DepartmentRepository;

public class SSSPTest {
	private ApplicationContext ctx = null;
	private DepartmentRepository departmentRepository;
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		departmentRepository = ctx.getBean(DepartmentRepository.class);
	}
	
	
	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}
	
	@Test
	public void testSecondLevelCache(){
		List<Department> departments = departmentRepository.getAll();
		//departments = departmentRepository.getAll();
	}
}
