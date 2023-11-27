<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 아이디 찾기 </title>
<!-- 파비콘 적용 -->
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<jsp:include page="../inc/top_logout.jsp" />
<link href="./fooding/css/findId.css" rel="stylesheet" />
</head>
<body>

 
	<%
		// 세션에서 foundId 값을 가져옴
		String foundId = (String) session.getAttribute("foundId");
	%>
    
	<c:if test="${not empty foundId}">
		<div class="idFd">
        	<!-- 테두리로 감싼 부분 -->
			<div class="idFdContent">
				<p>ID : ${foundId}</p>
			</div>
				<a class="loginTag" href="./MemberLogin.mem">로그인으로 돌아가기</a>
		</div>
	</c:if>
	
	
	
	<c:if test="${empty foundId}">
		<div class="idFd">
			<!-- 테두리로 감싼 부분 -->
			<div class="idFdContent">
			<p> 일치하는 회원정보가 없습니다.</p>
		</div>
			<a class="loginTag" href="./MemberLogin.mem">로그인으로 돌아가기</a>
		</div>
	</c:if>



	
</body>
</html>