package com.koreait.study.command;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.study.dao.MemberDao;
import com.koreait.study.dto.FindQueryDTO;
import com.koreait.study.dto.Member;

public class FindIdCommand implements MemberCommand {

	@Autowired
	private MemberDao memberDAO;
	
	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String tap = request.getParameter("tap");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email") + request.getParameter("site");
		System.out.println("이메일 : "+email);
	
		FindQueryDTO findQueryDTO = new FindQueryDTO();
		findQueryDTO.setName(name);
		findQueryDTO.setPhone(phone);
		findQueryDTO.setEmail(email);
		findQueryDTO.setTap(tap);
		
		List<Member> listId = memberDAO.findId(findQueryDTO);
		
		System.out.println(listId.toString());
		
		model.addAttribute("findId", listId);
		if (email.isEmpty()) {
			model.addAttribute("name", name);
			model.addAttribute("phone", phone);
		} else {
			model.addAttribute("email", email);
		}
			
			
		
	}

}
