package com.koreait.search.command;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.search.dao.SearchDAO;
import com.koreait.search.dto.Employees;
import com.koreait.search.dto.PageDTO;
import com.koreait.search.util.PagingUtils;

public class SearchAllCommand implements SearchCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		// Controller에서 받아온 파라미터 없음
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		/* 현재"page"를 전달받는 방법 [ Optional<String> ] */
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// DAO 호출
		SearchDAO searchDAO = sqlSession.getMapper(SearchDAO.class);
		// 전체 회원 수 호출
		int totalRecord = searchDAO.getTotalRecord();
		System.out.println(totalRecord);
		// Page 호출
		PageDTO pageDTO = PagingUtils.getPage(page, totalRecord);
		System.out.println(pageDTO.toString());
		// 이전   1 2 3 4 5  다음  (생성)
		String paging = PagingUtils.getPaging("searchAll.do", page);
		
		// 전체회원 목록 실행
		List<Employees> list = searchDAO.searchAll(pageDTO);
		System.out.println(list.toString());
		
		// Controller에 전달할 model에 삽입한다.
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		

	}

}
