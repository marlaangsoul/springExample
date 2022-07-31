<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt"  %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 올리기 (Ajax)</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	

<style>
	.fileDrop {
		width: 100%;
		height: 200px;
		border: 1px dotted navy;
	}
	small {
		margin-left: 3px;
		font-weight: bold;
		color: gray;
	}
</style>

<script>
$(function() {
	// 드래그의 기본 효과를 막는다.
	// 드래그의 기본 효과를 막지 않으면 드래그된 곳에 이미지가 보인다.
	// preventDefault() <== 기본적으로 표시되는 효과를 막는다.
	$(".fileDrop").on("dragenter dragover", function(event) {
		event.preventDefault();
	});
	//-------------------------------------------------------------------------------------------------------
	// 그림 전송영역에 그림을 떨어뜨리면 처리하는 부분
	//-------------------------------------------------------------------------------------------------------
	$(".fileDrop").on("drop", function(event) {  // 사진을 넣으면 사진이 뜸
		alert("그림을 떨구었습니다.");
		//그림이 drop될 때 기본 효과를 막는다.
		event.preventDefault();
		
		// 첨부파일 배열 : 드래그된 파일의 정보
		// ctrl+클릭으로 여러개를 동시에 올릴수도 있다.
		var files = event.originalEvent.dataTransfer.files;
		
		var file = files[0];  // 첫번째 첨부파일
		//alert(files[1]);
		
		// 파일정보가 콘솔에 올라간다.
		// 웹 브라우저에서 F12 키를 누르면 어떤 파일인지 알 수가 있다.
		console.log(file);
		
		// Ajax로 전달할 때에는 폼이 별도로 존재하지 않기 때문에 폼 객체를 만들어 주어야 한다.
		var formData = new FormData(); // 폼 객체
		
		// 폼에 file 변수를 추가한다.
		formData.append("file", file);
		
		// 서버에 파일을 업로드한다.(백그라운드에서 실행이 된다.)
		// contentType : false => multipart/form-data 로 처리가 된다.
		$.ajax({
			type: "post",
			url: "${contextPath}/upload/uploadAjax",
			data: formData,
			dataType: "text",
			processData: false,
			contentType: false,
			success: function(data, status, req) {
				console.log("data: " + data);    // 업로드된 파일이름
				console.log("status: " + status); // 성공, 실패여부
				console.log("req: " + req.status); // 요청 코드값
				var str = "";
				if(checkImageType(data)) { // 이미지 파일인 경우
					str = 	"<div><a href='${contextPath}/upload/displayFile?fileName=" 
							+ getImageLink(data) + "'>";
					str += 	"<img src='${contextPath}/upload/displayFile?fileName="
							+ data + "'></a>";
				} else { // 이미지가 아닌 경우
					str = "<div><a href='${contextPath}/upload/displayFile?fileName="
							+ data + "'>" + getOriginalName(data) + "</a>";
				}
				str += "<span data-src=" + data + ">[삭제]</span></div>";
				$(".uploadedList").append(str);
			}
			
		}); 
		
	}); // END - fileDrop함수
		
	//-------------------------------------------------------------------------------------------------------
	// 첨부파일 삭제하기(7.21)
	//-------------------------------------------------------------------------------------------------------
	$(".uploadedList").on("click", "span", function(event) {
		alert("첨부파일 삭제하기");
		
		// 현재 클릭한 태그
		var that = $(this);
		alert($(this).attr("data-src"));
		
		$.ajax({
			url: "${contextPath}/upload/deleteFile",
			type: "post",
			data: "fileName=" + $(this).attr("data-src"),
			dataType: "text",
			success:	function(result) {
				if(result == "deleted") {
					that.parent("div").remove();
				}
			},
			error: function(error) {
				alert("Error");
			},
			complete: function() {
				alert("Complete");
			}
		});
	});
	
	//-------------------------------------------------------------------------------------------------------
	// 이미지 파일인지 검사하는 함수
	// i : ignore case()
	//-------------------------------------------------------------------------------------------------------	
	function checkImageType(fileName) {
		var pattern = /jpg|png|jpeg|gif/i; // 정규표현식(대소문자 무시)
		return fileName.match(pattern);     // 규칙에 맞으면 true
	}
	
	//-------------------------------------------------------------------------------------------------------
	// 이미지의 원래 이름 가져오기
	// 1680901a-da8b-4025-b302-0b0029c1b793_KakaoTalk_20220512_145246763.jpg
	//-------------------------------------------------------------------------------------------------------	
	function getOriginalName(fileName) {
		if(checkImageType(fileName)) { // 이미지 파일이면 skip
			return;
		}	
		var idx = fileName.indexOf("_") + 1;
		return fileName.substr(idx);
	}
	
	//-------------------------------------------------------------------------------------------------------
	// 
	//-------------------------------------------------------------------------------------------------------	
	function getImageLink(fileName) {
		if(!checkImageType(fileName)) { // 이미지 파일이 아니면 skip
			return; // 함수 종료
		}
		// 이미지 파일이면
		// /2022/07/20/s_1680901a-da8b-4025-b302-0b0029c1b793_KakaoTalk_20220512_145246763.jpg
		var front = fileName.substr(0, 12);  // 연월일 경로
		var end = fileName.substr(14);   	 // s_제거
		console.log(front);
		console.log(end);
		return front+end;
	}
});
</script>
</head>
<body>

<!-- 상단 메뉴 -->
<jsp:include page="../common/topMenu.jsp" flush="false"/>

<div class="container">
	<h2>Ajax File Upload</h2>
	<!-- 파일을 업로드할 영역 -->
	<div class="fileDrop"></div>
	<!-- 업로드 된 파일 목록을 출력할 영역 -->
	<div class="uploadedList"></div>
	
</div>


<!-- 하단 메뉴 -->
<jsp:include page="../common/footer.jsp" flush="false"/>
   
</body>
</html>