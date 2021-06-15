package com.koreait.mvc03.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.mvc03.dto.Person;

@Controller
public class MyController6 {

	/*
			전달받은 파라미터 처리
			
			1. HttpServletRequest request
				: session을 사용하기 위해서는 "Request"가 필요하기 때문에 
				  _완전히 사용하지 않지 않기 때문에 사용방법 알아두어야 한다.
				  
			2. @RequestParam
				: 사용하는 속성 (value, required, defaluteValue)
				
			3. DTO
			
			↑↑↑ 지금까지는, 받아서 - 전달해주는 2번의 작업으로 진행
			
			4. @ModelAttribute
				: 받아서 보내는 작업을 '동시에' [한번에] 진행한다
				  
	 */
	
	
	// 1. request 이용하기
	// index.jsp에서 : /mvc03/f5/v01?name=브레드&age=50
	// ▼ ▼
	// HttpServletRequest request : name=브레드&age=50 ( >> "request"가 처리한다)
	// Model model : "folder05/view01" (결과가 나올 "응답"을 처리한다)
	
	@RequestMapping("f5/v01") 
	public String a(HttpServletRequest request, Model model) { /* request에 name,age(파라미터) */ 
		
		// (1) 파라미터 처리_ name,age : request.getParameter("__") 
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		// (2) model에 담기_ name,age : model.addAttribute("__", __) -> '속성'으로 담긴다.
		model.addAttribute("name", name); /* request에 name(속성) */ 
		model.addAttribute("age", age);	  /* request에 age (속성) */ 
		
		return "folder05/view01"; /* forward : 기존 request를 보냄 */ 
								  // ${param.name}	: request에 name,age(파라미터)
		  						  // ${param.age}
								  // ${name}		: request에 name,age(속성)
								  // ${age}
	}
	
	
	
	// 2. @RequestParam 이용하기
	// @RequestParam(value="파라미터")
	// @RequestParam("파라미터")
	// @RequestParam(value="파라미터")
	// @RequestParam(value="파라미터")
	// @RequestParam(value="파라미터")
	@RequestMapping("f5/v02")
	public String b( @RequestParam("name") String name,  /* @RequestParam(파라미터명) 저장할 변수 */
					 @RequestParam("age") int age,
					 Model model ) {
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "folder05/view02";
	}
	
	/*
			@RequestParam("name") String name
			↑ (해당 대체하는 코드)
			String name = request.getParameter("name");
			
			RequestParam("name") : "name"  
			String name : String name
	 */
	
	
	
	// 3. @RequestParam의 null 처리
	// @RequestParam(required=false) 
	// 		(1) required : 반드시 필요한가? 
	// 				↑ false = 파라미터가 없어도 처리된다.
	// 		(2) defaultValue="0" : 파라미터가 없으면 0을 사용한다. (기본값의 개념)
	//				↑ int age : 스스로 parsing(파싱)하기 때문에 파라미터가 없으면 '오류처리'된다. (해당 속성을 사용하는 이유)
	@RequestMapping("f5/v03")
	public String c( @RequestParam(value="name", required=false) String name,
					 @RequestParam(value="age", required=false, defaultValue="0") int age,  
					 Model model ) {
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		
		return "folder05/view03";
	}
	
	
	
	// 4. DTO 이용하기
	@RequestMapping("f5/v04")		 // DTO를 준비해두면 알아서 파라미터가 저장이 된다.
	public String d( Person person,  /* 파라미터 name,age가 "알아서" person에 저장된다. */
					 Model model ) {
		
		model.addAttribute("person", person);
		
		return "folder05/view04";
	}
	
	
	
	
	// 5. @ModelAttribute 이용하기 : 파라미터를 받기
	@RequestMapping("f5/v05")
					 /* 파라미터 name을 String name에 저장한 뒤, model에 저장한다. */
	public String e( @ModelAttribute(value="name") String name,  
					 @ModelAttribute("age") int age) {  
					 // Model의 선언은 없다.
		return "folder05/view05";
		
	}
	
	
	// 6. @ModelAttribute 이용하기 : DTO를 받기
	@RequestMapping("f5/v06")
	public String f( @ModelAttribute("person") Person person) {
		return "folder05/view06";
	}
	
	
	
	
	
	
}
