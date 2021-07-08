<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>갤러리 게시글 만들기</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"> </script>
	<script>
		$(document).ready(function(){
			fn_insert();
			fn_init();
		}) // 페이지 로드 이벤트
		
		/* 함수 생성 */
		function fn_insert() {
			$('#f').submit(function(event){
				if ($('#title').val() == '') {
					alert('제목을 입력해주세요!');
					event.preventDefault();
					return false;
				}
			});
		} //
		
		function fn_init() {
			$('#init_btn').click(function(){
				$('#title').val('');
				$('#content').val('');
				$('#image').val('');
			});
		} //
		
	</script>
	
	<style>
		.textarea {display:flex;}
	</style>
</head>
<body>

	<div class="container">
		<header>
			<h1>갤러리 게시글 작성</h1>
		</header>
		
		<section>
			<!-- 파일을 추가할 때는 [ enctype="multipart/form-data" ] ** 필수 ** -->
			<form id="f" action="insert_gallery.do" method="post" enctype="multipart/form-data">
				<label><span>작성자</span> <input type="text" name="id" id="id" value="${loginUser.id}" readonly></label>
				<br>
				<label><span>제목</span> <input type="text" name="title" id="title"></label>
				<br>
				<div class="textarea">
					<span>내용</span>
					<textarea rows="20" cols="20" name="content" id="content" placeholder="내용을 입력하세요."></textarea>
				</div>
				<br>
				<span>첨부 이미지</span>
				<!-- 다중 첨부는  "multiple"사용한다 -->
				<input type="file" name="image" id="image"> 
				<br>
				<button>글 작성하기</button>
				<input type="button" value="초기화" id="init_btn">
				<input type="button" value="목록으로 가기" onclick="location.href='galleryBoardPage.do'">
			</form>
		</section>
		
		<footer></footer>
	</div>

</body>
</html>