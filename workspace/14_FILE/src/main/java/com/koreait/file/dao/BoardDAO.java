package com.koreait.file.dao;

import java.util.List;

import com.koreait.file.dto.Board;

public interface BoardDAO {

	public List<Board> selectBoardList();
	public int insertBoard(String writer, String title, String content, String filename);
	public Board selectBoardByNo(long no);
	public int updateBoard(String title, String content, String filename, long no);
	public int deleteBoard(long no);
}
