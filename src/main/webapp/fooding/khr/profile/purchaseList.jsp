<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문목록</title>
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<link href="./fooding/khr/css/purchaseList.css" rel="stylesheet">
<!-- css 복사 -->
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
<!-- css 복사 -->
</head>
<body>
<!-- 	<h1>purchaseInfo.jsp (MVC)</h1> -->
<jsp:include page="../inc/top.jsp" />

	<c:if test="${empty sessionScope.id }">
		<c:redirect url="./FtMainAction.man" /> <!-- main 페이지 주소 -->
	</c:if>

<div class="row" style="float: left; margin-top: 50px;" id="moving-element">
<div class="col-3" style="width: 250px">
<div class="list-group">
  <a href="./Profile.pro" class="list-group-item list-group-item-action">마이페이지</a>
  <a href="./ProfileMemberInfo.pro" class="list-group-item list-group-item-action">회원정보</a>
  <a href="./OrderDetails.pay" class="list-group-item list-group-item-action active" aria-current="true">
    주문내역
  </a>
  <a href="./ProfileReviewList.pro" class="list-group-item list-group-item-action">리뷰</a>
  <a href="./Support.sup" class="list-group-item list-group-item-action">고객센터</a>
</div>	
</div>
</div>

<div style="height: auto; min-height: 100%; padding-bottom: 44px;">
	<section class="p-3 mb-2 bg-light text-dark">
	<div class="div1">
	<br>
	<h1>Order History</h1>
	<br>
	<c:if test="${requestScope.list == null }">
	<br>
	<h2>주문 내역이 없습니다.</h2>
	</c:if>
	<c:if test="${requestScope.list != null }">
	<table border="1" class="table table-hover">
		<tr>
			<th>주문번호</th>
			<th>대표 상호명</th>
			<th>주문일</th>
			<th>금액</th>
			<th>상세내역</th>
		</tr>
		<c:forEach var="dto" items="${requestScope.list }">
			<tr>
				<td>${dto.purchaseid }</td>
				<td>${dto.foodtruck_name }</td>
				<td>${dto.orderdate }</td>
				<td>${dto.price }원</td>
				<td>
				<form action="./ProfilePurchaseDetail2.pro?pageNum=${requestScope.pageNum }" method="post">
				<input type="hidden" name="purchaseid" value="${dto.purchaseid }">
    				<input type="submit" class="btn btn-primary btn-sm" value="주문 확인">
<!-- 				<input type="submit" value="주문 확인"> -->
				</form>
				</td>
			</tr>
		</c:forEach>
	</table>
			<nav aria-label="Page navigation example">
  				<ul class="pagination justify-content-center">
  				<c:if test="${requestScope.startPage > requestScope.pageBlock }">
  				  <li class="page-item"><a class="page-link" href="./OrderDetails.pay?pageNum=${requestScope.startPage - requestScope.pageBlock }">이전</a></li>
				</c:if>
    			<c:forEach var="i" begin="${requestScope.startPage }" end="${requestScope.endPage }" step="1">
    			  <li class="page-item"><a class="page-link" href="./OrderDetails.pay?pageNum=${i }">${i }</a></li>
				</c:forEach>
				<c:if test="${requestScope.endPage < requestScope.pageCount }">
    			  <li class="page-item"><a class="page-link" href="./OrderDetails.pay?pageNum=${requestScope.startPage + requestScope.pageBlock }">다음</a></li>
				</c:if>		
  				</ul>
			</nav>
	</c:if>		
	<br>
	<h1>fooding.</h1>
	<br>
	</div>
	</section>
	<br>
</div>
<jsp:include page="../inc/bottom.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>