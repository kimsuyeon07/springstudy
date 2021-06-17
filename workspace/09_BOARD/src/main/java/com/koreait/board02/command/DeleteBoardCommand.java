package com.koreait.board02.command;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.board02.dao.BoardDAO;

public class DeleteBoardCommand implements BoardCommand {

	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public void execute(Model model) {

		// model >> map
		Map<String , Object> map = model.asMap();
		long no = (long)map.get("no");
		
		boardDAO.deleteBoard(no);
		

	}

}
