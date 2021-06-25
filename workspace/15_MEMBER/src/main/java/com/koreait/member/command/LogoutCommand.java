package com.koreait.member.command;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public class LogoutCommand implements MemberCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		// 로그아웃 시, session을 꺼내서 session의 초기화를 해준다.
		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession)map.get("session");
		
		if (session.getAttribute("loginUser") != null) {
			session.invalidate();
		}
			

	}

}
