package com.koreait.contact03.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.contact03.dao.ContactDAO;

public class DeleteContactCommand implements ContactCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		Map<String , Object> map = model.asMap();
		
		ContactDAO contactDAO = sqlSession.getMapper(ContactDAO.class);
		int count = contactDAO.deleteContact((long)map.get("no"));

	}

}
