package com.koreait.contact02.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.contact02.dao.ContactDAO;
import com.koreait.contact02.dto.Contact;

public class SelectContactListCommand implements ContactCommand {

	// <Bean>으로 생성한 DAO 호출
	@Autowired
	private ContactDAO contactDAO;
	
	@Override
	public void execute(Model model) {
		
		// DAO => selectContactList()의 결과 값 : List<Contact>
		List<Contact> list = contactDAO.selectContactList();
		
		// Controller에게 보내줄 list 작업
		model.addAttribute("list", list);
	}

}
