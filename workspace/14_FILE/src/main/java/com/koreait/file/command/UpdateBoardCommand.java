package com.koreait.file.command;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.file.dao.BoardDAO;

public class UpdateBoardCommand implements BoardCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)map.get("multipartRequest");
		
		long no = Long.parseLong(multipartRequest.getParameter("no"));
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		String filename1 = multipartRequest.getParameter("filename1"); // 서버에 저장된 파일
		
		/*서버에 새로운 파일 업데이트 하기*/
		MultipartFile filename2 = multipartRequest.getFile("filename2"); // 새로운 첨부파일
		String realPath = multipartRequest.getServletContext().getRealPath("resources/archive");
		/* 
			1.현재 파일을 삭제하고,    
			2.업데이트된 파일을 저장한다. 
			3.DB로 수정내용을 보내준다. 
		 */
		File file = new File(realPath, filename1); // 서버에 저장된 파일 (기존의 첨부)
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		// ↓ 새로운 첨부 파일 저장하기 [파일명]
		// →  ◆  기존 첨부와 새로운 첨부가 '모두' 있으면  =>  기존 첨부를 지운다  ◆   ←
		if (filename2 != null && !filename2.isEmpty()) {
			// 기존의 파일 지우기
			if (file != null) {
				// ↓ 파일이 존재하면, 파일을 지워주겠다.
				if (file.exists()) file.delete();
			}
			// 새 첨부 넣기
			String originalFilename = filename2.getOriginalFilename();
			String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			String filename = originalFilename.substring(0, originalFilename.lastIndexOf("."));
			String uploadFilename = filename + "_" + System.currentTimeMillis() + "." + extension;			
			// ↓ IO : 파일을 생성하겠다. 
			File uploadFile = new File(realPath, uploadFilename);
			try {
				filename2.transferTo(uploadFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// DB에 넣는 파일명 인코딩 처리
			try {
				uploadFilename = URLEncoder.encode(uploadFilename, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			// DB에 데이터 저장
			boardDAO.updateBoard(title, content, uploadFilename, no);
			
		} else { // 새로운 첨부가 없으면,
			// 업로드 파일이 없는 경우의 : DB에 데이터 저장 (기존파일명)
			boardDAO.updateBoard(title, content, filename1, no);		
		}
		
		
		
		
		
		
		
		
	}
		
		


}
