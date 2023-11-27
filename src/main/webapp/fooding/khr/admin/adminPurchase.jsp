<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제내역 관리</title>
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lora:wght@500&display=swap" rel="stylesheet">

<link href="./fooding/khr/css/adminPurchase.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href='https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css'>
	
<!-- 제이쿼리 사이드바 이동 -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script>
  $(document).ready(function() {
    $(window).scroll(function() {
      var scrollTop = $(window).scrollTop();
      $("#moving-element").css("top", scrollTop + "px");
    });
  });
</script>
<!-- 제이쿼리 사이드바 이동 -->
</head>
<body style="background-color: #28282B;">
<!-- 세션 제어 (로그인 안 되어 있으면 메인 이동) -->
	<c:if test="${empty sessionScope.id }">
		<c:redirect url="./FtMainAction.man" />
	</c:if>
	<c:if test="${!empty sessionScope.id && !sessionScope.id.equals('admin') }">
		<c:redirect url="./FtMainAction.man"/>
	</c:if>
<!-- 세션 제어 (로그인 안 되어 있으면 메인 이동) -->
<%-- <jsp:include page="../inc/top.jsp" /> --%>
<!-- 사이드바 -->	
<div class="row" style="float: left;" id="moving-element">
<div class="col-3" style="width: 250px">
<div class="list-group">
  <a class="list-group-item list-group-item-action disabled" aria-disabled="true"><b>관리자 모드 접속 중</b></a>
  <a href="./Admin.adm" class="list-group-item list-group-item-action">
    관리자 페이지
  </a>
  <a href="./AdminPurchase.adm" class="list-group-item list-group-item-action active" aria-current="true">주문내역 관리</a>
  <a href="./AdminFoodtruck.adm" class="list-group-item list-group-item-action">푸드트럭 관리</a>
  <a href="./AdminMember.adm" class="list-group-item list-group-item-action">회원 관리</a>
  <a href="./AdminProduct.adm" class="list-group-item list-group-item-action">상품 관리</a>
  <a href="./Profile.pro" class="list-group-item list-group-item-action">관리자 모드 종료</a>
</div>	
</div>
</div>
<!-- 사이드바 -->		

<section class="p-3 mb-2 bg-light text-dark">
	<br>
	<h1>Order History</h1>
	
<c:if test="${empty requestScope.list }">
<br>
<br>
<h2>현재 상품이 등록되어 있지 않습니다.</h2>
<br>
<br>
</c:if>

<c:if test="${!empty requestScope.list }">	
<br>
<table class="table table-hover">
	<tr>
		<th>주문번호</th>
		<th>주문자</th>
		<th>대표 상호명</th>
		<th>주문일</th>
		<th>가격</th>
		<th>상세 확인</th>
	</tr>
	<c:forEach var="dto" items="${requestScope.list }">
	<tr>
		<td>${dto.purchaseid }</td>
		<td>${dto.id }</td>
		<td>${dto.foodtruck_name }</td>
		<td>${dto.orderdate }</td>
		<td>${dto.price }</td>
		<td><button type="button" class="btn btn-secondary btn-sm" onclick="location.href='./AdminPurchaseDetail.adm?purchaseid=${dto.purchaseid}&pageNum=${requestScope.pageNum }';">상세 내역</button></td>
	</tr>
	</c:forEach>
</table>
</c:if>
<br>
<nav aria-label="Page navigation example">
  	<ul class="pagination justify-content-center">
  		<c:if test="${requestScope.startPage > requestScope.pageBlock }">
  			<li class="page-item"><a class="page-link" href="./AdminPurchase.adm?pageNum=${requestScope.startPage - requestScope.pageBlock }">이전</a></li>
		</c:if>
    	<c:forEach var="i" begin="${requestScope.startPage }" end="${requestScope.endPage }" step="1">
    		<li class="page-item"><a class="page-link" href="./AdminPurchase.adm?pageNum=${i }">${i }</a></li>
		</c:forEach>
		<c:if test="${requestScope.endPage < requestScope.pageCount }">
    	    <li class="page-item"><a class="page-link" href="./AdminPurchase.adm?pageNum=${requestScope.startPage + requestScope.pageBlock }">다음</a></li>
		</c:if>		
  	</ul>
</nav>
<br>
<br>
<h1>fooding.</h1>
</section>
<br>
<%-- <jsp:include page="../inc/bottom.jsp" /> --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>