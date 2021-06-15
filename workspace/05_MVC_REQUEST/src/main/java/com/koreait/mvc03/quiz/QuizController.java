package com.koreait.mvc03.quiz;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class QuizController {
	
	Date date = null;
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

	// 1. request : HttpServletRequest request
	@RequestMapping("quiz/v01")
	public String a(HttpServletRequest request, Model model) {
		
		
		String title = request.getParameter("title");
		int hit = Integer.parseInt(request.getParameter("hit"));
		date = new Date();
		
		model.addAttribute("title", title);
		model.addAttribute("hit", hit);
		model.addAttribute("date", f.format(date));
		
		return "quiz/view01";
	}
	
	// 2. @RequestParam
	@RequestMapping("quiz/v02")
	public String b( @RequestParam(value="title", required=false, defaultValue="공지사항") String title,
					 @RequestParam(value="hit", required=false, defaultValue="5") int hit,
					 Model model ) {
		
		date = new Date();
		
		model.addAttribute("title", title);
		model.addAttribute("hit", hit);
		model.addAttribute("date", f.format(date));
		
		return "quiz/view02";
	}
	
	// 3. DTO
	@RequestMapping("quiz/v03")
	public String c( Board board, Model model) {
		
		board.setData(new Date());
		
		model.addAttribute("board", board);
		
		return "quiz/view03";
	}
	
	// 4. @ModelAttribute
	@RequestMapping("quiz/v04")
	public String d(@ModelAttribute("board") Board board,
					Model model) {
		
		date = new Date();
		
		model.addAttribute("date", f.format(date));
		return "quiz/view04";
	}
	
}
