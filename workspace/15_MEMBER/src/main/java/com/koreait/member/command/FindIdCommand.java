package com.koreait.member.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.member.dao.MemberDAO;
import com.koreait.member.dto.Member;

public class FindIdCommand implements MemberCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String , Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String email = request.getParameter("email");
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		Member findUser = memberDAO.findId(email);
		
		// 아이디 찾기 결과페이지로 넘어갈 예정
		if (findUser != null) {
			model.addAttribute("findUser", findUser); // 검색결과를 표시할 JSP로 전달하기 위해서
		}
			

	}

}
