package com.koreait.member1.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.member.command.IdCheckCommand;

@Controller
public class MemberController {

	// field
	private SqlSession sqlSession;
	
	// <bean>으로 생성한 constructor
	@Autowired
	public MemberController(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
