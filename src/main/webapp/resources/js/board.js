/**
 * 	게시글 관련 함수
 */

//-----------------------------------------------------------------------------------------------------------
// 게시글 등록
//-----------------------------------------------------------------------------------------------------------
function fn_boardRegister() {
	// 화면에서 입력한 값을 변수에 저장한다.
	var	subject	= $("#subject").val();
	var	name	= $("#name").val();
	var	content	= $("#content").val();
	
	// alert(subject + ":" + name + ":" + content);

	// 제목 항목의 값이 없으면 입력하도록 한다.
	if($("#subject").val() == "") {
		alert("제목은 필수 입력 항목입니다.");
		$("#subject").focus();
		return false;
	}
	
	// 이름 항목에 값이 없으면 입력하도록 한다.
	if($("#name").val() == "") {
		alert("이름은 필수 입력 항목입니다.");
		$("#name").focus();
		return false;
	}
	
	//  내용 항목에 값이 없으면 입력하도록 한다.
	if($("#content").val() == "") {
		alert("내용은 필수 입력 항목입니다.");
		$("#content").focus();
		return false;
	}

	$.ajax({
		type:	"POST",
		url:	"/board/boardRegister",
		data:	{subject:subject, name:name, content:content},
		success:	function(data) {
			if(data == "Y") {
				// 게시글 등록이 완료되면 게시글 목록화면으로 이동한다.
				alert("게시글을 등록하였습니다.");
				location.href = "/board/boardList";
			}
		},
		error:		function(data) {
			alert("게시글을 등록하는데 실패하였습니다.");
		}
	});
	
} // End - function fn_boardRegister()	

//-----------------------------------------------------------------------------------------------------------
// 게시글 수정화면(7.15-18)
// form에 action과 method가 없기 때문에 여기에서 만든다. 
// <form action="/board/boardUpdateForm", method="POST" id="form_board">
// 전송할 seq를 담아 놓은 것이 없으므로, input을 만들어 seq를 전송한다.
// frm.append($('만들 태그',  { 태그에 사용할 속성 : 값, 속성 : 값, .....  } ));
//-----------------------------------------------------------------------------------------------------------
function fn_boardUpdateForm(seq) {

	var f = $("#frm");
	f.attr("action", "/board/boardUpdateForm");
	f.attr("method", "POST");
	f.append($('<input/>', {type: 'hidden', name: 'seq', value: seq }));
	f.appendTo('body');
	f.submit();
	
}

//-----------------------------------------------------------------------------------------------------------
// 게시글 수정(7.18)
//-----------------------------------------------------------------------------------------------------------
function fn_boardUpdate() {

	var	seq		= $("#seq").val();
	var	subject	= $("#subject").val();
	var	name	= $("#name").val();
	var	content	= $("#content").val();
	
	// alert(seq + " - " + subject + " - " + name + " - " + content);
	 
	$.ajax({
		type:		"POST",
		url:		"/board/boardUpdate",
		data:		{seq: seq, subject: subject, name: name, content: content},
		success:	function(data) {
			if(data == "Y") {
				alert("게시글 수정이 완료되었습니다.");
				fn_boardDetail(seq);
			} else {
				alert("게시글 수정에 실패하였습니다.\n\n잠시 후에 다시 해주십시오.");
			}		
		},
		error:		function(data) {
			alert("실패");
			console.log(data);
		}
	});

}
//-----------------------------------------------------------------------------------------------------------
// 게시글 삭제(7.15)
//-----------------------------------------------------------------------------------------------------------
function fn_boardDelete(seq) {
	
	if(!confirm("게시글을 삭제하시겠습니까?\n\n삭제하시려면 [확인]을 누르시고, 아니면 [취소]를 누르세요.")) {
		alert("게시글 삭제를 취소하셨습니다.");
	} else {
		$.ajax({
			type: "POST",
			url: "/board/boardDelete",
			data: {seq : seq},
			success: function(data) {
				if(data == "Y") {
					alert("게시글 삭제가 완료되었습니다.");
					location.href = "/board/boardList";
				}
			}, 
			error: function(data) {
					alert("게시글을 삭제하는데 에러가 발생하였습니다.");
			}
			
		});
	}

} // end - function fn_boardDelete(seq)