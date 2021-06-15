<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>

	<h1>view02.jsp</h1>
	이름 : ${name}   <br>
	나이 : ${age}	   <br>
	취미 : ${hobbies.toString()}	   <br>
	<!-- ModelAndView 클래스로 넘긴 값(attribute) : request로 넘어온다. -->

</body>
</html>