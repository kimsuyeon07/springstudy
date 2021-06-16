<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			const f = $('#f');
			const list_btn = $('#list_btn');
			const title = $('#title');
			const content = $('#content');
			
			list_btn.click(function(){
				location.href='selectBoardList.do';
			})
			
			f.submit(function(event){
				if (title.val() == '${board.title}' && content.val() == '${board.content}') {
					alert('수정할 내용이 없습니다.');
					event.preventDefault();
					return false;
				}
			})
			
		}) // 페이지 로드 이벤트 종료
	</script>
</head>
<body>

	<div id="update">
		<h1>${board.no}번 게시글 수정 페이지</h1>
		<form id="f" action="updateBoard.do" method="post">
			제목 <br>
			<input type="text" name="title" id="title" value="${board.title}">	<br><br>
			내용 <br>
			<input type="text" name="content" id="content" value="${board.content}">	<br><br>
			<input type="hidden" name="no" value="${board.no}">
			
			<button>수정하기</button>
			<input type="button" value="목록으로 이동" id="list_btn">
		</form>
	</div>

</body>
</html>