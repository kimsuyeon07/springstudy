<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>게시글 작성</title>
</head>
<body>

	<h1>게시글 작성 화면</h1>
	<!-- 파일을 업로드하기 위해서 필요한 "form"의 속성 : method="post" enctype="multipart/form-data" -->
	<form action="insertBoard.do" method="post" enctype="multipart/form-data">
		
		<p>작성자</p>
		<input type="text" name="writer">	<br>

		<p>제목</p>
		<input type="text" name="title">	<br>

		<p>내용</p>
		<input type="text" name="content">	<br>

		<p>첨부</p> <!-- multiple : 다중 첨부 가능 -->
		<input type="file" name="files" multiple>	<br>
		
		<button>저장하기</button>
		
	</form>

</body>
</html>