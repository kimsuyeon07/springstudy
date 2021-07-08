package com.koreait.study.command.galleryBoard;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.study.dao.GalleryBoardDAO;
import com.koreait.study.dto.GalleryBoard;

public class UpdateGalleryCommand implements GalleryBoardCommand {

	@Autowired
	private GalleryBoardDAO galleyBoardDAO;
	
	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)map.get("multipartRequest");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		
		long no = Long.parseLong(multipartRequest.getParameter("no"));
		String id = multipartRequest.getParameter("id");
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		String image0 = multipartRequest.getParameter("image0"); //수정 전에 있던 이미지명
		
		GalleryBoard galleryBoard = new GalleryBoard();
		galleryBoard.setNo(no);
		galleryBoard.setId(id);
		galleryBoard.setTitle(title);
		galleryBoard.setContent(content);
		
		// 수정 시, 첨부한 파일이 있다면,
		MultipartFile file = multipartRequest.getFile("image");
		if (file != null && !file.isEmpty()) {
			// 파일명
			String originalFilename = file.getOriginalFilename();
			String extension = originalFilename.substring( originalFilename.lastIndexOf(".") );
			String imagename = originalFilename.substring( 0, originalFilename.lastIndexOf(".") );
			String uploadFilename = imagename + "_" + System.currentTimeMillis() + "." + extension;
			// 파일 저장할 경로 및 저장 진행
			String realPath = multipartRequest.getServletContext().getRealPath("resources/archive");
			File archive = new File(realPath);
			if (!archive.exists()) {
				archive.mkdirs();
			}
			File attach = new File(archive, uploadFilename);
			// 수정 전에 있던 이미지는 지워준다.
			File beforeFile = new File(realPath, image0);
			if (beforeFile != null) {
				if (beforeFile.exists()) beforeFile.delete();
			}
			
			try {
				file.transferTo(attach);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// DB에 넣는 파일명 인코딩!!
			try {
				uploadFilename = URLEncoder.encode(uploadFilename, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			galleryBoard.setImagename(uploadFilename);
		} else {
			galleryBoard.setImagename(image0);
		}
			
		int result = galleyBoardDAO.updateGallery(galleryBoard);
			
		response.setContentType("text/html; charset=UTF-8");
		try {
			if (result > 0) {
				response.getWriter().println("<script>");
				response.getWriter().println("alert('갤러리게시글 수정 완료!')");
				response.getWriter().println("location.href='galleryBoardView.do?no="+no+"&id="+id+"'");
				response.getWriter().println("</script>");
			} else {
				response.getWriter().println("<script>");
				response.getWriter().println("alert('갤러리게시글 수정 실패!')");
				response.getWriter().println("history.back()");
				response.getWriter().println("</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		
		

	}

}
