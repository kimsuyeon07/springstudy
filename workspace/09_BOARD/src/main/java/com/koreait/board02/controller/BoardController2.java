package com.koreait.board02.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.board02.command.DeleteBoardCommand;
import com.koreait.board02.command.InsertBoardCommand;
import com.koreait.board02.command.SelectBoardListCommand;
import com.koreait.board02.command.SelectBoardViewCommand;
import com.koreait.board02.command.UpdateBoardCommand;
import com.koreait.board02.config.BeanConfiguration;
import com.koreait.board02.dto.Board;

@Controller
public class BoardController2 {


	// field
	private AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
	
	
	
	/*==========================================================*/
	/* [필수]index.jsp 이동 */ 
	@GetMapping(value="/")
	public String index() {
		return "index"; // index.jsp (forward)
	}
	/*==========================================================*/
	
	/* 페이지 이동 */
	@PostMapping("updateBoardPage.do") 
	public String updateBoardPage(Board board, Model model) { 
		// 페이지로 이동할 때, 파라미터도 같이 넘겨주자 : no, title, content
		model.addAttribute("board", board);
		return "board/update"; // board/update.jsp로 포워드
	}
	@GetMapping(value="insertBoardPage.do")
	public String insertBoardPage() {
		return "board/insert";
	}
	
	
	/* ----------------------------------- */
	
	// 1. 게시글 목록 반환
	@GetMapping(value="selectBoardList.do")
	public String selectBoardList(Model model) {
		SelectBoardListCommand selectBoardListCommand = ctx.getBean("listCommand", SelectBoardListCommand.class);
		selectBoardListCommand.execute(model);
		
		return "board/list"; // board/list.jsp로 포워드 (model.addAttribute 처리한 속성이 넘어감)
	}
	
	// 2. 게시글 내용 보기
	@GetMapping(value="selectBoardByNo.do")
	public String selectByNo(@RequestParam("no") long no, Model model) {
		model.addAttribute("no", no); // SelectBoardViewCommand에게 "no"를 넘겨주기 위해서 model을 사용한다.
		SelectBoardViewCommand selectBoardViewCommand = ctx.getBean("viewCommand", SelectBoardViewCommand.class);
		selectBoardViewCommand.execute(model);
		return "board/view"; // board/view.jsp로 포워드 (selectBoardViewCommand가 model에 저장한 board 가지고 이동)
	}
	
	// 3. 게시글 수정하기
	@PostMapping(value="updateBoard.do")
	public String updateBoard(HttpServletRequest request, // update.jsp에서 전달한 파라미터를 
							  Model model) {  
		model.addAttribute("req", request); // updateBoardCommand에게 request를 전달하기 위해서
		UpdateBoardCommand updateBoardCommand = ctx.getBean("updateCommand", UpdateBoardCommand.class);
		updateBoardCommand.execute(model);
		return "redirect:selectBoardByNo.do"; // update ==> "redirect"를 적용해줘야 한다.
	}
	
	// 4. 게시글 삭제
	@GetMapping(value="deleteBoard.do")
	public String deleteBoard(@RequestParam("no") long no, Model model) {
		model.addAttribute("no", no);
		DeleteBoardCommand deleteBoardCommand = ctx.getBean("deleteCommand", DeleteBoardCommand.class);
		deleteBoardCommand.execute(model);
		return "redirect:selectBoardList.do";
	}
	
	// 5. 게시글 추가
	@GetMapping(value="insertBoard.do") 
	public String insertBoard(Board board, Model model) {
		model.addAttribute("board", board);
		InsertBoardCommand insertBoardCommand = ctx.getBean("insertCommand", InsertBoardCommand.class);
		insertBoardCommand.execute(model);
		return "redirect:selectBoardList.do";
	}
	
	
}
