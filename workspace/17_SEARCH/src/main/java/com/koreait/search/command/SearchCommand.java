package com.koreait.search.command;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface SearchCommand {

	public void execute(SqlSession sqlSession, Model model);
	
}
