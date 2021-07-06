package com.koreait.apptest.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.apptest.dao.MemberDAO;

public class IdCheckCommand {

	public Map<String, Integer> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String id = request.getParameter("id");
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		
		Map<String, Integer> resultMap = new HashMap<>();
		resultMap.put("count", memberDAO.idCheck(id));
		return resultMap;
		
	}

}
