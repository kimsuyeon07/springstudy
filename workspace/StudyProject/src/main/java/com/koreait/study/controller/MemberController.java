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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.study.command.DeleteMemberCommand;
import com.koreait.study.command.EmailAuthCodeCommand;
import com.koreait.study.command.FindIdCommand;
import com.koreait.study.command.FindPwCommand;
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
	private FindPwCommand findPwCommand;
	private DeleteMemberCommand deleteMemberCommand;
	
	// constructor
	@Autowired
	public MemberController(SqlSession sqlSession, 
							IdCheckCommand idCheckCommand,
							EmailAuthCodeCommand emailAuthCodeCommand,
							JoinCommand joinCommand,
							LoginCommand loginCommand,
							LogoutCommand logoutCommand,
							FindIdCommand findIdCommand,
							FindPwCommand findPwCommand,
							DeleteMemberCommand deleteMemberCommand) {
		this.sqlSession = sqlSession;
		this.idCheckCommand = idCheckCommand;
		this.emailAuthCodeCommand = emailAuthCodeCommand;
		this.joinCommand = joinCommand;
		this.loginCommand = loginCommand;
		this.logoutCommand = logoutCommand;
		this.findIdCommand = findIdCommand;
		this.findPwCommand = findPwCommand;
		this.deleteMemberCommand = deleteMemberCommand;
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
	public String login(HttpServletRequest request,
						HttpServletResponse response, 
						Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		loginCommand.execute(sqlSession, model);
		return null;
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
	// FindPw 페이지 이동
	@GetMapping(value="findPwPage.do")
	public String findPwPage() {
		return "member/findPw";
	} // ----------------------
	// Find - findID/findPw
	@PostMapping(value="findId.do")
	public String findId(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		findIdCommand.execute(sqlSession, model);
		return "member/findResult";
	}
	@PostMapping(value="findPw.do")
	public String findPw(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		findPwCommand.execute(sqlSession, model);
		return "member/findResult";
	}
	
	// 이메일 인증 _
	// FindId/FindPw/Join - email(인증번호받기)
	@GetMapping(value="authCode.do", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, String> authCode(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return emailAuthCodeCommand.execute(sqlSession, model);
	}
	
	// 마이 페이지 이동
	@GetMapping(value="myPage.do")
	public String myPage() {
		return "member/myPage";
	}
	
	// 회원 탈퇴
	@GetMapping(value="deleteMember.do")
	public String deleteMember(HttpServletRequest request, 
							   HttpServletResponse response, 
							   Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		deleteMemberCommand.execute(sqlSession, model);
		return null;
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
	public String join(HttpServletRequest request, 
					   HttpServletResponse response, 
					   Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		joinCommand.execute(sqlSession, model);
		return null;
	}
	
	
	
	
}
