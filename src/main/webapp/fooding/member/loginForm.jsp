<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<!-- 파비콘 적용 -->
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<jsp:include page="../inc/top_logout.jsp" />
<link href="./fooding/css/loginForm.css" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	// 페이지 로드 시 저장된 쿠키를 읽어와서 아이디 저장 체크박스에 반영
	var savedId = getCookie("savedId");
	if (savedId) {
		$("#saveId").prop("checked", true);
		$("#id").val(savedId);
	}

// 로그인 폼 제출 시 아이디 저장 체크박스 상태에 따라 쿠키 저장
$("form").submit(function () {
	if ($("#saveId").is(":checked")) {
		var enteredId = $("#id").val();
		setCookie("savedId", enteredId, 7); // 7일로 수정
		} else {
			deleteCookie("savedId");
		}
	});
});

	function setCookie(name, value, days) {
		var expires = new Date();
		expires.setTime(expires.getTime() + days * 24 * 60 * 60 * 1000);
		document.cookie = name + "=" + encodeURIComponent(value) + ";expires=" + expires.toUTCString() + ";path=/";
	}

	function getCookie(name) {
		
		var search = name + "=";
		var cookieArr = document.cookie.split(';');
		
		for (var i = 0; i < cookieArr.length; i++) {
		  
		var cookie = cookieArr[i];
		
		while (cookie.charAt(0) === ' ') {
			cookie = cookie.substring(1);
		}
		if (cookie.indexOf(search) === 0) {
			return decodeURIComponent(cookie.substring(search.length, cookie.length));
		}
		}
		
		return null;
	}
	
	function deleteCookie(name) {
		document.cookie = name +  "=; expires=0; path=/";
	}
	
</script>
</head>
<body>
    
	<div class="loginFm " >
	<h2 class="loginTitle">푸딩로그인</h2>
		<form action="./MemberLoginAction.mem" method="post" onsubmit="return frm_check();">
		
			<label for="id">아이디</label>
			<input type="text" name="id" id="id" placeholder="아이디를 입력하세요!" class="in">

			<label for="pw">비밀번호</label>
			<input type="password" name="pw" id="pw" placeholder="비밀번호를 입력하세요" class="in">
			
			<div class="form-check">
  			<input class="form-check-input" type="checkbox" id="saveId">
  			<label class="form-check-label" for="saveId">
    			아이디 저장
			</label></div>
			<br>

			<input type="submit" value="로그인" id="loginBtn" >
			
		</form>
			<a class="loginTag" href="./MemberJoin.mem">회원가입</a>
			<span class="separator">ㅣ</span>
			<a class="loginTag" href="./MemberIdFind.mem">아이디찾기</a>
			<span class="separator">ㅣ</span>
			<a class="loginTag" href="./MemberPwFind.mem">비밀번호찾기</a>
	</div> 
    
</body>
</html>