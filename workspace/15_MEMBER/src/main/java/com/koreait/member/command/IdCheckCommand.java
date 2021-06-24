package com.koreait.member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.member.dao.MemberDAO;

public class IdCheckCommand {
	// 해당 Command는 "인터페이스"를 상속하지 않는다.
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
