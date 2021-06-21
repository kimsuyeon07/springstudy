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
	$(document).ready(function(){
			$('#update_page_btn').click(function(){
				$('#f').attr('action', 'updateBoardPage.do');
				$('#f').submit(); // <form>에 들어있는 정보를 가지고 가기 위해서 작업.
			})
			$('#delete_btn').click(function(){
				if (confirm('삭제하시겠습니까?')) {
					location.href='deleteBoard.do?no=${board.no}'; 
				}
			})
			$('#list_btn').click(function(){
				location.href='selectBoardList.do';
			})
		})
	</script>
	
</head>
<body>
	
	<div id="view_con" class="btn_basic">
		<h1>${board.no}번 게시글</h1>
		<p>작성자 : ${board.writer}</p>
		<p>제목 : ${board.title}	</p>
		<p>내용 : ${board.content}</p>
		<p>작성일 : ${board.postdate}</p>
		
		<form id="f" method="post">
			<!-- ▼ 수정 시, 가지고 갈 내용 ▼ -->
			<input type="hidden" name="no" value="${board.no}">
			<input type="hidden" name="title" value="${board.title}">
			<input type="hidden" name="content" value="${board.content}">
			<!-- btn -->
			<input type="button" value="수정하러가기" id="update_page_btn">
			<input type="button" value="삭제하기" id="delete_btn">
			<input type="button" value="목록으로이동" id="list_btn">
		</form>
	</div>
</body>
</html>