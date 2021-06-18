package com.koreait.contact01.command;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Map;

import org.springframework.ui.Model;

import com.koreait.contact01.dao.ContactDAO;
import com.koreait.contact01.dto.Contact;

public class UpdateContactCommand implements ContactCommand {

	@Override
	public void execute(Model model) {

		// model >> map
		Map<String, Object> map = model.asMap();
		Contact contact = (Contact)map.get("contact");
		
		// DAO
		int result = ContactDAO.getInstance().updateContact(contact);
		model.addAttribute("updateResult", result);

	}

}
