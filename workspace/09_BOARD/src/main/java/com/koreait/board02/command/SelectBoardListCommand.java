package com.koreait.board02.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.board02.dao.BoardDAO;

public class SelectBoardListCommand implements BoardCommand {

	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public void execute(Model model) { // Controller가 전닿하는 model과 같다.
		// model에 게시글목록 저장
		// view.jsp로 이동 지정
		
		model.addAttribute("list", boardDAO.selectBoardList());

	}

}
