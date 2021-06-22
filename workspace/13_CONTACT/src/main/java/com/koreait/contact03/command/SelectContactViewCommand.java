package com.koreait.contact03.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.contact03.dao.ContactDAO;
import com.koreait.contact03.dto.Contact;

public class SelectContactViewCommand implements ContactCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		// Controller에서 전달받은 model을 map으로 변환
		Map<String , Object> map = model.asMap();
		// DAO를 호출
		ContactDAO contactDAO = sqlSession.getMapper(ContactDAO.class);
		Contact contact = contactDAO.selectContactView((long)map.get("no"));
		// model에 저장 >> Controller에 보내기 위해
		model.addAttribute("contact", contact);

	}

}
