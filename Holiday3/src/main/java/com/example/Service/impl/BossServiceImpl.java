package com.example.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.BossRepository;
import com.example.Service.BossService;
import com.example.entity.Boss;

@Service
public class BossServiceImpl implements BossService{
	
	@Autowired
	private BossRepository bossrepository;

	@Override
	public Boss saveBoss(Boss boss) {
		
		return bossrepository.save(boss);
	}

	@Override
	public List<Boss> getAllBoss() {
		return bossrepository.findAll();
	}

	@Override
	public Boss getBossId(Long id) {
		Optional<Boss> boss=bossrepository.findById(id);
		if(boss.isPresent()) {
			return boss.get();
		}else {
			throw new RuntimeException("Id:"+id+" 該主管不存在");
		}
	}

	@Override
	public Boss updateBoss(Boss boss, Long id) {
		Optional<Boss> existingboss=bossrepository.findById(id);
		if(existingboss.isPresent()) {
			Boss newboss=existingboss.get();
			newboss.setDepartment(boss.getDepartment());
			newboss.setUsername(boss.getUsername());
			newboss.setUserpassword(boss.getUserpassword());
			bossrepository.save(newboss);
			return newboss;
		}else {
			throw new RuntimeException("Id:"+id+" 該主管不存在");
		}
	}

	@Override
	public void deteleBoss(Long id) {
		bossrepository.deleteById(id);
	}

}
