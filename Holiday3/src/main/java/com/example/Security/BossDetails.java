package com.example.Security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.entity.Boss;

public class BossDetails implements UserDetails{

	private Boss boss;
	
	public BossDetails(Boss boss) {
		this.boss=boss;
	}
	
	//所有帳號皆可使用
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		SimpleGrantedAuthority authority=new SimpleGrantedAuthority("ROLE_USER");
		return Arrays.asList(authority);
		
	}

	@Override
	public String getPassword() {
		return boss.getUserpassword();
	}

	@Override
	public String getUsername() {
		return boss.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
