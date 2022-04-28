package com.example.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.Temporal;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Repository.EmployeeRepository;
import com.example.Service.impl.BossServiceImpl;
import com.example.Service.impl.EmployeeServiceImpl;
import com.example.entity.Boss;
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
	
	@GetMapping("/boss")
	public List<Boss> bossdata(){
		return bossservice.getAllBoss();
	}
	
	@PostMapping("/")
	public String addEmp(@RequestBody Map<String, String> map) {
		Long bossid=Long.valueOf(map.get("bossid"));
		Employee employee=new Employee();
		employee.setBoss(bossservice.getBossId(bossid));
		employee.setUsername(map.get("username"));
		employeeservice.saveEmployee(employee);
		 return "成功";
		
	}
	
	@GetMapping("/{id}")
	public Employee getEmp(@PathVariable("id") Long id) {
		Employee employee=employeeservice.getEmployee(id);
		return employee;
	}
	
	
	@PutMapping("/update/{id}")
	public void updateEmp(@PathVariable("id") Long id,@RequestBody Map<String, String> map) {
		Employee employee=employeeservice.getEmployee(id);
		Long bossid=Long.valueOf(map.get("bossid"));
		employee.setUsername(map.get("username"));
		employee.setBoss(bossservice.getBossId(bossid));
		employeeservice.saveEmployee(employee);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteEmp(@PathVariable("id") Long id){
		employeeservice.deteleEmployee(id);
	}
	

}
