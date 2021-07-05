package com.koreait.integration1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.integration1.domain.SearchBoard;
import com.koreait.integration1.service.SearchBoardService;


@org.springframework.stereotype.Controller
public class SearchBoradController {

	// field
	@Autowired
	private SearchBoardService searchBoardService;


	// INDEX로 이동
	@GetMapping(value= {"/", "index.do"})
	public String index() {
		return "index";
	}
	
	// 게시글 목록 가져오기
	@GetMapping(value="selectAll.do", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> selectList() {
		
		List<SearchBoard> list = searchBoardService.selectAll();
		int totalBoardList = searchBoardService.totalBoardList();
		
		Map<String , Object> resultMap = new HashMap<String, Object>();
		if (list.size() > 0) {
			resultMap.put("list", list);
			resultMap.put("status", 200);
			resultMap.put("message", "전체 " + totalBoardList + "개을 목록을 가져왔습니다.");
		} else {
			resultMap.put("status", 500);
			resultMap.put("list", null);
			resultMap.put("message", "게시글이 없습니다.");
		}
		
		return resultMap;
	}
	
	// 게시글 검색
	@GetMapping(value="search.do", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest request) {
		
		Map<String , String> map = new HashMap<>();
		map.put("column", request.getParameter("column"));
		map.put("query", request.getParameter("query"));
		
		List<SearchBoard> list = searchBoardService.search(map);
		
		Map<String , Object> resultMap = new HashMap<String, Object>();
		if (list.size() > 0) {
			resultMap.put("list", list);
			resultMap.put("status", 200);
			resultMap.put("message", request.getParameter("query") + "결과 입니다.");
		} else {
			resultMap.put("status", 500);
			resultMap.put("list", null);
			resultMap.put("message", request.getParameter("query") + "결과가 없습니다.");
		}
		
		return resultMap;
	}
	
}
