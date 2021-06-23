<%@page import="java.net.URLEncoder"%>
<%@page import="com.koreait.file.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>게시글 조회</title>
</head>
<body>

	<h1>게시글 조회 화면</h1>
	<!-- 파일을 업로드하기 위해서 필요한 "form"의 속성 : method="post" enctype="multipart/form-data" -->
	<form method="post">
		
		<p>작성자</p>
		<p>${board.writer}</p>	<br>

		<p>제목</p>
		<input type="text" name="title" value="${board.title}">	<br>

		<p>내용</p>
		<input type="text" name="content" value="${board.content}">	<br>

		<p>첨부 이미지</p> <!-- multiple : 다중 첨부 가능 -->
		<img alt="${board.filename}" src="resources/archive/${board.filename}" style="width:300px;">	<br>
		
		
		
	</form>

</body>
</html>