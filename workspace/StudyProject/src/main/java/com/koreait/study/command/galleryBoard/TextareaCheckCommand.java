package com.koreait.study.command.galleryBoard;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public class TextareaCheckCommand {

	 
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String originalContent = URLEncoder.encode(request.getParameter("originalContent"));
		String updateContent = URLEncoder.encode(request.getParameter("updateContent"));
		
		System.out.println("내용확인: " + originalContent + ", " + updateContent);
		
		
		// 응답 resultMap 생성
				Map<String, Object> resultMap = new HashMap<String, Object>();
				if(originalContent == updateContent) {
					resultMap.put("status", 200); 
				} else {
					resultMap.put("status", 500);
				}
				
				return resultMap;
		
		

	}

}
