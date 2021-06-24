package com.koreait.file.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.file.command.DeleteBoardCommand;
import com.koreait.file.command.DownloadCommand;
import com.koreait.file.command.InsertBoardCommand;
import com.koreait.file.command.SelectBoardListCommand;
import com.koreait.file.command.UpdateBoardCommand;

import selectBoardByNo.SelectboardViewCommand;

@Controller
public class BoardController {
	
	// field
	private SqlSession sqlSession;
	private SelectBoardListCommand selectBoardListCommand;
	private InsertBoardCommand insertBoardCommand;
	private DownloadCommand downloadCommand;
	private SelectboardViewCommand selectBoardViewCommand;
	private UpdateBoardCommand updateBoardCommand;
	private DeleteBoardCommand deleteBoardCommand;
	
	// constructor  <Bean으로 생성된 값을 가지고 온다>
	@Autowired
	public BoardController(SqlSession sqlSession,
						   SelectBoardListCommand selectBoardListCommand,
						   InsertBoardCommand insertBoardCommand,
						   DownloadCommand downloadCommand,
						   SelectboardViewCommand selectBoardViewCommand,
						   UpdateBoardCommand updateBoardCommand,
						   DeleteBoardCommand deleteBoardCommand) {
		this.sqlSession = sqlSession;
		this.selectBoardListCommand = selectBoardListCommand;
		this.insertBoardCommand = insertBoardCommand;
		this.downloadCommand = downloadCommand;
		this.selectBoardViewCommand = selectBoardViewCommand;
		this.updateBoardCommand = updateBoardCommand;
		this.deleteBoardCommand = deleteBoardCommand;
	}
	

	@GetMapping(value="/") 
	public String index() {
		return "index";
	}
	
	@GetMapping(value="selectBoardList.do")
	public String selectBoardList(Model model) {
		selectBoardListCommand.execute(sqlSession, model);
		return "board/listBoard";
	}
	
	@GetMapping(value="insertBoardPage.do")
	public String insertBoardPage() {
		return "board/insertBoard";
	}
	@PostMapping(value="insertBoard.do")
	public String insertBoard(MultipartHttpServletRequest multipartRequest, Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		insertBoardCommand.execute(sqlSession, model);
		return "redirect:selectBoardList.do";
	}
	
	@GetMapping(value="download.do") 
	public void download(HttpServletRequest request, 
						 HttpServletResponse response,
						 Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		downloadCommand.execute(model);
	}
	
	@GetMapping(value="selectBoardByNo.do")
	public String selectBoardByNo(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		selectBoardViewCommand.execute(sqlSession, model);
		return "board/viewBoard";
	}
	
	@PostMapping(value="updateBoard.do")
	public String updateBoard(MultipartHttpServletRequest multipartRequest, Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		updateBoardCommand.execute(sqlSession, model);
		long no = Long.parseLong(multipartRequest.getParameter("no"));
		return "redirect:selectBoardByNo.do?no=" + no ;
	}
	
	@PostMapping(value="deleteBoard.do")
	public String deleteBoard(MultipartHttpServletRequest multipartRequest, Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		deleteBoardCommand.execute(sqlSession, model);
		return "redirect:selectBoardList.do";
	}
	
	
	
	
	
	
	
	
}
