<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt"  %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소 검색</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
</head>
<body>
   
<!-- 상단 메뉴 -->
<jsp:include page="../common/topMenu.jsp" flush="false"/>

<div class="container">
	<form class="form-horizontal" name="zipForm" method="post">
		<div class="form-group">
			<label class="col-sm-2" style="text-align : right">우편번호</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" name="zipcode" id="zipcode" readonly/>
				<input type="button" class="form-control" onclick="daumZipCode()" value="우편번호검색"/>
			</div>
		</div>
		<div class="form-group" >
			<label class="col-sm-2" style="text-align : right">주 소</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="address01" name="address01"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2" style="text-align : right">상세 주소</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="address02" name="address02"/>
			</div>
		</div>
	</form>
</div>



<!-- 하단 메뉴 -->
<jsp:include page="../common/footer.jsp" flush="false"/>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
function daumZipCode() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업창에서 검색한 결과 중 항목을 클릭하였을 경우에 실행할 코드를 이곳에 작성한다.
			
			// 각 주소의 노출 규칙에 따라서 주소를 조합해야 한다.
			// 내려오는 변수가 값이 없는 경우에는 공백('') 값을 가지므로 이름을 참고하여 분기한다.
			var fullAddr = ''; //최종 주소 변수
			var subAddr = '';  // 조합형 주소 변수
			
			// 사용자가 선택한 주소의 타입에 따라서 해당 주소 값을 가져온다.
			if(data.userSelectedType == 'R'){ // 도로명 주소를 선택한 경우
				fullAddr = data.roadAddress;
			} else { // 지번 주소를 선택한 경우
				fullAddr = data.jibunAddress;
			}
			
			// 사용자가 선택한 주소가 도로명 타입일 경우 조합한다.
			if(data.userSelectedType == 'R'){
				// 법정동명이 있을 경우 추가한다.
				if(data.bname != '') {
					subAddr += data.bname;
				}
				// 건물명이 있을 경우에 추가한다.
				if(data.buildingName != '') {
					subAddr += (subAddr != '' ? ', ' + data.buildingName : data.buildingName); // 삼항연산자
				}
				// 조합형 주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
				fullAddr += (subAddr != '' ? '(' + subAddr + ')' : '');
			} 
			
			// 우편번호와 주소 정보를 해당 필드에 출력한다.
			// 5자리의 새 우편번호
			document.getElementById('zipcode').value = data.zonecode;
			document.getElementById('address01').value = fullAddr;
			// 커서를 상세주소 입력란으로 이동시킨다.
			document.getElementById('address02').focus();
		}
	}).open({
		// 우편번호 팝업 창이 여러개 뜨는 것을 방지하기 위해서 popupName을 사용한다.
		popupName: 'postcodePopup'
	});
}
</script>

</body>
</html>