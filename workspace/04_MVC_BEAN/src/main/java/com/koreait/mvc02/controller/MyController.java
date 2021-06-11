package com.koreait.mvc02.controller;

import javax.servlet.jsp.PageContext;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koreait.mvc02.dto.BeanConfiguration;
import com.koreait.mvc02.dto.Member;

@Controller  // 컨트롤러
public class MyController {

	// URLMapping을 처리하는 메소드 단위로 구성한다.
	/*
			switch(cmd) {
			case "":
				command = new ....();
				break;
			.
			.
			.
			}
			▼ ▼ ▼
			
	 * */
	
	// URLMapping을 처리하는 메소드 단위로 구성한다.
	// @RequestMapping(value="/")  // URLMapping이 contextPath인 경우이다. (
	// @RequestMapping(method=RequestMethod.GET)  // GET방식의 요청이다. (생략 가능)
	@RequestMapping(value="/", method=RequestMethod.GET)
	/*
 			1.반환타입 : String, 응답View의 이름을 반환한다.
 			2.메소드명 : a, 아무 역할이 없다.
 			3.매개변수 : Model model, request를 이용하는 객체이다.  <보안이 좋다>
	 * */ 
	public String a(Model model) {
		
		
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
		
		Member m3 = ctx.getBean("member3", Member.class);
		
		// 매개변수 Model의 역할
		// 응답 View로 보낼 데이터를 저장한다.
		model.addAttribute("m3", m3);
		
		
		/*
			return "index";
			1.DispatcherServlet : servlet-context.xml에 정의된 ViewResolver에 의해서 처리된다.
				(1) prefix : "/WEB-INF/views/", return 앞에 추가한다.
				(2) suffix : ".jsp", return 뒤에 추가한다.
			2. forward로 이동된다. (기본 값_redirect가 없다면 "forward"이다)
				: ModelAndView가 불필요해진다.
		*/
		
		return "index";  // return "/WEB-INF/views/index/jsp";
		// index.jsp 로 (model.addAttribute("m3", m3);)을 보내겠다.
	}
	
	
	
	
	
	
	
}
