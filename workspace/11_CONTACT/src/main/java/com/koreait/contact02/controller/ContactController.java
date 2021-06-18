package com.koreait.contact02.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.contact02.command.DeleteContactCommand;
import com.koreait.contact02.command.InsertContactCommand;
import com.koreait.contact02.command.SelectContactListCommand;
import com.koreait.contact02.command.SelectContactViewCommand;
import com.koreait.contact02.command.UpdateContactCommand;
import com.koreait.contact02.config.BeanConfiguration;
import com.koreait.contact02.dto.Contact;

@Controller
public class ContactController {

	// java로 <Bean>을 생성하면 ↓
	private AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
	
	/* index 이동 */
	@GetMapping(value="/")
	public String index() {
		return "index";
	}
	/* -------- */
	
	/* 연락처 목록으로 이동 */
	@GetMapping(value="contactList.do")
	public String contactList(Model model) {
		
		SelectContactListCommand listCommand = ctx.getBean("listCommand", SelectContactListCommand.class);
		listCommand.execute(model);
		
		return "contact/list";
	}
	
	/* 연락처 조회로 이동 */
	@GetMapping(value="contactView.do")
	public String contactView(@RequestParam("no") long no, Model model) {
		// 받아온 파라미터를 Command에 전달하기 위해 model에 저장
		model.addAttribute("no", no);
		
		SelectContactViewCommand viewCommand = ctx.getBean("viewCommand", SelectContactViewCommand.class);
		viewCommand.execute(model);
		
		return "contact/view";
	}
	
	/* 연락처 수정 : redirect */
	@PostMapping(value="updateContact.do")
	public String updateContact(Contact contact, Model model) {
		// model에 contact 담기
		model.addAttribute("contact", contact);
		// Command 호출
		UpdateContactCommand updateCommand = ctx.getBean("updateCommand", UpdateContactCommand.class);
		updateCommand.execute(model);
		// 연락처 조회에 필요한 파라미터 : 연락처의 idx번호
		long no = contact.getNo();
		
		return "redirect:contactView.do?no="+no;
	}
	
	/* 연락처 삭제 : redirect */
	@GetMapping(value="deleteContact.do")
	public String deleteContact(@RequestParam("no") long no, Model model) {
		// model에 파라미터 저장
		model.addAttribute("no", no);
		// <bean>으로 처리한 Command실행
		DeleteContactCommand deleteCommand = ctx.getBean("deleteCommand", DeleteContactCommand.class);
		deleteCommand.execute(model);
		
		return "redirect:contactList.do";
	}
	
	
	/* insertPage로 이동 */
	@GetMapping(value="insertContactPage.do")
	public String insertPage() {
		return "contact/insert";
	}
	
	/* 연락처 추가 : redirect */
	@PostMapping(value="insertContact.do")
	public String insertContact(Contact contact, Model model) {
		// model에 파라미터 저장
		model.addAttribute("contact", contact);
		// <bean>처리한 Command 실행
		InsertContactCommand insertCommand = ctx.getBean("insertCommand", InsertContactCommand.class);
		insertCommand.execute(model);
		
		return "redirect:contactList.do";
	}
	
	
	
	
	
	
	
}
