package com.koreait.study.command;

import java.util.HashMap;
import java.util.Map;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.koreait.study.util.SecurityUtils;

@Service
public class EmailAuthCodeCommand {

	@Autowired
	private JavaMailSender mailSender;
	
	public Map<String, String> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String email = request.getParameter("email") + request.getParameter("site");
		System.out.println(email);
		
		String authCode = null;
		MimeMessage message = mailSender.createMimeMessage();
		try {
			// 보내는 사람
			message.setHeader("Content-Type", "text/plain; charset=UTF-8");
			// 받는 사람
			message.setFrom(new InternetAddress("kimsuyeon7598@gmail.com", "관리자"));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject("인증 요청 메일입니다.");
			authCode = SecurityUtils.getAuthCode(15); // 15자리 인증코드
			message.setText("인증번호는 " + authCode + " 입니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 메일 보내기
		mailSender.send(message);
		
		
		// join.jsp로 다시 돌아 갈 때, "authCode"를 가지고 가야 ( 인증번호 확인이 가능하다 )
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("authCode", authCode);
		
		return resultMap;
		
	}
	
}
