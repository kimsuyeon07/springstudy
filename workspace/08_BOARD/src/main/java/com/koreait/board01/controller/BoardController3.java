package com.koreait.board01.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
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
import com.koreait.board01.config.BeanConfiguration;
import com.koreait.board01.dto.Board;

@Controller
public class BoardController3 {

	/* field */
	// Logger:인터페이스 (org.slf4j.Logge)  ,  LoggerFactory:클래스(org.slf4j.LoggerFactory)
	private static final Logger logger = LoggerFactory.getLogger(BoardController3.class);
	
	// ********************************************
	// BeanConfiguration.java 이용한 bean 생성 
	private AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
	// ********************************************
	
	
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
		BoardCommand command = ctx.getBean("boardListCommand", BoardListCommand.class);
		command.execute(model);
		
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
		
		BoardCommand command = ctx.getBean("insertBoardCommand", InsertBoardCommand.class);
		command.execute(model);
		
		// 삽입 후에는 반드시 "redirect"
		
		return "redirect:selectBoardList.do"; // ** "redirect:매핑" 으로 적어줘야 한다.**
	}
	
	
	
	/* 게시글 한개 목록 반환 */
	@GetMapping("selectBoardByNo.do")
	public String selectBoardByNo(HttpServletRequest request, Model model) {
		
		logger.info("selectBoardByNo() 호출");
		
		model.addAttribute("request", request);
		
		BoardCommand command = ctx.getBean("selectBoardByNoCommand", SelectBoardByNoCommand.class);
		command.execute(model);
		
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
		
		BoardCommand command = ctx.getBean("updateBoardCommand", UpdateBoardCommand.class);
		command.execute(model);
		
		
		return "redirect:selectBoardList.do";
	}
	
	
	
	/* 게시글 삭제하기 */
	@PostMapping("deleteBoard.do")
	public String deleteBoard(@RequestParam("no") long no, Model model) {
		
		model.addAttribute("no", no);
		BoardCommand command = ctx.getBean("deleteBoardCommand", DeleteBoardCommand.class);
		command.execute(model);
		
		return "rediect:selectBoardList.do";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}