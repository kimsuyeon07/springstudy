<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>
		
	<% request.setCharacterEncoding("UTF-8"); %>
	<h1>Quiz_view04.jsp</h1>
	
	제목 : ${board.title}		<br>
	조회수 : ${board.hit}		<br>
	작성일 : ${date}		<br>
	
</body>
</html>