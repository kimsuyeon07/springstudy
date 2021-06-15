<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>	

	${param.name}		<br>
	${param.age}		<br>
	
	<br><br>
	
	<!-- RedirectAttributes 인터페이스의 addFlashAttribute()로 저장하면 속성으로 저장된다. -->
	${name}			<br>
	${age} 			<br>

</body>
</html>