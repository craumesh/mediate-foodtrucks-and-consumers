<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<!-- 파비콘 적용 -->
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<jsp:include page="../inc/top_logout.jsp" />
<link href="./fooding/css/pwFind.css" rel="stylesheet" />
</head>
<body>

	<div class="pwFd">
	<h2>푸딩 비밀번호찾기</h2>
		<form action="./MemberPwFindAction.mem" method="post">
		<label for="id">아이디</label>
			<input type="text" name="id" id="id" placeholder="아이디를 입력하세요!" class="in">
		<label for="email">이메일</label>
			<input type="email" name="email" id="email" placeholder="이메일을 입력하세요!" class="in">
			<input type="submit" value="비밀번호 찾기" id="pwFdBtn">
		</form>
			<a class="loginTag" href="./MemberJoin.mem">회원가입</a>
			<span class="separator">ㅣ</span>
			<a class="loginTag" href="./MemberIdFind.mem">아이디찾기</a>
			<span class="separator">ㅣ</span>
			<a class="loginTag" href="./MemberLogin.mem">로그인</a>
	</div>	
		
</body>
</html>