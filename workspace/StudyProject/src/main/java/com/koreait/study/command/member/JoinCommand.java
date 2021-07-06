package com.koreait.study.command.member;

import java.io.IOException;
import java.util.HashMap;
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

public class JoinCommand implements MemberCommand {
	
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
		member.setName(request.getParameter("name"));
		member.setEmail(request.getParameter("email")+request.getParameter("site"));
		member.setPhone(request.getParameter("phone"));
		member.setAddress(request.getParameter("address"));
		// 비밀번호는 암호화한 뒤 DTO에 저장한다.
		String pw = request.getParameter("pw");
		member.setPw(SecurityUtils.endcodeBase64(pw));
		
		int result = memberDAO.join(member);
		System.out.println(result);
		
		// 응답 (Response)
		response.setContentType("text/html; charset=utf-8");
		try {
			if (result > 0) {
				response.getWriter().println("<script>");
				response.getWriter().println("alert('회원가입이 완료되었습니다.')");
				response.getWriter().println("location.href='loginPage.do'");
				response.getWriter().println("</script>");
				
			} else {
				response.getWriter().println("<script>");
				response.getWriter().println("alert('회원가입에 실패했습니다.')");
				response.getWriter().println("history.back()");
				response.getWriter().println("</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
