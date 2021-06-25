package com.koreait.member.command;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.member.dao.MemberDAO;
import com.koreait.member.dto.Member;

public class JoinCommand implements MemberCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// model >> map
		Map<String , Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		
		/* pw의 암호화 */ 
		String encodedPW = SecurityUtils.encodeBase64(pw);
		/* name의 xxs처리 */
		String xxsName = SecurityUtils.xxs(name);
		// DTO
		Member member = new Member();
		member.setId(id);
		member.setPw(encodedPW);
		member.setName(xxsName);
		member.setEmail(email);
		
		// DAO
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		memberDAO.join(member);
		
		
	}

}
