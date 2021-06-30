package com.koreait.search.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.springframework.ui.Model;

import com.koreait.search.dao.SearchDAO;
import com.koreait.search.dto.Employees;
import com.koreait.search.dto.QueryDTO;

public class AutoCompleteCommand implements SearchCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		// 받아온 파라미터를 QueryDTO에 담아준다.
		QueryDTO queryDTO = (QueryDTO)map.get("queryDTO");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		
		System.out.println(queryDTO.toString());
		
		// DAO
		SearchDAO searchDAO = sqlSession.getMapper(SearchDAO.class);
		List<Employees> list = searchDAO.autoComplete(queryDTO);
		System.out.println(list.toString());
		// 
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// AJAX가 성공 여부에 따라서, "status"의 number를 지정해주자
		if (list.size() == 0) {
			resultMap.put("status", 500);
			// resultMap.put("message", "해당 검색어가 없습니다.");
			resultMap.put("list", null);
		} else {
			resultMap.put("status", 200); 
			resultMap.put("list", list);
		}

		// 응답 : Response(사용) _AJAX로 다시 보내줄때  **** try문 사용!! ****
		// (요청한 jsp로 응답이 이루어지므로 index.jsp로 응답된다.
		// index.jsp에서 ajax로 요청했으므로 index.jsp의 success로 응답된다.
		try {
			response.setContentType("text/html; charset=UTF-8"); 
			// JSONObject obj = new JSONObject(Map변수 OR String)  :: 가능하다
			JSONObject obj = new JSONObject(resultMap);
			response.getWriter().println(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
