package com.koreait.ajax.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface MemberCommand {

	public Map<String, Object> execute(SqlSession sqlSession, Model model) ;
	
}
