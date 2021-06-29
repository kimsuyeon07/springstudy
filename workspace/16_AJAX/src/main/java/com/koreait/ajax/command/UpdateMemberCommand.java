package com.koreait.ajax.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.ajax.dao.MemberDAO;
import com.koreait.ajax.dto.Member;

public class UpdateMemberCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		Member member = (Member)map.get("member");
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		int count = memberDAO.updateMember(member);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("count", count);
		
		return resultMap;
	}

}
