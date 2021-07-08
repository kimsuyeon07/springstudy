<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>UPDATE_GalleryBoard</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"> </script>
	<script>
	
		$(document).ready(function(){
			fn_update();
			fn_init();
		}); //페이지 로드 이벤트 (종료)
		
		/* 함수 생성 */
		function fn_update() {
			$('#update_btn').click(function(){
				if ( $('#title').val() == '' ) {
					alert('제목은 필수 입니다!');
					return false;
				} else {
					$('#f').attr('action', 'updateGallery.do');
					$('#f').submit();
				}
			});
		} //
		function fn_init() {
			$('#init_btn').click(function(){
				$('#title').val('');
				$('#content').val('');
				$('#file').val('');
			});
		}//
		
		
		
	</script>
	<style>
		*{margin:0 auto; padding: 0;}
		img {width:700px;}
	</style>
</head>
<body>

	<div class="container">
	
		<header>
			<h1>UPDATE</h1>
		</header>
		
		<section>
			<form id="f" enctype="multipart/form-data" method="post">
				<div id="id_box" class="box">
				<span>작성자</span>
				<input type="text" name="id" id="id" value="${viewGallery.id}" readonly>
				</div>
				<div id="title_box" class="box">
					<input type="text" name="title" id="title" value="${viewGallery.title}">
				</div>			
				<div id="content_box" class="box">
					<textarea rows="50" cols="50" name="content" id="content">${viewGallery.content}</textarea>
				</div>		
				<div id="img_box" class="box">
					<img alt="${viewGallery.imagename}" src="resources/archive/${viewGallery.imagename}">
				</div>		
				<div id="file_box" class="box">
					<span>첨부 이미지</span>
					<!-- 다중 첨부는  "multiple"사용한다 -->
					<input type="file" name="image" id="image">
					<!-- hidden -->
					<input type="hidden" name="no" id="no" value="${viewGallery.no}"> 
					<input type="hidden" name="image0" id="image0" value="${viewGallery.imagename}"> 
				
				</div>
				
				<input type="button" value="초기화" id="init_btn">
				<input type="button" value="수정하기" id="update_btn">
				<input type="button" value="목록으로 돌아가기" onclick="location.href='index.do'">
				 
			</form>
		</section>		
		
		
	</div>

</body>
</html>