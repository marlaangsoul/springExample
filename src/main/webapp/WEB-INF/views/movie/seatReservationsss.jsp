<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt"  %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>영화 좌석 예매</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
</head>
<body>
<!-- 상단 메뉴 -->
<jsp:include page="../common/topMenu.jsp" flush="false"/>

<div class="container">
	<div class="row" align="center">
		<h2>좌석 예약</h2>
	</div>	
	<div class="row" align="center">
		<h3><label class="control-label col-sm-2 label-primary">남은 좌석수</label></h3>
		<div id="seatCount1" class="col-sm-1" style="font-size:1.2em; width:80px; background-color:2F70A9; color:FFFFFF; height:28px; border:1px solid; float:left;">${reserveNO}</div>
		<h3><label class="control-label col-sm-2 label-warning">예약 좌석수</label></h3>
		<div id="seatCount2" class="col-sm-1" style="font-size:1.2em; width:80px; background-color:D77875; color:FFFFFF; height:28px; border:1px solid; float:left;">${reserveOK}</div>
	</div>
	
	<hr/>
	
	<div class="row" align="center">
		<form action="" method="post" class="pt-3" style="max-width:1720px;">
			<c:forEach var="list" items="${SeatList}" begin="0" end="99" varStatus="status">
				<c:choose>
					<c:when test="${list.status == false}">
						<button type="button" class="btn btn-primary seatNO" title="seatNO" style="width:50px;" 
								value="${list.seatID}">${list.seatID}</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-danger seatNO" title="seatOK" style="width:50px;" 
								value="${list.seatID}" >${list.seatID}</button>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${status.count % 14 ==0}">
					 	<br/>
					</c:when>
				</c:choose>
			</c:forEach>
			
			<br/><br/><br/>
			
			<c:forEach var="list" items="${SeatList}" begin="100" end="199" varStatus="status">
				<c:choose>
					<c:when test="${list.status == false}">
						<button type="button" class="btn btn-primary seatNO" title="seatNO" style="width:50px;" 
								value="${list.seatID}">${list.seatID}</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-danger seatNO" title="seatOK" style="width:50px;" 
								value="${list.seatID}" >${list.seatID}</button>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${status.count % 16 ==0}">
					 	<br/>
					</c:when>
				</c:choose>
			</c:forEach>
		</form>
	</div>
	
	
	
</div>

<!-- 하단 메뉴 -->
<jsp:include page="../common/footer.jsp" flush="false"/>

<script>
// 빈좌석을 클릭하면 예약 업무를 실행한다.
$('.seatNO').on('click', function() {
	alert("좌석 예약/취소 하기");
	var idx = $('.seatNO').index(this);
	//alert(idx);
	// alert($('seatNO').eq(idx).val());
	
	//alert($('.seatNO').eq(idx).val() + "번 상태 : " + $('.seatNO').eq(idx).attr('title'));
	
	if($('.seatNO').eq(idx).attr('title') == 'seatOK') {	
		// 예약 취소
		if(!confirm("좌석" + $('.seatNO').eq(idx).val() + "번 예약을 취소하시겠습니까?")) {
			// alert("예약을 취소합니다.");
			$.ajax({
				url: "/movie/seatReservationCancel",
				type: "post",
				dataType: "json",
				data: {"seatID" : $('.seatNO').eq(idx).val()}, // 누른 좌석번호
				success: function(data) {
					// 좌석 예매가 성공하면 1을 돌려 받는다.
					if(data == 1) {
						alert("좌석예매를 취소하였습니다.");
						// 예약 좌석 취소가 완료되었으므로 버튼의 색상을 변경한다.
						$('.seatNO').eq(idx).addClass('btn-primary').removeClass('btn-danger');
						// 예약 좌석 취소가 완료되었으므로 예약된 버튼을 누르지 못하도록 변경한다.
						// $('.seatNO').eq(idx).attr('disabled', true);
						
						// 예매취소가 성공하였으므로 남은 좌석은 +1로 화면에 보여준다.
						let remainCount = ${reserveNO} +1;
						$("#seatCount1").text(remainCount);
						// 예매취소가 성공하였으므로 예약 좌석은 -1로 화면에 보여준다.
						let reserveCount = ${reserveOK} -1;
						$("#seatCount2").text(reserveCount);
					} else {
						alert("예약좌석 취소 중 장애가 발생하였습니다.\n 잠시 후에 다시 해주십시오.");
					}
				},
				error: function(data) {
					alert("좌석 예매에 문제가 발생하였습니다.\n 잠시 후에 다시 시도해주십시오.");
				}
			});
		} else {
			alert("예약 좌석 취소를 취소하였습니다.");
		}
	} else {
		// 예약 확정
		if(!confirm("좌석" + $('.seatNO').eq(idx).val() + "번을 예약합니다.")) {
			//alert("예약을 취소합니다.");
		} else {
			alert("좌석을 예약합니다.");
			$.ajax({
				url: "/movie/seatReservation",
				type: "post",
				dataType: "json",
				data: {"seatID" : $('.seatNO').eq(idx).val()}, // 누른 좌석번호
				success: function(data) {
					// 좌석 예매가 성공하면 1을 돌려 받는다.
					if(data == 1) {
						alert("예약 결과 : " + data);
						// 예약이 완료되었으므로 버튼의 색상을 변경한다.
						$('.seatNO').eq(idx).addClass('btn-danger').removeClass('btn-primary');
						// 예약이 완료되었으므로 예약된 버튼을 누르지 못하도록 변경한다.
						// $('.seatNO').eq(idx).attr('disabled', true);
						
						// 예매가 성공하였으므로 남은 좌석은 -1로 화면에 보여준다.
						let remainCount = ${reserveNO} -1;
						$("#seatCount1").text(remainCount);
						// 예매가 성공하였으므로 예약 좌석은 +1로 화면에 보여준다.
						let reserveCount = ${reserveOK} +1;
						$("#seatCount2").text(reserveCount);
					} else {
						alert("좌석예매 중 장애가 발생하였습니다.\n 잠시 후에 다시 해주십시오.");
					}
				},
				error: function(data) {
					alert("좌석 예매에 문제가 발생하였습니다.\n 잠시 후에 다시 시도해주십시오.");
				}
			});
		}
	}
});
</script>
</body>
</html>

