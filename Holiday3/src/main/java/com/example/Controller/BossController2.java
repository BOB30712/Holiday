package com.example.Controller;

import java.util.List;
import java.util.Map;

import org.hibernate.proxy.map.MapLazyInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Service.impl.BossServiceImpl;
import com.example.entity.Boss;

@RestController
@RequestMapping("/BossController2")
public class BossController2 {

	@Autowired
	private BossServiceImpl bossservice;

	@GetMapping("/")
	public List<Boss> data(){
		return bossservice.getAllBoss();
	}
	
	@PostMapping("/")
	public void addBoss(@RequestBody Map<String, String> map) {
		Boss boss=new Boss();
		boss.setUsername(map.get("username"));
		boss.setUserpassword(map.get("userpassword"));
		bossservice.saveBoss(boss);
	}
	
	@GetMapping("/{id}")
	public Boss getBoss(@PathVariable("id") Long id) {
		Boss boss=bossservice.getBossId(id);
		return boss;
	}
	
	@PutMapping("/update/{id}")
	public void update(@PathVariable("id") Long id,@RequestBody Map<String, String> map) {
		Boss boss=bossservice.getBossId(id);
		boss.setUsername(map.get("username"));
		boss.setUserpassword(map.get("userpassword"));
		bossservice.saveBoss(boss);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long id) {
		bossservice.deteleBoss(id);
	}
}
