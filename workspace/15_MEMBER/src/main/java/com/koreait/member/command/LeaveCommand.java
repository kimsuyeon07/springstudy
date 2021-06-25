package com.koreait.member.command;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.member.dao.MemberDAO;
import com.koreait.member.dto.Member;

public class LeaveCommand implements MemberCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession)map.get("session");
		long no = ((Member)session.getAttribute("loginUser")).getNo();
		
		// DAO
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		int count = memberDAO.leave(no);
		
		// session의 loginUser도 초기화해준다.
		if (count > 0) {
			session.invalidate();
		}
		
	}

}
