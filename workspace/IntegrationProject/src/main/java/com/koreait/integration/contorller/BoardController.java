package com.koreait.integration.contorller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.integration.domain.Board;
import com.koreait.integration.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	// field
	private BoardService boardService;  // 인터페이스.클래스는 (new)가 되지 않는다. 
	
	
	
	/* -------- 기본값 (index) --------- */
	@GetMapping(value= {"/", "index.do"})
	public String index() {
		return "index";
	}
	/* ------------------------------ */
	
	
	// 게시글 목록 반환
	@GetMapping(value="selectAll.do", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> selectAll() {
		
		/* Service(DAO) 실행_ 목록 반환  */ 
		List<Board> list = boardService.totalList();
		
		/* AJAX로 전달해줄 JSON데이터 화 "Map"으로 만들어 준다. */
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (list.size() > 0) {
			resultMap.put("status", 200);
			resultMap.put("list", list);			
			resultMap.put("message", "게시글 목록을 가져왔습니다.");
		} else {
			resultMap.put("status", 500);
			resultMap.put("list", null);
			resultMap.put("message", "게시글 목록이 없습니다.");
		}
		
		/* ------------- */
		return resultMap;
		/* ------------- */

	}
	
	

	// 검색한 게시글 목록 반환
	@GetMapping(value="selectQuery.do", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> selectQuery(HttpServletRequest request) {
		
		/* 파라미터 처리 */
		Map<String, String> map = new HashMap<>();
		map.put("column", request.getParameter("column"));
		map.put("query", request.getParameter("query"));
		
		/* Service(DAO) 실행_ 목록 반환  */ 
		List<Board> list = boardService.searchList(map);
		
		/* AJAX로 전달해줄 JSON데이터 화 "Map"으로 만들어 준다. */
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (list.size() > 0) {
			resultMap.put("status", 200);
			resultMap.put("list", list);			
			resultMap.put("message", "검색 결과 목록을 가져왔습니다.");
		} else {
			resultMap.put("status", 500);
			resultMap.put("list", null);
			resultMap.put("message", "검색 결과가 없습니다.");
		}
		/* ------------- */
		return resultMap;
		/* ------------- */
	}
	
	
	
	
	
}
