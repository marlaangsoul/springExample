<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
   
   <!-- 상단 메뉴 -->
   <jsp:include page="../common/topMenu.jsp" flush="false"/>
   
   <div class="container">
	<form class="form-horizontal" id="frm">
		<div class="form-group">
			<div>
				<h2 align="center">게시글 목록</h2>
			</div>
		</div>
		<table class="table table-bordered table-striped table-hover">
			<thead>
				<tr class="info">
					<th>번  호</th>
					<th>제  목</th>
					<th>내  용</th>
					<th>작성자</th>
					<th>날  짜</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${boardList}" var="list">
					<tr>
						<td align="right">${list.seq}</td>
						<td><a href="${contextPath}/board/boardDetail?seq=${list.seq}">${list.subject}</a></td>
						<td>${list.content}</td>
						<td align="center">${list.name}</td>
						<fmt:parseDate value="${list.reg_date}" var="currentDate" pattern="yyyyMMddHHmmss"/>
						<td><fmt:formatDate value="${currentDate}" pattern="yyyy년 MM월 dd일"/></td>
						<td align="right">${list.readCount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<p align="center">
			<button type="button" class="btn btn-primary" onclick="location.href='/board/boardRegisterForm.do'">게시글 쓰기</button>
		</p>
	</form>
</div>

</body>
</html>