package com.koreait.study.command.reply;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface ReplyCommand {

	public void execute(SqlSession sqlSession, Model model);
	
}
