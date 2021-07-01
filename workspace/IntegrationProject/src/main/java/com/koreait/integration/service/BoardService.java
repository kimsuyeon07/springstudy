package com.koreait.integration.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.koreait.integration.domain.Board;

// Interface클래스 :  선언만 하는 자바파일이다.
public interface BoardService {

	// 유저에게 가까운 영역_ service레이어 영역(Command 영역)

	public List<Board> totalList();
	public List<Board> searchList(Map<String, String> map);
	

}
