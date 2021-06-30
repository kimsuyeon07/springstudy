package com.koreait.search.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.search.command.AutoCompleteCommand;
import com.koreait.search.command.SearchAllCommand;
import com.koreait.search.command.SearchQueryCommand;
import com.koreait.search.dto.Employees;
import com.koreait.search.dto.QueryDTO;

@Controller
public class SearchController {

	// field
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	private SqlSession sqlSession;
	private SearchAllCommand searchAllCommand;
	private AutoCompleteCommand autoCompleteCommand;
	private SearchQueryCommand searchQueryCommand;
	
	// constructor
	@Autowired
	public SearchController(SqlSession sqlSession,
							SearchAllCommand searchAllCommand,
							AutoCompleteCommand autoCompleteCommand,
							SearchQueryCommand searchQueryCommand) {
		super();
		this.sqlSession = sqlSession;
		this.searchAllCommand = searchAllCommand;
		this.autoCompleteCommand = autoCompleteCommand;
		this.searchQueryCommand = searchQueryCommand;
	}
	
	// 기본값_ 프로젝트를
	@GetMapping(value={ "/", "index.do"})
	public String index() {
		logger.info("call index()");
		return "index";
	}
	
	/* --------------------------------------------------- */
	
	@GetMapping(value="searchAll.do")
	public String searchAll(HttpServletRequest request, Model model) {
		// HttpServletRequest request >> ↓ 생성한 이유 ↓
		// : page번호를 전달하기 위해서 사용 (page를 클릭했을 때, 파라미터로 해당 페이지번호를 받아온다)
		model.addAttribute("request", request);
		// Command 호출
		searchAllCommand.execute(sqlSession, model);
		// model에 저장되어있는 값 : list(회원목록), paging(이전    1 2 3 4 5   다음)
		/* ----------- */
		return "list"; // SearchAllCommand가 model에 저장한 정보를 가지고 포워딩으로 list.jsp로 이동한다.
		/* ----------- */
	}
	
	@PostMapping(value="autoComplete.do", produces="application/json; charset=UTF-8")
	@ResponseBody
	public void autoComplete(@RequestBody QueryDTO queryDTO,
							 HttpServletResponse response, 
							 Model model) {
		logger.info(queryDTO.toString());
		model.addAttribute("queryDTO", queryDTO);
		model.addAttribute("response", response);
		
		autoCompleteCommand.execute(sqlSession, model);
		// ↓ Command에서 응답받아온 정보 : resultMap = { status: 500 OR 200 , list: list OR null }
	}
	
	@GetMapping(value="search.do")
	public String search(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		searchQueryCommand.execute(sqlSession, model);
		return "list";
	}
	
	
}
