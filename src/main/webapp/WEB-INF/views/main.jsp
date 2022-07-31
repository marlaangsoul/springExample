<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화면</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
   <!-- 메뉴바 -->
   <jsp:include page="./common/topMenu.jsp" flush="false"/>
   
   
   <!-- 메뉴바 하단 페이지에 소개를 보여준다. -->
   <div class="container text-center">
      <div class="jumbotron">
         <h1>도서 쇼핑몰</h1>
         <p>방문을 환영합니다.</p>
      </div>
   </div>
   
   <!-- 화면 중앙에 이미지를 보여준다. -->
   <div class="container">
      <div id="myCarousel" class="carousel slide" data-ride="carousel">
      <!-- Indicators -->
      <ol class="carousel-indicators">
         <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
         <li data-target="#myCarousel" data-slide-to="1"></li>
         <li data-target="#myCarousel" data-slide-to="2"></li>
      </ol>
      
      <!-- Wrapper for slides -->
      <div class="carousel-inner">
         <div class="item active">
            <!-- class="img-responsive center-block" : 반응형 이미지를 가운데 정렬한다. -->
            <img class="img-responsive center-block" src="${contextPath}/resources/images/book.jpg" width="100%" height="350px"/>
            <!-- carousel에 설명을 달아준다. -->
            <div class="carousel-caption">
               <h2>책방 내부 시설 1</h2>
            </div>
         </div> 
         <div class="item">
            <!-- class="img-responsive center-block" : 반응형 이미지를 가운데 정렬한다. -->
            <img class="img-responsive center-block" src="${contextPath}/resources/images/book1.jpg" width="100%" height="350px"/>
            <!-- carousel에 설명을 달아준다. -->
            <div class="carousel-caption">
               <h2>책방 내부 시설 2</h2>
            </div>
         </div> 
         <div class="item">
            <!-- class="img-responsive center-block" : 반응형 이미지를 가운데 정렬한다. -->
            <img class="img-responsive center-block" src="${contextPath}/resources/images/book3.jpg" width="100%" height="350px"/>
            <!-- carousel에 설명을 달아준다. -->
            <div class="carousel-caption">
               <h2>책방 내부 시설 3</h2>
            </div>
         </div> 
      </div>
      
      <!-- 좌측, 우측으로 그림을 움직일 수 있도록 좌/우버튼을 설정한다. -->
      <a class="left carousel-control" href="#myCarousel" data-slide="prev">
         <span class="glyphicon glyphicon-chevron-left"></span>
      </a>
     <a class="right carousel-control" href="#myCarousel" data-slide="next">
         <span class="glyphicon glyphicon-chevron-right"></span>>
     </a>
     </div>
   </div>
   
   <!-- 푸터영역 -->
   <jsp:include page="./common/footer.jsp" flush="false"/>
   
</body>
</html>