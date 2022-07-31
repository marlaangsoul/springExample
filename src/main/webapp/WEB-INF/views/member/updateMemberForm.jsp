<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
   
   <div class="container">
      <form class="form-horizontal" method="post" name="memModifyForm" action="${contextPath}/member/modifyMember.do">
         <div class="form-group">
            <div class="col-sm-offset-2 col-sm-5">
               <h2 align="center">회원 정보 수정</h2>
            </div>
         </div>
         <div class="form-group">
            <label for="id" class="col-sm-3 control-label">아이디</label>
            <div class="col-sm-2">
               <input type="text" class="form-control" id="id" name="id" maxlength="10" value="${member.id}" readonly/>
               <!-- name="id2"로 입력해서 결과 출력하면 null로 나옴 // MemberVO에 private String id로 저장해놨기 때문에 여기에 매칭 되어야 함-->
            </div>
         </div>
         <div class="form-group">
            <label for="id" class="col-sm-3 control-label">비밀번호</label>
            <div class="col-sm-3">
               <input type="password" class="form-control" id="pwd" name="pwd" maxlength="20" value="${member.pwd}"/>
            </div>
         </div>
         <div class="form-group">
            <label for="id" class="col-sm-3 control-label">비밀번호 확인</label>
            <div class="col-sm-3">
               <input type="password" class="form-control" id="repwd" name="repwd" maxlength="20" value="${member.pwd}"/>
            </div>
         </div>
         <div class="form-group">
            <label for="id" class="col-sm-3 control-label">이  름</label>
            <div class="col-sm-3">
               <input type="text" class="form-control" id="name" name="name" maxlength="50" value="${member.name}"/>
            </div>
         </div>
         <div class="form-group">
            <label for="id" class="col-sm-3 control-label">이메일</label>
            <div class="col-sm-3">
               <input type="text" class="form-control" id="email" name="email" maxlength="50" value="${member.email}"/>
            </div>
         </div>

         <div class="form-group">
            <div class="col-sm-offset-3 col-sm-4">
               <button type="reset" class="btn btn-warning">다시 입력</button>
               <button type="submit" class="btn btn-primary">회원 정보 수정</button>    
            </div>
         </div>
      </form>
   </div>
   
</body>
</html>