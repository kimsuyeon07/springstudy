package com.koreait.board03.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.board03.command.DeleteBoardCommand;
import com.koreait.board03.command.InsertBoardCommand;
import com.koreait.board03.command.SelectBoardListCommand;
import com.koreait.board03.command.SelectBoardViewCommand;
import com.koreait.board03.command.UpdateBoardCommand;
import com.koreait.board03.dto.Board;

@Controller
public class BoardController {

	// field
	@Autowired
	private SqlSession sqlSession;
	// == private SqlSessionTemplate sqlSession;
	// 	  ↑ (어떤 방식을 써도 상관없다) 클래스 : SqlSessionTemplate OR SqlSession
	
	// field >> Command 
	@Autowired
	private SelectBoardListCommand selectBoardListCommand;
	@Autowired
	private SelectBoardViewCommand selectBoardViewCommand;
	@Autowired
	private InsertBoardCommand insertBoardCommand;
	@Autowired
	private UpdateBoardCommand updateBoardCommand;
	@Autowired
	private DeleteBoardCommand deleteBoardCommand;
	
	
	
	// method : (*.do) 작업
	@GetMapping(value="/")
	public String index() {
		return "index";
	}
	
	@GetMapping(value="selectBoardList.do")
	public String selectBoardList(Model model) {
		selectBoardListCommand.execute(sqlSession, model);
		return "board/list"; // board/list.jsp로   forward
	}
	
	@GetMapping(value="selectBoardByNo.do")
	public String selectBoardByNo(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		selectBoardViewCommand.execute(sqlSession, model);
		return "board/view"; // board/view.jsp로  forward
	}
	
	// 페이지 이동
	@GetMapping(value="insertBoardPage.do")
	public String insertBoardPage() {
		return "board/insert"; // board/insert.jsp로  forward
	}
	
	@GetMapping(value="insertBoard.do")
	public String insertBoard(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		insertBoardCommand.execute(sqlSession, model);
		return "redirect:selectBoardList.do"; // board/list.jsp로 redirect
	}
	
	//페이지 이동
	@PostMapping(value="updateBoardPage.do")
	public String updateBoardPage(Board board, Model model) { // Board board, Model model == @ModelAttribute Board board
		model.addAttribute("board", board);
		return "board/update"; // board/update.jsp로 forward
	}
	
	@PostMapping(value="updateBoard.do")
	public String updateBoard(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		
		updateBoardCommand.execute(sqlSession, model);
		
		return "redirect:selectBoardByNo.do?no=" + request.getParameter("no") ;
	}
	
	@GetMapping(value="deleteBoard.do")
	public String deleteBoard(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		deleteBoardCommand.execute(sqlSession, model);
		return "redirect:selectBoardList.do";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
