<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style>
		*{
			margin: 0 auto;
			padding: 0;
			box-sizing: border-box;
			border-collapse: collapse;
		}
		
		select, input {
			height:35px;
			border:1px solid lightgray;
			outline:none;
		}
		form {text-align:center;}
		select {width:120px;}
		input[name="query"] {width:200px; padding-left:10px;}
		input[value="초기화"] {margin-left:10px;}
		input[type="button"] {padding: 0 12px; margin-top:-3px;}
		input[type="button"]:hover {cursor:pointer; background-color:black; color:white;}
		span {padding-left:10px; font-size:13px; color:darkgray;}
		
		
		table {width:500px; text-align:center;}
		thead tr{font-size:15px; font-weight:600; background-color:linen;}
		tr {}
		td {height:40px;}
		
	</style>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"> </script>
	<script>
		$(function(){
			fn_selectAll();
			fn_selectQuery();
			fn_init();
		}); // 페이지 로드 이벤트 (종료)
		
		/* 함수 생성 */
		
		function fn_init() { // 검색창의 초기화
			$('#init_btn').click(function(){
				$('#column').val('');
				$('#query').val('');
				fn_selectAll();
			}) //(end) $('#init_btn').click
		} //[END]fn_init()
		
		function fn_selectAll() { // 게시글 목록 반환
			$.ajax({
				url: 'selectAll.do',
				type: 'get',
				dataType: 'json',
				success: function(resultMap) { 
					alert(resultMap.message);
					fn_listTable(resultMap.status, resultMap.list); 	
				}
			}); //$.ajax (END)
		} //[END]fn_selectAll()
		
		
		function fn_listTable(status, list) {
			
			$('#list').empty(); //테이블 목록 초기화(제거)
			
			if(status == 200) {
				$.each(list, function(i, board) {
					$('<tr>')
					.append( $('<td>').text(board.no) )
					.append( $('<td>').text(board.writer) )
					.append( $('<td>').text(board.title) )
					.append( $('<td>').text(board.content) )
					.append( $('<td>').text(board.postdate) )
					.appendTo( '#list' );
				}); //$.each() (END)
			} else if(status == 500) {
				$('<tr>')
				.append( $('<td colspan="5">').text("게시글이 없습니다.") )
				.appendTo( '#list' );
			}
		} //[END]fn_listTable()
		
		
		function fn_selectQuery() { // 검색 결과 게시글 목록 반환
			$('#search_btn').click(function(){
				// 조건식(검색이 안되는 조건을 생성)
				if( $('#column').val() == '' ) {
					alert('검색 카테고리를 선택하세요!');
					return false;
				}
				// 검색결과를 넘겨준다.
				$.ajax({
					url: 'selectQuery.do',
					type: 'get',
					data: $('#f').serialize(),
					dataType: 'json',
					success: function(resultMap) { 
						alert(resultMap.message);
						fn_listTable(resultMap.status, resultMap.list); 
					}
				}); //$.ajax (END)
			}) //(end)$('#search_btn').click
		} //[END]fn_selectQuery()
				
	</script>
</head>
<body>

	<br><br>
	
	<form id="f" method="get">
		<select name="column" id="column">
			<option value="">:::선택안함:::</option>
			<option value="TITLE">TITLE</option>
			<option value="WRITER">WRITER</option>
			<option value="CONTENT">CONTENT</option>
			<option value="BOTH">TITLE+CONTNET</option>
		</select>
		<input type="text" id="query" name="query" placeholder="검색 내용 입력">
		<input type="button" value="검색" id="search_btn">
		<span> / </span>
		<input type="button" value="초기화" id="init_btn">
	</form>
	
	
	<br><br>
	
	
	<table border="1">
	
		<thead>
			<tr>
				<td>번호</td>
				<td>작성자</td>
				<td>제목</td>
				<td>내용</td>
				<td>작성일</td>
			</tr>
		</thead>
		
		<tbody id="list"></tbody>
		
		<tfoot></tfoot>
		
	</table>
	
</body>
</html>