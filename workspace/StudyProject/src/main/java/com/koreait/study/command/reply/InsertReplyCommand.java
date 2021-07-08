package com.koreait.study.command.reply;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.study.dao.ReplyDAO;
import com.koreait.study.dto.Reply;

public class InsertReplyCommand {

	 
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		long galleryNo = Long.parseLong(request.getParameter("galleryNo"));
		String id = request.getParameter("id");
		String replyContent = request.getParameter("replyContent");
		String ip = request.getRemoteAddr();
		
		Reply reply = new Reply();
		reply.setGalleryNo(galleryNo);
		reply.setId(id);
		reply.setContent(replyContent);
		reply.setIp(ip);
		
		
		ReplyDAO replyDAO = sqlSession.getMapper(ReplyDAO.class);
		int count = replyDAO.insertReply(reply);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (count > 0) {
			resultMap.put("status", 200);
		} else {
			resultMap.put("status", 500);
		}
			
		return resultMap;
		
		
		

	}

}
