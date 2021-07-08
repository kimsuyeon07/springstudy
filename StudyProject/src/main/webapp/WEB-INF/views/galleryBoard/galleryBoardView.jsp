<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>GalleryBoard-View</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"> </script>
	<script>
	
		$(document).ready(function(){
			
		}); //페이지 로드 이벤트 (종료)
		
		/* 함수 생성 */
		
	</script>
	<style>
		*{margin:0 auto; padding: 0;}
		img {width:700px;}
	</style>
</head>
<body>

	<div class="container">
	
		<header>
			<h1>GALLERY BOARD - ${viewGallery.title}</h1>
		</header>
		
		<section>
			<c:if test="${loginUser.id == viewGallery.id}">
				<a href="updateGalleryPage.do?no=${viewGallery.no}&id=${viewGallery.id}">갤러리 게시글 수정</a>
			</c:if>
			<div id="id_box" class="box">
				<p><span>작성자</span>${viewGallery.id}</p>
			</div>
			<div id="title_box" class="box">
				<p>${viewGallery.title}</p>
			</div>			
			<div id="content_box" class="box">
				<p>${viewGallery.content}<p>
			</div>		
			<c:if test="${ viewGallery.imagename != null }">	
				<div id="img_box" class="box">
					<img alt="${viewGallery.imagename}" src="resources/archive/${viewGallery.imagename}">
				</div>			
			</c:if>
			
		</section>		
		
		<footer>
			<input type="button" value="목록으로 돌아가기" onclick="location.href='galleryBoardPage.do'">
		</footer>
		
		
	</div>

</body>
</html>