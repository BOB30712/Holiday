package com.example.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.Repository.BossRepository;
import com.example.entity.Boss;

public class BossDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private BossRepository bossrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Boss boss=bossrepo.getUserByUsername(username);
		if(boss==null) {
			throw new UsernameNotFoundException("Could not fing user");
		}
		return new BossDetails(boss);
	}

}
