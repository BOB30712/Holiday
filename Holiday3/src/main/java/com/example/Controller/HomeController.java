package com.example.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Home")
public class HomeController {
	private int count=0;
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("number",count);
		count++;
		return "Home";
	}
	
	//@Controller需要改為@RestController
	@GetMapping("/test")
	@ResponseBody
	public String JQuerytest2() {
		return "第二個測試成功";
	}
}
