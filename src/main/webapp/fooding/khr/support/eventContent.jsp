<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트</title>
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<link href="./fooding/khr/css/faqContent.css" rel="stylesheet">
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
	</c:if>
<!-- 로그인 했을 때 -->	
<!-- 로그인 안 했을 때 -->
	<c:if test="${empty sessionScope.id }">
	<jsp:include page="../inc/top_logout.jsp" />
	</c:if>	
<!-- 로그인 안 했을 때 -->

<div class="row" style="float: left; margin-top: 50px;" id="moving-element">
<div class="col-3" style="width: 250px">
<div class="list-group">
  <a href="./Support.sup" class="list-group-item list-group-item-action">고객센터</a>
  <a href="./SupportNoticeList.sup" class="list-group-item list-group-item-action">공지사항</a>
  <a href="./SupportEventList.sup" class="list-group-item list-group-item-action active" aria-current="true">이벤트</a>
  <a href="./SupportFaqList.sup" class="list-group-item list-group-item-action">
    자주 묻는 질문
  </a>
  <a class="list-group-item list-group-item-action disabled" aria-disabled="true"><b>1:1 문의 : 1234-5678</b></a>
</div>	
</div>
</div>

<section class="p-3 mb-2 bg-light text-dark">
	<br>
	<h1>Event Now</h1>
	<br>
	<div class="row justify-content-center">
    	<label for="title" class="col-sm-2 col-form-label"><span>제목</span></label>
    		<div class="col-sm-10">
      		<input type="text" class="form-control" name="title" id="title" value="${dto.title }" readonly>
    	</div>
  	</div>
  	<div class="row justify-content-center">
    	<label for="title" class="col-sm-2 col-form-label"><span>등록일</span></label>
    		<div class="col-sm-10">
      		<input type="text" class="form-control" name="date" id="date" value="${dto.date }" readonly>
    	</div>
  	</div>
  	<div class="form-floating">
  		<textarea class="form-control" id="textarea" name="content" readonly>${dto.content }</textarea>
  		<label for="textarea">이벤트 본문</label>
	</div>
	<div class="div1">
		<button type="button" class="btn btn-primary btn-sm" onclick=" location.href='./SupportEventList.sup?pageNum=${requestScope.pageNum }'; ">목록으로</button>
	<c:if test="${!empty sessionScope.id && sessionScope.id.equals('admin') }">
		<button type="button" class="btn btn-secondary btn-sm" onclick=" location.href='./SupportEventUpdate.sup?table_id=${dto.table_id }&pageNum=${requestScope.pageNum }'; ">이벤트 수정</button>
		<button type="button" class="btn btn-danger btn-sm" onclick=" location.href='./SupportEventDelete.sup?table_id=${dto.table_id }&pageNum=${requestScope.pageNum }'; ">이벤트 삭제</button>
	</c:if>
	</div>
	<br>
	<br>	
	<h1>fooding.</h1>
</section>
<br>

<jsp:include page="../inc/bottom.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>