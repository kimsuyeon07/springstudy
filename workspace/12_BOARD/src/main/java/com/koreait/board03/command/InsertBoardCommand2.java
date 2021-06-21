package com.koreait.board03.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.board03.dao.BoardDAO;
import com.koreait.board03.dto.Board;

public class InsertBoardCommand2 implements BoardCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// model >> map
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		boardDAO.insertBoard2(writer, title, content);

	}

}
