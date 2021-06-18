<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Welcome</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		// 페이지 로드 이벤트
		$(document).ready(function(){
			setTimeout(function(){
				location.href='selectContactList.do';
			}, 3000);
		}) // (END) 페이지 로드 이벤트
	</script>

</head>
<body>

	<h1>3초 뒤에 연락처 목록으로 이동합니다.</h1>

</body>
</html>