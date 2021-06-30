package com.koreait.search.dao;

import java.util.List;

import com.koreait.search.dto.Employees;
import com.koreait.search.dto.PageDTO;
import com.koreait.search.dto.QueryDTO;

// @Mapper 사용하기도 한다. (굳이 안해도 되긴 함)

public interface SearchDAO {
	// SearchMapper  ==
	public int getTotalRecord();
	public List<Employees> searchAll(PageDTO page);
	public List<Employees> autoComplete(QueryDTO queryDTO);
	public List<Employees> search(QueryDTO queryDTO);
	public int getSearchRecord(QueryDTO queryDTO);
}
