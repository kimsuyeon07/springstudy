package com.koreait.mvc02.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

	//  == AppContext
	
	@Bean(name="contact3")
	public Contact contact() {
		return new Contact("010-3333-4444", "서울시 동교동 신촌로");
	}
	
	@Bean(name="member3")
	public Member member() {
		return new Member("bread", contact());
	}
	
	
	
}
