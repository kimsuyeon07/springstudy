package com.koreait.ajax.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.ajax.command.InsertMemberCommand;
import com.koreait.ajax.command.SelectMemberListCommand;
import com.koreait.ajax.command.SelectMemberViewCommand;
import com.koreait.ajax.dto.Member;
import com.koreait.ajax.dto.Page;


@RestController
public class MemberRestController {

	// 앞으로 모든 메서드는 @ResponseBody 애너테이션을
	// 추가하지 않아도 ajax 처리가 된다.
	
	// field
	private SqlSession sqlSession;
	private InsertMemberCommand insertMemberCommand;
	private SelectMemberListCommand selectMemberListCommand;
	private SelectMemberViewCommand selectMemberViewCommand;
	
	// constructor : Bean으로 생성한 sqlSession, Command를 불러온다.
	@Autowired
	public MemberRestController(SqlSession sqlSession,
								InsertMemberCommand insertMemberCommand,
								SelectMemberListCommand selectMemberListCommand,
								SelectMemberViewCommand selectMemberViewCommand) {
		this.sqlSession = sqlSession;
		this.insertMemberCommand = insertMemberCommand;
		this.selectMemberListCommand = selectMemberListCommand;
		this.selectMemberViewCommand = selectMemberViewCommand;
	}
	
	// JSON으로 넘겨준다.
	/* 회원 추가 */
	@PostMapping(value="insertMember.do",
				 produces="application/json; charset=UTF-8")
	public Map<String, Object> insertMember(@RequestBody Member member, 
											HttpServletResponse response,
											Model model) {
		model.addAttribute("member", member);
		model.addAttribute("response", response);
		return insertMemberCommand.execute(sqlSession, model);
		// { "result": 1 }이 반환(return)된다.
	}
	
	/* 회원 목록 반환 : 페이징에 의해  */
	@PostMapping(value="selectMemberList.do",
				produces="application/json; charset=UTF-8")
	public Map<String, Object> SelectMemberList(@RequestBody Page page,
												Model model) {
		model.addAttribute("page", page.getPage());
		return selectMemberListCommand.execute(sqlSession, model);
	}
	
	/* 회원 조회 */
	@PostMapping(value="selectMemberView.do", 
				 produces="application/json; charset=UTF-8")
	public Map<String, Object> selectMemberView(@RequestBody Member member, 
												Model model) {
		System.out.println(member.getNo());
		model.addAttribute("no", member.getNo());
		return selectMemberViewCommand.execute(sqlSession, model);
		// resultMap = { member : { no : member.getNo(), id : member.getId(), ... },
		//				 exists : true OR false }
	}
	
	
	
	
	
	
}
