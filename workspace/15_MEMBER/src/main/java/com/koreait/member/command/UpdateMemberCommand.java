package com.koreait.member.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.member.dao.MemberDAO;
import com.koreait.member.dto.Member;

public class UpdateMemberCommand implements MemberCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		long no = Long.parseLong(request.getParameter("no"));
		
		Member member = new Member();
		member.setNo(no);
		member.setName(SecurityUtils.xxs(name)); // 이름에 특수문자가 실행되는 일이 없도록 진행 (특수문자를 문자열로 바꿔준다)
		member.setEmail(email);
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		int count = memberDAO.updateMember(member);
		
		// 수정한 회원정보를 session의 loginUser정보도 업데이트해준다.
		if (count > 0) {
			HttpSession session = request.getSession();
			Member loginUser = (Member)session.getAttribute("loginUser");
			loginUser.setName(name);
			loginUser.setEmail(email);
		}

	}

}
