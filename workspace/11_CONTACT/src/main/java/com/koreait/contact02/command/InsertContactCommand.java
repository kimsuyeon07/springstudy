package com.koreait.contact02.command;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.contact02.dao.ContactDAO;
import com.koreait.contact02.dto.Contact;

public class InsertContactCommand implements ContactCommand {

	// <bean>으로 처리한 DAO 호출
	@Autowired
	private ContactDAO contactDAO;
	
	@Override
	public void execute(Model model) {
		// Controller에서 받아온 model을 map으로 변환
		Map<String, Object> map = model.asMap();
		// Controller에서 받아온 model의 타입 : Contact
		Contact contact = (Contact)map.get("contact");
		
		// Dao 실행
		int result = contactDAO.insertContact(contact);
		// model에 결과값 넣어주기
		model.addAttribute("insertResult", result);
	}

}
