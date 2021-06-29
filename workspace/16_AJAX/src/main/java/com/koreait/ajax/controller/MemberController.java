package com.koreait.ajax.controller;


import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.ajax.command.DeleteMemberCommand;
import com.koreait.ajax.command.InsertMemberCommand;
import com.koreait.ajax.command.SelectMemberListCommand;
import com.koreait.ajax.command.SelectMemberViewCommand;
import com.koreait.ajax.command.UpdateMemberCommand;
import com.koreait.ajax.dto.Member;
import com.koreait.ajax.dto.Page; 

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
	private InsertMemberCommand insertMemberCommand;
	private SelectMemberListCommand selectMemberListCommand;
	private SelectMemberViewCommand selectMemberViewCommand;
	private UpdateMemberCommand updateMemberCommand;
	private DeleteMemberCommand deleteMemberCommand;
	
	
	// constructor : Bean으로 생성한 sqlSession, Command를 불러온다.
	@Autowired
	public MemberController(SqlSession sqlSession, 
							InsertMemberCommand insertMemberCommand,
							SelectMemberListCommand selectMemberListCommand, 
							SelectMemberViewCommand selectMemberViewCommand,
							UpdateMemberCommand updateMemberCommand, 
							DeleteMemberCommand deleteMemberCommand ) {
		super();
		this.sqlSession = sqlSession;
		this.insertMemberCommand = insertMemberCommand;
		this.selectMemberListCommand = selectMemberListCommand;
		this.selectMemberViewCommand = selectMemberViewCommand;
		this.updateMemberCommand = updateMemberCommand;
		this.deleteMemberCommand = deleteMemberCommand;
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

	/* index -> manageMemberRest.jsp 페이지로 이동 */
	@GetMapping(value="manageMemberRest.do")
	public String manageMemberRest() {
		return "member/manageMemberRest";
	}
	
	
	
	// ======================================================
	
	
	// JSON으로 넘겨준다.
		/* 회원 추가 */
		@PostMapping(value="insertMember.do",
					 produces="application/json; charset=UTF-8")
		@ResponseBody
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
		@ResponseBody
		public Map<String, Object> SelectMemberList(@RequestBody Page page,
													Model model) {
			model.addAttribute("page", page.getPage());
			return selectMemberListCommand.execute(sqlSession, model);
		}
		
		/* 회원 조회 */
		@PostMapping(value="selectMemberView.do", 
					 produces="application/json; charset=UTF-8")
		@ResponseBody
		public Map<String, Object> selectMemberView(@RequestBody Member member, 
													Model model) {
			model.addAttribute("no", member.getNo());
			return selectMemberViewCommand.execute(sqlSession, model);
			// resultMap = { member : { no : member.getNo(), id : member.getId(), ... },
			//				 exists : true OR false }
		}
		
		/* 회원 수정 */
		@PostMapping(value="updateMember.do",
					 produces="application/json; charset=UTF-8")
		@ResponseBody
		public Map<String, Object> updateMember(@RequestBody Member member,
								Model model) {
			model.addAttribute("member", member);
			return updateMemberCommand.execute(sqlSession, model);
		}
		
		
		/* 회원 삭제 */
		@PostMapping(value="deleteMember.do",
					 produces="application/json; charset=UTF-8")
		@ResponseBody
		public Map<String, Object> deleteMember(@RequestBody Member member,
												Model model) {
			model.addAttribute("no", member.getNo());
			return deleteMemberCommand.execute(sqlSession, model);
			
		}
	
	
	
}
