<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<link href="./fooding/khr/css/memberInfo.css" rel="stylesheet">
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
<!-- 	<h1>memberInfo.jsp (MVC)</h1> -->
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
		<div class="div1">
			<br>
			<h1>My Info</h1>
			<br>
			<div id="memberinfo">
			<div class="row g-3">
			<div class="col-md-6">
    			<label for="inputId" class="form-label">아이디</label>
    			<input type="text" class="form-control" id="id" value="${requestScope.dto.id }" name="id" readonly>
  			</div>
  			<div class="col-md-6">
   				<label for="inputName" class="form-label">이름</label>
    			<input type="text" class="form-control" id="name" value="${requestScope.dto.name }" name="name" readonly>
 			</div>
  			<div class="col-12">
    			<label for="inputPhone" class="form-label">전화번호</label>
    			<input type="text" class="form-control" id="inputPhone" name="phone" value=${requestScope.dto.phone } readonly>
  			</div>				
 			<div class="col-12">
    			<label for="inputEmail" class="form-label">이메일</label>
    			<input type="email" class="form-control" id="inputEmail" name="email" value=${requestScope.dto.email } readonly>
  			</div>
  			 <div class="col-12">
    			<label for="inputRegdate" class="form-label">가입일</label>
    			<input type="text" class="form-control" id="inputRegdate" name="regdate" value=${requestScope.dto.regdate } readonly>
  			</div>	
    			<c:if test="${requestScope.count > 0 }">
 				<div class="col-auto mx-auto">
 				<br>
    			<h2>foodtruck</h2>
<%--     				<button type="button" class="btn btn-primary btn-sm" onclick="location.href='./ProfileFoodtruckLocation.pro?member_id=${requestScope.member_id}';">경로 추가</button><br><br> --%>
		    	<div class="row row-cols-lg-auto g-3 justify-content-center">
		    	    <form action="./ProfileFoodtruckPurchaseList.pro" method="post">
    				<input type="hidden" name="member_id" value="${requestScope.member_id }">
    				<button type="submit" class="btn btn-primary btn-sm">푸드트럭 주문 내역</button>
    				</form>
    				<form action="./ProfileFoodtruckList.pro" method="post">
    				<input type="hidden" name="member_id" value="${requestScope.member_id }">
    				<button type="submit" class="btn btn-secondary btn-sm">경로 입력</button>
    				</form>
    				<form action="./ProfileProductAdd.pro" method="post">
    				<input type="hidden" name="member_id" value="${requestScope.member_id }">
    				<button type="submit" class="btn btn-primary btn-sm">상품 추가</button>
    				</form>
    				<form action="./ProfileProductList.pro" method="post">
    				<input type="hidden" name="member_id" value="${requestScope.member_id }">
    				<button type="subtmit" class="btn btn-secondary btn-sm">상품 목록</button>
  					</form>
  				</div>
  				</div>
    			</c:if>
			</div>
			</div>
			</div>
			<br>
			<hr>
			<br>
			<h2>Update & Delete</h2>
			<br>
			<!-- 부트스트랩 input 태그 버튼 -->
			<div class="justify-content-center">
			<div class="row row-cols-lg-auto g-3 justify-content-center">
 				<div class="col-md-6">
    				<input type="password" class="form-control" name="pw" id="pw" placeholder="현재 비밀번호를 입력하세요">
  				</div>
				<button type="button" class="btn btn-primary" onclick="password1();">정보 수정</button>&nbsp;
				<form action="./ProfileDelete.pro" method="post" onsubmit="return password2();">
				<input type="hidden" name="member_id" value="${dto.member_id }">
				<button type="submit" class="btn btn-secondary">회원 탈퇴</button>
				</form>
			</div>
			</div>
			<br>
			<br>
			<h1>fooding.</h1>	
	</section>
	<br>

<!-- 회원 정보 수정 및 탈퇴 비밀번호 검증 -->
	<script>
	/* 회원 정보 수정하기 전 비밀번호 검증 메서드 */
	function password1(){
		if(document.getElementById("pw").value == ""){
			alert("비밀번호를 입력해주세요.");
		}
		else if(document.getElementById("pw").value == "${requestScope.dto.pw }"){
			alert("회원 정보 수정 페이지로 이동합니다.");
			location.href="./ProfileUpdate.pro";
		}else{
			alert("비밀번호가 일치하지 않습니다.");
		}
	}
	/* 회원 정보 수정하기 전 비밀번호 검증 메서드 */
	
	/* 회원 탈퇴 전 비밀번호 검증 메서드 */
	/* 회원 탈퇴는 1. 비밀번호 일치 2. 확인 버튼 클릭 */
		function password2(){
		if(document.getElementById("pw").value == ""){
			alert("비밀번호를 입력해주세요.");
			return false;
		}
		else if(document.getElementById("pw").value == "${requestScope.dto.pw }"){
			alert("회원 탈퇴 페이지로 이동합니다.");
			return true;
		}else{
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		}
	}
	
	function password3(){
		if(document.getElementById("pw").value == ""){
			alert("비밀번호를 입력해주세요.");
		}
		else if(document.getElementById("pw").value == "${requestScope.dto.pw }"){
			if(confirm("탈퇴하겠습니까?")){
				alert("회원을 탈퇴합니다. 이용해주셔서 감사합니다.");
				location.href="./ProfileDelete.pro";
			}else{
				alert("취소되었습니다.");
			}
		}else{
			alert("비밀번호가 일치하지 않습니다.");
		}
	}
	/* 회원 탈퇴 전 비밀번호 검증 메서드 */
	</script>
<!-- 회원 정보 수정 및 탈퇴 비밀번호 검증 -->

<jsp:include page="../inc/bottom.jsp" />	
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>