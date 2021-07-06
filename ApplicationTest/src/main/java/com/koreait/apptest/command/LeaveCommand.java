package com.koreait.apptest.command;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.apptest.dao.MemberDAO;
import com.koreait.apptest.dto.Member;

public class LeaveCommand implements MemberCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession)map.get("session");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		
		long no = ((Member)session.getAttribute("loginUser")).getNo();
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		int result = memberDAO.leaveHome(no);
		
		try {
			response.setContentType("text/html; charset=utf-8");
			if (result > 0) {
				session.invalidate();
				response.getWriter().append("<script>");
				response.getWriter().append("alert('탈퇴 성공');");
				response.getWriter().append("location.href='index.do';");
				response.getWriter().append("</script>");
			} else {
				response.getWriter().append("<script>");
				response.getWriter().append("alert('탈퇴 실패');");
				response.getWriter().append("history.back();");
				response.getWriter().append("</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
