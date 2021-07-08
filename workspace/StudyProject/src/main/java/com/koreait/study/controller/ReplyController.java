package com.koreait.study.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.study.command.reply.DeleteReplyCommand;
import com.koreait.study.command.reply.InsertReplyCommand;
import com.koreait.study.command.reply.ReplyListCommand;

@Controller
public class ReplyController {

	// field
	private SqlSession sqlSession;
	private ReplyListCommand replyListCommand;
	private InsertReplyCommand insertReplyCommand;
	private DeleteReplyCommand deleteReplyCommand;
	
	// constructor
	@Autowired
	public ReplyController(SqlSession sqlSession,
						   ReplyListCommand replyListCommand,
						   InsertReplyCommand insertReplyCommand,
						   DeleteReplyCommand deleteReplyCommand) {
		super();
		this.sqlSession = sqlSession;
		this.replyListCommand = replyListCommand;
		this.insertReplyCommand = insertReplyCommand;
		this.deleteReplyCommand = deleteReplyCommand;
	}
	
	// 댓글 목록 반환
	@GetMapping(value="replyList.do", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> replyList(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return replyListCommand.execute(sqlSession, model);
	}
	@PostMapping(value="insertReply.do", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> insertReply(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return insertReplyCommand.execute(sqlSession, model);
	}
	
	// 댓글 삭제
	@GetMapping(value="deleteReply.do", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> deleteReply(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return deleteReplyCommand.execute(sqlSession, model);
	} 
	
}
