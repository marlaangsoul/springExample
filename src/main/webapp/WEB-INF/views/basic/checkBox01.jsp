<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>체크 박스 선택</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
   
<!-- 상단 메뉴 -->
<jsp:include page="../common/topMenu.jsp" flush="false"/>
   
   <div class="container">
   		<h1 align="center">CheckBox Example</h1>
   		
   		<form action="/basic/checkBox01" method="post">
   			<div>전체<input type="checkbox" id="allCheck"/></div>
   			<div>테스트1<input id="ck1" type="checkbox"/></div>
   			<div>테스트2<input id="ck2" type="checkbox"/></div>
   			<div>테스트3<input id="ck3" type="checkbox"/></div>
   			
   			<button type="button" id="btn1">1번 체크/언체크</button>
   			<button type="button" id="btn2">2번 체크/언체크</button>
   			<button type="button" id="btn3">3번 체크/언체크</button>
   			<button type="button" onclick="checkResult();">체크 결과</button>
   		</form>
   		
   		<hr/>
   		
   		<input type="checkbox" name="animal" value="dog" onclick="getCheckBoxValue(event)"/>강아지
   		<input type="checkbox" name="animal" value="cat" onclick="getCheckBoxValue(event)"/>고양이
   		<div id="result"></div>
   		
   </div>

<!-- 하단 메뉴 -->
<jsp:include page="../common/footer.jsp" flush="false"/>

<script>
function getCheckBoxValue(event) {
	let result = '';
	if(event.target.checked) {
		result = event.target.value;
	} else {
		result = '';
	}
	document.getElementById('result').innerText = result;
}
</script>


<script>
$('#btn1').click(function() {
	var checked = $('#ck1').is(':checked');
	$('#ck1').prop('checked', !checked);
});
$('#btn2').click(function() {
	var checked = $('#ck2').is(':checked');
	$('#ck2').prop('checked', !checked);
});
$('#btn3').click(function() {
	var checked = $('#ck3').is(':checked');
	$('#ck3').prop('checked', !checked);
});

$('#allCheck').click(function() {
	var checked = $('#allCheck').is(':checked');

	if(checked)
		$('input:checkbox').prop('checked', true);
	else
		$('input:checkbox').prop('checked', false);
});

function checkResult() {
	var checked1 = $('#ck1').is(':checked');
	var checked2 = $('#ck2').is(':checked');
	var checked3 = $('#ck3').is(':checked');
	
	alert("체크결과 : " + checked1 + " : " + checked2 + " : " + checked3);
	
	if(checked1 == true && checked2 == true) {
		alert("1번과 2번이 체크된 상태입니다.");
	} else {
		alert("1번과 2번이 동시에 체크가 되지 않았습니다.");
	}
}
</script>

</body>
</html>