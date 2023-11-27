<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문내역</title>
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<link href="./fooding/khr/css/purchaseDetail.css" rel="stylesheet">
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

<jsp:include page="../inc/top.jsp" />
<%-- 	<h1>purchaseDetail.jsp (MVC) [주문번호 : ${requestScope.purchaseid }]</h1> --%>

<!-- 로그인 세션 제어 -->
	<c:if test="${empty sessionScope.id }">
		<c:redirect url="./FtMainAction.man" /> <!-- main 페이지 주소 -->
	</c:if>
<!-- 로그인 세션 제어 -->

<!-- 사이드바 -->
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
<!-- 사이드바 -->

<!-- 주문 상세 내역 -->	
<!-- 프로필에서 주문 내역 확인 -->
	<section class="p-3 mb-2 bg-light text-dark">
	  <div class="card">
	  <div class="card-header">
   		 	주문번호 : ${requestScope.purchaseid }<br>
			주문일 : ${requestScope.list[0].orderdate } <!-- 순차적으로 주문이 되므로 리스트의 0번 인덱스 = MIN(orderdate) -->
  	  </div>
  	  </div>
		<div class="div1">
			<h1>Order Details</h1>
			<br>

			<table class="table">
			<thead>
			<tr>
				<th>상품내역</th>
				<th>상호명</th>
				<th>상품이름</th>
				<th>수량</th>
				<th>가격</th>
				<th>수령지</th>
				<th>수령일시</th>
			<tr>
			</thead>
			<tbody>
			<c:set var="total" value="0" /> <!-- 누적 합 변수 선언 -->
			<c:forEach var="dto" items="${requestScope.list }" varStatus="loop">
			<tr>
				<td>${loop.index + 1 }</td>
				<td>${dto.foodtruck_name }</td>
				<td>${dto.name }</td>
				<td>${dto.quantity }</td>
				<td>${dto.price }</td>
				<td>${dto.address }</td>
				<td>${dto.date } ${dto.stoptime }</td>
			</tr>
			<c:set var="total" value="${total + dto.price }" /> <!-- 누적 합 계산 -->
			</c:forEach> 
			</tbody>
			</table>
			<div class="row justify-content-center">
    				<label for="nickname" class="col-sm-2 col-form-label"><span>총 결제 금액</span></label>
    				<div class="col-sm-2">
      					<input type="text" class="form-control" name="total" id="total" value="${total }원" readonly>
    				</div>
  			</div>
			<div class="div4">
			<c:if test="${requestScope.result == 1 }">
			<form action="./ProfileReviewContent.pro" method="post">
			<input type="hidden" name="review_id" value="${requestScope.review_id }">
			<!-- 부트스트랩 버튼 -->
			<input type="submit" class="btn btn-primary btn-sm" value="리뷰 확인하기">
			<input type="button" class="btn btn-secondary btn-sm" value="이전 페이지로" onclick=" location.href='./Profile.pro?pageNum=${requestScope.pageNum}'; ">
			<!-- 부트스트랩 버튼 -->
			</form>
			</c:if>
			<c:if test="${requestScope.result == 0 }">
			<form action="./ProfileReviewWrite.pro" method="post">
			<input type="hidden" name="purchaseid" value="${requestScope.purchaseid }">
			<!-- 부트스트랩 버튼 -->
			<input type="submit" class="btn btn-primary btn-sm" value="리뷰 작성하기">
			<input type="button" class="btn btn-secondary btn-sm" value="이전 페이지로" onclick=" location.href='./Profile.pro?pageNum=${requestScope.pageNum}'; ">
			<!-- 부트스트랩 버튼 -->
			</form>
			</c:if>
			<br>
			</div>
			<br>
			<h1>fooding.</h1>
		</div>
	</section>
	<br>
<!-- 주문 상세 내역 -->		

<jsp:include page="../inc/bottom.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>