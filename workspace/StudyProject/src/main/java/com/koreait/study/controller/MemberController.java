package com.koreait.study.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.study.command.EmailAuthCodeCommand;
import com.koreait.study.command.FindIdCommand;
import com.koreait.study.command.IdCheckCommand;
import com.koreait.study.command.JoinCommand;
import com.koreait.study.command.LoginCommand;
import com.koreait.study.command.LogoutCommand;

@Controller
public class MemberController {

	// field
	private SqlSession sqlSession;
	private IdCheckCommand idCheckCommand;
	private EmailAuthCodeCommand emailAuthCodeCommand;
	private JoinCommand joinCommand;
	private LoginCommand loginCommand;
	private LogoutCommand logoutCommand;
	private FindIdCommand findIdCommand;
	
	// constructor
	@Autowired
	public MemberController(SqlSession sqlSession, 
							IdCheckCommand idCheckCommand,
							EmailAuthCodeCommand emailAuthCodeCommand,
							JoinCommand joinCommand,
							LoginCommand loginCommand,
							LogoutCommand logoutCommand,
							FindIdCommand findIdCommand) {
		this.sqlSession = sqlSession;
		this.idCheckCommand = idCheckCommand;
		this.emailAuthCodeCommand = emailAuthCodeCommand;
		this.joinCommand = joinCommand;
		this.loginCommand = loginCommand;
		this.logoutCommand = logoutCommand;
		this.findIdCommand = findIdCommand;
	}
	
	
	/* ----------- index ------------- */
	@GetMapping(value= {"/", "index.do"})
	public String index() {
		return "index";
	}
	/* ------------------------------- */
	
	// Loign 페이지 이동
	@GetMapping(value="loginPage.do")
	public String loginPage() {
		return "member/login";
	} // ------------
	// Login - login
	@PostMapping(value="login.do")
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		loginCommand.execute(sqlSession, model);
		return "index";
	}
	
	// logout ---------------------
	@GetMapping(value="logout.do")
	public String logout(HttpSession session, Model model) {
		model.addAttribute("session", session);
		logoutCommand.execute(sqlSession, model);
		return "redirect:index.do";
	} //---------------------------
	
	// FindId 페이지 이동
	@GetMapping(value="findIdPage.do")
	public String findIdPage() {
		return "member/findId";
	} //-----------------------
	// FindId - findID
	@PostMapping(value="findId.do")
	public String findId(HttpRequest request, Model model) {
		model.addAttribute("request", request);
		findIdCommand.execute(sqlSession, model);
		return "member/findResult";
	}
	// 이메일 인증 _
	// FindId/Join - email(인증번호받기)
	@GetMapping(value="authCode.do", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, String> authCode(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return emailAuthCodeCommand.execute(sqlSession, model);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// Join 페이지 이동
	@GetMapping(value="joinPage.do")
	public String joinPage() {
		return "member/join";
	} // ------------
	// Join - idCheck
	@PostMapping (value="idCheck.do", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Boolean> idCheck(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return idCheckCommand.execute(sqlSession, model);
	}
	// Join - join
	@PostMapping(value="join.do")
	public String join(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		joinCommand.execute(sqlSession, model);
		return "redirect:index.do";
	}
	
	
	
	
}
