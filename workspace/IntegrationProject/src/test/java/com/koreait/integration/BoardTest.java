package com.koreait.integration;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.org.apache.xerces.internal.util.URI.MalformedURIException;

public class BoardTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {

		// 제목에 공지가 포함된 게시글을 검색
		// 다음 주소를 이용해서 테스트를 수행하시오.
		// 검색 되지 않는 경우 message를 출력 : fall( ".")처럼 나와야한다.
		
		try {		
			
			String query = URLEncoder.encode("공지","UTF-8");
			String apiURL = "http://localhost:9090/integration/selectQuery.do?column=TITLE&query=" + query;
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			// 읽어들일때 사용하는 메서드 (   .getInputStream( )   )
			// new InputStreamReader(arg0)) : inputStream을 Reader로 바꿔주는 (
			BufferedReader br = new BufferedReader(new InputStreamReader( con.getInputStream() ));
			String resultMap = "";
			String line = null;
			while( (line = br.readLine()) != null ) {
				resultMap += line;
			}
			// System.out.println(resultMap);
			
			// JSON문자열  => JSONObject : org.json
			JSONObject obj = new  JSONObject(resultMap);
			
			// 1. list
			JSONArray arr = obj.getJSONArray("list");
			for (int i = 0; i<arr.length(); i++) {
				JSONObject board = (JSONObject)arr.get(i);
				
				System.out.println("번호 : " + board.getInt("no"));
				System.out.println("제목 : " + board.getString("title"));
				System.out.println("내용 : " + board.getString("content"));
				System.out.println("작성자  : " + board.getString("writer"));
				System.out.println("작성일  : " + new Date((long)board.get("postdate")));
				// Date(java.sql.Date)을 사용하면 (timestamp값을 날짜 형식으로 바꿔서 출력할 수 있다)
				// new Date( (long)board.get("postdate") )
			}
			
			// 2. message 
			String message = obj.getString("message");
			System.out.println("메시지  : " + message);
			
			// 3. status
			int status = obj.getInt("status");
			System.out.println("메시지  : " + status);
			
			br.close();
			con.disconnect();
			
		
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURIException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
