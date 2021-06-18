package com.koreait.contact01.command;

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
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (result > 0) {
				response.getWriter().println("<script>");
				response.getWriter().println("alert('연락처 등록이 완료되었습니다.')");
				response.getWriter().println("location.href='selectContactList.do'");
				response.getWriter().println("</script>");
			} else {
				response.getWriter().println("<script>");
				response.getWriter().println("alert('연락처 등록에 실패했습니다.')");
				response.getWriter().println("history.back()");
				response.getWriter().println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
