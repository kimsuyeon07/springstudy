<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>GalleryBoard-View</title>
	<link rel="stylesheet" href="resources/asset/css/study.css">
	<link rel="stylesheet" href="resources/asset/css/galleryView.css">
	<link rel="stylesheet" href="resources/asset/css/paging.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"> </script>
	<script>
	
		$(document).ready(function(){
			fn_replyList();
			fn_paging();
			fn_insertReply();
			fn_deleteReply();
		}); //페이지 로드 이벤트 (종료)
		
		/* 함수 생성 */
		var page = 1;
		function fn_replyList() {
			$.ajax({
				url: 'replyList.do',
				type: 'get',
				data: 'galleryNo=${viewGallery.no}&page='+page,
				dataType: 'json',
				success: function(resultMap) {
					fn_reply_append(resultMap);
				}
			});
		}//
		var list = null;
		function fn_reply_append(resultMap){
			list = resultMap.list;
			console.log(list);
			$('#replyList').empty();
			if (resultMap.status == 200) {
				$.each(list, function(i, reply){
					$('<tr>')
					.append( $('<td class="en_B_lt">').text(reply.id) )
					.append( $('<td>').html(reply.content + '<span>' + reply.postdate + ', ip ( ' + reply.ip + ') </span>') )
					.append( $('<td>').html('<input type="button" value="삭제" id="deleteReply_btn">') )
					.append( $('<input type="hidden" name="no" id="no">').val(reply.no) )
					.append( $('<input type="hidden" name="id" id="id">').val(reply.id) )
					.appendTo('#replyList');
				});
			} else {
				$('<tr>')
				.append( $('<td colspan="3">').text('댓글을 달아보세요!') )
				.appendTo('#replyList');
			}
			/* success in 페이징 처리 */
			var paging = resultMap.paging;
			$('#paging').empty();
			// ◀
			if (paging.beginPage <= paging.pagePerBlock) {
				// 링크없음
				$('<div>').addClass('notLink').text('◀').appendTo('#paging');
			} else {
				// 이전 페이지로 이동하는 링크 있음
				$('<div>')
				.addClass('link').addClass('previous_block')
				.attr('data-page', paging.beginPage-1)
				.text('◀')
				.appendTo('#paging');
			}
			// 1 2 3 4 5 
			for (let p = paging.beginPage ; p <= paging.endPage ; p++) {
				if (paging.page == p) {
					// 현재 페이지이면 링크 없다 / 현재페이지 표시
					$('<div>').addClass('now_page').text(p).appendTo('#paging');
				} else {
					// 다른 페이지로 이동할 수 있는 링크 있음
					$('<div>')
					.addClass('link').addClass('go_page')
					.attr('data-page', p)
					.text(p)
					.appendTo('#paging');
				}
			}
			// ▶
			if (paging.endPage == paging.totalPage) {
				// 링크없음
				$('<div>').addClass('notLink').text('▶').appendTo('#paging');
			} else {
				// 다음페이지로 이동할 수 있는 링크 있음
				$('<div>')
				.addClass('link').addClass('next_block')
				.attr('data-page', paging.endPage+1)
				.text('▶')
				.appendTo('#paging');
			}
		} //
		
		// 페이징 링크 처리
		function fn_paging() {
			$('body').on('click', '.previous_block', function(){
				page = $(this).attr('data-page');
				fn_replyList();
			});
			$('body').on('click', '.next_block', function(){
				page = $(this).attr('data-page');
				fn_replyList();
			});
			$('body').on('click', '.go_page', function(){
				page = $(this).attr('data-page');
				fn_replyList();
			});
		} //
		
		function fn_insertReply() {
			$('#reply_btn').click(function(){
				if($('#replyContent').val() == '') {
					alert('댓글 내용을 입력하세요.');
					return false;
				} 
				$.ajax({
					url: 'insertReply.do',
					type: 'post',
					data: $('#f').serialize(),
					dataType: 'json',
					success: function(resultMap){
						if (resultMap.status == 200) {
							fn_replyList();
						} else {
							alert('댓글 생성 오류!');
						}
					}
				});
			});
		} //
		
		function fn_deleteReply() {
			$('body').on('click', '#deleteReply_btn', function(){
				var id = ($(this).parent().parent().find( $('input:hidden[name="id"]') )).val();
				var no = ($(this).parent().parent().find( $('input:hidden[name="no"]') )).val();
				
				if ( '${loginUser.id}' != id ) {
					alert('작성자만 삭제할 수 있습니다.');
					return false;
				}
				
				if (confirm('댓글을 삭제하시겠습니까?')) {
					
					$.ajax({
						url: 'deleteReply.do',
						type: 'get',
						data: 'no=' + no,
						dataType: 'json',
						success: function(resultMap) {
							if (resultMap.status == 200) {
								alert('댓글을 삭제했습니다.');
								fn_replyList();
							} else {
								alert('댓글을 삭제에 실패했습니다.');								
							}
						}
					});
					
				}
				
			});
		}
		
		
	</script>
	
</head>
<body>

	<div id="galleryView" class="container con">
	
		<header>
			<h1 class="B"> <span class="en_B_lt">GALLERY BOARD</span>_ ${viewGallery.title}</h1>
		</header>
		
		<section>
			
			<c:if test="${loginUser.id == viewGallery.id}">
				<a href="updateGalleryPage.do?no=${viewGallery.no}&id=${viewGallery.id}">갤러리 게시글 수정</a>
				<a href="deleteGallery.do?no=${viewGallery.no}">삭제하기</a>
			</c:if>
			<div id="view_box">
				<div id="id_box" class="box clear">
					<p class="en_B"><span class="L span1">작성자</span><span class="span2">|</span>${viewGallery.id}</p>
				</div>
				<div id="title_box" class="box">
					<p class="B">${viewGallery.title}</p>
				</div>			
				<div id="content_box" class="box">
					<p>${viewGallery.content}<p>
				</div>		
				<c:if test="${ viewGallery.imagename != null }">	
					<div id="img_box" class="box">
						<img alt="${viewGallery.imagename}" src="resources/archive/${viewGallery.imagename}">
					</div>			
				</c:if>
			</div>
			
		</section>		
		
		<footer>
			
			<div id="reply_form">
			
				<c:if test="${not empty loginUser}">
					<form id="f">
						<div id="reply_box">
							<span class="en_B_lt reply_span1">${loginUser.id}</span>
							<span class="reply_span2">|</span>
							<input type="text" name="replyContent" id="replyContent" placeholder="댓글 입력">
						</div>
						<input type="hidden" name="galleryNo" id="galleryNo" value="${viewGallery.no}">
						<input type="hidden" name="id" id="id" value="${loginUser.id}">
						<input type="button" value="입력" id="reply_btn">
					</form>
				</c:if>
				
				<div id="reply_table">
					<table>
						<tbody id="replyList">
						</tbody>
					</table>
					<span id="paging"></span>
				</div>
				
			</div>
			
			<input type="button" value="목록으로 돌아가기" onclick="location.href='galleryBoardPage.do'">
		
		</footer>
		
		
	</div>

</body>
</html>