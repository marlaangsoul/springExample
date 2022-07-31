<!--  7.4. -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 메뉴바 -->
<jsp:include page="../common/topMenu.jsp" flush="false"/>

   <div class="container">
   	  <form class="form-horizontal" method="get" action="${contextPath}/exam02/gugu.do">
   	  	<div class="form-group">
   	  		<div class="col-sm-offset-3 col-sm-3">
   	  			<h2 align="left">구구단</h2>
   	  		</div>
   	  	</div>
   	  	<div class="form-group">
   	  		<label class="col-sm-offset-2 col-sm-1 comtrol-label">단 입력</label>
   	  		<div class="col-sm-1">
   	  			<input type="text" class="form-control" id="dan" name="dan" maxlength="2" placeholder="단입력"/>
   	  		</div>
   	  	</div>
   	  	<div class="form-group">
   	  		<div class="col-sm-offset-3 col-sm-4">
   	  			<button type="reset" class="btn btn-warning">다시 입력</button>
   	  			<button type="submit" class="btn btn-success">전송</button>
   	  		</div>
   	  	</div>
   	  </form>
   </div>
   
<!-- footer -->
<jsp:include page="../common/footer.jsp" flush="false"/>   
</body>
</html>