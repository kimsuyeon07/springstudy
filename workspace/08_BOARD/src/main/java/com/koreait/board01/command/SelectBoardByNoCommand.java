package com.koreait.board01.command;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.koreait.board01.dao.BoardDAO;
import com.koreait.board01.dto.Board;

public class SelectBoardByNoCommand implements BoardCommand {

	@Override
	public void execute(Model model) {

		// model을 map으로 만들기
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		long no = Long.parseLong(request.getParameter("no"));
		
		// dao 호출
		Board board = BoardDAO.getInstance().selectBoardByNo(no);
		
		// model 에 담아주기
		model.addAttribute("board", board);

	}

}
