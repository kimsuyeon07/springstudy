<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Contact Insert</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			const f = $('#f');
			const name = $('#name');
			const tel = $('#tel');
			const addr = $('#addr');
			const email = $('#email');
			const note = $('#note');
			const list_btn = $('#list_btn');
			
			list_btn.click(function(){
				location.href='selectContactList.do';
			})
			
			f.submit(function(event){
				if (name.val() == '' || tel.val()=='' || addr.val()=='' || email.val()=='' || note.val()=='') {
					alert('정보를 입력해주세요!');
					event.preventDefault();
					return false;
				}
			})
			
			
			
		})
	</script>
</head>
<body>

	<div id="insert_con">
		<h1>연락처 등록</h1>
		<form action="insertContact.do" id="f" method="post">
			<p>이름</p>
			<input type="text" name="name" id="name">	<br><br>
			<p>전화</p>
			<input type="text" name="tel" id="tel">		<br><br>
			<p>주소</p>
			<input type="text" name="addr" id="addr">	<br><br>
			<p>이메일</p>
			<input type="text" name="email" id="email">	<br><br>
			<p>비고</p>
			<input type="text" name="note" id="note">	<br><br>
			<button>연락처 저장하기</button>
			<input type="button" value="전체연락처" id="list_btn">
		</form>
	</div>

</body>
</html>