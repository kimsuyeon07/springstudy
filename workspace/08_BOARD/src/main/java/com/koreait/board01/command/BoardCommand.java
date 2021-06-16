package com.koreait.board01.command;

import org.springframework.ui.Model;

public interface BoardCommand {

	// 인터페이스
	// Model : org.springframework.ui.Model
	public void execute(Model model);
	
}
