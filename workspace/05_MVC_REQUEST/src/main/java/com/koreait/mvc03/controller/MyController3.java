package com.koreait.mvc03.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.mvc03.dto.Person;

@Controller
@RequestMapping("f2")
public class MyController3 {

	/*
			Spring의 ModelAndView 클래스
			
			1. 스프링2 이전에 많이 사용하던 클래스이다.
			2. 현재도 많이 존재하고 있는 클래스이다.
			3. 앞으로는 사용을 자제하는 것이 좋다. 
			4. Model과 View를 모두 처리한다.
			   1) Model : 응답View에게 값을 전달할 때 사용하는 클래스이다.
			   2) View  : 응답View를 의미하다.
	*/
	
	@RequestMapping("v01")
	public ModelAndView a() {
		
		ModelAndView mav = new ModelAndView();
		
		// View 설정
		mav.setViewName("folder02/view01"); // "WEB-INF/views/folder02/view01.jsp"
		
		// 보낼 데이터 : 내부적으로 request를 사용한다.
		mav.addObject("name", "브레드");
		mav.addObject("age", 50);
		
		return mav;
	}
	
	
	@RequestMapping("v02")
	public ModelAndView b() {
		
		Person person = new Person();
		List<String> hobbies = new ArrayList<String>();
		hobbies.add("독서");
		hobbies.add("영화");
		hobbies.add("여행");
		hobbies.add("게임");
		hobbies.add("맛집탐방");

		person.setName("Bread");
		person.setAge(20);
		person.setHobbies(hobbies);
		
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("folder02/view02");
		
		mav.addObject("name", person.getName());
		mav.addObject("age", person.getAge());
		mav.addObject("hobbies", person.getHobbies());
		
		return mav;
	}
	
	
	
}
