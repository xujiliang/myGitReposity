package com.my.crud.test;

import static org.junit.Assert.*;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.crud.bean.Department;
import com.my.crud.bean.Employee;
import com.my.crud.dao.DepartmentMapper;
import com.my.crud.dao.EmployeeMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	
	@Autowired 
	DepartmentMapper departmentMapper;
	
	@Autowired
	EmployeeMapper employeeMapper; 
	@Autowired
	SqlSession sqlSession;
	@Test
	public void test() {
		
//		System.out.println(departmentMapper);
//		Department department1 = new Department(null, "研发部");
//		Department department2 = new Department(null, "测试部");
//		departmentMapper.insertSelective(department1);
//		departmentMapper.insertSelective(department2);
//		employeeMapper.insertSelective(new Employee(null,"Tony","M","tony@qq.com",1));
//		
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i=0;i<=1000;i++){
			
			String uuid = UUID.randomUUID().toString().substring(0, 5)+i; 
			mapper.insertSelective(new Employee(null,uuid,"M",uuid+"@qq.com",1));
		}
		
	}

}
