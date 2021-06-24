package com.koreait.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.member.command.EmailAuthCommand;
import com.koreait.member.command.IdCheckCommand;

@Controller
public class MemberController {

	// field
	private SqlSession sqlSession;
	private IdCheckCommand idCheckCommand;
	private EmailAuthCommand emailAuthCommand;
	
	// <bean>으로 생성한 constructor
	@Autowired
	public MemberController(SqlSession sqlSession,
							IdCheckCommand idCheckCommand,
							EmailAuthCommand emailAuthCommand) {
		this.sqlSession = sqlSession;
		this.idCheckCommand = idCheckCommand;
		this.emailAuthCommand = emailAuthCommand;
	}
	
	
	// 기본값 : 프로젝트를 실행 할 때, 바로 index.jsp로 이동하겠다.
	@GetMapping(value="/")
	public String index() {
		return "index";
	}
	
	// 페이지로 이동
	@GetMapping(value="joinPage.do")
	public String joinPage() {
		return "member/join";
	}
	
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
	
	
	@GetMapping(value="verifyNum.do", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, String> verifyNum(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		
		return emailAuthCommand.execute(sqlSession, model);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
