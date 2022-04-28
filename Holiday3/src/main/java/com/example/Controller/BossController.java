package com.example.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Repository.BossRepository;
import com.example.Service.impl.BossServiceImpl;
import com.example.entity.Boss;
import com.example.entity.Employee;

@Controller
@RequestMapping("/boss")
public class BossController {
	
	@Autowired
	private BossServiceImpl bossservice;
	
	@RequestMapping("/")
	public String index(Model model,@ModelAttribute Boss boss) {
		model.addAttribute("_method","POST");
		model.addAttribute("bosses",bossservice.getAllBoss());
		return "boss";
	}
	
	@GetMapping("/{id}")
	public String get(@PathVariable("id") Long id, Model model) {
		model.addAttribute("_method", "PUT");
		model.addAttribute("boss", bossservice.getBossId(id));
		model.addAttribute("bosses",bossservice.getAllBoss());
		return "boss";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		bossservice.deteleBoss(id);
		return "redirect:../";
	}
	
	@PostMapping("/")
	public String add(Model model,@Valid @ModelAttribute Boss boss,BindingResult result) {
		//一旦傳出錯誤，返回原先頁面
		if(result.hasErrors()) {
			model.addAttribute("_method","POST");
			model.addAttribute("bosses",bossservice.getAllBoss());
			return "boss";
		}
		/*
		 * 採用BCryptPasswordEncoder加密時，將預設密碼加密後再存入資料庫
		BCryptPasswordEncoder encode=new BCryptPasswordEncoder();
		String newPassword=encode.encode(boss.getUserpassword());
		boss.setUserpassword(newPassword);
		*/
		bossservice.saveBoss(boss);
		return "redirect:./";
	}
	
	@PutMapping("/")
	public String update(@Valid @ModelAttribute Boss boss, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("_method", "PUT");
			model.addAttribute("bosses",bossservice.getAllBoss());
			return "boss";
		}
		bossservice.updateBoss(boss,boss.getId());
		return "redirect:./";
	}
	
	@GetMapping("/FullCalender")
	public String employeeindex(@Valid Long id) {
		return "FullCalender";
	}
	
	
	
	@RequestMapping("/Emptest")
	public String Emptest() {
		return "Emptest";
	}
	
	@RequestMapping("/boss2")
	public String boss2() {
		return "boss2";
	}
}
