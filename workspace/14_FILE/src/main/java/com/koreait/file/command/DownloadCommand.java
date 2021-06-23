package com.koreait.file.command;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;

public class DownloadCommand {

	// DB 접근이 없는 Command이기 때문에 sqlSession을 사용하지 않는다.
	// 따라서, BoardCommand를 구현하지 않아도 상관없다. (구현해도 상관없다.)
	
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		
		String realPath = request.getServletContext().getRealPath("resources/archive");
		
		// (DB와 서버에 저장된 파일명)
		String filename = request.getParameter("filename"); // _ 과 시간이포함된 이름값 
		// 파일명 원상 복구 (선택)
		String extension = filename.substring(filename.lastIndexOf(".") + 1);
		String originalFilename = filename.substring(0, filename.lastIndexOf("_"));
		String downloadFilename = originalFilename + "." + extension;
		
		
		// 다운로드 할 File 생성
		File download = new File(realPath, filename);  // 서버에서 찾아야하는 파일명을 작성 (다운로드할 파일을 찾는다)
		
		// 스트림 준비
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			// response 다운로드 처리 : .setHeader은 "문자열"로만 저장할 수 있다.
			response.setHeader("Content-Type", "application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(downloadFilename, "UTF-8"));
			response.setHeader("Content-Length", download.length() + "");
			
			// 서버에 저장된 download할 파일을 읽는 스트림
			bis = new BufferedInputStream(new FileInputStream(download)); // 다운로드할 파일을 읽어들이겠다.
			
			// 사용자에게 파일을 생성하는 출력 스트림
			bos = new BufferedOutputStream(response.getOutputStream()); // 사용자에게 출력해주는 
			
			// bis로 읽은 내용을 bos로 전송 (파일 복사 진행)
			FileCopyUtils.copy(bis, bos);
			
			// 혹시 남은게 있다면
			bos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) bos.close();
				if (bis != null) bis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
