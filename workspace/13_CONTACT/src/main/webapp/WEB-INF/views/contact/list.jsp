<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Contact List</title>
	<link rel="stylesheet" href="resources/assets/css/style.css" />
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		
	</script>
</head>
<body>
	
	<div id="list_con">
		<h1>연락처 목록</h1>
		<table>
			<thead>
				<tr>
					<td>번호</td>
					<td>이름</td>
					<td>전화</td>
					<td>주소</td>
					<td>이메일</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty list}">
					<tr>
						<td colspan="5"><b>등록된 연락처가 없습니다.</b></td>
					</tr>
				</c:if>
				<c:if test="${not empty list}">
					<c:forEach var="contact" items="${list}">
						<tr>
							<td>${contact.no}</td>
							<td>
								<a href="contactView.do?no=${contact.no}">${contact.name}</a>
							</td>
							<td>${contact.tel}</td>
							<td>${contact.addr}</td>
							<td>${contact.email}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="5">
						<a href="insertContactPage.do">신규 연락처 등록하기</a>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
	
</body>
</html>