package com.koreait.study.command.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.study.dao.ReplyDAO;
import com.koreait.study.dto.PagingDTO;
import com.koreait.study.dto.Reply;
import com.koreait.study.util.PagingUtil;

public class ReplyListCommand {

	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		long galleryNo = Long.parseLong(request.getParameter("galleryNo"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		ReplyDAO replyDAO = sqlSession.getMapper(ReplyDAO.class);
		
		int totalRecord = replyDAO.replyTotalCount(galleryNo); 
		PagingDTO pagingDTO = PagingUtil.getPage(totalRecord, page);
		List<Reply> list = replyDAO.replyList(galleryNo, pagingDTO.getBeginRecord(), pagingDTO.getEndRecord());
		
		System.out.println("list : "+list.toString());
		System.out.println("paging : "+pagingDTO.toString());
		System.out.println("갤러리게시글 총 수 : "+totalRecord);
		System.out.println("페이지 : "+page);
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("paging", pagingDTO);
		if (list.size() > 0) {
			resultMap.put("list", list);
			resultMap.put("status", 200);
		} else {
			resultMap.put("list", null);
			resultMap.put("status", 500);
		}
			
		return resultMap;

	}

}
