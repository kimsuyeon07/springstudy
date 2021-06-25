package com.koreait.member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.member.dto.Member;
import com.koreait.member.utill.SecurityUtils;

public class PresonPwCheckCommand{

	public Map<String, Boolean> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map =model.asMap();
		Member member = (Member)map.get("member");
		HttpSession session = (HttpSession)map.get("session");
		// <암호화가 되어있지 않은>member.getPw() == <암호화가 되어있는>((Member)session.getAttribute("loginUser")).getPw()
		String pw1 = ((Member)session.getAttribute("loginUser")).getPw();
		String pw2 = SecurityUtils.encodeBase64(member.getPw());
		
		Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
		resultMap.put("isCorrect", pw1.equals(pw2));
		
		return resultMap;
		

	}

}
