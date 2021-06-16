package com.koreait.board01.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.koreait.board01.dao.BoardDAO;
import com.koreait.board01.dto.Board;

public class UpdateBoardCommand implements BoardCommand {

	@Override
	public void execute(Model model) {
		
		// model을 map으로 변환
		Map<String, Object> map = model.asMap();
		//HttpServletRequest request = (HttpServletRequest)map.get("board");
		Board board = (Board)map.get("board");
		
		//Board board = new Board();
		//board.setNo(Long.parseLong(request.getParameter("no")));
		//board.setTitle(request.getParameter("title"));
		//board.setContent(request.getParameter("content"));
		
		// DAO 호출
		BoardDAO.getInstance().updateBoard(board);
	}

}
