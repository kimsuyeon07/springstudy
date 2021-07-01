package com.koreait.integration.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.integration.domain.Board;
import com.koreait.integration.repository.BoardRepository;

// 인터페이스를 구현(완성)하는 자바파일
@Service // ← Service( == Command ) 영역임을 알려주는 애너테이션
public class BoardServiceImpl implements BoardService {


	// field (DAO_Repository를 필드로 잡아둔다.)
	@Autowired
	private BoardRepository repository;
	

	/* ======= Interface에서 가지고온 미완성의 메서드 들 : 완성해준다  ======= */
	
	// 전체 게시글 목록
	@Override 
	public List<Board> totalList() {
		return repository.selectAll();
	}

	// 검색한 게시글 목록
	@Override
	public List<Board> searchList(Map<String, String> map) {
		return repository.selectQuery(map);
	}

}
