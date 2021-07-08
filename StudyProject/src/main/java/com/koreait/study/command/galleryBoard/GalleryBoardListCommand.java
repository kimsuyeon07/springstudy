package com.koreait.study.command.galleryBoard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.study.dao.GalleryBoardDAO;
import com.koreait.study.dto.GalleryBoard;
import com.koreait.study.dto.PagingDTO;
import com.koreait.study.util.PagingUtil;

public class GalleryBoardListCommand {

	@Autowired
	private GalleryBoardDAO galleryBoardDAO;
	 
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		// Paging에 필요한 값
		int page = Integer.parseInt(request.getParameter("page"));
		int totalRecord = galleryBoardDAO.galleryBoardTotal();
		
		System.out.println("갤러리게시글 총 수 : "+totalRecord);
		System.out.println("페이지 : "+page);
		
		PagingDTO pagingDTO = PagingUtil.getPage(totalRecord, page);
		// 페이징에 따라 목록 반환
		List<GalleryBoard> list = galleryBoardDAO.galleryBoardList(pagingDTO);
		
		// 응답 resultMap 생성
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (list.size() > 0) {
			resultMap.put("list", list);
			resultMap.put("status", 200);
			resultMap.put("paging", pagingDTO);
		} else {
			resultMap.put("list", null);
			resultMap.put("status", 500);
		}
		
		return resultMap;
	}

}
