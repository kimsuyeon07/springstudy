package com.koreait.file.command;

import java.io.File;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.file.dao.BoardDAO;

public class DeleteBoardCommand implements BoardCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)map.get("multipartRequest");
		
		long no = Long.parseLong(multipartRequest.getParameter("no"));
		String filename1 = multipartRequest.getParameter("filename1");
		
		/* 파일 삭제 */
		String realPath = multipartRequest.getServletContext().getRealPath("resources/archive");
		File file = new File(realPath, filename1);
		if (file != null) {
			if (file.exists()) file.delete();
		}
		
		// DB로 데이터 보내기
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		boardDAO.deleteBoard(no);
		
		

	}

}
