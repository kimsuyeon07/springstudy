<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style>
		* {margin: 0 auto; padding: 0; text-align: center; text-decoration: none;}
		body {width: 100%;}
		a {
			display:inline-block;
			padding:15px 30px;
			border: 1px solid lightgray; 
			color: red;
			margin-top:150px; 
			font-size:18px;
		}
		a:hover {
			font-weight:600; 
			text-decoration:underline; 
			border-color:red; 
			background-color:#f9f9f9;
			cursor:pointer;
		}
		#rest {margin-top:20px;}
	</style>
</head>
<body>

	<a href="manageMember.do">회원관리로 이동</a>
	<br><br>
	<a href="manageMemberRest.do" id="rest">회원관리(Rest)로 이동</a>

</body>
</html>