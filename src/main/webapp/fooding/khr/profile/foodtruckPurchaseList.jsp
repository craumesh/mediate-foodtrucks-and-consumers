<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>푸드트럭 상품 목록</title>
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<link href="./fooding/khr/css/profileFoodtruckPurchaseList.css" rel="stylesheet">
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
<body>
<jsp:include page="../inc/top.jsp" />

	<!-- 세션 제어 -->
	<c:if test="${empty sessionScope.id }">
		<c:redirect url="./FtMainAction.man" /> <!-- main 페이지 주소 -->
	</c:if>
	<!-- 세션 제어 -->
	
<!-- 사이드바 -->	
<div class="row" style="float: left; margin-top: 50px;" id="moving-element">
<div class="col-3" style="width: 250px">
<div class="list-group">
  <a href="./Profile.pro" class="list-group-item list-group-item-action">마이페이지</a>
  <a href="./ProfileMemberInfo.pro" class="list-group-item list-group-item-action active" aria-current="true">
    회원정보
  </a>
  <a href="./OrderDetails.pay" class="list-group-item list-group-item-action">주문내역</a>
  <a href="./ProfileReviewList.pro" class="list-group-item list-group-item-action">리뷰</a>
  <a href="./Support.sup" class="list-group-item list-group-item-action">고객센터</a>
</div>	
</div>
</div>
<!-- 사이드바 -->

<section class="p-3 mb-2 bg-light text-dark">
	<br>
	<h1>Order History of My Foodtruck</h1>
	
<c:if test="${empty requestScope.list }">
<br>
<br>
<h2>현재 주문 내역이 존재하지 않습니다.</h2>
<br>
<br>
</c:if>

<c:if test="${!empty requestScope.list }">	
<br>
<table class="table table-hover">
	<tr>
		<th>주문번호</th>
		<th>주문자</th>
		<th>주문상품명</th>
		<th>수량</th>
		<th>주문금액</th>
		<th>정차지</th>
		<th>수령일</th>
		<th>푸드트럭명</th>
	</tr>
	<c:forEach var="dto" items="${requestScope.list }" varStatus="loop">
	<tr>
		<td>${dto.purchaseid }</td>
		<td>${dto.id }</td>
		<td>${dto.product_name }</td>
		<td>${dto.quantity }개</td>
		<td>${dto.price }원</td>
		<td>${dto.address }</td>
		<td>${dto.pickup }</td>
		<td>${dto.foodtruck_name }</td>
	</tr>
	</c:forEach>
</table>
</c:if>
<br>
<nav aria-label="Page navigation example">
  	<ul class="pagination justify-content-center">
  		<c:if test="${requestScope.startPage > requestScope.pageBlock }">
  			<li class="page-item"><a class="page-link" href="./ProfileFoodtruckPurchaseList.pro?pageNum=${requestScope.startPage - requestScope.pageBlock }">이전</a></li>
		</c:if>
    	<c:forEach var="i" begin="${requestScope.startPage }" end="${requestScope.endPage }" step="1">
    		<li class="page-item"><a class="page-link" href="./ProfileFoodtruckPurchaseList.pro?pageNum=${i }">${i }</a></li>
		</c:forEach>
		<c:if test="${requestScope.endPage < requestScope.pageCount }">
    	    <li class="page-item"><a class="page-link" href="./ProfileFoodtruckPurchaseList.pro?pageNum=${requestScope.startPage + requestScope.pageBlock }">다음</a></li>
		</c:if>		
  	</ul>
</nav>
<input type="button" class="btn btn-primary btn-sm" value="이전 페이지로" onclick="history.back(); ">
<br>
<br>
<h1>fooding.</h1>
</section>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<jsp:include page="../inc/bottom.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>