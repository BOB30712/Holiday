package com.example.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Service.impl.BossServiceImpl;
import com.example.Service.impl.EmployeeServiceImpl;
import com.example.entity.Employee;

@RestController
@RequestMapping("/EmployeeController2")
public class EmployeeController2 {
	
	@Autowired
	private EmployeeServiceImpl employeeservice;
	
	@Autowired
	private BossServiceImpl bossservice;
	
	@GetMapping("/")
	public List<Employee> index(){
		return employeeservice.getAllEmployee();
	}
	
	public Employee addEMp(@RequestBody Map<String,String> map) {
		 Employee employee=new Employee();
		 employee.setUsername(map.get("username"));
		 Long bossid=Long.parseLong(map.get("boss"));
		 employee.setBoss(bossservice.getBossId(bossid));
		 employee.setDepartment(map.get("departyment"));
		 
		 employeeservice.saveEmployee(employee);
		 return employee;
	}

}
