package com.koreait.contact01.controller;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.contact01.command.DeleteContactCommand;
import com.koreait.contact01.command.InsertContactCommand;
import com.koreait.contact01.command.SelectContactByNoCommand;
import com.koreait.contact01.command.SelectContactListCommand;
import com.koreait.contact01.command.UpdateContactCommand;
import com.koreait.contact01.dto.Contact;

@Controller
public class ContactController {

	// field
	private SelectContactListCommand selectContactListCommand;
	private InsertContactCommand insertContactCommand;
	private SelectContactByNoCommand selectContactByNoCommand;
	private UpdateContactCommand updateContactCommand;
	private DeleteContactCommand deleteContactCommand;
	
	// Command를 부르기 위한 작업
	@Autowired
	public void setCommand(SelectContactListCommand selectContactListCommand,
						   InsertContactCommand insertContactCommand,
						   SelectContactByNoCommand selectContactByNoCommand,
						   UpdateContactCommand updateContactCommand,
						   DeleteContactCommand deleteContactCommand) {
		this.selectContactListCommand = selectContactListCommand;
		this.insertContactCommand = insertContactCommand;
		this.selectContactByNoCommand = selectContactByNoCommand;
		this.updateContactCommand = updateContactCommand;
		this.deleteContactCommand = deleteContactCommand;
	}
	
	/* index */
	@GetMapping(value="/")
	public String index() {
		return "index";
	}
	/* ---- */
	
	
	/* --------------------------- */
	// 페이지 이동
	/* 연락처 추가 페이지 */
	@GetMapping(value="insertContactPage.do")
	public String insertContactPage() {
		return "contact/insert";
	}
	/* --------------------------- */


	/* 연락처 목록 반환 */
	@GetMapping(value="selectContactList.do") 
	public String selectContactList(Model model) {
		selectContactListCommand.execute(model);
		return "contact/list";
	}
	
	/* 연락처 등록 */
	@PostMapping(value="insertContact.do")
	public void insertContact(Contact contact, HttpServletResponse response ,Model model) {
		
		model.addAttribute("contact", contact);
		model.addAttribute("response", response);
		insertContactCommand.execute(model);
		
	}

	/* 연락처 조회 */
	@GetMapping(value="selectContactView.do")
	public String selectContactViewPage(@RequestParam("no") long no, Model model) {
		
		model.addAttribute("no", no);
		selectContactByNoCommand.execute(model);
		
		return "contact/view";
	}
	
	/* 연락처 수정 */
	@PostMapping(value="updateContact.do")
	public String updateContact(Contact contact, Model model) {
		
		long no = contact.getNo();
		model.addAttribute("contact", contact);
		
		updateContactCommand.execute(model);
		
		
		return "redirect:selectContactView.do?no="+no;
	}

	/* 연락처 삭제 */
	@GetMapping(value="deleteContact.do")
	public String deleteContact(@RequestParam("no") long no, Model model) {
		model.addAttribute("no", no);
		deleteContactCommand.execute(model);
		
		return "redirect:selectContactList.do";
	}




















}
