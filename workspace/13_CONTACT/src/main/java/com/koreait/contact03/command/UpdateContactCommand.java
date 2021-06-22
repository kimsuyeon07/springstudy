package com.koreait.contact03.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.contact03.dao.ContactDAO;
import com.koreait.contact03.dto.Contact;

public class UpdateContactCommand implements ContactCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// Controller에서 받아온 model을 map으로 변환
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		// DTO에 삽입
		Contact contact = new Contact();
		contact.setNo(Long.parseLong(request.getParameter("no")));
		contact.setName(request.getParameter("name"));
		contact.setTel(request.getParameter("tel"));
		contact.setAddr(request.getParameter("addr"));
		contact.setEmail(request.getParameter("email"));
		contact.setNote(request.getParameter("note"));
		// DAO 호출
		ContactDAO contactDAO = sqlSession.getMapper(ContactDAO.class);
		int count = contactDAO.updateContact(contact);

	}

}
