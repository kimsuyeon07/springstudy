package com.koreait.integration1;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchBoardTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {

		/*
			resultMap.put("status", 500);
			resultMap.put("list", null);
			resultMap.put("message", "게시글이 없습니다.");
		*/ 
		
		try {
			// 검색할 "query" 입력
			String query = URLEncoder.encode("영화","UTF-8");
			// 검색할 column의 "TITLE" 입력
			String apiURL = "http://localhost:9090/integration1/search.do?column=TITLE&query=" + query;
			URL url = new URL(apiURL);
			
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			
			
			// 읽어들인다.
			BufferedReader br = new BufferedReader(new InputStreamReader( con.getInputStream() ));
			String searchlist = ""; // 초기화 : 읽어들인 내용
			String line = null;    // 읽을 line이 있다면, 
			while( (line = br.readLine()) != null ) {
				searchlist += line;
			}
			
			// JSON 객체로 만들기
			JSONObject obj = new JSONObject(searchlist);
			JSONArray arr = obj.getJSONArray("list");
			for (int i = 0; i<arr.length(); i++) {
				JSONObject searchBoard = (JSONObject)arr.get(i);
				
				System.out.println("번호 : " + searchBoard.getLong("no"));
				System.out.println("제목 : " + searchBoard.getString("title"));
				System.out.println("내용 : " + searchBoard.getString("content"));
				System.out.println("작성일  : " + new Date((long)searchBoard.get("regdate")));
			}
			

			String message = obj.getString("message");
			System.out.println("메시지  : " + message);

			int status = obj.getInt("status");
			System.out.println("메시지  : " + status);
			
			br.close();
			con.disconnect();
			
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
