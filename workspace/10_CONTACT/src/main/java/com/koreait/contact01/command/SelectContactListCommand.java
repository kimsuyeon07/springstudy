package com.koreait.contact01.command;

import java.util.List;

import org.springframework.ui.Model;

import com.koreait.contact01.dao.ContactDAO;
import com.koreait.contact01.dto.Contact;

public class SelectContactListCommand implements ContactCommand {

	@Override
	public void execute(Model model) {

		List<Contact> list = ContactDAO.getInstance().selectContactList();
		model.addAttribute("list", list);
		

	}

}
