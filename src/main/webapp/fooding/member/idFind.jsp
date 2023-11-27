<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<!-- 파비콘 적용 -->
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<jsp:include page="../inc/top_logout.jsp" />
<link href="./fooding/css/idFind.css" rel="stylesheet" />
<script type="text/javascript">
var getEmail = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;

	function check(){
	
	var name = doucument.fr.name.value;
	var email = document.fr.email.value;
	
	
	// 이름
	if(name == ""){
		alert(' 이름을 입력하세요! ');
		document.fr.name.focus();
		return false;
	} // NA
	
	
	// 이메일
	else if(email == ""){
		alert(' 이메일을 입력하세요! ');
		document.fr.email.focus();
		return false;
	} 
	
	// 이메일 형식
	else if(!getEmail.test(email)){
		alert(' 이메일 형식에 맞게 입력해주세요! ');
		return false;
	}
	
} // check()
</script>

</head>
<body>

	<div class="idFd">
	<h2>푸딩 아이디찾기</h2>
		<form action="./MemberIdFindAction.mem" method="post" name="fr" onsubmit="return check();">
		<label for="name">이름</label>
			<input type="text" name="name" id="name" placeholder="이름을 입력하세요!" class="in">
		<label for="email">이메일</label>
			<input type="email" name="email" id="email" placeholder="이메일 입력하세요!" class="in">
			<input type="submit" value="아이디 찾기" id="idFdBtn">
		</form>
			<a class="loginTag" href="./MemberJoin.mem">회원가입</a>
			<span class="separator">ㅣ</span>
			<a class="loginTag" href="./MemberPwFind.mem">비밀번호찾기</a>
			<span class="separator">ㅣ</span>
			<a class="loginTag" href="./MemberLogin.mem">로그인</a>
	</div>

</body>
</html>