package com.koreait.ajax.controller;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping; 

/*
		@RestController
		: 모든 메소드의 반환 값을 @ResponseBody 처리한다.
		▼
		따로 메소드에
		@ResponseBody를 써주지 않아도 된다.
		( ※  해당 Controller의 메소드가 "@ResponseBody"처리가 되기 때문에 
		 	따로 Controller를 분리해서 사용해야 한다. )
*/

@Controller
public class MemberController {

	// field
	private SqlSession sqlSession;
	
	// constructor : Bean으로 생성한 sqlSession, Command를 불러온다.
	@Autowired
	public MemberController(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	
	
	// 기본값
	@GetMapping(value= {"/", "index.do"})
	public String index() {
		return "index";
	} 
	
	/* index -> manageMember.jsp 페이지로 이동 */
	@GetMapping(value="manageMember.do")
	public String manageMember() {
		return "member/manageMember";
	}
	
	
	
}
