package com.koreait.integration1.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.integration1.domain.QueryDTO;
import com.koreait.integration1.domain.SearchBoard;
import com.koreait.integration1.repository.SearchBoardRepository;

@Service
public class SearchBoardIpl implements SearchBoardService {

	// Repository(필드)
	@Autowired
	private SearchBoardRepository searhcBoardRepository;
	
	
	@Override
	public List<SearchBoard> selectAll() {
		return searhcBoardRepository.selectAll();
	}
	
	@Override
	public int totalBoardList() {
		return searhcBoardRepository.totalBoardList();
	}
	
	@Override
	public List<SearchBoard> search(Map<String , String> map) {
		
		QueryDTO queryDTO = new QueryDTO();
		queryDTO.setColumn(map.get("column"));
		queryDTO.setQuery(map.get("query"));
		System.out.println(queryDTO.getColumn() + " " + queryDTO.getQuery());
		
		return searhcBoardRepository.search(queryDTO);
	}
}
