package com.koreait.study.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.study.dao.MemberDao;
import com.koreait.study.dto.Member;
import com.koreait.study.util.SecurityUtils;

public class LoginCommand implements MemberCommand {
	
	// field로 "MemberDAO" 잡기
	@Autowired
	private MemberDao memberDAO; 

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		
		// DTO에 저장 (DB로 전달할)
		Member member = new Member();
		member.setId(request.getParameter("id"));
		// 비밀번호는 암호화한 뒤 DTO에 저장한다.
		String pw = request.getParameter("pw");
		member.setPw(SecurityUtils.endcodeBase64(pw));
		
		// 로그인한 유저를 저장하고 [Session]에 담는다.
		Member loginUser = memberDAO.login(member);
		System.out.println(loginUser);
		
		// 응답 (Response)
		response.setContentType("text/html; charset=utf-8");
		try {
			if (loginUser != null) {
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", loginUser);
				
				response.getWriter().println("<script>");
				response.getWriter().println("alert('로그인이 되었습니다.')");
				response.getWriter().println("location.href='index.do'");
				response.getWriter().println("</script>");
				
			} else {
				response.getWriter().println("<script>");
				response.getWriter().println("alert('아이디나 비밀번호를 다시 확인하세요.')");
				response.getWriter().println("history.back()");
				response.getWriter().println("</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
