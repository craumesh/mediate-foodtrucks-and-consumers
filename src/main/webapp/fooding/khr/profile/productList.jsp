<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<link href="./fooding/khr/css/adminProduct.css" rel="stylesheet">
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
	<h1>Product List</h1>
	
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
		<th>상품번호</th>
		<th>상품명</th>
		<th>가격</th>
		<th>판매점</th>
		<th>대분류</th>
		<th>소분류</th>
		<th>상품 삭제</th>
	</tr>
	<c:forEach var="dto" items="${requestScope.list }">
	<tr>
		<td>${dto.product_id }</td>
		<td>${dto.product_name }</td>
		<td>${dto.price }</td>
		<td>${dto.foodtruck_name }</td>
		<td>${dto.largeclass }</td>
		<td>${dto.smallclass }</td>
		<td><button type="button" class="btn btn-secondary btn-sm" onclick="if(confirm('상품 삭제를 진행하시겠습니까?')){
			if(confirm('다시 한 번 더 확인해주세요')){
				alert('상품 삭제를 진행합니다.');
				location.href='./ProfileProductDelete.pro?product_id=${dto.product_id}';
			}else{
				alert('취소하셨습니다.');
			}
		}else{
			alert('취소하셨습니다.');
		}">삭제</button></td>
	</tr>
	</c:forEach>
</table>
</c:if>
<br>
<nav aria-label="Page navigation example">
  	<ul class="pagination justify-content-center">
  		<c:if test="${requestScope.startPage > requestScope.pageBlock }">
  			<li class="page-item"><a class="page-link" href="./ProfileProductList.pro?pageNum=${requestScope.startPage - requestScope.pageBlock }">이전</a></li>
		</c:if>
    	<c:forEach var="i" begin="${requestScope.startPage }" end="${requestScope.endPage }" step="1">
    		<li class="page-item"><a class="page-link" href="./ProfileProductList.pro?pageNum=${i }">${i }</a></li>
		</c:forEach>
		<c:if test="${requestScope.endPage < requestScope.pageCount }">
    	    <li class="page-item"><a class="page-link" href="./ProfileProductList.pro?pageNum=${requestScope.startPage + requestScope.pageBlock }">다음</a></li>
		</c:if>		
  	</ul>
</nav>
<form action="./ProfileProductAdd.pro" method="post">
<input type="hidden" name="member_id" value="${requestScope.member_id }">
<button type="submit" class="btn btn-primary btn-sm">상품 추가</button>
<input type="button" class="btn btn-secondary btn-sm" value="이전 페이지로" onclick="history.back(); ">
</form>
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
<br>
<br>
<jsp:include page="../inc/bottom.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>