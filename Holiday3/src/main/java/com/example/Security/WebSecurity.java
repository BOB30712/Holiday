package com.example.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;





@EnableWebSecurity
@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new BossDetailServiceImpl();
	}
	
	//加密
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//不加密
	@Bean
	public static NoOpPasswordEncoder passwordEncoder2() {
	 return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder2() );
		return authenticationProvider;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.
			authorizeRequests()
			.antMatchers("/holiday/paidleave/").authenticated()
			.anyRequest().permitAll() //對象為所有網址
			.and()
			.formLogin()//若未不符合authorize條件，則產生預設login表單
			.defaultSuccessUrl("/holiday/paidleave/")
			.and()
			.httpBasic() //產生基本表單
			.and()
			.logout()
			.logoutSuccessUrl("/holiday/");
	}
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		
		auth.authenticationProvider(authenticationProvider());
		/*
		auth 
			.inMemoryAuthentication() //自訂Runtime時的使用者帳號
				.withUser("admin") //新增user
				.password("{noop}admin12345") //指定密碼
				.roles("ADMIN", "USER") //指派權限群組
				.and() //再新增使用者
				.withUser("user")
				.password("user12345")
				.roles("USER");
				*/
	}
}
