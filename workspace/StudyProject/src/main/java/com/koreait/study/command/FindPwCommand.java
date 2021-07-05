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
import com.koreait.study.util.SecurityUtils;

public class FindPwCommand implements MemberCommand {

	@Autowired
	private MemberDao memberDAO;
	
	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String id = request.getParameter("id");
		String email = request.getParameter("email") + request.getParameter("site");
	
		FindQueryDTO findQueryDTO = new FindQueryDTO();
		findQueryDTO.setId(id);
		findQueryDTO.setEmail(email);
		
		Member member = memberDAO.findPw(findQueryDTO);
		System.out.println("member: "+member);
		
		if(member != null) {
			model.addAttribute("findPw", SecurityUtils.decodeBase64(member.getPw()));
			model.addAttribute("email", email);
		}
			
		
		
	}
}
