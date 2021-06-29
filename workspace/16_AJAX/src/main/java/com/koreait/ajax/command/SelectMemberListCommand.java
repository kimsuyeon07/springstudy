package com.koreait.ajax.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.ajax.dao.MemberDAO;
import com.koreait.ajax.dto.Member;
import com.koreait.ajax.dto.Page;
import com.koreait.ajax.utill.PagingUtils;

public class SelectMemberListCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String , Object> map = model.asMap();
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		
		// 현재 페이지 번호
		int page = (int)map.get("page");
		// 전체 회원 수
		int totalRecord = memberDAO.getTotalMemberCount();
		// 페이징 처리에 필요한 모든 변수를 저장한 클래스
		Page paging = PagingUtils.getPage(page, totalRecord);
		// 현재 페이지에 보여줄 회원 목록 
		List<Member> list = memberDAO.selectMemberList(paging);
		
		/* Controller에 전달 할 Map 생성 */
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("list", list);
		resultMap.put("exists", list.size() > 0);
		resultMap.put("paging", paging);
		
		/* -------------- */
		return resultMap;
		/* -------------- */
	}

}
