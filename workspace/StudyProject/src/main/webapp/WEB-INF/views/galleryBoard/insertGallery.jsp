<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>갤러리 게시글 만들기</title>
	<link rel="stylesheet" href="resources/asset/css/study.css">
	<link rel="stylesheet" href="resources/asset/css/updateGallery.css">
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

	<div id="insertGallery" class="container">
	
		<header>
			<h1 class="en_B_lt">INSERT</h1>
		</header>
		
		<section>
			<form id="f" action="insertGallery.do" enctype="multipart/form-data" method="post">
			
				<div id="btn">
					<input type="button" value="초기화" id="init_btn">
					<input type="button" value="목록으로 돌아가기" onclick="location.href='index.do'">
				</div>
				
				<div id="id_box" class="box">
				<span class="B">작성자</span>
				<input type="text" name="id" id="id" value="${loginUser.id}" readonly class="en_R_lt">
				</div>
				
				<div id="title_box" class="box">
					<span class="B">제목</span>
					<input type="text" name="title" id="title">
				</div>		
					
				<div id="file_box" class="box">
					<span class="B">첨부 이미지</span>
					<!-- 다중 첨부는  "multiple"사용한다 -->
					<input type="file" name="image" id="image">
				</div>
				
				<div id="content_box" class="box">
					<span class="B">내용</span>
					<textarea rows="50" cols="50" name="content" id="content"></textarea>
				</div>		
				
				<!-- 저장 버튼 -->
				<button>저장하기</button>
			
			</form>
		</section>		
		
		
	</div>

</body>
</html>