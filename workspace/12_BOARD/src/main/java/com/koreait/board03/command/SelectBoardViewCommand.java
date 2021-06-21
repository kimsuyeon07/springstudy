package com.koreait.board03.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.board03.dao.BoardDAO;

public class SelectBoardViewCommand implements BoardCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// model >> map
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		long no = Long.parseLong(request.getParameter("no"));
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		model.addAttribute("board", boardDAO.selectBoardByNo(no));
		
	}

}
