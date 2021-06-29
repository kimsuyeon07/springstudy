package com.koreait.ajax.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.ajax.dao.MemberDAO;
import com.koreait.ajax.dto.Member;

public class SelectMemberViewCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		long no = (long)map.get("no");
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		Member member = memberDAO.selectMemberByNo(no);
		
		// '리턴' 해 줄 map(resultMap)을 만들어 준다.
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("member", member);
		resultMap.put("exists", member != null);
		// System.out.println(resultMap);
		
		/* ------------- */
		return resultMap;
		/* ------------- */
		
	}

}
