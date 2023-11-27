<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<link href="./fooding/khr/css/noticeList.css" rel="stylesheet">

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
	<c:if test="${!empty sessionScope.id }">
	<jsp:include page="../inc/top.jsp" />
	</c:if>
	
	<c:if test="${empty sessionScope.id }">
	<jsp:include page="../inc/top_logout.jsp" />
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
<div style="height: auto; min-height: 100%; padding-bottom: 41px;">
	<section class="p-3 mb-2 bg-light text-dark">
	<br>
	<h1>Notice</h1><br>
	<table border="1" class="table table-hover">
	<tr>
		<th class="bno">번호</th>
		<th class="subject">제목</th>
	</tr>
	<c:forEach var="dto" items="${requestScope.list }">
	<tr>
		<td>${dto.bno }</td>
		<td>
		<a href="./SupportNoticeContent.sup?table_id=${dto.table_id }&pageNum=${requestScope.pageNum }">${dto.title }</a>
		</td>
	</tr>
	</c:forEach>
	</table>
	<c:if test="${!empty sessionScope.id && sessionScope.id.equals('admin') }">
	<button type="button" class="btn btn-secondary btn-sm" onclick=" location.href='./SupportNoticeForm.sup'; " style="float: right;">공지사항 작성</button>
	</c:if>
	
			<nav aria-label="Page navigation example">
  				<ul class="pagination justify-content-center">
  				<c:if test="${requestScope.startPage > requestScope.pageBlock }">
  				  <li class="page-item"><a class="page-link" href="./SupportNoticeList.sup?pageNum=${requestScope.startPage - requestScope.pageBlock }">이전</a></li>
				</c:if>
    			<c:forEach var="i" begin="${requestScope.startPage }" end="${requestScope.endPage }" step="1">
    			  <li class="page-item"><a class="page-link" href="./SupportNoticeList.sup?pageNum=${i }">${i }</a></li>
				</c:forEach>
				<c:if test="${requestScope.endPage < requestScope.pageCount }">
    			  <li class="page-item"><a class="page-link" href="./SupportNoticeList.sup?pageNum=${requestScope.startPage + requestScope.pageBlock }">다음</a></li>
				</c:if>		
  				</ul>
			</nav>
		<br>
		<h1>fooding.</h1>
		<br>	
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
</div>	
<jsp:include page="../inc/bottom.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>	
</body>
</html>