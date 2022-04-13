package com.example.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Home")
public class HomeController {
	
	@RequestMapping("/")
	public String index() {
		return "Home";
	}
}
