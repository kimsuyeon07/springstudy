package com.koreait.member.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.member.dao.MemberDAO;
import com.koreait.member.dto.Member;

public class UpdatePwCommand implements MemberCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String pw = request.getParameter("pw");
		long no = Long.parseLong(request.getParameter("no"));
		
		Member member = new Member();
		member.setNo(no);
		member.setPw(SecurityUtils.encodeBase64(pw));

		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		int count = memberDAO.updatePw(member);
		
		if (count > 0) {
			HttpSession session = request.getSession();
			Member loginUser = (Member)session.getAttribute("loginUser");
			if (loginUser != null) {
				loginUser.setPw(SecurityUtils.encodeBase64(pw));
			}				
		}
	}

}
