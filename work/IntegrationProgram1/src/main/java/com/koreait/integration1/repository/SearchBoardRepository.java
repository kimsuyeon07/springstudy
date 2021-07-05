package com.koreait.integration1.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.integration1.domain.QueryDTO;
import com.koreait.integration1.domain.SearchBoard;

@Repository
public class SearchBoardRepository {
	
	@Autowired
	private SqlSession sqlSession;
	

	public List<SearchBoard> selectAll() {
		return sqlSession.selectList("com.koreait.integration1.repository.searchBoard.selectAll");
	}
	
	public int totalBoardList() {
		return sqlSession.selectOne("com.koreait.integration1.repository.searchBoard.totalBoardList");
	}
	
	public List<SearchBoard> search(QueryDTO queryDTO) {
		return sqlSession.selectList("com.koreait.integration1.repository.searchBoard.search", queryDTO);
	}
	
}
