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
			const f = $('#f');
			const writer = $('#writer');
			const title = $('#title');
			const content = $('#content');
			
			f.submit(function(event) {
				if (writer.val() == '' || title.val() == '' || content.val() == '') {
					alert('내용을 입력해주세요!');
					event.preventDefault();
					return false;
				}
			})
			
		})
	</script>
</head>
<body>

	<div id="insert_con" class="btn_basic">
		<h1>게시글 작성하기</h1>
		<form action="insertBoard.do" id="f">
			<p>작성자</p>
			<input type="text" name="writer" id="writer"> 	<br><br>
			<p>제목</p>
			<input type="text" name="title" id="title">		<br><br>
			<p>내용</p>
			<input type="text" name="content" id="content">		<br><br>
			<button>작성완료</button>
		</form>	
	</div>

</body>
</html>