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
		}); // 페이지 로드 이벤트 (종료)
		
		
		/* 함수 */
		
		var page = 1;  // 기본 초기값
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
					$('#member_list').empty(); // 기존 목록 초기화 작업
					// resultMap.list 출력
					if (resultMap.exists) { 
						// list가 존재하면 실행하겠다.
						$.each(resultMap.list, function(i, member){
							$('<tr>')
							/* .append('<td>').text(member.id) */
							.append('<td>' + member.id + '</td>')
							.append('<td>' + member.name + '</td>')
							.append('<td>' + member.address + '</td>')
							.append('<td>' + member.gender + '</td>')
							.append('<input type="hidden" name="no" value="'+ member.no +'" id="no">')
							.append('<td> <input type="button" value="조회" id="view_btn"> </td>')
							.appendTo('#member_list');
						});
					} else {
						$('<tr>')
						.append ( '<td colspan="5">등록된 회원이 없습니다.</td>' )
						.appendTo ('#member_list');
					}
				},
				error: function(xhr, textStatus, errorThrown){
				}
			}); //$.ajax(END)
		} /* [END]fn_selectMemberList() */
		
		
		// 2. 회원목록 페이징
		function fn_paging() {
			
		} /* [END]fn_paging() */
		
		
		// 3. 회원 정보 보기
		function fn_selectMemberByNo() {
			
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
			
		} /* [END]fn_updateMember() */
		
		
		// 6. 회원 삭제
		function fn_deleteMember() {
			
		} /* [END]fn_deleteMember() */
		
	</script>
	
</head>
<body>

	<h1>회원관리</h1>
	<label>아이디<input type="text" name="id" id="id"></label>			<br>
	<label>이름<input type="text" name="name" id="name"></label>			<br>
	<label>주소<input type="text" name="address" id="address"></label>	<br>
	성별
	<input type="radio" name="gender" value="남" id="male"> <label for="male">남성</label>
	<input type="radio" name="gender" value="여" id="female"> <label for="female">여성</label>
	<br>
	<input type="button" value="등록" id="insert_btn">					<br>
	
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