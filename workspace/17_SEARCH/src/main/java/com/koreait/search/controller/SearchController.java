package com.koreait.search.controller;


import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {

	// field
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	private SqlSession sqlSession;
	
	// constructor
	@Autowired
	public SearchController(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}
	
	// 기본값_ 프로젝트를
	@GetMapping(value={ "/", "index.do"})
	public String index() {
		logger.info("call index()");
		return "index";
	}
	
	
}
