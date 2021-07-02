package com.koreait.study.command;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.study.dao.MemberDao;
import com.koreait.study.dto.Member;

public class FindIdCommand implements MemberCommand {

	@Autowired
	private MemberDao memberDAO;
	
	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email") + request.getParameter("site");
		
		Member member = new Member();
		member.setName(name);
		member.setPhone(phone);
		member.setEmail(email);
		
		List<Member> listId = memberDAO.findId(member);
		
		model.addAttribute("findId", listId);
		if (email == "") {
			model.addAttribute("name", name);
			model.addAttribute("phone", phone);
		} else {
			model.addAttribute("email", email);
		}
			
			
		
	}

}
