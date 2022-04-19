package com.example.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.entity.Employee;
import com.example.Repository.BossRepository;
import com.example.Repository.EmployeeRepository;
import com.example.Service.impl.BossServiceImpl;
import com.example.Service.impl.EmployeeServiceImpl;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeServiceImpl employeeservice;

	@Autowired
	private BossServiceImpl bossservice;
	
	@GetMapping("/")
	public String index(Model model,@ModelAttribute Employee employee) {
		model.addAttribute("_method","POST");
		model.addAttribute("employees",employeeservice.getAllEmployee());
		model.addAttribute("bosses",bossservice.getAllBoss());
		return "employee";
	}
	
	@GetMapping("/{id}")
	public String get(@PathVariable("id") Long id, Model model) {
		model.addAttribute("_method", "PUT");
		model.addAttribute("employee", employeeservice.getEmployee(id));
		model.addAttribute("employees", employeeservice.getAllEmployee());
		model.addAttribute("bosses",bossservice.getAllBoss());
		return "employee";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		employeeservice.deteleEmployee(id);
		return "redirect:../";
	}
	@PostMapping("/")
	public String add(Model model,@Valid @ModelAttribute Employee employee,BindingResult result) {
		//一旦傳出錯誤，返回原先頁面
		if(result.hasErrors()) {
			model.addAttribute("_method", "POST");
			model.addAttribute("employees",employeeservice.getAllEmployee());
			model.addAttribute("bosses",bossservice.getAllBoss());
			return "employee";
		}
		
		//將新增表單的內容傳到資料庫儲存
		employeeservice.saveEmployee(employee);
		return "redirect:./";
	}
	
	@PutMapping("/")
	public String update(@Valid @ModelAttribute Employee employee, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("_method", "PUT");
			model.addAttribute("employees",employeeservice.getAllEmployee());
			model.addAttribute("bosses",bossservice.getAllBoss());
			return "employee";
		}
		employeeservice.updateEmployee(employee, employee.getId());
		return "redirect:./";
	}
}
