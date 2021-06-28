package com.koreait.ajax.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.ajax.dao.MemberDAO;
import com.koreait.ajax.dto.Member;

public class SelectMemberListCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String , Object> map = model.asMap();
		int page = (int)map.get("page");
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		// 전체 회원 수
		int totalRecord = memberDAO.getTotalMemberCount();
		// 현재 페이지에서 보여주고자 하는 회원 정보 수
		int recordPerPage = 5;
		int beginRecord = (page - 1) * recordPerPage + 1;
		int endRecord = beginRecord + recordPerPage - 1;
		endRecord = endRecord > totalRecord ? totalRecord : endRecord ;
		
		Map<String, Integer> pagingMap = new HashMap<String, Integer>();
		pagingMap.put("beginRecord", beginRecord);
		pagingMap.put("endRecord", endRecord);
		
		// 현재 페이지에 보여줄 회원 목록 
		List<Member> list = memberDAO.selectMemberList(pagingMap);
		System.out.println("회원 수 : " + list.size());
		System.out.println(list.toString());
		
		/* Controller에 전달 할 Map 생성 */
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("list", list);
		resultMap.put("exists", list.size() > 0);
		
		/* -------------- */
		return resultMap;
		/* -------------- */
	}

}
