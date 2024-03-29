package com.koreait.study.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.study.command.galleryBoard.GalleryBoardListCommand;
import com.koreait.study.command.galleryBoard.GalleryBoardViewCommand;
import com.koreait.study.command.galleryBoard.InsertGalleryCommand;
import com.koreait.study.command.galleryBoard.TextareaCheckCommand;
import com.koreait.study.command.galleryBoard.UpdateGalleryCommand;

@Controller
public class GalleryBoardController {

	private SqlSession sqlSession;
	private GalleryBoardListCommand galleryBoardListCommand;
	private InsertGalleryCommand insertGalleryCommand;
	private GalleryBoardViewCommand galleryBoardViewCommand;
	private UpdateGalleryCommand updateGalleryCommand;
	private TextareaCheckCommand textareaCheckCommand;
	
	@Autowired
	public GalleryBoardController(SqlSession sqlSession,
								  GalleryBoardListCommand galleryBoardListCommand,
								  InsertGalleryCommand insertGalleryCommand,
								  GalleryBoardViewCommand galleryBoardViewCommand,
								  UpdateGalleryCommand updateGalleryCommand,
								  TextareaCheckCommand textareaCheckCommand) {
		super();
		this.sqlSession = sqlSession;
		this.galleryBoardListCommand = galleryBoardListCommand;
		this.insertGalleryCommand = insertGalleryCommand;
		this.galleryBoardViewCommand = galleryBoardViewCommand;
		this.updateGalleryCommand = updateGalleryCommand;
		this.textareaCheckCommand = textareaCheckCommand;
	}
	
	
	// 매핑에 따른 Command 작업	
	@GetMapping(value="galleryBoardPage.do")
	public String galleryBoardPage() {
		return "galleryBoard/galleryBoard";
	} // ---------------------------------
	
	// 갤러리게시글 목록 반환
	@GetMapping(value="galleryBoardList.do", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> galleryBoardList(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return galleryBoardListCommand.execute(sqlSession, model);
	}
	
	// 새글 작성 페이지 이동
	@GetMapping(value="insert_galleryBoardPage.do")
	public String insert_galleryBoardPage() {
		return "galleryBoard/insertGallery";
	} // -----------------------------------
	// 새글 작성 - 파일 추가 경우_ "multipartSerlvetRequest"를 사용한다.
	@PostMapping(value="insertGallery.do")
	public String insert_gallery(MultipartHttpServletRequest multipartRequest, 
								 HttpServletResponse response,
								 Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		model.addAttribute("response", response);
		insertGalleryCommand.execute(sqlSession, model);
		return null;
	}
	// 갤러리 게시글 한개 반환
	@GetMapping(value="galleryBoardView.do")
	public String galleryBoardView(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		galleryBoardViewCommand.execute(sqlSession, model);
		return "galleryBoard/galleryBoardView";
	}
	// 업데이트 페이지 이동
	@GetMapping(value="updateGalleryPage.do")
	public String updateGalleryPage(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		galleryBoardViewCommand.execute(sqlSession, model);
		return "galleryBoard/updateGallery";
	}//-------------------------------------
	@PostMapping(value="updateGallery.do")
	public String updateGallery(MultipartHttpServletRequest multipartRequest, HttpServletResponse response, Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		model.addAttribute("response", response);
		updateGalleryCommand.execute(sqlSession, model);
		return null;
	}
	// 업데이트 전 content(내용) 동일한지 검사
	@GetMapping(value="textareaCheck.do", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> textareaCheck(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return textareaCheckCommand.execute(sqlSession, model);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
