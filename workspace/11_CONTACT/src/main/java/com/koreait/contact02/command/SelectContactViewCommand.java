package com.koreait.contact02.command;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.contact02.dao.ContactDAO;
import com.koreait.contact02.dto.Contact;

public class SelectContactViewCommand implements ContactCommand {

	// <bean>으로 생성 해둔 DAO호출
	@Autowired
	private ContactDAO contactDAO;
	
	@Override
	public void execute(Model model) {
		// Controller에서 받아온 model을 map으로 변환
		Map<String , Object> map = model.asMap();
		long no = (long)map.get("no");
		
		// DAO => selectContactView()의 결과값 : Contact
		Contact contact = contactDAO.selectContactView(no);
		// Controller에 전달할 수 있도록 model에 저장
		model.addAttribute("contact", contact);
	}

}
