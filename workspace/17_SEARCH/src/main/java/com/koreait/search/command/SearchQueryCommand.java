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
import com.koreait.search.dto.QueryDTO;
import com.koreait.search.util.PagingUtils;

public class SearchQueryCommand implements SearchCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		// Controller에서 받아온 파라미터 없음
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		/* 현재"page"를 전달받는 방법 [ Optional<String> ] */
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// 전달받은 검색에 필요한 파라미터
		String column = request.getParameter("column");
		String query = request.getParameter("query");
		String top = request.getParameter("top");
		String bottom = request.getParameter("bottom");
		
		// QueryDTO에 저장하기.
		QueryDTO queryDTO = new QueryDTO();
		queryDTO.setColumn(column);
		queryDTO.setQuery(query);
		queryDTO.setTop(top);
		queryDTO.setBottom(bottom);
		
		// DAO 호출
		SearchDAO searchDAO = sqlSession.getMapper(SearchDAO.class);
		// 검색결과 사원 수 호출
		int getSearchRecord = searchDAO.getSearchRecord(queryDTO);
		System.out.println(getSearchRecord);
		// Page 호출
		PageDTO pageDTO = PagingUtils.getPage(page, getSearchRecord);
		System.out.println(pageDTO.toString());
		// QueryDTO에 [ (pagingDTO)의 beginRecord, endRecord ] 저장  
		queryDTO.setBeginRecord(pageDTO.getBeginRecord());
		queryDTO.setEndRecord(pageDTO.getEndRecord());
		
		// 이전   1 2 3 4 5  다음  (생성)
		String paging = null;
		if(column.equals("SALARY")) {
			paging = PagingUtils.getPaging( "search.do?column=" + column + "&top" + top + "&bottom=" + bottom  , page );
		} else {
			paging = PagingUtils.getPaging( "search.do?column=" + column + "&query=" + query  , page );
											// 검색결과에 page처리할 때는 파라미터로 검색에 사용한 파라미터를 계속 넘겨줘야 한다.
		}
		
		// 검색결과 사원 목록 실행
		List<Employees> list = searchDAO.search(queryDTO);
		System.out.println(list.toString());
		
		// Controller에 전달할 model에 삽입한다.
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
				
		

	}

}
