package com.koreait.file.command;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.file.dao.BoardDAO;

public class InsertBoardCommand implements BoardCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		/*
		  		파일이 첨부된 "request"는 [ HttpServletRequest로 받을 수 없다. ]
				→  ◆ [ * MultipartHttpServletRequest * ]로 받아야 한다. ← ◆
		 */
		
		// model에 전달된 "request"값을 map으로 전환
		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)map.get("multipartRequest");

		// 파라미터 빼기 : file첨부를 제외하고는   >>  request와 동일한 방법으로 데이터를 빼준다.
		String writer = multipartRequest.getParameter("writer");
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		
		/*
		 * 	[ * 첨부에서 사용하는 클래스 : MultipartFile, File * ]
		 * 	[ * 첨부에서 사용하는 속성 : .getOriginalFilename() , .substring(-,-) * ]
		 * 
			※ 단일 파일 첨부 ※   <input type="file" name="filename">
			  →  MultipartFile file = multipartRequest.getFile("filename");
				: ( List<>, for문만 삭제하면 같은 코드 ) ↓
			
			※ 다중 파일 첨부 ※   <input type="file" name="files" multiple>
			  →  List<MultipartFile> files = multipartRequest.getFiles("files");
		 */
		
		/* ========================================================================= */
		// [ * 다중 파일 첨부 * ]
		List<MultipartFile> files = multipartRequest.getFiles("files");
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		
		for(MultipartFile file : files) {
			if (file != null && !file.isEmpty()) {
				// 올릴 때 파일명 (첨부된 파일명 확인)	         ↗ .getOriginalFilename() : 첨부할 당시의 원본 파일명
				String originalFilename = file.getOriginalFilename();
				System.out.println("첨부파일명: " + originalFilename);
				
				// 서버에 저장할 파일명
				// 파일명의 중복 방지 대책이 필요
				// 파일명_올린시간.확장자
				String extension = originalFilename.substring( originalFilename.lastIndexOf(".") + 1 );
				// originalFilename.lastIndexOf(".") : 파일명의 마지막 "."의 위치를 잦는다.
				String filename = originalFilename.substring( 0, originalFilename.lastIndexOf(".") );
				// originalFilename.substring( 0, originalFilename.lastIndexOf(".") ); 
				/*
				  	↑ "테스트파일.txt" 일 때 
						extension: ".txt",  filename: "테스트파일"
				 */
				String uploadFilename = filename + "_" + System.currentTimeMillis() + "." + extension;
				
				// 첨부파일 저장할 서버 위치
				String realPath = multipartRequest.getServletContext().getRealPath("resources/archive"); // archive 디렉터리는 없으므로 생성이 필요
				
				// archive 디렉터리 생성
				File archive = new File(realPath);
				if (!archive.exists()) {
					archive.mkdirs();
				}
				
				// 서버에 첨부파일 저장
				File attach = new File(archive, uploadFilename);
				try {
					file.transferTo(attach);
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
				boardDAO.insertBoard(writer, title, content, uploadFilename);
				
				} else {
					// 첨부파일이 없는 경우의 DB에 데이터 저장
					boardDAO.insertBoard(writer, title, content, "");
				} // if문 (종료)
		} // for문 (종료)
					
		
		
		
		
		
		
		
		
		
		
	}

}
