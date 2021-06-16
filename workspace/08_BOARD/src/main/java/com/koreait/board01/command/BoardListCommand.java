package com.koreait.board01.command;

import org.springframework.ui.Model;

import com.koreait.board01.dao.BoardDAO;

public class BoardListCommand implements BoardCommand {

	@Override
	public void execute(Model model) {
		// Model : JSP에게 값을 전달 할 때 사용
		
		model.addAttribute("list", BoardDAO.getInstance().selectBoardList());
		// List 타입으로 결과를 저장했다.
		
		

	}

}
