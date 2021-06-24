<%@page import="java.net.URLEncoder"%>
<%@page import="com.koreait.file.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>게시글 조회</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"> </script>
	<script>
		$(function(){
			fn_update();
			fn_delete();
		}) // 페이지 로드 이벤트(종료)
		
		
		/* 함수 생성 */
		function fn_update() {
			$('#update_btn').click(function(){
				$('#f').attr('action', 'updateBoard.do');
				$('#f').submit();
			});
		}
		
		function fn_delete() {
			$('#delete_btn').click(function(){
				if(confirm('삭제하시겠습니까?')){
				$('#f').attr('action', 'deleteBoard.do');
				$('#f').submit();					
				}
			})
		}
				
		
	</script>
</head>
<body>

	<h1>게시글 조회 화면</h1>
	<!-- 파일을 업로드하기 위해서 필요한 "form"의 속성 : method="post" enctype="multipart/form-data" -->
	<form id="f" method="post" enctype="multipart/form-data">
		
		<input type="button" value="수정하기" id="update_btn">
		<input type="button" value="삭제하기" id="delete_btn">

		<!-- 수정, 삭제를 위한 조건문을 생성하기 위해서 필요한 '필수' 데이터값 -->
		<input type="hidden" name="no" value="${board.no}">
		<input type="hidden" name="filename1" value="${filename}"> <!-- 서버에 첨부된 첨부파일명 -->
		
		<p>작성자</p>
		<p>${board.writer}</p>	<br>

		<p>제목</p>
		<input type="text" name="title" value="${board.title}">	<br>

		<p>내용</p>
		<input type="text" name="content" value="${board.content}">	<br>

		<p>첨부변경</p>
		<input type="file" name="filename2"> <!-- View에서는 다중첨부가 안된다. -->
		

		<p>첨부 이미지</p> <!-- multiple : 다중 첨부 가능 -->
		<img alt="${filename}" src="resources/archive/${filename}" style="width:300px;">	<br>
		
		
	</form>

</body>
</html>