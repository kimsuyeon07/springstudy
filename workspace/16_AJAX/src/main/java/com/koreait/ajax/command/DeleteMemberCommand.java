package com.koreait.ajax.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.ajax.dao.MemberDAO;

public class DeleteMemberCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		long no = (long)map.get("no");
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		int count = memberDAO.deleteMember(no);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("count", count);
		
		return resultMap;
	}

}
