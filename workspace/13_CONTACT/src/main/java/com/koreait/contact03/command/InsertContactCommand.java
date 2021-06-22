package com.koreait.contact03.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.contact03.dao.ContactDAO;
import com.koreait.contact03.dto.Contact;

public class InsertContactCommand implements ContactCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		// Controller에서 받아온 model의 내용 map으로 빼주기
		Map<String , Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		// 전달받은 "request"데이터를 DTO로 저장
		Contact contact = new Contact();
		contact.setName(request.getParameter("name"));
		contact.setTel(request.getParameter("tel"));
		contact.setAddr(request.getParameter("addr"));
		contact.setEmail(request.getParameter("email"));
		contact.setNote(request.getParameter("note"));
		
		// DAO에 "contact"를 저장한다.
		ContactDAO contactDAO = sqlSession.getMapper(ContactDAO.class);
		int count = contactDAO.insertContact(contact);
		

	}

}
