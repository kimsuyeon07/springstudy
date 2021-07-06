<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>GalleryBoard</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"> </script>
	<script>
	
		$(document).ready(function(){
			fn_galleryBoardList();
			fn_paging();
		}); //페이지 로드 이벤트 (종료)
		
		/* 함수 생성 */
		var page = 1; //(현재 페이지 _초기화)
		function fn_galleryBoardList() {
			$.ajax({
				url: 'galleryBoardList.do',
				type: 'get',
				data: 'page=' + page,
				dataType: 'json',
				success: function(resultMap){
					console.log(resultMap);
					fn_table_append(resultMap);
				}
			}) //$.ajax
		} //
		var list = null;
		function fn_table_append(resultMap) {
			list = resultMap.list;
			$('#galleryBoard_list').empty();
			if (resultMap.status == 200) {
				$.each(list, function(i, galleryBoard) {
					$('<tr>')
					.append( $('<td>').text(galleryBoard.no) )
					.append( $('<td>').text(galleryBoard.id) )
					.append( $('<td>').html('<a href="galleryBoardView.do?no='+ galleryBoard.no +'&id='+ galleryBoard.id +'">galleryBoard.title</a>') )
					.append( $('<td>').text(galleryBoard.hit) )
					.append( $('<td>').text(galleryBoard.lastdate) )
					.appendTo('#galleryBoard_list');
				}) // $.each
			} else {
				$('<tr>')
				.append( $('<td colspan="6">').text('갤러리 게시글이 없습니다.') )
				.appendTo('#galleryBoard_list');
			}
			/* success in 페이징 처리 */ 
			var paging = resultMap.paging;
			console.log(paging);
			$('#paging').empty();
			// ◀
			if (paging.beginPage <= paging.pagePerBlock) { 
				// 시작페이지가 보여주는 페이지보다 작은 숫자이면 (이전으로 가는 링크가 없다.)
				$('<div>').addClass('notLink').text('◀').appendTo('#paging');
			} else {
				// 시작페이지가 보여주는 페이지보다 큰 숫자이면 (이전으로 가는 링크가 존재한다.)
				$('<div>')
				.addClass('link').addClass('previous_block')
				.attr('data-page', paging.beginPage - 1)
				.text('◀')
				.appendTo('#paging');
			}
			// 1 2 3 4 5
			for (let p = paging.beginPage; p <= paging.endPage; p++) {
				// 현재 페이지이면 (링크는 없다.)
				if (p == paging.page) {
					$('<div>').addClass('now_page').text(p).appendTo('#paging');
				} else {
					$('<div>')
					.addClass('link').addClass('go_page')
					.attr('data-page', p)
					.text(p)
					.appendTo('#paging');
				}
			}
			// ▶
			if (paging.endPage == paging.totalPage) {
				// 마지막 페이지와 총 페이지가 동일하면 (다음으로 가는 링크는 없다.)
				$('<div>').addClass('notLink').text('▶').appendTo('#paging');
			} else {
				// 마지막 페이지가 총 페이지보다 작으면 (다음으로 가는 링크가 존재한다.)
				$('<div>')
				.addClass('link').addClass('next_block')
				.attr('data-page', paging.endPage + 1)
				.text('▶')
				.appendTo('#paging');
			}
		} //
		
		// 페이징 링크 처리
		function fn_paging() {
			$('body').on('click', '.previous_block', function(){
				page = $(this).attr('data-page');
				fn_galleryBoardList();
			});
			$('body').on('click', '.next_block', function(){
				page = $(this).attr('data-page');
				fn_galleryBoardList();
			});
			$('body').on('click', '.go_page', function(){
				page = $(this).attr('data-page');
				fn_galleryBoardList();
			});
		}
		
	</script>
</head>
<body>

	<div class="container">
		<h1>GALLERY BOARD</h1>
		
		<c:if test="${not empty loginUser}">
			<div id="insert">
				<a href="insert_galleryBoardPage.do"> 갤러리 게시글 작성하러 가기 </a>
			</div>
		</c:if>
		<div id="back">
			<a href="index.do"> 뒤로 가기 </a>
		</div>
		
		<table border="1">
			<thead>
				<tr>
					<td>순번</td>
					<td>작성자</td>
					<td>제목</td>
					<td>조회수</td>
					<td>최종수정일</td>
				</tr>
			</thead>
			<tbody id="galleryBoard_list"></tbody>
			<tfoot>
				<tr>
					<td colspan="5" id="paging"></td>
				</tr>
			</tfoot>
		</table>
	</div>

</body>
</html>