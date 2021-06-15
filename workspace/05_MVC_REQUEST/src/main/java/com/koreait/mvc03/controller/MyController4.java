package com.koreait.mvc03.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.mvc03.dto.Person;

@Controller
public class MyController4 {

	/*
			Spring의 Model 인터페이스
			
			1. ModelAndView 대신 사용된다.
			2. Model은 응답View로 값을 전달 할 때 사용한다.
			3. Model은 내부적으로 request의 attribute를 이용한다.
			   - 기존 : request.setAttribute("속성명", 값)
			   - Model : model.addAttribute("속성명", 값)
			4. "new Model()" 은 불가능 : 인터페이스이기 때문에
			   - 메소드의 매개변수에 선언하고 사용한다.ㄴ
			
	 */
	
	@RequestMapping("f3/v01")
	public String a(Model model) {
		
		// 응답View에 전달할 데이터
		model.addAttribute("name", "Bread");
		model.addAttribute("age", 50);
		
		return "folder03/view01";
		
	}
	
	@RequestMapping("f3/v02")
	public String b(Model model) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "초코");
		map.put("age", "30");
		
		model.addAttribute("map", map);
		return "folder03/view02";
		
	}
	
	
	// root-context.xml에 존재하는 bean 생성
	// @Inject, @Resource @Autowired 등의 애너테이션 이용
	
	// <bean id="p1" class="com.koreait.mvc03.dto.Person">
	
	// 1. @Inject
	// 	  1) pom.xml에 디펜던시 추가 (02_DI 참고)
	// 	  2) 필드, 생성자, setter를 이용해서 주입
	// 	  3) 클래스를 기반으로 생성 (같은 클래스 타입을 찾아서 생성한다.
	// 	  4) 예시) 필드를 이용해서 주입하기
	//		 @Inject
	//		 private Person p;
	
	// 2. @Resource
	//	  1) pom.xml에 디펜던시 추가 (02_DI 참고)
	// 	  2) 필드, setter를 이용해서 주입 (생성자 X)
	// 	  3) bean의 id속성을 기반으로 생성 (id가 일치하면 만들어진다.)
	//	  4) 예시) setter를 이용해서 주입하기
	//		 private Person p1;
	//		 @Resource
	//		 private void setP1(Person p1) { <<< Person p1 <<< "p1" 반드시, p1이여야 한다.
	//			this.p1 = p1;
	//		 }

	// 3. @AutoWired
	//	  1) 별도 디펜던시가 필요 없다.
	// 	  2) 필드, 생성자, setter를 이용해서 주입
	// 	  3) 클래스를 기반으로 생성 (같은 클래스 타입을 찾아서 생성한다.
	
	// field   >>>>   필드로 만드는 "Autowired"는 바로 밑의 필드만 적용되기 때문에 다른 필드도 적용하고자 하면 위에 다시 선언해줘야 한다.
	@Autowired
	@Qualifier("p1")
	private Person person1;
	
	@RequestMapping("f3/v03")
	public String c(Model model) {
		
		model.addAttribute("person1", person1);
		
		return "folder03/view03";
	}
	
	@Autowired 
	@Qualifier("p2")
	private Person person2;
	
	@RequestMapping("f3/v04")
	public String d(Model model) {
		
		model.addAttribute("person2", person2);
		
		return "folder03/view04";
	}
	
	
}
