package com.koreait.integration.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.integration.domain.Board;

@Repository  // 저장소에 들어갔다 나갔다 할 수 있는 클래스이다 (체크)
public class BoardRepository {

	@Autowired
	private SqlSession sqlSession;
	
	
	/* ------------------------------------ */
	
	
	/*전체 게시글 목록 */
	public List<Board> selectAll() {
		return sqlSession.selectList("com.koreait.integration.repository.board.selectAll");
	 // return sqlSession.selectList(".xml의 [매퍼인namespace.쿼리문의id]")
	}

	/*검색한 게시글 목록 : Map으로 파라미터를 전달받았다. */
	public List<Board> selectQuery(Map<String, String> map) {
		return sqlSession.selectList("com.koreait.integration.repository.board.selectQuery", map);
	 // return sqlSession.selectList(".xml의 [매퍼인namespace.쿼리문의id]", 쿼리문으로 전달할 파라미터값)
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
