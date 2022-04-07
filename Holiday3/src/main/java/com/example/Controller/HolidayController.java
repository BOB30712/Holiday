package com.example.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Repository.BossRepository;
import com.example.Repository.EmployeeRepository;
import com.example.Repository.HolidayRepository;
import com.example.entity.Boss;
import com.example.entity.Employee;
import com.example.entity.Holiday;

@Controller
@RequestMapping("/holiday")
public class HolidayController {
	
	@Autowired
	private HolidayRepository holidayrepo;
	
	@Autowired
	private EmployeeRepository employeerepo;
	
	@Autowired
	private BossRepository bossrepo;
	
	@RequestMapping("/")
	public String index(Model model,@ModelAttribute Holiday holiday) {
		model.addAttribute("_method","POST");
		model.addAttribute("holidays",holidayrepo.findAll());
		model.addAttribute("employees",employeerepo.findAll());
		model.addAttribute("bosses",bossrepo.findAll());
		return "holiday";
	}
	
	@GetMapping("/{id}")
	public String get(@PathVariable("id") Long id, Model model) {
		model.addAttribute("_method", "PUT");
		model.addAttribute("holiday", holidayrepo.getById(id));
		model.addAttribute("holidays",holidayrepo.findAll());
		model.addAttribute("employees",employeerepo.findAll());
		model.addAttribute("bosses",bossrepo.findAll());
		return "holiday";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		holidayrepo.deleteById(id);
		return "redirect:../";
	}
	
	@PostMapping("/")
	public String add(Model model,@Valid @ModelAttribute Holiday holiday,BindingResult result) {
		//一旦傳出錯誤，返回原先頁面
		if(result.hasErrors()) {
			model.addAttribute("_method","POST");
			model.addAttribute("holidays",holidayrepo.findAll());
			model.addAttribute("employees",employeerepo.findAll());
			model.addAttribute("bosses",bossrepo.findAll());
			return "holiday";
		}
		//將新增表單的內容傳到資料庫儲存
		
		
		holidayrepo.save(holiday);
		
		return "redirect:./";
	}
	
	@PutMapping("/")
	public String update(@Valid @ModelAttribute Holiday holiday, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("_method", "PUT");
			model.addAttribute("holidays",holidayrepo.findAll());
			model.addAttribute("employees",employeerepo.findAll());
			model.addAttribute("bosses",bossrepo.findAll());
			return "holiday";
		}
		holidayrepo.save(holiday);
		return "redirect:./";
	}
	
	/*
	 * 登入請假申請審核畫面
	 * 1.登入者必須是主管
	 * 2.只會個別顯示有申請至改主管的請假申請
	 * 3.審核後依據該員工剩餘特休時數扣除
	 */
	@RequestMapping("/paidleave")
	public String processindex(Model model,@ModelAttribute Holiday holiday) {
		
		//取得登入者名稱
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Long bid=null;
		for(Boss b:bossrepo.findAll()) {
			if(b.getUsername().equals(username)) {
				bid=b.getId();
			}
		}
		model.addAttribute("name","hello "+username+" "+"wellcome!!");
		model.addAttribute("_method","POST");
		model.addAttribute("holidays",holidayrepo.findByBossname(bid));
		model.addAttribute("employees",employeerepo.findAll());
		model.addAttribute("bosses",bossrepo.findAll());
		
		return "paidleave";
	}
	
	@GetMapping("/paidleave/yes/{id}")
	public String processaddyes(@PathVariable("id") Long id) {
		String s="同意";
		Holiday holiday=holidayrepo.getById(id);
		Employee employee=employeerepo.getById(holiday.getEmployee().getId());
		Integer time=employee.getPaidleave()-holiday.getHour();
		holidayrepo.updateresult(s, id);
		employeerepo.updatepaidleave(time, employee.getId());
		return "redirect:../";
	}
	
	@GetMapping("/paidleave/no/{id}")
	public String processaddno(@PathVariable("id") Long id) {
		String s="不同意";
		holidayrepo.updateresult(s, id);
		return "redirect:../";
	}
}
