package com.koreait.board03.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.board03.dao.BoardDAO;

public class DeleteBoardCommand implements BoardCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// model >> map
		Map<String , Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		boardDAO.deleteBoard(Long.parseLong(request.getParameter("no")));

	}

}
