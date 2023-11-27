<%@page import="com.fooding.ft.db.FTDTO"%>
<%@page import="com.fooding.ft.db.FTDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

<!-- help Ajaxtry -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 

<head>
<meta charset="UTF-8">

<title>푸딩</title>

<!-- css -->
<link href="./fooding/css/styles.css" rel="stylesheet">
<link href="./fooding/css/ft.css" rel="stylesheet">

<!-- 파비콘 적용 -->
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">


<style type="text/css">
/* 푸드트럭 정보 */
.food-truck p {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    margin-top: 10px;
}

/* 검색 버튼 */
.btnFt {
	background-color: cornflowerblue; 
	border: 1px solid black; 
	color: white;
	padding-top: 5px;
	padding-bottom: 5px;
	border-radius: 5px;
}
</style>
</head>

<body style="background-color: #f0f0f0;">

	<!-- 로그인 했을 때 -->
    <c:if test="${!empty sessionScope.id }">
    <jsp:include page="../inc/top_another.jsp" />
    </c:if>
	<!-- 로그인 했을 때 -->

	<!-- 로그인 안 했을 때 -->
    <c:if test="${empty sessionScope.id }">
    <jsp:include page="../inc/top_logout_another.jsp" />
    </c:if>
	<!-- 로그인 안 했을 때 -->

	<jsp:include page="../inc/top_btn.jsp" />

		<section id="ft">
			<div id="ftcss">
				<div class="fts">
					<h2>운행 푸드트럭</h2>
					<!-- 푸드트럭 검색 -->
					<div id="table_search">
						<form action="./FtkList.ftk" method="get">
							<input type="text" name="search" class="input_box"
								   placeholder=" '푸드', '푸드트럭' 등등... "> 
							<input type="submit"
								   value="검색" class="btnFt">
						</form>
					</div>
					<!-- 푸드트럭 검색 -->
		
					<!-- 푸드트럭 정보 -->
					<div class="ftls">
						<c:forEach items="${ftList}" var="ftl">
							<div class="food-truck"
		                         onclick="location.href='./FtInfoMainAction.fti?foodtruck_id=${ftl.foodtruck_id}';"
		                         style="cursor: pointer;">
								 <a> <img src="./upload/foodtruck/${ftl.FImage}"> <br> <span>${ftl.ftName}</span>
									 <p>${ftl.ftInfo}</p>
								 </a>
							</div>
						</c:forEach>
		
						<c:forEach items="${ftInfo}" var="fti">
							<div class="food-truck"
		                         onclick="location.href='./FtInfoMainAction.fti?foodtruck_id=${fti.foodtruck_id}';"
		                         style="cursor: pointer;">
								 <a> <img src="./upload/foodtruck/${fti.FImage}"> <br> 
								 <b> <span>${fti.ftName}</span> </b>
									 <p>${fti.ftInfo.split("\\.")[0]}</p>
								 </a>
							</div>
						</c:forEach>
					</div>
					<!-- 푸드트럭 정보 -->
					
				</div>
			</div>
		</section>
		
		<!-- footer -->
		<footer>
			<div id="company-info">
				<%@ include file="../inc/bottom.jsp"%>
			</div>
		</footer>
		<!-- footer -->
		
</body>
</html>