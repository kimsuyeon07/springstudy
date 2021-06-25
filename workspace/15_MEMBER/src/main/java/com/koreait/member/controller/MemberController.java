package com.koreait.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.member.command.EmailAuthCommand;
import com.koreait.member.command.IdCheckCommand;
import com.koreait.member.command.JoinCommand;
import com.koreait.member.command.LeaveCommand;
import com.koreait.member.command.LoginCommand;
import com.koreait.member.command.LogoutCommand;
import com.koreait.member.command.PresonPwCheckCommand;
import com.koreait.member.command.UpdateMemberCommand;
import com.koreait.member.command.UpdatePwCommand;
import com.koreait.member.dto.Member;

@Controller
public class MemberController {

	// field
	private SqlSession sqlSession;
	private IdCheckCommand idCheckCommand;
	private EmailAuthCommand emailAuthCommand;
	private JoinCommand joinCommand;
	private LoginCommand loginCommand;
	private LogoutCommand logoutCommand;
	private LeaveCommand leaveCommand;
	private UpdateMemberCommand updateMemberCommand;
	private PresonPwCheckCommand presonPwCheckCommand;
	private UpdatePwCommand updatePwCommand;
	
	// <bean>으로 생성한 constructor
	@Autowired
	public MemberController(SqlSession sqlSession,
							IdCheckCommand idCheckCommand,
							EmailAuthCommand emailAuthCommand,
							JoinCommand joinCommand,
							LoginCommand loginCommand,
							LogoutCommand logoutCommand,
							LeaveCommand leaveCommand, 
							UpdateMemberCommand updateMemberCommand,
							PresonPwCheckCommand presonPwCheckCommand,
							UpdatePwCommand updatePwCommand) {
		this.sqlSession = sqlSession;
		this.idCheckCommand = idCheckCommand;
		this.emailAuthCommand = emailAuthCommand;
		this.joinCommand = joinCommand;
		this.loginCommand = loginCommand;
		this.logoutCommand = logoutCommand;
		this.leaveCommand = leaveCommand;
		this.updateMemberCommand = updateMemberCommand;
		this.presonPwCheckCommand = presonPwCheckCommand;
		this.updatePwCommand = updatePwCommand;
	}
	
	
	// 기본값 : 프로젝트를 실행 할 때, 바로 index.jsp로 이동하겠다.
	@GetMapping(value= {"/", "index.do"})
	public String index() {
		return "index";
	}
	
	// 페이지로 이동 -----------------------
	@GetMapping(value="joinPage.do")
	public String joinPage() {
		return "member/join";
	}
	@GetMapping(value="myPage.do")
	public String myPage() {
		return "member/myPage";
	}
	@GetMapping(value="findIdPage.do")
	public String findIdPage() {
		return "member/findIdPage";
	}
	// --------------------------------
	
	/* idCheck */
	@GetMapping(value="idCheck.do", 
				produces="application/json; charset=UTF-8") 
	@ResponseBody  // return값이 view가 아닌 데이터값을 가지겠다.
	public Map<String, Integer> idCheck(HttpServletRequest request, Model model) {
		// controller에는 값만 전달하는 것 이외는 사용하지 않는 것이 좋다. (간략한 코드만 입력하는 것이 좋다)
		// ↘ 
		model.addAttribute("request", request);
		return idCheckCommand.execute(sqlSession, model);  // type : String
	}
	
	/*
			[ ** SET ** ]	
			<dataType: 'text'> 일 때,
			1. produces="text/plain; charset=UTF-8"
			2. @ResponseBody
			3. public String  ...
			
			<dataType: 'json'> 일 때, >>>> 디펜던시 추가
			1. produces="application/json; charset=UTF-8"
			2. @ResponseBody
			3. public Map<String, Integer>  ...
	*/ 
	
	/* 아이디중복체크 확인 : */
	@GetMapping(value="verifyNum.do", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, String> verifyNum(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		
		return emailAuthCommand.execute(sqlSession, model);
	}
	
	@PostMapping(value="join.do")
	public String join(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		joinCommand.execute(sqlSession, model);
		return "redirect:/"; // idex으로 돌아간다.
	}
	
	@PostMapping(value="login.do")
	public String login(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		loginCommand.execute(sqlSession, model);
		return "redirect:/";
	}
	
	@GetMapping(value="logout.do") 
	public String logout(HttpSession session, Model model) {
		model.addAttribute("session", session);
		logoutCommand.execute(sqlSession, model);
		return "redirect:/";
	}
	
	@GetMapping(value="leave.do")
	public String leave(HttpSession session, Model model) {
		model.addAttribute("session", session);
		leaveCommand.execute(sqlSession, model);
		return "redirect:/";
	}
	
	@PostMapping(value="updateMember.do")
	public String updateMember(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		updateMemberCommand.execute(sqlSession, model);
		return myPage();
	}
	
	/* 비밀번호 (수정) _  */
	@PostMapping(value="presentPwCheck.do", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Boolean> presentPwCheck(@RequestBody Member member, 
											   HttpSession session,
											   Model model) {
		model.addAttribute("member", member);
		model.addAttribute("session", session);
		return presonPwCheckCommand.execute(sqlSession, model);
		
	}
	
	@PostMapping(value="updatePw.do")
	public String updatePw(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		updatePwCommand.execute(sqlSession, model);
		return index();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
