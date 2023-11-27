<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰</title>
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<link href="./fooding/khr/css/reviewList.css" rel="stylesheet">
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
<!-- 로그인 했을 때 -->
	<c:if test="${!empty sessionScope.id }">
	<jsp:include page="../inc/top.jsp" />
	<div class="row" style="float: left; margin-top: 50px;" id="moving-element">
		<div class="col-3" style="width: 250px">
		<div class="list-group">
  		<a href="./Profile.pro" class="list-group-item list-group-item-action">마이페이지</a>
  		<a href="./ProfileMemberInfo.pro" class="list-group-item list-group-item-action">회원정보</a>
  		<a href="./OrderDetails.pay" class="list-group-item list-group-item-action">주문내역</a>
  		<a href="./ProfileReviewList.pro" class="list-group-item list-group-item-action active" aria-current="true">
    		리뷰
  		</a>
  		<a href="./Support.sup" class="list-group-item list-group-item-action">고객센터</a>
		</div>	
		</div>
	</div>
	</c:if>
<!-- 로그인 했을 때 -->	
<!-- 로그인 안 했을 때 -->
	<c:if test="${empty sessionScope.id }">
	<jsp:include page="../inc/top_logout.jsp" />
	</c:if>	
<!-- 로그인 안 했을 때 -->

<div style="height: auto; min-height: 100%; padding-bottom: 22px;">
	<section class="p-3 mb-2 bg-light text-dark">
	<div class="div1">
	<br>
	<c:if test="${empty param.search }">
		<h1>Review</h1>
	</c:if>
	
	<c:if test="${!empty param.search }">
		<h1>My Review</h1>
	</c:if>
	<br>
	
	<c:if test="${empty requestScope.list }">
	<h2>고객님이 작성하신 리뷰가 없습니다.</h2>
	</c:if>
	
	<c:forEach var="dto" items="${requestScope.list }">
	<div class="tablediv">
	<table border="1" class="table">
	<c:if test="${!empty dto.image }">
	<tr>
	<td class="imagetd"><img src="./upload/review/${dto.image }" class="img-thumbnail" alt="..."></td>
	</tr>
	</c:if>
	<c:if test="${empty dto.image }">
	<tr>
	<td class="imagetd"><img src="./fooding/khr/profile/images/foodtruck.png" class="img-thumbnail" alt="..."></td>
	</tr>
	</c:if>
	<tr>
	<td><a href="./ProfileReviewContent.pro?review_id=${dto.review_id }&pageNum=${requestScope.pageNum }&search=${param.search }">${dto.title }</a></td>
	</tr>
	<tr>
	<td>${dto.score }</td>
	</tr>
	<tr>
	<td>${dto.date }</td>
	</tr>
	</table>
	</div>
	</c:forEach>
	
	<div class="button">
		<nav aria-label="Page navigation example" id="nav">
  			<ul class="pagination justify-content-center">
  			<c:if test="${requestScope.startPage > requestScope.pageBlock }">
  				<li class="page-item"><a class="page-link" href="./ProfileReviewList.pro?pageNum=${requestScope.startPage - requestScope.pageBlock }&search=${param.search }">이전</a></li>
			</c:if>
    		<c:forEach var="i" begin="${requestScope.startPage }" end="${requestScope.endPage }" step="1">
    			<li class="page-item"><a class="page-link" href="./ProfileReviewList.pro?pageNum=${i }&search=${param.search }">${i }</a></li>
			</c:forEach>
			<c:if test="${requestScope.endPage < requestScope.pageCount }">
    			<li class="page-item"><a class="page-link" href="./ProfileReviewList.pro?pageNum=${requestScope.startPage + requestScope.pageBlock }&search=${param.search }">다음</a></li>
			</c:if>		
  			</ul>
			</nav>
			<div class="justify-content-center">
				<a href="./ProfileReviewList.pro?pageNum=${sessionScope.pageNum }" class="btn btn-primary btn-sm" role="button" aria-disabled="false" id="input1">전체 리뷰 보기</a>
				<c:if test="${!empty sessionScope.id }">
				<a href="./ProfileReviewList.pro?pageNum=${sessionScope.pageNum }&search=${sessionScope.id }" class="btn btn-success btn-sm" role="button" aria-disabled="false" id="input2">내 리뷰 보기</a>
				</c:if>
			</div>
		</div>
	</div>
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
	</div>
	<jsp:include page="../inc/bottom.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>              
</body>
</html>