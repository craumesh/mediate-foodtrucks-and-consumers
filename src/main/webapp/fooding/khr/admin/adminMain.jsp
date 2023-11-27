<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lora:wght@500&display=swap" rel="stylesheet">

<link href="./fooding/khr/css/adminMain.css" rel="stylesheet">
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
  <a href="./Admin.adm" class="list-group-item list-group-item-action active" aria-current="true">
    관리자 페이지
  </a>
  <a href="./AdminPurchase.adm" class="list-group-item list-group-item-action">주문내역 관리</a>
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
<h1>Administrator page</h1>
<br>
<h2>New Order History</h2>
<br>
<c:if test="${empty requestScope.list }">
<br>
<br>
<h2>현재 주문 내역이 존재하지 않습니다.</h2>
<br>
<br>
</c:if>
<c:if test="${!empty requestScope.list }">
<table class="table table-hover">
	<tr>
		<th>주문번호</th>
		<th>주문자</th>
		<th>대표 상호명</th>
		<th>주문일</th>
		<th>금액</th>
		<th>상세 내역</th>
	</tr>
	<c:forEach var="dto" items="${requestScope.list4 }">
	<tr>
		<td>${dto.purchaseid }</td>
		<td>${dto.id }</td>
		<td>${dto.foodtruck_name }</td>
		<td>${dto.orderdate }</td>
		<td>${dto.price }</td>
		<td><button type="button" class="btn btn-secondary btn-sm" onclick="location.href='./AdminPurchaseDetail.adm?purchaseid=${dto.purchaseid}';">상세 내역</button></td>
	</tr>
	</c:forEach>
</table>
</c:if>
<br>
<br>
<hr>
<br>
<br>
<h2>New Foodtruck</h2>
<br>
<c:if test="${empty requestScope.list }">
<br>
<br>
<h2>현재 푸드트럭이 등록되어 있지 않습니다.</h2>
<br>
<br>
</c:if>
<c:if test="${!empty requestScope.list }">
<table class="table table-hover">
	<tr>
		<th>부여번호</th>
		<th>푸드트럭명</th>
		<th>활성화 여부</th>
		<th>정보 확인</th>
	</tr>
	<c:forEach var="dto" items="${requestScope.list }">
	<tr>
		<td>${dto.foodtruck_id }</td>
		<td>${dto.foodtruck_name }</td>
		<td>${dto.f_activation }</td>
		<td><button type="button" class="btn btn-secondary btn-sm" onclick="location.href='./AdminFoodtruckContent.adm?foodtruck_id=${dto.foodtruck_id}';">확인</button></td>
	</tr>
	</c:forEach>
</table>
</c:if>
<button class="btn btn-primary btn-sm" type="button" onclick="location.href='./AdminFoodtruckAdd.adm';">신규 푸드트럭 등록</button>
<br>
<br>
<hr>
<br>
<h2>New Member</h2>
<br>
<c:if test="${empty requestScope.list2 }">
<br>
<br>
<h2>현재 회원이 등록되어 있지 않습니다.</h2>
<br>
<br>
</c:if>
<c:if test="${!empty requestScope.list2 }">
<table class="table table-hover">
	<tr>
		<th>회원번호</th>
		<th>회원ID</th>
		<th>회원이름</th>
		<th>전화번호</th>
		<th>이메일</th>
		<th>가입일</th>
		<th>활성화여부</th>
		<th>회원삭제</th>
	</tr>
	<c:forEach var="dto" items="${requestScope.list2 }">
	<tr>
		<td>${dto.member_id }</td>
		<td>${dto.id }</td>
		<td>${dto.name }</td>
		<td>${dto.phone }</td>
		<td>${dto.email }</td>
		<td>${dto.regdate }</td>
		<td>${dto.activation }</td>
		<td>
		<c:if test="${dto.activation.equals('Y') }">
		<button type="button" class="btn btn-secondary btn-sm" onclick="if(confirm('회원 삭제를 진행하시겠습니까?')){
			if(confirm('다시 한 번 더 확인해주세요')){
				alert('회원 삭제를 진행합니다.');
				location.href='./AdminMemberDelete.adm?member_id=${dto.member_id}';
			}else{
				alert('취소하셨습니다.');
			}
		}else{
			alert('취소하셨습니다.');
		}">삭제</button>
		</c:if></td>
	</tr>
	</c:forEach>
</table>
</c:if>
<br>
<hr>
<br>
<h2>New Product</h2>
<br>
<c:if test="${empty requestScope.list3 }">
<br>
<br>
<h2>현재 상품이 등록되어 있지 않습니다.</h2>
<br>
<br>
</c:if>
<c:if test="${!empty requestScope.list3 }">
<table class="table table-hover">
	<tr>
		<th>상품번호</th>
		<th>상품명</th>
		<th>가격</th>
		<th>판매점</th>
		<th>대분류</th>
		<th>소분류</th>
		<th>활성화여부</th>
		<th>상품 삭제</th>
	</tr>
	<c:forEach var="dto" items="${requestScope.list3 }">
	<tr>
		<td>${dto.product_id }</td>
		<td>${dto.product_name }</td>
		<td>${dto.price }</td>
		<td>${dto.foodtruck_name }</td>
		<td>${dto.largeclass }</td>
		<td>${dto.smallclass }</td>
		<td>${dto.p_activation }</td>
		<td>
		<c:if test="${dto.p_activation.equals('Y') }">
		<button type="button" class="btn btn-secondary btn-sm" onclick="if(confirm('상품 삭제를 진행하시겠습니까?')){
			if(confirm('다시 한 번 더 확인해주세요')){
				alert('상품 삭제를 진행합니다.');
				location.href='./AdminProductDelete.adm?product_id=${dto.product_id}';
			}else{
				alert('취소하셨습니다.');
			}
		}else{
			alert('취소하셨습니다.');
		}">삭제</button>
		</c:if>
		</td>
	</tr>
	</c:forEach>
</table>
</c:if>
<button class="btn btn-primary btn-sm" type="button" onclick="location.href='./AdminProductAdd.adm';">신규 상품 등록</button>
<br>
<br>
<h1>fooding.</h1>
<br>
</section>
<br>
<%-- <jsp:include page="../inc/bottom.jsp" /> --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>