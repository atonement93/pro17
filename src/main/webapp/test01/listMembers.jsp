<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 리스트</title>
	<style>
		.cls1 {
			font-size: 40px;
			text-align: center;
		}
		.cls2 {
			font-size: 20px;
			text-align: center;
		}
	</style>
</head>
<body>
	<p class="cls1">회원정보</p>
	<table align="center" border="1">
		<tr align="center" bgcolor="pink">
			<td width="10%"><b>아이디</b></td>
			<td width="10%"><b>비밀번호</b></td>
			<td width="10%"><b>이름</b></td>
			<td width="10%"><b>이메일</b></td>
			<td width="10%"><b>가입일</b></td>
		</tr>

		<c:choose>
			<c:when test="${membersList==null}">
				<tr>
					<td colspan=5>
						<b>등록된 회원이 아닙니다.</b>
					</td>
				</tr>
			</c:when>
			<c:when test="${membersList != null}">
				<c:forEach var="mem" items="${membersList}">
					<tr align="center">
						<td>${mem.id}</td>
						<td>${mem.pw}</td>
						<td>${mem.name}</td>
						<td>${mem.email}</td>
						<td>${mem.joinDate}</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>	
	</table>
	
	<button class="cls2">
	<a href="http://localhost:8090/pro17/member/memberForm.do">
	회원가입
	</a>
	</button>
</body>
</html>