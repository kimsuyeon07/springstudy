package com.koreait.contact02.command;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.contact02.dao.ContactDAO;
import com.koreait.contact02.dto.Contact;

public class UpdateContactCommand implements ContactCommand {

	// <Bean>으로 생성해둔 DAO 호출
	@Autowired
	private ContactDAO contactDAO;
	
	@Override
	public void execute(Model model) {
		// Controller에서 받아온 model을 map으로 변환
		Map<String , Object> map = model.asMap();
		// Controller에서 받아온 model의 타입 : Contact
		Contact contact = (Contact)map.get("contact");
		// DAO 실행
		int result = contactDAO.updateContact(contact);
		// result값 model에 저장
		model.addAttribute("updateResult", result);

	}

}
