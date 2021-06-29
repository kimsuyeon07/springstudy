<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"> </script>
	<script>
	
		$(document).ready(function(){
			fn_selectMemberList();
			fn_paging();
			fn_selectMemberByNo();
			fn_insertMember();
			fn_updateMember();
			fn_deleteMember();
			fn_init();
		}); // 페이지 로드 이벤트 (종료)
		
		
		/* 함수 */
		
		var page = 1;  // 기본 초기값 : 전역 변수 page는 페이징을 클릭하면 
					   // 		    fn_pageing()에 의해서 값이 변한다.
		
		// 1. 회원목록
		function fn_selectMemberList() {
			var obj = {
				page: page	
			};
			$.ajax({
				url: 'selectMemberList.do',
				type: 'post',
				contentType: 'application/json',
				data: JSON.stringify(obj),
				dataType: 'json',
				success: function(resultMap) {	
					
					/* 1. 목록 만들기 */
					$('#member_list').empty(); // 기존 목록 초기화 작업
					// resultMap.list 출력
					if (resultMap.exists) { 
						// list가 존재하면 실행하겠다.
						$.each(resultMap.list, function(i, member){
							$('<tr>')
							/* .append( $('<td>').text(member.id) ) */
							.append('<td>' + member.id + '</td>')
							.append('<td>' + member.name + '</td>')
							.append('<td>' + member.address + '</td>')
							.append('<td>' + member.gender + '</td>')
							.append('<input type="hidden" name="no" value="'+ member.no +'" id="no">')
							// .append( $('<input type="hidden" name="no" id="no">').val(member.no) )
							.append('<td> <input type="button" value="조회" id="view_btn"> </td>')
							.appendTo('#member_list');
						});
					} else {
						$('<tr>')
						.append ( '<td colspan="5">등록된 회원이 없습니다.</td>' )
						.appendTo ('#member_list');
					}
					
					/* 2. 페이징 만들기 */
					var paging = resultMap.paging;
					// 1) 기존 페이징 초기화
					$('#paging').empty(); 
					// 2) 이전('◀')
					if (paging.beginPage <= paging.pagePerBlock) { // 이전이 없음 (1블록)
						// class="disable" : 링크 없다.  
						// color:silver; 
						$('<div>').addClass('disable').text('◀').appendTo('#paging');
					} else {
						// class="previous_block" : click 이벤트에서 활용  
						// class="link" : cursor:pointer 
						$('<div>')
						.addClass('previous_block').addClass('link')
						.attr('data-page', paging.beginPage - 1)
						.text('◀')
						.appendTo('#paging');
					}
					// 3) 1 2 3 
					for (let p = paging.beginPage; p <= paging.endPage; p++) {
						if (p == paging.page) { // 현재 페이지 이면
							// class="now_page" : color:limegreen
							$('<div>')
							.addClass('now_page')
							.text(p)
							.appendTo('#paging');
						} else {
							// class="go_page" : click 이벤트에서 활용
							// class="link" : cursor:pointer
							$('<div>')
							.addClass('go_page').addClass('link')
							.attr('data-page', p)
							.text(p)
							.appendTo('#paging');
						}
					}
					// 4) 다음('▶')
					if(paging.endPage == paging.totalPage) {
						// class="disable" : 링크 없다.  
						// color:silver; 
						$('<div>').addClass('disable').text('▶').appendTo('#paging');
					} else {
						// class="next_block" : click 이벤트에서 활용
						// class="link" : cursor:pointer
						$('<div>')
						.addClass('next_block').addClass('link')
						.attr('data-page', paging.endPage + 1)
						.text('▶')
						.appendTo('#paging');
					}
					
				},
				error: function(xhr, textStatus, errorThrown){
				}
			}); //$.ajax(END)
		} /* [END]fn_selectMemberList() */
		
		
		// 2. 회원목록 페이징 (페이징 링크 처리)
		function fn_paging() {	
			
			/* 해당 클래스의 text()를 클릭할 때, */
			$('body').on('click', '.previous_block', function(){
				// → $(this).data('page') == $(this).attr('data-page')
				page = $(this).attr('data-page');
				fn_selectMemberList();
			}); //$('body').on('click', '.previous_block'... (END)
					
			$('body').on('click', '.go_page', function(){
				page = $(this).attr('data-page');
				fn_selectMemberList(); 
			}); //$('body').on('click', '.go_page'... (END)
					
			$('body').on('click', '.next_block', function(){
				page = $(this).attr('data-page');
				fn_selectMemberList(); 
			}); //$('body').on('click', '.next_block'... (END)
					
		} /* [END]fn_paging() */
		
		
		// 3. 회원 정보 보기
		function fn_selectMemberByNo() {
			$('body').on('click', '#view_btn', function(){
				var obj = {
						no : $(this).parent().prev().val()	
					 // no : $(this).parent().siblings('#no').val()
					 // .siblings('#no') >> 같은 부모의 형제들 중에서 id="no"을 찾아준다.	
				}; 
				$.ajax({
					url: 'selectMemberView.do',
					type: 'post',
					contentType: 'application/json',
					data: JSON.stringify(obj),
					dataType: 'json',
					success: function(resultMap) {
						if (resultMap.exists) { // member가 존재하면 실행
							$('input:text[name="id"]').val(resultMap.member.id);
							$('input:text[name="name"]').val(resultMap.member.name);
							$('input:text[name="address"]').val(resultMap.member.address);
							$('input:text[name="gender"]').attr('checked');
							$('input:radio[name="gender"][value="'+ resultMap.member.gender +'"]').prop('checked', true);
							$('#view_area input:hidden[name="no"]').val(resultMap.member.no);
							// 조회버튼을 눌렀을 때, id박스를 수정할 수 없도록 한다.
							$('#id').attr('readonly', true);
						} else { // member가 존재하지 않으면 실행
							alert(obj.no + '번 회원 정보가 없습니다.');
						}
					},
					error: function(xhr, textStatus, errorThrown){
						
					}
				}); //$.ajax (END)
			}) //$('body').on('click', '#view_btn' ... (END)
		} /* [END]fn_selectMemberByNo() */
		
		
		// 4. 회원 삽입
		function fn_insertMember() {
			$('#insert_btn').click(function(){
				var member = {
					id: $('#id').val(),
					name: $('#name').val(),
					address: $('#address').val(),
					gender: $('input:radio[name="gender"]:checked').val()
				}; // member(JavaScript 객체로 생성)
				   // => JSON객체로 변환하는 방법
				   //	 : JSON.stringify(member) 
				$.ajax({
					url: 'insertMember.do',
					type: 'post',
					data: JSON.stringify(member), // JSON 전달
					contentType: 'application/json', 
					dataType: 'json',
					success: function(resultMap) {
						if (resultMap.result > 0) {
							alert('새로운 회원이 등록되었습니다.');
							fn_selectMemberList();
						} else {
							alert('회원 등록에 실패했습니다.');
						}
					}, 
					error: function(xhr, textStatus, errorThrown) {
						/* 
							if (xhr.status == 1001) {
								alert(xhr.responseText);
							}  
							↓ ( == ) 동일한 결과
						*/
						switch (xhr.status) {
						case 1001:
							alert(xhr.responseText);
							break;
						}
					}
					
				}); //$.ajax (END)
			}); // $('#insert_btn').click (END)
		} /* [END]fn_insertMember() */
		
		
		// 5. 회원 수정
		function fn_updateMember() {
			$('#update_btn').click(function(){
				var obj = {
					no : $('#view_area input:hidden[name="no"]').val(),
					id : $('#id').val(),
					name : $('#name').val(),
					address : $('#address').val(),
					gender : $('input:radio[name="gender"]:checked').val()
				};
				console.log(obj);
				
				// 조건문
				/* if ($('#id').val() != obj.id) {
					alert('아이디는 수정할 수 없습니다.');
					return false;
				} else if ($('#name').val() == obj.name && $('#address').val() == obj.address && $('input:radio[name="gender"]:checked').val() == obj.gender ) {
					alert('수정할 내용이 없습니다.');
					return false;
				} */
				
				$.ajax({
					url: 'updateMember.do',
					type: 'post',
					contentType: 'application/json',
					data: JSON.stringify(obj),
					dataType: 'json',
					success: function(resultMap) {
						if (resultMap.count > 0) {
							alert('회원 정보가 수정되었습니다.');
							fn_selectMemberList();
						} else {
							alert('회원 정보 수정에 실패앴습니다.');
						}
					}, 
					error: function(xhr, textStatus, errorThrown) {
						
					}
				}); //$.ajax (END)
			}); //$('#update_btn').click (END)
		} /* [END]fn_updateMember() */
		
		
		// 6. 회원 삭제
		function fn_deleteMember() {
			$('#delete_btn').click(function(){
				if (!confirm('삭제할까요?')) {
					return false;
				} //-----------------------------------  ↓
				var obj = {
					no : $('input:hidden[name="no"]').val()	
				};
				$.ajax({
					url: 'deleteMember.do',
					type: 'post',
					contentType: 'application/json',
					data: JSON.stringify(obj),
					dataType: 'json',
					success: function(resultMap) {
						if (resultMap.count > 0) {
							alert('회원 정보가 삭제되었습니다.');
							// 입력창 초기화
							$('#id').val('').attr('readonly', false);
							$('#name').val('');
							$('#address').val('');
							$('input:radio[name="gender"]').prop('checked', false);
							// 회원 목록 다시 불러오기
							fn_selectMemberList();
						} else {
							alert('회원 정보 삭제가 실패했습니다.');
						}
					},
					error: function(xhr, textStatus, errorThrown) {
						
					}
				}); //$.ajax (END)
			}); //$('#delete_btn').click (END)
		} /* [END]fn_deleteMember() */
		
		// 7. 초기화
		function fn_init() {
			$('#init_btn').click(function(){
				$('#id').val('').attr('readonly', false);
				$('#name').val('');
				$('#address').val('');
				$('input:radio[name="gender"]').prop('checked', false);
			});
		}
		
		
	</script>
	
	<style>
		#paging {
			width: 50%;
			margin: 5px auto;
			display: flex;
			justify-content: space-between;
			text-align: center;
		}
		.disable { color: silver; }
		.link {cursor: pointer; }
		.now_page { color: limegreen; font-weight:600; }
		table {box-sizing: border-box; border-collapse: collapse; width:390px;}
		thead tr {background-color:mediumseagreen; color: white;}
		thead td {padding: 7px 15px;}
		tr {text-align: center;}
		td {padding: 5px 15px;}
	</style>
	
</head>
<body>

	<h1>회원관리</h1>
	<div id="view_area">
		<label>아이디<input type="text" name="id" id="id"></label>			<br>
		<label>이름<input type="text" name="name" id="name"></label>			<br>
		<label>주소<input type="text" name="address" id="address"></label>	<br>
		성별
		<input type="radio" name="gender" value="남" id="male"> <label for="male">남성</label>
		<input type="radio" name="gender" value="여" id="female"> <label for="female">여성</label>
		<br>
		<input type="hidden" name="no" id="no">
		
		<!-- == <input type="button" value="초기화" id="init_btn" onclick="location.href='manageMember.do'"> -->
		<input type="button" value="초기화" id="init_btn">
		<input type="button" value="등록" id="insert_btn">	 
		<input type="button" value="수정" id="update_btn">	 
		<input type="button" value="삭제" id="delete_btn">		 
		<div id="btn"></div>
	</div>
	<br><br>
	<hr>
	<br><br>
	
	<table border="1">
		<thead>
			<tr>
				<td>아이디</td>
				<td>이름</td>
				<td>주소</td>
				<td>성별</td>
				<td>-</td>
			</tr>
		</thead>
		<tbody id="member_list"> </tbody>
		<tfoot>
			<tr>
				<td colspan="5">
					<!-- 페이징 표시  [ 이전  1 2 3 4 5  다음 ] -->
					<div id="paging"></div>
				</td>
			</tr>
		</tfoot>
	</table>

</body>
</html>