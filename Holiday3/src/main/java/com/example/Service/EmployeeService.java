package com.example.Service;

import java.util.List;

import com.example.entity.Employee;

public interface EmployeeService {
	
	//新增
	Employee saveEmployee(Employee employee);
	//取得全部
	List<Employee> getAllEmployee();
	//取得單一
	Employee getEmployee(Long id);
	//修改
	Employee updateEmployee(Employee employee,Long id);
	//刪除
	void deteleEmployee(Long id);
}
