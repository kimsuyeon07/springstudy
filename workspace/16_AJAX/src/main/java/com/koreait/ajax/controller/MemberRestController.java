package com.koreait.ajax.controller;


import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.ajax.command.DeleteMemberCommand;
import com.koreait.ajax.command.InsertMemberCommand;
import com.koreait.ajax.command.SelectMemberListCommand;
import com.koreait.ajax.command.SelectMemberViewCommand;
import com.koreait.ajax.command.UpdateMemberCommand;
import com.koreait.ajax.dto.Member;
import com.koreait.ajax.dto.Page;

/*
		회원 RESTful 처리
		
		1. RESTful : URI + HTTP Method
		2. CRUD
			→			URI(경로)			HTTP Method			기존 URI
			1) 목록		member			GET					selectMemberList.do
			2) 보기		member/{no}		GET					selectMemberByNo.do?no=1
			3) 삽입		member			POST				insertMember.do
			4) 수정		member			PUT					updateMember.do
			5) 삭제		member/{no}		DELETE				deleteMember.do?no=1
			
		 ** URI ==> URL의 폭 넚은 의미로 사용한다. **
*/

/*
		@RestController
	
		1. 모든 메소드 값을 @ResponseBody 처리한다.  
		2. 해당 파일의 모든 메서드들은 @ResponseBody 애너테이션을 추가하지 않아도 ajax 처리가 된다.
*/

@RestController 

public class MemberRestController {

	
	// field
	private SqlSession sqlSession;
	private InsertMemberCommand insertMemberCommand;
	private SelectMemberListCommand selectMemberListCommand;
	private SelectMemberViewCommand selectMemberViewCommand;
	private UpdateMemberCommand updateMemberCommand;
	private DeleteMemberCommand deleteMemberCommand;
	
	// constructor : Bean으로 생성한 sqlSession, Command를 불러온다.
	@Autowired
	public MemberRestController(SqlSession sqlSession,
								InsertMemberCommand insertMemberCommand,
								SelectMemberListCommand selectMemberListCommand,
								SelectMemberViewCommand selectMemberViewCommand,
								UpdateMemberCommand updateMemberCommand,
								DeleteMemberCommand deleteMemberCommand) {
		this.sqlSession = sqlSession;
		this.insertMemberCommand = insertMemberCommand;
		this.selectMemberListCommand = selectMemberListCommand;
		this.selectMemberViewCommand = selectMemberViewCommand;
		this.updateMemberCommand = updateMemberCommand;
		this.deleteMemberCommand = deleteMemberCommand;
	}
	
	
	/* Method */
	
	@GetMapping(value="member", produces="application/json; charset=UTF-8")
	// 메인메서드 실행 전, @RestController 를 선언해서  >> @ResponseBody 선언하지 않아도 된다.
	public Map<String, Object> selectMemberList(@RequestParam("page") int page, Model model) {
		model.addAttribute("page", page);
		return selectMemberListCommand.execute(sqlSession, model);
	}
	
	/* value="member/{no}"  ,   @PathVariable("no") long no  */
	@GetMapping(value="member/{no}", produces="application/json; charset=UTF-8")
	// 경로에 포함되어 있는 변수를 뺄 때, [ @PathVariable("no") long no ] 
	public Map<String, Object> selectMemberByNo(@PathVariable("no") long no, Model model) {
		model.addAttribute("no", no);
		return selectMemberViewCommand.execute(sqlSession, model);
	}
	
	@PostMapping(value="member", produces="application/json; charset=UTF-8")
	public Map<String, Object> insertMember(@RequestBody Member member, Model model) {
		model.addAttribute("member", member);
		return insertMemberCommand.execute(sqlSession, model);
	}
	
	@PutMapping(value="member", produces="application/json; charset=UTF-8")
	public Map<String, Object> updateMember(@RequestBody Member member, Model model) {
		model.addAttribute("member", member);
		return updateMemberCommand.execute(sqlSession, model);
	}
	
	
	@DeleteMapping(value="member/{no}", produces="application/json; charset=UTF-8")
	public Map<String, Object> deleteMember(@PathVariable("no") long no, Model model) {
		model.addAttribute("no", no);
		return deleteMemberCommand.execute(sqlSession, model);
	}
	
	
}
