package com.koreait.board02.command;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.board02.dao.BoardDAO;

public class SelectBoardViewCommand implements BoardCommand {

	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public void execute(Model model) {

		// model >> map
		Map<String, Object> map = model.asMap();
		long no = (long)map.get("no");
		
		// dao 실행
		// 결과값을 model에 넣는다.
		model.addAttribute("board", boardDAO.selectBoardByNo(no));

	}

}
