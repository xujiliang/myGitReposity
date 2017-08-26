package com.my.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.my.crud.bean.Employee;
import com.my.crud.bean.EmployeeExample;
import com.my.crud.bean.EmployeeExample.Criteria;
import com.my.crud.dao.EmployeeMapper;
@Service
public class EmployeeService {
	
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	/**
	 * 获取所有员工数据
	 * @return
	 */
	public List<Employee> getEmployees(){

		return employeeMapper.selectByExampleWithDept(null);
	}

	
	/**
	 * 保存员工数据
	 * @param employee
	 */
	public void saveEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.insertSelective(employee);
	}

	/**
	 * 验证用户名是否可用
	 * true表示可用
	 * false表示不可用
	 * @param name
	 */
	public boolean checkName(String name) {
		// TODO Auto-generated method stub
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(name);
		long count = employeeMapper.countByExample(example);
		return count==0;
	}

	/**
	 * 根据员工id查询员工信息
	 * @param id
	 * @return
	 */
	public Employee getEmployee(Integer id) {
		// TODO Auto-generated method stub
		Employee employee = employeeMapper.selectByPrimaryKey(id);
		return employee;
	}


	public void updateEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.updateByPrimaryKeySelective(employee);
	}


	public void deleteEmp(Integer id) {
		// TODO Auto-generated method stub
		employeeMapper.deleteByPrimaryKey(id);
	}


	public void deleteEmpsById(List<Integer> list) {
		// TODO Auto-generated method stub
		
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpIdIn(list);
		employeeMapper.deleteByExample(example);
	}
}
