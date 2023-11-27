<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰</title>
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<link href="./fooding/khr/css/reviewContent.css" rel="stylesheet">
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
<title>reviewForm</title>
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

	<section class="p-3 mb-2 bg-light text-dark">
		<div class="div1">
			<br>
			<h1>Review Content</h1>
			<br>
			<div class="div2">
			<table class="table">
			<thead>
				<tr>
					<th>No.</th>
					<th>상호명</th>
					<th>상품명</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dto" items="${requestScope.list }" varStatus="loop">
				<tr>
					<td>${loop.index + 1 }</td>
					<td>${dto.foodtruck_name }</td>
					<td>${dto.name }</td>
				</tr>
				</c:forEach>			
			</tbody>
			</table>
			</div>
			<div class="div3">
  				<div class="row justify-content-center">
    				<label for="nickname" class="col-sm-2 col-form-label"><span>닉네임</span></label>
    				<div class="col-sm-9">
      					<input type="text" class="form-control" name="nickname" id="nickname" value="${dto.nickname }" readonly>
    				</div>
  				</div>
  				<div class="row justify-content-center">
    				<label for="title" class="col-sm-2 col-form-label"><span>제목</span></label>
    				<div class="col-sm-9">
      					<input type="text" class="form-control" name="title" id="title" value="${dto.title }" readonly>
    				</div>
  				</div>
  				<div class="row justify-content-center">
    				<label for="title" class="col-sm-2 col-form-label"><span>별점</span></label>
    				<div class="col-sm-9">
      					<input type="text" class="form-control" name="score" id="score" value="${dto.score }" readonly>
    				</div>
  				</div>
			</div>
			<c:if test="${!empty dto.image }">
			<span>사진</span><br>
			<img src="./upload/review/${dto.image }" class="img-fluid" id="reviewimage">
			</c:if>
			<div class="form-floating">
  				<textarea class="form-control"id="textarea" name="content" readonly>${dto.content }</textarea>
  				<label for="textarea">리뷰 내용</label>
			</div>
  			<c:if test="${requestScope.result == 1 }">
  			<div class="row justify-content-center">
    			<label for="pw" class="col-sm-2 col-form-label"><span>비밀번호</span></label>
    				<div class="col-sm-9">
      			<input type="password" class="form-control" name="pw" id="pw" placeholder="리뷰 수정 및 삭제 시 비밀번호를 입력해주세요" required>
    		</div>
  			</div>
  			<div class="col-auto mx-auto">
  			<form action="./ProfileReviewUpdate.pro?pageNum=${requestScope.pageNum}&search=${param.search }" method="post" onsubmit="return password1();" style="display: inline-block">
    			<input type="hidden" name="review_id" value="${dto.review_id }">
    			<button type="submit" class="btn btn-primary btn-sm">리뷰 수정</button>
			</form>
			<form action="./ProfileReviewDelete.pro?pageNum=${requestScope.pageNum}&search=${param.search }" method="post" onsubmit="return password2();" style="display: inline-block">
				<input type="hidden" name="review_id" value="${dto.review_id }">
    			<button type="submit" class="btn btn-secondary btn-sm">리뷰 삭제</button>
    		</form>
    			<button type="button" class="btn btn-primary btn-sm" onclick=" location.href='./ProfileReviewList.pro?pageNum=${requestScope.pageNum}&search=${param.search }'; " style="display: inline-block">리뷰 페이지로</button>
			</div>
    		</c:if>
    		<c:if test="${requestScope.result != 1 }">
    		<div class="col-auto mx-auto">
    		    <button type="button" class="btn btn-primary btn-sm" onclick=" location.href='./ProfileReviewList.pro?pageNum=${requestScope.pageNum}&search=${param.search }'; " style="display: inline-block">리뷰 페이지로</button>
  			</div>
  			</c:if>
  			<br>
  			<br>
  			<h1>fooding.</h1>
  	</div>
	</section>
	<br>
	
	<script>
	function password1(){
		if(document.getElementById("pw").value == ""){
			alert("비밀번호를 입력해주세요.");
			return false;
		}else if(document.getElementById("pw").value == "${dto.pw }"){
			alert("리뷰 수정 페이지로 이동합니다.");
			return true;
// 			location.href="./ProfileReviewUpdate.pro?review_id=${requestScope.dto.review_id }&pageNum=${requestScope.pageNum}&search=${param.search }";
		}else{
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		}
	}
	
	function password2(){
		if(document.getElementById("pw").value == ""){
			alert("비밀번호를 입력해주세요.");
			return false;
		}else if(document.getElementById("pw").value == "${dto.pw }"){
			if(confirm("리뷰를 삭제하시겠습니까?")){
				return true;
// 				location.href="./ProfileReviewDelete.pro?review_id=${requestScope.dto.review_id}&pageNum=${requestScope.pageNum}&search=${param.search }";
			}else{
				alert("취소하셨습니다.");
				return false;
			}
		}else{
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		}
	}
	</script>
	
<jsp:include page="../inc/bottom.jsp" />	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>