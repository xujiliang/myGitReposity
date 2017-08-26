package com.my.sssp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.sssp.entities.Employee;
import com.my.sssp.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@Transactional
	public void deleteEmployee(Integer id){
		employeeRepository.delete(id);
	}
	
	@Transactional(readOnly=true)
	public Employee getEmployeeById(Integer id){
		Employee employee = employeeRepository.findOne(id);
		return employee;
	}
	
	@Transactional
	public void saveEmployee(Employee employee ){
		if(employee.getId()==null){
			employee.setCreateTime(new Date());
		}
		
		employeeRepository.saveAndFlush(employee);
	}
	
	@Transactional(readOnly=true)
	public Employee getEmployee(String lastName){
		Employee employee = employeeRepository.getByLastName(lastName);
		System.out.println(employee);
		return employee;
	}
	@Transactional(readOnly=true)
	public Page<Employee> getPage(int pageNo,int pageSize){
		
		PageRequest pageable = new PageRequest(pageNo-1,pageSize);
		
		return employeeRepository.findAll(pageable);
		
	}
	
}
