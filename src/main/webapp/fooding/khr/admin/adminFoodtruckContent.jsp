<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>푸드트럭 관리</title>
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
  <a href="./AdminPurchase.adm" class="list-group-item list-group-item-action">주문내역 관리</a>
  <a href="./AdminFoodtruck.adm" class="list-group-item list-group-item-action active" aria-current="true">푸드트럭 관리</a>
  <a href="./AdminMember.adm" class="list-group-item list-group-item-action">회원 관리</a>
  <a href="./AdminProduct.adm" class="list-group-item list-group-item-action">상품 관리</a>
  <a href="./Profile.pro" class="list-group-item list-group-item-action">관리자 모드 종료</a>
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

	<div class="col-auto mx-auto">
    	<c:if test="${requestScope.dto.f_activation.equals('Y') }">
    <div id="placecontainer">  	
	    <c:forEach var="wayInfoArr" items="${wayInfoArr}">
	        <c:if test="${not empty wayInfoArr}">
	        <div style="width: 300px; margin: 10px auto;">
	            <label class="form-control" style="line-height: 16px;">
	                ${wayInfoArr.split(",")[0]} - 경로 ${wayInfoArr.split(",")[1]}개
		            <button type="button" class="btn btn-primary btn-sm" onclick="location.href='./AdminFoodtruckWayAddAction.adm?foodtruck_id=${requestScope.dto.foodtruck_id}&pageNum=${requestScope.pageNum }&date=${wayInfoArr.split(',')[0]}';">수정</button>
		            <button type="button" class="btn btn-danger btn-sm" onclick="location.href='./AdminFoodtruckWayDelAction.adm?foodtruck_id=${requestScope.dto.foodtruck_id}&pageNum=${requestScope.pageNum }&date=${wayInfoArr.split(',')[0]}';">삭제</button>
	            </label>
	        </div>    
	        </c:if>
	    </c:forEach>
	</div>
    	<button type="button" class="btn btn-primary btn-sm" onclick="location.href='./AdminFoodtruckUpdate.adm?foodtruck_id=${requestScope.dto.foodtruck_id}&pageNum=${requestScope.pageNum }';">수정하기</button>
    	<button type="button" class="btn btn-primary btn-sm" onclick="location.href='./AdminFoodtruckWayAddAction.adm?foodtruck_id=${requestScope.dto.foodtruck_id}&pageNum=${requestScope.pageNum }';">일정등록</button>
    	<button type="button" class="btn btn-danger btn-sm" onclick="if(confirm('푸드트럭 삭제를 진행하시겠습니까?')){
			if(confirm('다시 한 번 더 확인해주세요')){
				alert('푸드트럭 삭제를 진행합니다.');
				location.href='./AdminFoodtruckDelete.adm?foodtruck_id=${dto.foodtruck_id}';
			}else{
				alert('취소하셨습니다.');
			}
		}else{
			alert('취소하셨습니다.');
		}">삭제하기</button>
		</c:if>
    	<button type="button" class="btn btn-secondary btn-sm" onclick="location.href='./AdminFoodtruck.adm?pageNum=${requestScope.pageNum}';">목록으로</button>
    	<br>
    	<br>
    	<h1>fooding.</h1>
  	</div>
</section>
<br>
<%-- <jsp:include page="../inc/bottom.jsp" /> --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>