package com.koreait.board02.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.board02.dao.BoardDAO;
import com.koreait.board02.dto.Board;

public class UpdateBoardCommand implements BoardCommand {

	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public void execute(Model model) {

		// model >> map
		Map<String , Object> map = model.asMap();
		// public String updateBoard(HttpServletRequest request, 으로 받았기 때문에
		HttpServletRequest request = (HttpServletRequest)map.get("req");
		
		// Board에 넣어주기
		Board board = new Board();
		board.setNo(Long.parseLong(request.getParameter("no")));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		
		model.addAttribute("no", request.getParameter("no"));
		
		// DAO 호출
		boardDAO.updateBoard(board);
	}

}
