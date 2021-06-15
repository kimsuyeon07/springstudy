<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>

	<h1>index.jsp</h1>
	
	<a href="/mvc03/loginPage.do">로그인 하러 가기</a>
	
	<hr>
	<a href="/mvc03/view01">folder01/view01.jsp로 이동하기</a>	 		<br>
	<a href="/mvc03/a/b/c/d/e/v02">folder01/view02.jsp로 이동하기</a>	<br>
	<a href="/mvc03/member/view03">folder01/view03.jsp로 이동하기</a>	<br>
	<hr><br>
	<a href="/mvc03/f2/v01">folder02/view01.jsp로 이동하기</a>			<br>
	<a href="/mvc03/f2/v02">folder02/view02.jsp로 이동하기</a>			<br>
	<hr><br>
	<a href="/mvc03/f3/v01">folder03/view01.jsp로 이동하기</a>			<br>
	<a href="/mvc03/f3/v02">folder03/view02.jsp로 이동하기</a>			<br>
	<a href="/mvc03/f3/v03">folder03/view03.jsp로 이동하기</a>			<br>
	<a href="/mvc03/f3/v04">folder03/view04.jsp로 이동하기</a>			<br>
	<hr><br>
	<a href="/mvc03/f4/v01">folder04/view01.jsp로 이동하기</a>			<br>
	<a href="/mvc03/f4/v02">folder04/view02.jsp로 이동하기</a>			<br>
	<!-- ↑ 파라미터를 받아오기 않아도 알아서 가져온다_ forward 방식으로 전달된다 -->
	
	<hr><br>
	<a href="/mvc03/f5/v01?name=브레드&age=50">folder05/view01.jsp로 이동하기</a>		<br>
	<a href="/mvc03/f5/v02?name=브레드&age=50">folder05/view02.jsp로 이동하기</a>		<br>
	<a href="/mvc03/f5/v03">folder05/view03.jsp로 이동하기</a>						<br>
	<a href="/mvc03/f5/v04?name=브레드&age=50">folder05/view04.jsp로 이동하기</a>		<br>
	<a href="/mvc03/f5/v05?name=브레드&age=50">folder05/view05.jsp로 이동하기</a>		<br>
	<a href="/mvc03/f5/v06?name=브레드&age=50">folder05/view06.jsp로 이동하기</a>		<br>

	<hr><br>
	<hr><br><br><br>
	<a href="/mvc03/quiz/v01?title=공지사항&hit=5">quiz/view01.jsp로 이동하기 (request)</a>	<br>
	<a href="/mvc03/quiz/v02?title=공지사항&hit=5">quiz/view02.jsp로 이동하기 (@RequestParam)</a>	<br>
	<a href="/mvc03/quiz/v03?title=공지사항&hit=5">quiz/view03.jsp로 이동하기 (DTO)</a>	<br>
	<a href="/mvc03/quiz/v04?title=공지사항&hit=5">quiz/view04.jsp로 이동하기 (@ModelAttribute)</a>	<br>
	



</body>
</html>