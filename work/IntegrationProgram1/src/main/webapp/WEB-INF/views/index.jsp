<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"> </script>
	<script>
		$(document).ready(function(){
			fn_selectList();
			fn_search();
			fn_init();
		}); // 페이지 로드 이벤트;
		
		
		/* 함수 생성 */
		function fn_selectList() {
			$.ajax({
				url: 'selectAll.do',
				type: 'get',
				dataType: 'json',
				success: function(resultMap) {
						alert(resultMap.message);
						fn_table_append(resultMap);
				}
			}); //$.ajax
		}
		var list = null;
		function fn_table_append(resultMap) {
			$('#list').empty();
			list = resultMap.list;
			if (resultMap.status == 200) {
				$.each(list, function(i, searchBoard) {
					$('<tr>')
					.append( $('<td>').text(searchBoard.title) )
					.append( $('<td>').text(searchBoard.content) )
					.append( $('<td>').text(searchBoard.regdate) )
					.appendTo('#list');
				}); //$.each
			} else {
				$('<tr>')
				.append( $('<td colspan="3">').text('게시글이 없습니다.') )
				.appendTo('#list');
			}
		}
		
		function fn_search() {
			$('#search_btn').click(function(){
				if ($('#query').val() == '') {
					alert('검색어를 입력해주세요.');
					return false;
				}
				$.ajax({
					url: 'search.do',
					type: 'get',
					data: $('#f').serialize(),
					dataType: 'json',
					success: function(resultMap) {
						alert(resultMap.message);
						fn_table_append(resultMap);
					}
				}); //$.ajax
			}); //$('#search_btn').click
		}
		
		function fn_init() {
			$('#init_btn').click(function(){
				$('#query').val('');
				fn_selectList();
			});
		}
		
		
		
		
		
	</script>
</head>
<body>

	<div class="container">
		<form id="f">
			<select name="column" id="column">
				<option value="TITLE">제목</option>
				<option value="CONTENT">내용</option>
			</select>
			<input type="text" name="query" id="query">
			<input type="button" value="검색" id="search_btn">
			<input type="button" value="초기화" id="init_btn">
		</form>
		
		<hr>
		
		<div id="conList">
			<table border="1">
				<thead>
					<tr>
						<td>제목</td>
						<td>내용</td>
						<td>작성일</td>
					</tr>	
				</thead>
				<tbody id="list"></tbody>
			</table>
		</div>
		
	</div>
	

</body>
</html>