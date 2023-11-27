<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<!-- 파비콘 적용 -->
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<jsp:include page="../inc/top_logout.jsp" />
<link href="./fooding/css/findPw.css" rel="stylesheet" />
<script type="text/javascript">
</script>
</head>
<body>


	<c:if test="${not empty temporaryPassword}">
		<div class="pwFd">
			<!-- 테두리로 감싼 부분 -->
			<div class="pwFdContent">
				<li class="pwFd1">
					임시 비밀번호를 고객님의 이메일로 전송하였습니다!<br>
				</li>
			</div>
				<a class="loginTag" href="./MemberLogin.mem">로그인</a>
		</div>
	</c:if>



	<c:if test="${empty temporaryPassword}">
		<div class="pwFd">
			<!-- 테두리로 감싼 부분 -->
			<div class="pwFdContent">
				<li class="pwFd1">
					일치하는 회원정보가 없습니다. <br>
				</li>
			</div>
				<a class="loginTag" href="./MemberLogin.mem">로그인으로 돌아가기</a>
		</div>
	</c:if>
	
	


</body>
</html>