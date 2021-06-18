package com.koreait.contact01.command;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.koreait.contact01.dao.ContactDAO;
import com.koreait.contact01.dto.Contact;

public class InsertContactCommand implements ContactCommand {

	@Override
	public void execute(Model model) {
		
		// model >> map
		Map<String, Object> map = model.asMap();
		Contact contact = (Contact)map.get("contact");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		
		// dao 호출
		int result = ContactDAO.getInstance().insertContact(contact);
		
		// 응답 View
		PrintWriter out = response.getWriter();
		if (result > 0) {
			out.println("<script>");
			out.println("alert('연락처 등록이 완료되었습니다.')");
			out.println("location.href='selectContactList.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('연락처 등록에 실패했습니다.')");
			out.println("location.href='selectContactList.do'");
			out.println("</script>");
			
		}
		
		// model에 결과값 담아주기
		model.addAttribute("insertResult", result);
		

	}

}
