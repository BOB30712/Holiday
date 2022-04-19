package com.example.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.EmployeeRepository;
import com.example.Service.EmployeeService;
import com.example.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeerepository;
	
	@Override
	public Employee saveEmployee(Employee employee) {
		
		return employeerepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		
		return employeerepository.findAll();
	}

	@Override
	public Employee getEmployee(Long id) {
		Optional<Employee> employee=employeerepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}else {
			throw new RuntimeException("Id:"+id+" 該員工不存在");
		}
	}

	@Override
	public Employee updateEmployee(Employee employee, Long id) {
		Optional<Employee> existingemployee=employeerepository.findById(id);
		if(existingemployee.isPresent()) {
			Employee newemployee=existingemployee.get();
			newemployee.setBoss(employee.getBoss());
			newemployee.setDepartment(employee.getDepartment());
			newemployee.setJob(employee.getJob());
			newemployee.setPaidleave(employee.getPaidleave());
			newemployee.setUsername(employee.getUsername());
			newemployee.setUserpassword(employee.getUserpassword());
			employeerepository.save(newemployee);
			return newemployee;
		}else {
			throw new RuntimeException("Id:"+id+" 該員工不存在");
		}
	}

	@Override
	public void deteleEmployee(Long id) {
		employeerepository.deleteById(id);
	}

}
