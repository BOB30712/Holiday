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

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	/*
	 * 1.GET  /emp/------->到員工維護畫面
	 * 2.POST /emp/add---->新增員工>回到員工維護畫面
	 * 3.GET  /emp/{id}--->取得單一員工資料
	 * 4.GET  /emp/delete->刪除單一員工資料
	 * 5.PUT  /emp/------->更新員工資料
	 */

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private BossRepository BossRepo;

	@GetMapping("/")
	public String index(Model model,@ModelAttribute Employee employee) {
		model.addAttribute("_method","POST");
		model.addAttribute("employees",employeeRepo.findAll());
		model.addAttribute("bosses",BossRepo.findAll());
		return "employee";
	}
	
	@GetMapping("/{id}")
	public String get(@PathVariable("id") Long id, Model model) {
		model.addAttribute("_method", "PUT");
		model.addAttribute("employee", employeeRepo.getById(id));
		model.addAttribute("employees", employeeRepo.findAll());
		model.addAttribute("bosses",BossRepo.findAll());
		return "employee";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		employeeRepo.deleteById(id);
		return "redirect:../";
	}
	@PostMapping("/")
	public String add(Model model,@Valid @ModelAttribute Employee employee,BindingResult result) {
		//一旦傳出錯誤，返回原先頁面
		if(result.hasErrors()) {
			model.addAttribute("_method", "POST");
			model.addAttribute("customers",employeeRepo.findAll());
			model.addAttribute("bosses",BossRepo.findAll());
			return "employee";
		}
		
		//將新增表單的內容傳到資料庫儲存
		employeeRepo.save(employee);
		return "redirect:./";
	}
	
	@PutMapping("/")
	public String update(@Valid @ModelAttribute Employee employee, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("_method", "PUT");
			model.addAttribute("employees",employeeRepo.findAll());
			model.addAttribute("bosses", BossRepo.findAll());
			return "employee";
		}
		employeeRepo.save(employee);
		return "redirect:./";
	}
}
