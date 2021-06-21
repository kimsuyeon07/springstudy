package com.koreait.board03.dao;

import java.util.List;

import com.koreait.board03.dto.Board;

public interface BoardDAO {

	// ** BoardDAO >> 인터페이스파일로 생성!! **
	
	// BoardDAO는 board.xml과 직접 연결한다.
	// BoardDAO method == board.xml mapper id와 맞춘다.
	// 					  →	<mapper namespace="com.koreait.board03.dao.BoardDAO">
	
	public List<Board> selectBoardList();
	public Board selectBoardByNo(long no);
	
	public void insertBoard(Board board);
	public void insertBoard2(String writer, String title, String content);
	
	public void updateBoard(Board board);
	public void updateBoard2(String title, String content, long no);
	
	public void deleteBoard(long no);
	
}
