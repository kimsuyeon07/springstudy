package com.koreait.contact02.command;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.contact02.dao.ContactDAO;

public class DeleteContactCommand implements ContactCommand {

	// <bean>으로 처리한 DAO 호출
	@Autowired
	private ContactDAO contactDAO;
	
	@Override
	public void execute(Model model) {
		// Controller에서 받아온 model을 map으로 변환
		Map<String , Object> map = model.asMap();
		long no = (long)map.get("no");
		// DAO실행
		int result = contactDAO.deleteContact(no);
		// 결과값 model에 전달
		model.addAttribute("deleteResult", result);
	}

}
