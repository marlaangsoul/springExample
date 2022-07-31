<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세정보</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/board.js"></script>
</head>
<body>
   
   <!-- 상단 메뉴 -->
   <jsp:include page="../common/topMenu.jsp" flush="false"/>
   
   <div class="container">
   	<form class="form-horizontal" id="frm">
   		<div class="form-group">
   			<h2 align="center">게시글 상세정보</h2>
   		</div>
   		<div class="form-group">
   			<label for="subject" class="col-sm-2 control-label">제  목</label>
   			<div class="col-sm-3">
   				<input type="text" class="form-control" id="subject" name="subject" maxlength="20" value="${boardDetail.subject}" readonly/>
    			</div>
   		</div>
   		<div class="form-group">
   			<label for="subject" class="col-sm-2 control-label">작성일자</label>
   			<div class="col-sm-3">
   				<fmt:parseDate value="${boardDetail.reg_date}" var="dateValue" pattern="yyyyMMddHHmmss"/> 
            <input type="text" class="form-control" id="reg_date" name="reg_date" value="<fmt:formatDate value="${dateValue}" pattern="yyyy년 MM월 dd일 HH시 mm분"/>" readonly/>
   			</div>
   		</div>
   		<div class="form-group">
   			<label for="subject" class="col-sm-2 control-label">조회수</label>
   			<div class="col-sm-3">
   				<input type="text" class="form-control" id="readCount" name="readCount" maxlength="10" value="${boardDetail.readCount}" readonly/>
   			</div>
   		</div>
   		<div class="form-group">
   			<label for="subject" class="col-sm-2 control-label">글쓴이</label>
   			<div class="col-sm-3">
   				<input type="text" class="form-control" id="name" name="name" maxlength="20" value="${boardDetail.name}" readonly />
   			</div>
   		</div>
   		<div class="form-group">
   			<label for="subject" class="col-sm-2 control-label">내  용</label>
   			<div class="col-sm-8">
   				<textarea rows="10" cols="160" class="form-control" id="content" name="content" readonly>${boardDetail.content}</textarea> 
   			</div>
   		</div>
   		<div class="form-group">
			<p align="center">
				<button type="button" class="btn btn-primary" onclick="location.href='/board/boardList'">
					<span class="glyphicon glyphicon-list-alt"> 게시글 목록</span>
				</button>
				<button type="button" class="btn btn-warning" onclick="fn_boardUpdateForm(${boardDetail.seq})">
					<span class="glyphicon glyphicon-pencil"> 게시글 수정</span>
				</button>
				<button type="button" class="btn btn-danger" onclick="fn_boardDelete(${boardDetail.seq})">
					<span class="glyphicon glyphicon-erase"> 게시글 삭제</span>
				</button>
			</p>
		</div>
   	</form>
   </div>
   
   
   
   <!-- 하단 메뉴 -->
   <jsp:include page="../common/footer.jsp" flush="false"/>
</body>
</html>