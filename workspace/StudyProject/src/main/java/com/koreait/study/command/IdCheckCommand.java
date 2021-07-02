package com.koreait.study.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.study.dao.MemberDao;

public class IdCheckCommand {

	// field
	@Autowired
	private MemberDao memberDAO;
	
	public Map<String, Boolean> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		int result = memberDAO.idCheck(request.getParameter("id"));
		
		// AJAX에 전달할 결과값 <Map>으로 생성
		Map<String, Boolean> resultMap = new HashMap<String, Boolean>(); 
		if (result == 0) {
			// 중복되는 아이디가 없으면 : "true"반환
			resultMap.put("result", true);
		} else {
			// 중복되는 아이디가 있으면 : "false"반환
			resultMap.put("result", false);
		}
	
		return resultMap;

	}

}
