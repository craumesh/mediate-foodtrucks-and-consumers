<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./fooding/khr/css/reviewForm.css" rel="stylesheet">
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
<title>리뷰 작성</title>
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
</head>
<body>
<jsp:include page="../inc/top.jsp" />	

	<c:if test="${empty sessionScope.id }">
		<c:redirect url="./FtMainAction.man" /> <!-- main 페이지 주소 -->
	</c:if>

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

	<section class="p-3 mb-2 bg-light text-dark">
	<div class="card">
	  <div class="card-header">
   		 	주문번호 : ${requestScope.purchaseid }<br>
  	  </div>
  	  </div>
		<div class="div1">
			<h1>Review Write</h1>
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
			<form action="./ProfileReviewWriteAction.pro" method="post" enctype="multipart/form-data">
			<input type="hidden" name="purchaseid" value="${requestScope.purchaseid }">
			<div class="div3">
  				<div class="row justify-content-center">
    				<label for="nickname" class="col-sm-2 col-form-label"><span>닉네임</span></label>
    				<div class="col-sm-9">
      					<input type="text" class="form-control" name="nickname" id="nickname" placeholder="닉네임을 입력하세요" required>
    				</div>
  				</div>
  				<div class="row justify-content-center">
    				<label for="title" class="col-sm-2 col-form-label"><span>제목</span></label>
    				<div class="col-sm-9">
      					<input type="text" class="form-control" name="title" id="title" placeholder="제목을 입력하세요" required>
    				</div>
  				</div>
			</div>
			<div class="row justify-content-center">
    		<label for="score" class="col-sm-2 col-form-label"><span>별점</span></label>
    		 <div class="col-sm-4">
			<select class="form-select form-select-sm-2" name="score" id="score" required>
				<option value="☆☆☆☆☆">☆☆☆☆☆</option>
				<option value="★☆☆☆☆">★☆☆☆☆</option>
				<option value="★★☆☆☆">★★☆☆☆</option>
				<option value="★★★☆☆">★★★☆☆</option>
				<option value="★★★★☆">★★★★☆</option>
				<option value="★★★★★">★★★★★</option>
			</select>
			</div>
			</div>
			<div class="form-floating">
  				<textarea class="form-control" placeholder="리뷰를 입력해주세요" id="textarea" name="content" required></textarea>
  				<label for="textarea">리뷰 내용</label>
			</div>
			<div class="row justify-content-center">
 			 	<label for="image" class="col-sm-3 col-form-label"><span>이미지 업로드</span></label>
 			 	<div class="col-sm-8">
  					<input class="form-control" type="file" id="image" name="image" accept="image/*">
  				</div>
			</div>
			<div class="row justify-content-center">
    			<label for="pw" class="col-sm-2 col-form-label"><span>비밀번호</span></label>
    			<div class="col-sm-9">
      				<input type="password" class="form-control" name="pw" id="pw" placeholder="리뷰 수정 및 삭제 시 사용될 비밀번호를 입력해주세요" required minlength="4">
    			</div>
  			</div>
  			<div class="col-auto mx-auto">
    			<button type="submit" class="btn btn-primary btn-sm">작성하기</button>
    			<button type="button" class="btn btn-secondary btn-sm" onclick="history.back();">이전 페이지로</button>
  			</div>
			<br>
			<br>
			<h1>fooding.</h1>
			</form>
		</div>
		</div>
	</section>
	<br>
	
<jsp:include page="../inc/bottom.jsp" />		
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>	
</body>
</html>