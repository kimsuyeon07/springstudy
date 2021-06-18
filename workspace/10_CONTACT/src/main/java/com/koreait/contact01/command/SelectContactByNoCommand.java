package com.koreait.contact01.command;

import java.util.Map;

import org.springframework.ui.Model;

import com.koreait.contact01.dao.ContactDAO;
import com.koreait.contact01.dto.Contact;

public class SelectContactByNoCommand implements ContactCommand {

	@Override
	public void execute(Model model) {
		
		// model >> map
		Map<String, Object> map = model.asMap();
		long no = (long)map.get("no");
		
		// DAO 호출
		Contact contact = ContactDAO.getInstance().selectContactByNo(no);
		model.addAttribute("contact", contact);

	}

}
