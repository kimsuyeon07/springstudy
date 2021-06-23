<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	
</head>
<body>

	<h1>게시글 목록 보기</h1>
	
	<a href="insertBoardPage.do">새 글 작성하러 가기</a>
	<br><br>	
	
	<table border="1">
		<thead>
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>첨부</td>
			</tr>
		</thead>
		
		<tbody>
			<c:if test="${empty list}">
				<tr>
					<td colspan="5">게시글 목록이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty list}">
				<c:forEach var="board" items="${list}">				
					<tr>
						<td>${board.no}</td>
						<td><a href="selectBoardByNo.do?no=${board.no}">${board.title}</a></td>
						<td>${board.writer}</td>
						<td>${board.postdate}</td>
						<td>
							<c:if test="${not empty board.filename}">
								<a href="download.do?filename=${board.filename}"><i class="fas fa-paperclip"></i></a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
		
	</table>

</body>
</html>