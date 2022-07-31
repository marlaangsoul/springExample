<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Timestamp" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); 
	Timestamp nowTime = new Timestamp(System.currentTimeMillis());
	int lastYear = Integer.parseInt(nowTime.toString().substring(0, 4));
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>날짜 입력</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>	
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
	<script src="${contextPath}/resources/js/board.js"></script>
	<script>
		function nextFocus(where) {
			if(event.keyCode == 13) {
				where.focus();
			}
		}
	</script>
</head>
<body>

<!-- 상단 메뉴 -->
<jsp:include page="../common/topMenu.jsp" flush="false"/>

<div class="container">
	<form class="form-horizontal">
		<div class="form-group">
			<div>
				<h2 align="center">게시글 상세 조회</h2>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">출판일자</label>
			<div class="col-sm-2">
				<div class="input-group">
					<select class="form-control" name="publishing_yer" style="width: 100%;" onkeydown="nextFocus(publishing_month)">
						<%for(int i = lastYear; i >= 2001; i--) {%>
						<option value="<%=i%>"><%=i %></option>
						<%} %>					
					</select>
					<span class="input-group-addon">년</span>
				</div>
			</div>
			<div class="col-sm-2">
				<div class="input-group">
					<select class="form-control" name="publishing_month" style="width: 100%;" onkeydown="nextFocus(publishing_day)">
						<c:forEach var="i" begin="1" end="12" step="1">
							<option value="${i}" <c:if test="${i == 5}">selected</c:if> >${i}</option>
						</c:forEach>
					</select>
					<span class="input-group-addon">월</span>
				</div>
			</div>
			<div class="col-sm-2">
				<div class="input-group">
					<select class="form-control" name="publishing_day" style="width: 100%;" onkeydown="nextFocus(publishing_com)">
						<c:forEach var="i" begin="1" end="31" step="1">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>
					<span class="input-group-addon">일</span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">출판사</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" maxlength="40" name="publishing_com" onkeydown="nextFocus(btn_OK)" placeholder="출판사"/>
			</div>
		</div>
		<div class="form-group">
			<p align="center">
				<button type="reset"  class="btn btn-warning">
					<span class="glyphicon glyphicon-erase"> 다시 입력</span>
				</button>
				<button type="button" class="btn btn-primary" name="btn_OK" onclick="location.href='/board/boardList'">
					<span class="glyphicon glyphicon-list-alt"> 게시글 목록</span>
				</button>
			</p>
		</div>
	</form>
</div>

<hr/>

<div class="container">
	<form class="form-horizontal">
		<div class="form-group">
			<div>
				<h2 align="center">게시글 상세 조회</h2>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">일자</label>
			<div class="col-sm-2">
				<input type="text" class=" form-control" id="datepicker1" placeholder="날짜를 선택하십시오."/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">일자</label>
			<div class="col-sm-2">
				<input type="text" class=" form-control" id="datepicker2" placeholder="날짜를 선택하십시오."/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">조회기간을 선택하세요.</label>
			<div class="col-sm-2">
				<input type="text" class=" form-control" id="datepicker3" placeholder="날짜를 선택하십시오."/>
			</div>
			<div class="col-sm-1">
				&nbsp;~&nbsp;
			</div>
			<div class="col-sm-2">
				<input type="text" class=" form-control" id="datepicker4" placeholder="날짜를 선택하십시오."/>
			</div>
		</div>
	</form>
</div>

<!-- 하단 메뉴 -->
<jsp:include page="../common/footer.jsp" flush="false"/>

</body>

<script>
$(function() {
	
	$("#datepicker1").datepicker();
	
	$("#datepicker2").datepicker({
		// 선택할 수 있는 최대 날짜 +1m +1w은 1달 1주일 뒤까지 선택이 가능하다. [+, -][숫자][y, m, w, d]
		maxDate:	"+1m +1w", 
		minDate:	"-1y"
	});
	
	$("#datepicker3, #datepicker4").datepicker({
		// 옵션들 생략
	});
	
});

$.datepicker.setDefaults({
	showOn:			"both",		// 버튼과 텍스트 필드 모두 캘린더를 보여준다.
	changeYear:		true,		// 년을 바꿀 수 있는 셀렉트 박스를 표시한다.
	changeMonth:	true,		// 월을 바꿀 수 있는 셀렉트 박스를 표시한다.
	showAnim:		"slide",	// 애니메이션을 적용한다.
	dateFormat:		'yy-mm-dd',	// 날짜 포맷.
	prevText:		'이전 달',	// 마우스 오버시 이전달이라는 텍스트 풍선도움말을 보여준다.
	nextText:		'다음 달',	// 마우스 오버시 다음달이라는 텍스트 풍선도움말을 보여준다.
	closeText:		'닫기',		// 닫기 버튼 텍스트 변경
	currentText:	'오늘',		// 
	monthNames:		['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], // 월을 한글로 표시
	monthNamesShort:['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], // 월을 한글로 표시
	dayNames:		['일', '월', '화', '수', '목', '금', '토'],	// 주를 한글로 표시
	dayNamesShort:	['일', '월', '화', '수', '목', '금', '토'],	// 주를 한글로 표시
	dayNamesMin:	['일', '월', '화', '수', '목', '금', '토'],	// 주를 한글로 표시
	showMonthAfterYear:	true,	// true : 년 월   false : 월 일 순으로 보여준다.
	yearSuffix:		'년',
	showButtonPanel:	true	// 오늘로 가는 버튼과 달력 닫기 버튼 보기 옵션
});
</script>

</html>