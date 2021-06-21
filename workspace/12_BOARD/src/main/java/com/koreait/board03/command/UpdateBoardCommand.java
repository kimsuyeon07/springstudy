package com.koreait.board03.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.board03.dao.BoardDAO;
import com.koreait.board03.dto.Board;

public class UpdateBoardCommand implements BoardCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// model >> map
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		long no = Long.parseLong(request.getParameter("no"));
		
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setNo(no);
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		boardDAO.updateBoard(board);

	}

}
