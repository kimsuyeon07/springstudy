package com.koreait.study.command.reply;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.study.dao.ReplyDAO;

public class DeleteReplyCommand{

	 
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		long no = Long.parseLong(request.getParameter("no"));
		
		ReplyDAO replyDAO = sqlSession.getMapper(ReplyDAO.class);
		
		int count = replyDAO.deleteReply(no);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (count > 0) {
			resultMap.put("status", 200);
		} else {
			resultMap.put("status", 500);
		}
			
		return resultMap;

	}

}
