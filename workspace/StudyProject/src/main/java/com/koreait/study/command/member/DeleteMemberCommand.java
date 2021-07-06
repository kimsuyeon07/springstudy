package com.koreait.study.command.member;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.study.dao.MemberDao;
import com.koreait.study.dto.Member;

public class DeleteMemberCommand implements MemberCommand {
	
	@Autowired
	private MemberDao memberDAO;

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		// Controller에서 전달받아온 파라미터 처리
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		long no = Long.parseLong(request.getParameter("no"));
		
		// DAO 호출
		int result = memberDAO.deleteMember(no);
		
		response.setContentType("text/html; charset=UTF-8");
		try {
			// 회원탈퇴는 "session"에 저장해둔 [loginUser]정보를 지워야 한다.
			HttpSession session = request.getSession();
			Member loginUser = (Member)session.getAttribute("loginUser");
			if (loginUser != null) {
				session.removeAttribute("loginUser");
			}
			// 응답 : response.getWriter()
			if (result > 0) {
				response.getWriter().println("<script>");
				response.getWriter().println("alert('회원탈퇴를 완료하였습니다.')");
				response.getWriter().println("location.href='index.do'");
				response.getWriter().println("</script>");
			} else {
				response.getWriter().println("<script>");
				response.getWriter().println("alert('회원탈퇴를 실패하였습니다.')");
				response.getWriter().println("history.back()");
				response.getWriter().println("</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		

	}

}
