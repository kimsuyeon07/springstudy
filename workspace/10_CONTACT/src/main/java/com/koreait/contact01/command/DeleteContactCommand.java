package com.koreait.contact01.command;

import java.util.Map;

import org.springframework.ui.Model;

import com.koreait.contact01.dao.ContactDAO;

public class DeleteContactCommand implements ContactCommand {

	@Override
	public void execute(Model model) {
		// model >> map
		Map<String , Object> map = model.asMap();
		long no = (long)map.get("no");
		
		// dao 호출
		int result = ContactDAO.getInstance().deleteContact(no);
		model.addAttribute("deleteResult", result);

	}

}
