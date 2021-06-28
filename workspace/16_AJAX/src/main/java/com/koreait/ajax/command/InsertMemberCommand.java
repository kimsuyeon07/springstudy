package com.koreait.ajax.command;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.ajax.dao.MemberDAO;
import com.koreait.ajax.dto.Member;

public class InsertMemberCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		HttpServletResponse response = null;
		Map<String , Object> resultMap = null;
		
		try {
			
		Map<String, Object> map = model.asMap();
		Member member = (Member)map.get("member");
		response = (HttpServletResponse)map.get("response");
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		int result = memberDAO.insertMember(member);
		
		resultMap = new HashMap<String, Object>();
		resultMap.put("result", result);
		
		} catch (SQLIntegrityConstraintViolationException e) { 
			// 키 위반 (아이디 중복으로 인한 위반)
			try {
				response.setStatus(1001);
				response.getWriter().println("사용 중인 아이디입니다!");
				// ↑ "response.getWriter()"는 [try문]이 "필수"이다!!
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			
			/*
			 * 		catch문은 
			 * 		$.ajax ({
			 * 			error: ... 
			 * 			↑
			 * 			해당 "error" 부분에서 실행된다.
			 */
				
		}
		
		// resultMap = { "result": 1 } 형태로 전달된다.
		/* -------------- */
		return resultMap;
		/* -------------- */
	}

}
