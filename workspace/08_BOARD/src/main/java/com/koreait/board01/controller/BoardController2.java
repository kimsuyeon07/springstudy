package com.koreait.board01.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.board01.command.BoardCommand;
import com.koreait.board01.command.BoardListCommand;
import com.koreait.board01.command.DeleteBoardCommand;
import com.koreait.board01.command.InsertBoardCommand;
import com.koreait.board01.command.SelectBoardByNoCommand;
import com.koreait.board01.command.UpdateBoardCommand;
import com.koreait.board01.dto.Board;

// @Controller
public class BoardController2 {

	/* field */
	// Logger:인터페이스 (org.slf4j.Logge)  ,  LoggerFactory:클래스(org.slf4j.LoggerFactory)
	private static final Logger logger = LoggerFactory.getLogger(BoardController2.class);

	// ↓ 인터페이스를 사용하지 않고 진행해보기
	// private BoardCommand command;
	// com.koreait.baord01.command >> BoardCommand(인터페이스)
	
	
	// root-context.xml 정의된 bean 주입
	/*
			1. 필드 이용하기 (하나씩 모두 적용)
			@Autowired
			private BoardListCommand boardListCommand;
			@Autowired
			private SelectListBoardByNoCommand selectListBoardByNoCommand;
			... ( + 5개의 Command 모두)
			
			2. Setter 형태의 메소드 이용하기
			@Autowired
			public void setCommand( BoardListCommand boardListCommand, -- 매개변수로 자동 저장된다.
									SelectBoardByNoCommand selectBoardByNoCommand,
									InsertBoardCommand insertBoardCommand,
									UpdateBoardCommand updateBoardCommand,
									DeleteBoardCommand deleteBoardCommand) {
				this.boardListCommand = boardListCommand;
				this.selectBoardByNoCommand = selectBoardByNoCommand;
				this.insertBoardCommand = insertBoardCommand;
				this.updateBoardCommand = updateBoardCommand;
				this.deleteBoardCommand = deleteBoardCommand;
				// 매개변수(boardListCommand)가 필드(this.boardListCommand)로 값을 전달한다.
			}
			
	 */
	private BoardListCommand boardListCommand;
	private SelectBoardByNoCommand selectBoardByNoCommand;
	private InsertBoardCommand insertBoardCommand;
	private UpdateBoardCommand updateBoardCommand;
	private DeleteBoardCommand deleteBoardCommand;
	
	@Autowired
	public void setCommand( BoardListCommand boardListCommand,
							SelectBoardByNoCommand selectBoardByNoCommand,
							InsertBoardCommand insertBoardCommand,
							UpdateBoardCommand updateBoardCommand,
							DeleteBoardCommand deleteBoardCommand) {
		this.boardListCommand = boardListCommand;
		this.selectBoardByNoCommand = selectBoardByNoCommand;
		this.insertBoardCommand = insertBoardCommand;
		this.updateBoardCommand = updateBoardCommand;
		this.deleteBoardCommand = deleteBoardCommand;
		// 매개변수(boardListCommand)가 필드(this.boardListCommand)로 값을 전달한다.
	} 
	
	/* index.jsp 이동 */
	@GetMapping(value="/")  // @GetMapping == @RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		logger.info("index() 호출");
		return "index";
	}
	
	
	// insert/ update/ delete 는 forward 하지 않는다.

	
	/* 게시글 목록 반환 */
	@GetMapping(value="selectBoardList.do")
	public String selectBoardList(Model model) {
		logger.info("selectBoardList()호출");
		
		// command 작업
		// command = new BoardListCommand(); >> 상단의 @Autowired로 command를 bean으로 생성해두어 할 필요가 없어졌다.
		boardListCommand.execute(model);
		
		// 응답 view : 기본값 : forward >> model을 가지고 이동한다
		return "board/list";
	}
	
	
	/* 게시글 추가 */
		/* 페이지 이동 */
	@GetMapping(value="insertBoardPage.do")
	public String insertBoardPage() {
		return "board/insert"; // board/insert.jsp로 이동
	}
	@GetMapping(value="insertBoard.do")
	public String insertBoard(HttpServletRequest request, // <form>태그 요소가 파라미터로 전달된다.
			Model model) { 			  // 파라미터 : 3개(writer, title. content)
		
		logger.info("insertBoard() 호출");
		
		// 모든 Command에는 model만 전달할 수 있다.
		// 따라서, Command에 전달할 데이터들은 모두 model에 저장한다.
		model.addAttribute("request", request);
		
		insertBoardCommand.execute(model);
		
		// 삽입 후에는 반드시 "redirect"
		
		return "redirect:selectBoardList.do"; // ** "redirect:매핑" 으로 적어줘야 한다.**
	}
	
	
	
	/* 게시글 한개 목록 반환 */
	@GetMapping("selectBoardByNo.do")
	public String selectBoardByNo(HttpServletRequest request, Model model) {
		
		logger.info("selectBoardByNo() 호출");
		
		model.addAttribute("request", request);
		
		selectBoardByNoCommand.execute(model);
		
		return "board/view";
		
	}

	
	
	/* 게시글 수정 */
		/* 페이지 이동 */
	@PostMapping(value="updateBoardPage.do")
	public String updateBoardPage(@ModelAttribute Board board) {
		logger.info("updateBoardPage() 호출");
		System.out.println(board);
		return "board/update"; // board/update.jsp로 이동
	}
	@PostMapping("updateBoard.do")
	public String updateBoard(Board board, Model model) {
		
		logger.info("updateBoard() 호출");
		
		model.addAttribute("board", board);
		
		updateBoardCommand.execute(model);
		
		
		return "redirect:selectBoardList.do";
	}
	
	
	
	/* 게시글 삭제하기 */
	@PostMapping("deleteBoard.do")
	public String deleteBoard(@RequestParam("no") long no, Model model) {
		
		// 매개변수를 model에 저장
		model.addAttribute("no", no);
		// 해당 model을 가지고 command 실행
		deleteBoardCommand.execute(model);
		
		// 목록으로 이동
		return "redirect:selectBoardList.do";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}