package com.koreait.mvc03.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

	@RequestMapping("loginPage.do")
	public String a() {
		return "member/login";
	}
	
	@RequestMapping("login.do")
	public String b(HttpServletRequest request, Model model) {
		
		model.addAttribute("id", request.getParameter("id"));
		model.addAttribute("pw", request.getParameter("pw"));
		
		return "member/loginResult";
	}
	
}
