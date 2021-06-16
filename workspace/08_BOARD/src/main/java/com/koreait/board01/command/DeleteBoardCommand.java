package com.koreait.board01.command;

import java.util.Map;

import org.springframework.ui.Model;

import com.koreait.board01.dao.BoardDAO;

public class DeleteBoardCommand implements BoardCommand {

	@Override
	public void execute(Model model) {

		// model >> map
		Map<String, Object> map = model.asMap();
		long no = (long)map.get("no");
		
		// dao 호출
		BoardDAO.getInstance().deleteBoard(no);
		

	}

}
