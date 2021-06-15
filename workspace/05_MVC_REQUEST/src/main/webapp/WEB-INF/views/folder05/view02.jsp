<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	</head>
<body>
	
	<h1>view02.jsp</h1>
	
	<!-- index.jsp에서 보낸 '파라미터' -->
	${param.name}	<br>
	${param.age}	<br><br>
	
	<!-- controller의 Model이 저장한 '속성' -->
	${name} 	<br>
	${age}		<br><br>
	
	
</body>
</html>