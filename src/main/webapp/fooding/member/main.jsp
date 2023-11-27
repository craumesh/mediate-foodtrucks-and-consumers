<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- 
	     메인페이지는 로그인을 수행한 사용자만 확인가능
	     로그인 없이 접근한 경우 로그인 처리 할 수 있도록 페이지 이동
	 -->
	 <%-- <c:if test="${ id == null }"> --%>
	 
	<c:if test="${ empty id }">
		<c:redirect url="./MemberLogin.mem"/>	 
	</c:if>
	
	<c:if test="${ !empty id }">
		<c:redirect url="./FtMainAction.man"/>	 
	</c:if>
	
	

	
	

	
	
	
</body>
</html>