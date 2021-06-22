package com.koreait.contact03.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.contact03.command.DeleteContactCommand;
import com.koreait.contact03.command.InsertContactCommand;
import com.koreait.contact03.command.SelectContactListCommand;
import com.koreait.contact03.command.SelectContactViewCommand;
import com.koreait.contact03.command.UpdateContactCommand;

@Controller
public class ContactController {

	// field
	private SqlSession sqlSession;
	private SelectContactListCommand selectContactListCommand;
	private InsertContactCommand insertContactCommand;
	private SelectContactViewCommand selectContactViewCommand;
	private UpdateContactCommand updateContactCommand;
	private DeleteContactCommand deleteContactCommand;
	
	// construction
	@Autowired
	public void setCommand(SqlSession sqlSession,
						   SelectContactListCommand selectContactListCommand,
						   InsertContactCommand insertContactCommand,
						   SelectContactViewCommand selectContactViewCommand,
						   UpdateContactCommand updateContactCommand,
						   DeleteContactCommand deleteContactCommand) {
		this.sqlSession = sqlSession;
		this.selectContactListCommand = selectContactListCommand;
		this.insertContactCommand = insertContactCommand;
		this.selectContactViewCommand = selectContactViewCommand;
		this.updateContactCommand = updateContactCommand;
		this.deleteContactCommand = deleteContactCommand;
	}
	
	/* ----------------------------------------------- */
	
	@GetMapping(value="/")
	public String index() {
		return "index";
	}
	
	/* 연락처 조회 */
	@GetMapping(value="contactList.do")
	public String selectList(Model model) {
		
		// Command 호출 
		selectContactListCommand.execute(sqlSession, model);
		
		return "contact/list";
	}
	
	/* insertPage */
	@GetMapping(value="insertContactPage.do")
	public String insertContactPage() {
		return "contact/insert";
	}
	/* 연락처 추가 */
	@PostMapping(value="insertContact.do")
	public String insertConatct(HttpServletRequest request, Model model) {
		// 전달받은 파라미터를 그대로 model에 저장  >> Command에 보내줄 것
		model.addAttribute("request", request);
		// Command 호출
		insertContactCommand.execute(sqlSession, model);
		
		return "redirect:contactList.do";
	}
	
	/* 연락처 조회 */
	@GetMapping(value="contactView.do")
	public String selectContactView(@RequestParam("no") long no, Model model) {
		// 전달 받은 파라미터를 model에 저장 >> Command에 보내줄 것
		model.addAttribute("no", no);
		// Command 호출
		selectContactViewCommand.execute(sqlSession, model);
		return "contact/view";
	}
	
	/* 연락처 수정 */
	@PostMapping(value="updateContact.do")
	public String updateContact(HttpServletRequest request, Model model) {
		// no을 알아내기
		long no = Long.parseLong(request.getParameter("no"));
		// 전달 받은 request를 그대로 model에 저장 >> Command로 보낸다.
		model.addAttribute("request", request);
		// Command 호출
		updateContactCommand.execute(sqlSession, model);
		
		return "redirect:contactView.do?no="+no;
	}
	
	/* 연락처 삭제 */
	@GetMapping(value="deleteContact.do")
	public String deleteContact(@RequestParam("no") long no, Model model) {
		model.addAttribute("no", no);
		deleteContactCommand.execute(sqlSession, model);
		return "redirect:contactList.do";
	}
	
}
