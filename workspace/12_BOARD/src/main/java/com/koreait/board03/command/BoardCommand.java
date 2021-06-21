package com.koreait.board03.command;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface BoardCommand {

	// execute()메서드의 파라미터를 SqlSession, Model 로 만든다
	// ※
	// ↓ SqlSession sqlSession 입력 이유 
	// board.xml에서 SQL작업을 하고, 이를 DAO와 연결해서 사용하기 위해서 !!
	public void execute(SqlSession sqlSession, Model model);
	
}
