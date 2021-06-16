<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="resources/assets/css/style.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		
	</script>
</head>
<body>

	<form action="insertBoard.do" id="f">
		작성자<br>
		<input type="text" name="writer" id="writer"> 	<br><br>
		제목<br>
		<input type="text" name="title" id="title">		<br><br>
		내용<br>
		<input type="text" name="content" id="content">		<br><br>
		<button>작성완료</button>
	</form>	

</body>
</html>