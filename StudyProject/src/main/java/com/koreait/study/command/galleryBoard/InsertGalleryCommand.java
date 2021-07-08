package com.koreait.study.command.galleryBoard;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;import org.springframework.jdbc.object.UpdatableSqlQuery;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.study.dao.GalleryBoardDAO;
import com.koreait.study.dto.GalleryBoard;

public class InsertGalleryCommand implements GalleryBoardCommand {

	@Autowired
	private GalleryBoardDAO galleryBoardDAO;
	
	
	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)map.get("multipartRequest");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		
		String id = multipartRequest.getParameter("id");
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		String ip = multipartRequest.getRemoteAddr();
		String uploadFilename = null;
		
		MultipartFile file = multipartRequest.getFile("image");
		System.out.println("오리지널 파일 명: " + file.getOriginalFilename());
		
		if (file != null && !file.isEmpty()) {
			String orignalFileName = file.getOriginalFilename();
			
			// 확장자명
			String extension = orignalFileName.substring( orignalFileName.lastIndexOf(".") );
			// 이미지명
			String filename = orignalFileName.substring( 0, orignalFileName.lastIndexOf(".") );
			// 업로드할 이미지명
			uploadFilename = filename + "_" + System.currentTimeMillis() + "." + extension;
			
			// 첨부파일을 저장 할 위치
			String realPath = multipartRequest.getServletContext().getRealPath("resources/archive");
			File archive = new File(realPath); // << 폴더 생성
			if (!archive.exists()) {
				archive.mkdirs();
			}
			
			// 서버에 파일 저장
			File attach = new File(archive, uploadFilename);
			try {
				file.transferTo(attach);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		GalleryBoard galleryBoard = new GalleryBoard();
		galleryBoard.setId(id);
		galleryBoard.setTitle(title);
		galleryBoard.setContent(content);
		galleryBoard.setIp(ip);
		galleryBoard.setImagename(uploadFilename);
		System.out.println(galleryBoard.toString());
		
		int result = galleryBoardDAO.insertGallery(galleryBoard);
		
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (result > 0) {
				response.getWriter().println("<script>");
				response.getWriter().println("alert('갤러리게시글 작성 완료!')");
				response.getWriter().println("location.href='galleryBoardPage.do'");
				response.getWriter().println("</script>");
			} else {
				response.getWriter().println("<script>");
				response.getWriter().println("alert('갤러리게시글 작성 실패!')");
				response.getWriter().println("history.back()");
				response.getWriter().println("</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
}

