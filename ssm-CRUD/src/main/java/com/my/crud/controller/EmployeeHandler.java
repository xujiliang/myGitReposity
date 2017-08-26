package com.my.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.crud.bean.Employee;
import com.my.crud.bean.Msg;
import com.my.crud.service.EmployeeService;
@Controller
public class EmployeeHandler {
	@Autowired
	EmployeeService employeeService;
	
	
	
	/**
	 * 验证用户名是否可用
	 */
	@RequestMapping("/checkname")
	@ResponseBody
	public Msg checkName(@RequestParam("empName")String empName){
		//后台验证用户名格式
		String ajax = "(^[a-z0-9_-]{5,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		
		if(!empName.matches(ajax)){
			return Msg.failure().add("ajMsg", "用户名格式不合法！");
		}
		
		boolean b = employeeService.checkName(empName);
		if(b){
			return Msg.success();
		}else{
			return Msg.failure().add("ajMsg", "用户名以存在！");
		}
	}
	/**
	 * 保存模态框中的表单数据
	 * @return
	 */
	@RequestMapping(value="/emp", method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result){
		if(result.hasErrors()){
			Map<String,Object> map = new HashMap<>();
			List<FieldError> error = result.getFieldErrors();
			for (FieldError fieldError : error) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			
			return Msg.failure().add("fieldErrors", map);
		}else{
			
			employeeService.saveEmp(employee);
			return Msg.success();
		}
		
	}
	
	
	/**
	 * 根据员工Id查询员工信息
	 * 
	 */
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id") Integer id){
		
		Employee employee = employeeService.getEmployee(id);
		return Msg.success().add("employee", employee);
	}
	
	
	
	/**
	 * 返回json数据
	 * 加入jackson包
	 * 返回所有员工
	 * @param pn
	 * @return
	 */
	
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmps(@RequestParam(value="pn",defaultValue="1") Integer pn){
		
		PageHelper.startPage(pn,5);
		
		List<Employee> emps = employeeService.getEmployees();
	
		PageInfo page = new PageInfo(emps,5);
		return Msg.success().add("pageInfo", page);
	}
	
	//@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue="1") Integer pn,Model model){
		
		PageHelper.startPage(pn,5);
		
		List<Employee> emps = employeeService.getEmployees();
	
		PageInfo page = new PageInfo(emps,5);
		model.addAttribute("pageInfo", page);
		//model.addAttribute("employee", new Employee(1,"aa","aa@qq.com","F",1));
		return "list";
	}
	
	/**
	 * 更新员工
	 */
	@RequestMapping(value="/emp/{empId}", method=RequestMethod.PUT)
	@ResponseBody
	public Msg update(Employee employee){
		System.out.println(employee);
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	
	/**
	 * 删除员工
	 */
	@RequestMapping(value="/emp/{empIds}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg delete(@PathVariable("empIds")String empIds){
		System.out.println(empIds);
		if(empIds.contains("-")){
			
			List<Integer> list = new ArrayList<>();
			String[] emp_Ids = empIds.split("-");
			for (String ids : emp_Ids) {
				System.out.println(ids);
			list.add(Integer.parseInt(ids));
			}
			
			employeeService.deleteEmpsById(list);
		}else{
			Integer id = Integer.parseInt(empIds);
			employeeService.deleteEmp(id);
		}
		
		return Msg.success();
	}
}
