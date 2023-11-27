<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lora:wght@500&display=swap" rel="stylesheet">

<link href="./fooding/khr/css/deleteForm.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href='https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css'>
</head>
<body>
<!-- 	<h1>memberInfo.jsp (MVC)</h1> -->

	<!-- 세션 제어 -->
	<c:if test="${empty sessionScope.id }">
		<c:redirect url="./FtMainAction.man" /> <!-- main 페이지 주소 -->
	</c:if>
	<!-- 세션 제어 -->
	
	<section class="p-3 mb-2 bg-light text-dark">
		<div class="div1">
			<br>
			<h1>Member Delete</h1>
			<form action="./ProfileDeleteAction.pro" method="post">
			<input type="hidden" value="${requestScope.member_id }" name="member_id">
			<br>
			<span>
			현재 귀하께서 이용 중이신 'fooding.'에서 이용자의 계정이 삭제됩니다.<br>
			탈퇴 시 현재 가입하신 개인 정보 (아이디, 휴대전화, 이메일)로 1년간 가입을 다시 진행하실 수 없습니다.<br>
			탈퇴 시 개인 정보가 즉시 삭제되지 않고, 1년 간 보관됨을 알려드립니다.<br>
			동의하신다면 아래에 탈퇴 사유를 입력 후 약관 동의를 체크하시고 진행하십시오.
			</span>
			<br>
			<div class="form-floating">
  				<textarea class="form-control" placeholder="회원 탈퇴 사유를 입력해주세요" id="textarea" name="reason" required style="resize: none; height: 100px;"></textarea>
  				<label for="textarea">회원 탈퇴 사유</label>
			</div>
			<div class="form-check" style="margin-top: 10px;">
  				<input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" required>
  				<label class="form-check-label" for="flexCheckChecked">
    			<span>회원 탈퇴 약관 동의</span>
  				</label>
			</div>
			<div class="row justify-content-center">
	    	<label for="pw" class="col-sm-3 col-form-label"><span>현재 비밀번호</span></label>
	    	<div class="col-sm-6">
	      		<input type="password" class="form-control" name="pw" id="pw" placeholder="현재 비밀번호를 입력하세요" required>
	    	</div>
	  		</div>	
  			<br>
			<!-- 부트스트랩 input 태그 버튼 -->
			<div class="justify-content-center">
			<div class="row row-cols-lg-auto g-3 justify-content-center">
				<button type="submit" class="btn btn-danger btn-sm">회원 탈퇴</button>&nbsp;&nbsp;
				<button type="button" class="btn btn-primary btn-sm" onclick="history.back();">이전 페이지로</button>
			</div>
			</div>
			</form>
			<br>
			<h1>fooding.</h1>	
		</div>
	</section>
	<br>


	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>