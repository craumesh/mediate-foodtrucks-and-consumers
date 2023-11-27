<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 내역 상세</title>
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lora:wght@500&display=swap" rel="stylesheet">

<link href="./fooding/khr/css/adminPurchaseDetail.css" rel="stylesheet">
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
<body style="background-color: #28282B;">
<!-- 세션 제어 (로그인 안 되어 있으면 메인 이동) -->
	<c:if test="${empty sessionScope.id }">
		<c:redirect url="./FtMainAction.man" />
	</c:if>
	<c:if test="${!empty sessionScope.id && !sessionScope.id.equals('admin') }">
		<c:redirect url="./FtMainAction.man"/>
	</c:if>
<!-- 세션 제어 (로그인 안 되어 있으면 메인 이동) -->

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

<!-- 주문 상세 내역 -->	
<!-- 프로필에서 주문 내역 확인 -->
	<section class="p-3 mb-2 bg-light text-dark">
	  <div class="card">
	  <div class="card-header">
   		 	주문번호 : ${requestScope.purchaseid }<br>
   		 	주문자 : ${requestScope.list[0].id }<br>
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
				<th>결제취소</th>
			<tr>
			</thead>
			<tbody>
			<c:set var="total" value="0" /> <!-- 누적 합 변수 선언 -->
			<c:forEach var="dto" items="${requestScope.list }" varStatus="loop">
			<tr style="vertical-align: middle;">
				<td>${loop.index + 1 }</td>
				<td>${dto.foodtruck_name }</td>
				<td>${dto.name }</td>
				<td>${dto.quantity }</td>
				<td>${dto.price }</td>
				<td>${dto.address }</td>
				<td>${dto.date } ${dto.time1 } ~ ${dto.time2 }</td>
				<td>
				<input type="button" class="btn btn-outline-danger btn-sm" value="결제취소" 
				onclick="setupAdRefund(${requestScope.purchaseid },${dto.price },${dto.detail_id },false)">
				</td>
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
    				<div class="col-sm-2">
						<input type="button" class="btn btn-danger btn" value="전체취소" onclick="setupAdRefund(${requestScope.purchaseid },${total},null,true)">
    				</div>
  			</div>
			<div class="div4">
			<input type="button" class="btn btn-secondary btn-sm" value="목록으로" onclick=" location.href='./AdminPurchase.adm?pageNum=${requestScope.pageNum}'; ">
			<br>
			</div>
			<br>
			<h1>fooding.</h1>
		</div>
	</section>
	<br>
<!-- 주문 상세 내역 -->		
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<script src="./pjs/refund.js"></script>
</body>
</html>