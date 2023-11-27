<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>푸드트럭 콘텐츠</title>
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lora:wght@500&display=swap" rel="stylesheet">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lora:wght@500&display=swap" rel="stylesheet">

<link href="./fooding/khr/css/adminFoodtruckForm.css" rel="stylesheet">
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
	<h1>Foodtruck Info</h1>
	<br>
	<div class="row justify-content-center">
    	<label for="nickname" class="col-sm-2 col-form-label"><span>푸드트럭명</span></label>
    		<div class="col-sm-9">
      		<input type="text" class="form-control" name="foodtruck_name" id="foodtruck_name" value="${requestScope.dto.foodtruck_name }" readonly>
    	</div>
  	</div>
  	<div class="row justify-content-center">
    	<label for="nickname" class="col-sm-4 col-form-label"><span>푸드트럭 운영자 아이디</span></label>
    		<div class="col-sm-5">
      		<input type="text" class="form-control" name="id" id="id" value="${requestScope.dto.id }" readonly>
    	</div>
  	</div>
	<div class="row justify-content-center">
			<span>사진</span><br>
			<img src="./upload/foodtruck/${requestScope.dto.image }" class="img-fluid" id="reviewimage">
	</div>
	<div class="form-floating">
  		<textarea class="form-control" placeholder="푸드트럭 정보를 입력해주세요" id="textarea" name="info" readonly>${requestScope.dto.info }</textarea>
  		<label for="textarea">푸드트럭 정보</label>
	</div>
	<div id="placecontainer">  	
	    <c:forEach var="wayInfoArr" items="${wayInfoArr}">
	        <c:if test="${not empty wayInfoArr}">
	        <div style="width: 300px; margin: 10px auto;">
	            <label class="form-control" style="line-height: 16px;">
	                ${wayInfoArr.split(",")[0]} - 경로 ${wayInfoArr.split(",")[1]}개
		            <button type="button" class="btn btn-primary btn-sm" onclick="location.href='./ProfileFoodtruckWayAdd.pro?foodtruck_id=${requestScope.dto.foodtruck_id}&pageNum=${requestScope.pageNum }&date=${wayInfoArr.split(',')[0]}';">수정</button>
		            <button type="button" class="btn btn-danger btn-sm" onclick="location.href='./ProfileFoodtruckWayDel.pro?foodtruck_id=${requestScope.dto.foodtruck_id}&pageNum=${requestScope.pageNum }&date=${wayInfoArr.split(',')[0]}';">삭제</button>
	            </label>
	        </div>    
	        </c:if>
	    </c:forEach>
	</div>
	<div class="col-auto mx-auto">
    	<button type="button" class="btn btn-primary btn-sm" onclick="location.href='./ProfileFoodtruckWayAdd.pro?foodtruck_id=${requestScope.dto.foodtruck_id}&pageNum=${requestScope.pageNum }';">일정등록</button>
    	<button type="button" class="btn btn-secondary btn-sm" onclick="history.back();">이전 페이지로</button>
    	<br>
    	<br>
    	<h1>fooding.</h1>
  	</div>
</section>
<br>
<jsp:include page="../inc/bottom.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>