<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입(Ajax)</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script>
 function fn_idCheck() {
	 //alert($("#id").val());
	 $.ajax({
		 url: "/member/idCheck",
		 type: "post",
		 // contentType: "application/json",
		 dataType: "json",
		 data: {"id" : $("#id").val()},
		 success: function(data) {
			// alert("1111");
			 if(data == 1) {
				 alert("이미 사용 중인 아이디입니다.\n\n다른 아이디를 입력하세요.");
			 } else if(data == 0) {
				 $("#idCheck").attr("value", "Y");  // 버튼 id = "idCheck"의 속성인 value의 값을 "Y"로 변경한다.
				 alert("사용 가능한 아이디입니다.");
			 }
		 }	 
	 });
 }
</script>
<script>
$(document).ready(function(){
	// 취소 버튼이 눌렸을 경우 로그인 화면으로 이동한다.
	$(".cancel").on("click", function() {
		location.href = "/member/loginForm.do";
	});
	
	// 회원 가입 버튼을 눌렀을 경우
    $("#submit").on("click", function(){
	   //alert("회원가입 버튼을 누르셨습니다.");
	   if($("#id").val() == "") {
		   alert("아이디를 입력하셔야 합니다.");
		   $("#id").focus();
		   return false;
	   }
	   if($("#id").val().length < 4) {
			alert("아이디는 최소 4자리를 입력하셔야 합니다.");
			$("#id").focus();
			return false;
		}
	   if($("#pwd").val() == "") {
		   alert("비밀번호를 입력하셔야 합니다.");
		   $("#pwd").focus();
		   return false;
	   }
	   if($("#name").val() == "") {
		   alert("이름을 입력하셔야 합니다.");
		   $("#name").focus();
		   return false;
	   }
	   if($("#email").val() == "") {
		   alert("이메일을 입력하셔야 합니다.");
		   $("#email").focus();
		   return false;
	   }
    });
	
	// 아이디 입력란에 글자를 입력하면 실시간으로 사용가능 여부를 검사한다.
	$("#id").on("input", function() {
		// alert("글자를 입력하셨습니다."); ==> " + $("#id").val());"
		
		// 입력된 글자의 값을 변수에 저장한다.
		var inputID = $("#id").val();
		// alert(inputID);
		
		// 입력한 아이디가 서버에 존재하는지 알아낸다.
		$.ajax({
			url: "/member/idCheck",
		    type: "post",
		    dataType: "json",
		    data: {"id" : $("#id").val()},
		    success:   function(data) {
	               if(inputID == "" && data == '0') {
	                  $(".signUpBtn").prop("disabled", true);
	                  $(".signUpBtn").css("background-color", "#AAAAAA");
	                  $("#idCheck").css("background-color", "#FFCECE");
	                  document.getElementById("msg2").innerHTML = "아이디를 입력하십시오.";
	                  document.getElementById("msg").value = "아이디를 입력하십시오.";
	               } else if(inputID != "" && data == '0' && inputID.length >= 4) {
	                  $("#idCheck").css("background-color", "#B0F6AC");
	                  document.getElementById("msg2").innerHTML = "사용이 가능한 아이디입니다.";
	                  document.getElementById("msg").value = "사용이 가능한 아이디입니다.";
	                  $("#msg").css("background-color", "#B0F6AC");
	                  $(".signUpBtn").prop("disabled", false);
	                  $(".signUpBtn").css("background-color", "#4CAF50");
	               } else if(data == '1') {
	                  $(".signUpBtn").prop("disabled", true);
	                  $(".signUpBtn").css("background-color", "#AAAAAA");
	                  $("#idCheck").css("background-color", "#FFCECE");
	                  document.getElementById("msg2").innerHTML = "이미 사용 중인 아이디입니다.";
	                  document.getElementById("msg").value = "이미 사용 중인 아이디입니다.";
	                  $("msg").css("background-color", "#FFCECE");
	               }
	            
		    }
		});
	});
	
});

    
</script>
</head>
<body>
   
    <!-- 메뉴바 -->
	<jsp:include page="../common/topMenu.jsp" flush="false"/>

   <h1 align="center">회원 가입(Ajax)</h1>
   <div class="container">
      <form class="form-horizontal" method="post" action="${contextPath}/member/register">
         <div class="form-group">
            <div class="col-sm-offset-2 col-sm-6">
               <h2 align="center">회원 가입(실시간 아이디 검사)</h2>
            </div>
         </div>
         <div class="form-group">
            <label for="id" class="col-sm-2 control-label">아이디</label>
            <div class="col-sm-2">
               <input type="text" class="form-control" id="id" name="id" maxlength="10" placeholder="Enter ID"/>
            </div>
            <div class="col-sm-2">
               <button class="idCheck btn btn-warning" type="button" id="idCheck" onClick="fn_idCheck();" value="N">아이디 중복 확인</button>
            </div>
         </div>
         <div class="form-group">
            <label for="id" class="col-sm-2 control-label">비밀번호</label>
            <div class="col-sm-3">
               <input type="password" class="form-control" id="pwd" name="pwd" maxlength="20" placeholder="Enter PASSWORD"/>
            </div>    
         </div>
         <div class="form-group">
            <label for="id" class="col-sm-2 control-label">이  름</label>
            <div class="col-sm-4">
               <input type="text" class="form-control" id="name" name="name" maxlength="50" placeholder="Enter NAME"/>
            </div>    
         </div>
         <div class="form-group">
            <label for="id" class="col-sm-2 control-label">이메일</label>
            <div class="col-sm-4">
               <input type="text" class="form-control" id="email" name="email" maxlength="50" placeholder="Enter EMAIL"/>
            </div>    
         </div>
         <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
               <button type="reset" class="btn btn-warning">입력 취소</button>
               <button type="button" class="btn btn-danger cancel">회원가입 취소</button>
               <button type="submit" class="btn btn-success">회원가입</button>
               <button type="submit" class="btn btn-primary signUpBtn" disabled="disabled">회원가입</button>
            </div>
         </div>
         <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
               <input type="text" class="form-control msg" name="msg" placeholder="Message" id="msg"/>
            </div>
            <div id="msg2"></div>
         </div>
      </form>
   </div>
   
    <!-- footer -->
	<jsp:include page="../common/footer.jsp" flush="false"/>
	
</body>
</html>