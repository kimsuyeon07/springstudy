package com.koreait.mvc03.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.koreait.mvc03.dto.Person;

@Configuration
public class BeanConfiguration {
	
	@Bean(name="man")
	public Person p1() {
		Person m = new Person();
		m.setName("버터");
		m.setAge(35);
		List<String> hobbies = new ArrayList<String>();
		hobbies.add("헬스");
		hobbies.add("등산");
		hobbies.add("수영");
		m.setHobbies(hobbies);
		
		return m;
	}
	
	@Bean(name="woman")
	public Person p2() {
		Person w = new Person();
		w.setName("사과");
		w.setAge(25);
		List<String> hobbies = new ArrayList<String>();
		hobbies.add("헬스");
		hobbies.add("산책");
		w.setHobbies(hobbies);
		
		return w;
	}
	
	
}
