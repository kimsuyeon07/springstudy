package com.koreait.member.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.member.dao.MemberDAO;
import com.koreait.member.dto.Member;
import com.koreait.member.utill.SecurityUtils;

public class LoginCommand implements MemberCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// model >> map
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// 비밀번호암호화
		String encodePW = SecurityUtils.encodeBase64(pw);
		// DTO
		Member member = new Member();
		member.setId(id);
		member.setPw(encodePW);
		
		// DAO
		// login정보 담아주기
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		Member loginUser = memberDAO.login(member);
		if (loginUser != null) {
			// session에 담아서 페이지창을 닫기 전까지 내용을 유지한다.
			request.getSession().setAttribute("loginUser", loginUser);
		}
		

		
		
		
	}

}
