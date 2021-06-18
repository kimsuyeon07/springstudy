<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Contact View</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			const f = $('#f');
			const name = $('#name');
			const tel = $('#tel');
			const addr = $('#addr');
			const email = $('#email');
			const note = $('#note');
			const delete_btn = $('#delete_btn');
			const list_btn = $('#list_btn');
			
			// 버튼 클릭
			list_btn.click(function(){
				location.href='selectContactList.do';
			})
			delete_btn.click(function(){
				if (confirm('삭제하시겠습니까?')) {
					location.href='deleteContact.do?no=${contact.no}';
				}
			})
			
			// form Submit
			f.submit(function(event){
				if (name.val() == '' || tel.val()=='' || addr.val()=='' || email.val()=='' || note.val()=='') {
					alert('정보를 입력해주세요!');
					event.preventDefault();
					return false;
				} 
				else if (name.val() == '${contact.name}' && tel.val()=='${contact.tel}' && addr.val()=='${contact.addr}' && email.val()=='${contact.email}' && note.val()=='${contact.note}') {
					alert('수정할 정보가 없습니다.');
					event.preventDefault();
					return false;
				} 
				
				// 수정 결과 확인
				if ('${param.updateResult}' != '') {
					if ('${param.updateResult}' == '0') {
						alert('수정을 완료하지 못하였습니다.');
					} else {
						alert('수정이 완료되었습니다.');
					}
				}
				
			})
			
		})
	</script>
</head>
<body>

	<div id="view_con">
		<h1>연락처 등록</h1>
		<form action="updateContact.do" id="f" method="post">
			<p>이름</p>
			<input type="text" name="name" id="name" value="${contact.name}">	<br><br>
			<p>전화</p>
			<input type="text" name="tel" id="tel" value="${contact.tel}">		<br><br>
			<p>주소</p>
			<input type="text" name="addr" id="addr" value="${contact.addr}">	<br><br>
			<p>이메일</p>
			<input type="text" name="email" id="email" value="${contact.email}">	<br><br>
			<p>비고</p>
			<input type="text" name="note" id="note" value="${contact.note}">	<br><br>
			<input type="hidden" name="no" id="no" value="${contact.no}">
			<button>수정하기</button>
			<input type="button" value="삭제하기" id="delete_btn">
			<input type="button" value="전체연락처" id="list_btn">
		</form>
	</div>

</body>
</html>