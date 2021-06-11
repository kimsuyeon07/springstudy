<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<img src="/mvc01/resources/eagle.jpg" alt="독수리" style="width:100%;" />
<br>
<img src="/mvc01/assets/image/wall.jpg" alt="벽" style="width:100%;" />
<!-- http://localhost:9090/mvc01/assets/image/wall.jpg -->
<!-- 
		servlet-context.xml 작업을 해줘야 한다. (디스펜던스)
		↓
		<resources mapping="/assets/**" location="/assets/" /> 
		▶ ▶ ▶ resources의 추가가 가능하다  
-->



</body>
</html>
