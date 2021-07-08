package com.koreait.study.command.galleryBoard;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface GalleryBoardCommand {

	public void execute(SqlSession sqlSession, Model model) ;
	
}
