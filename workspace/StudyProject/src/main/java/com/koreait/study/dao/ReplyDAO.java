package com.koreait.study.dao;

import java.util.List;


import com.koreait.study.dto.Reply;

public interface ReplyDAO {

	public int replyTotalCount(long galleryNo);
	public List<Reply> replyList(long galleryNo, int beginRecord, int endRecord);
	public int insertReply(Reply reply);
	public int deleteReply(long no);
	
}
