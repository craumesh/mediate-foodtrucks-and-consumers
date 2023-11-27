<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./fooding/khr/css/noticeForm.css" rel="stylesheet">
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
<title>공지사항</title>
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
</head>
<body>

	<c:if test="${!empty sessionScope.id }">
	<jsp:include page="../inc/top.jsp" />
	</c:if>
	
	<c:if test="${empty sessionScope.id }">
		<c:redirect url="./Support.sup" /> <!-- faq 페이지 주소 -->	
	</c:if>

	<c:if test="${!empty sessionScope.id && !sessionScope.id.equals('admin') }">
		<c:redirect url="./Support.sup" /> <!-- faq 페이지 주소 -->	
	</c:if>

<div class="row" style="float: left; margin-top: 50px;" id="moving-element">
<div class="col-3" style="width: 250px">
<div class="list-group">
  <a href="./Support.sup" class="list-group-item list-group-item-action">고객센터</a>
  <a href="./SupportNoticeList.sup" class="list-group-item list-group-item-action active" aria-current="true">
    공지사항
  </a>
  <a href="./SupportEventList.sup" class="list-group-item list-group-item-action">이벤트</a>  
  <a href="./SupportFaqList.sup" class="list-group-item list-group-item-action">자주 묻는 질문</a>
  <a class="list-group-item list-group-item-action disabled" aria-disabled="true"><b>1:1 문의 : 1234-5678</b></a>
</div>	
</div>
</div>

	<section class="p-3 mb-2 bg-light text-dark">
		<br>
		<div class="div1">
			<h1>Notice Update</h1>
			<br>
			<div class="div2">
			<form action="./SupportNoticeUpdatePro.sup?table_id=${requestScope.table_id }&pageNum=${requestScope.pageNum}" method="post">
			<div class="div3">
  				<div class="row justify-content-center">
    				<label for="title" class="col-sm-2 col-form-label"><span>제목</span></label>
    				<div class="col-sm-9">
      					<input type="text" class="form-control" name="title" id="title" placeholder="제목을 입력하세요" value="${dto.title }" required>
    				</div>
  				</div>
			</div>
			<div class="form-floating">
  				<textarea class="form-control" placeholder="공지사항 본문을 입력해주세요" id="textarea" name="content" required>${dto.content }</textarea>
  				<label for="textarea">공지사항 본문</label>
			</div>
  			<div class="col-auto mx-auto">
  				<input type="hidden" name="class" value="공지">
    			<button type="submit" class="btn btn-primary btn-sm">수정하기</button>
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