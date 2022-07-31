<!-- 6.30. -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberData</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 메뉴바 -->
<jsp:include page="../common/topMenu.jsp" flush="false"/>

   <h1>회원 정보</h1>
   <hr/>
   <h2>MemberVO 아 이 디 : ${member.userid}</h2>
   <h2>MemberVO 비밀번호 : ${member.userpw}</h2>
   
   <hr/>
   
   <h2>Map</h2>
   <h2>Map 아 이 디 : ${map.userid}</h2>
   <h2>Map 비밀번호 : ${map.userpw}</h2>
</body>
</html>