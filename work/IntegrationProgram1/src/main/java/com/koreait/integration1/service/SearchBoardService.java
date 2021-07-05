package com.koreait.integration1.service;

import java.util.List;
import java.util.Map;

import com.koreait.integration1.domain.SearchBoard;

public interface SearchBoardService {

	public List<SearchBoard> selectAll();
	public int totalBoardList();
	public List<SearchBoard> search(Map<String , String> map);
}
