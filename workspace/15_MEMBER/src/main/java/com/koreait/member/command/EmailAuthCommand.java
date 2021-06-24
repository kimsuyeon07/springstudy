package com.koreait.member.command;

import java.util.HashMap;
import java.util.Map;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;

public class EmailAuthCommand{

	@Autowired
	private JavaMailSender mailSender;
	// JavaMailSender : 인터페이스 (org.springframework.mail.javamail.JavaMailSender)
	public Map<String, String> execute(SqlSession sqlSession, Model model) {

		// model >> map
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		// 인증번호를 받는 이메일주소
		String email = request.getParameter("email");
		
		// 랜덤으로 6자리의 코드 보내기
		char[] characters = { 
				'A','B','C','D','E',
				'F','G','H','I','J',
				'K','L','M','N','O',
				'P','Q','R','S','T',
				'U','V','W','X','Y',
				'Z','0','1','2','3',
				'4','5','6','7','8',
				'9','!','$','?', '&' 
		};
		String authCode = "";
		for(int i = 0; i < 6; i++) {
			authCode += characters[(int)(Math.random()*characters.length)];
		}
		
		// MimeMessage 클래스
		// 이메일을 작성하는 클래스
		// MimeMessage (javax.mail.internet.MimeMessage)
		MimeMessage message = mailSender.createMimeMessage();
		try {
			message.setHeader("Content-Type", "text/plain; charset=UTF-8");
			message.setFrom(new InternetAddress("kimsuyeon7598@gmail.com", "관리자")); 	// 보내는 사람
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));	// 받는 사람
			message.setSubject("인증 요청 메일입니다.");
			message.setText("인증번호는 " + authCode + "입니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 이메일 보내기
		mailSender.send(message);
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("authCode", authCode);
		
		return resultMap;
	}

}
