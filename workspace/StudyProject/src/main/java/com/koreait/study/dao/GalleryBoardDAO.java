package com.koreait.study.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.study.dto.GalleryBoard;
import com.koreait.study.dto.Member;
import com.koreait.study.dto.PagingDTO;

@Repository
public class GalleryBoardDAO {

	@Autowired
	private SqlSession sqlSession;
	
	/* method */
	public List<GalleryBoard> galleryBoardList(PagingDTO pagingDTO) {
		return sqlSession.selectList("com.koreait.study.dao.galleryBoard.galleryBoardList", pagingDTO);
	}
	public int galleryBoardTotal() {
		return sqlSession.selectOne("com.koreait.study.dao.galleryBoard.galleryBoardTotal");
	}
	public int insertGallery(GalleryBoard galleryBoard) {
		return sqlSession.insert("com.koreait.study.dao.galleryBoard.insertGallery", galleryBoard);
	}
	public GalleryBoard galleryBoardView(long no) {
		return sqlSession.selectOne("com.koreait.study.dao.galleryBoard.galleryBoardView", no);
	}
	public int updateHitGallery(GalleryBoard galleryBoard) {
		return sqlSession.update("com.koreait.study.dao.galleryBoard.updateHitGallery", galleryBoard);
	}
	
	
	
}
