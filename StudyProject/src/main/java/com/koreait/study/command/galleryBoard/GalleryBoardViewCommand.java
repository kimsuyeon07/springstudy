package com.koreait.study.command.galleryBoard;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.study.dao.GalleryBoardDAO;
import com.koreait.study.dto.GalleryBoard;

public class GalleryBoardViewCommand implements GalleryBoardCommand {

	@Autowired
	private GalleryBoardDAO galleryBoardDAO;
	
	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String id = request.getParameter("id");
		long no = Long.parseLong(request.getParameter("no"));
		
		GalleryBoard galleryBoard = new GalleryBoard();
		galleryBoard.setNo(no);
		galleryBoard.setId(id);
		
		int updateResult = galleryBoardDAO.updateHitGallery(galleryBoard);
		GalleryBoard viewGallery = galleryBoardDAO.galleryBoardView(no);
		
		if (viewGallery != null) {
			model.addAttribute("viewGallery", viewGallery);
		}
				

	}

}
