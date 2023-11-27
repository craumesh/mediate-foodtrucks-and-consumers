<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>주문내역</title>
<!-- 파비콘 적용 -->
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<!-- Core theme CSS (includes Bootstrap)-->
<link rel="stylesheet" href="./fooding/css/team2.css">
<link rel="stylesheet" href="./fooding/css/orderDetail.css">
<!-- 테마 기본 설정 끝 -->
<!-- iamport.payment.js -->
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<!-- 포트원 결제연동 소스 -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
	<!-- css 복사 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href='https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css'>

<!-- 제이쿼리 사이드바 이동 -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script>
  $(document).ready(function() {
	  // 메뉴바 스크롤
    $(window).scroll(function() {
      var scrollTop = $(window).scrollTop();
      $("#moving-element").css("top", scrollTop + "px");
    });
    
	  // 주문번호 찾아가기
    var urlParams = new URLSearchParams(window.location.search);
    var purchaseid = urlParams.get('purchaseid');
    if (purchaseid) {
        var target = document.getElementById('order-' + purchaseid);
        if (target) {
            target.scrollIntoView({behavior: "smooth"});
        }
    }
  });
</script>
<!-- 제이쿼리 사이드바 이동 -->
<!-- css 복사 -->
</head>
<body>
	<!-- 헤더 들어가는곳 -->
	<jsp:include page="../khr/inc/top.jsp"></jsp:include>
	<!-- 헤더 들어가는곳 -->
	<!-- 세션 제어 (로그인 안 되어 있으면 메인 이동) -->
	<c:if test="${empty sessionScope.id }">
		<c:redirect url="./FtMainAction.man" />
		<!-- main 페이지 주소 -->
	</c:if>
<!-- 세션 제어 (로그인 안 되어 있으면 메인 이동) -->

<!-- 사이드바 -->
<div class="row" style="float: left; margin-top: 50px;" id="moving-element">
<div class="col-3" style="width: 250px">
<div class="list-group">
  <a href="./Profile.pro" class="list-group-item list-group-item-action">마이페이지</a>
  <a href="./ProfileMemberInfo.pro" class="list-group-item list-group-item-action">회원정보</a>
  <a href="./OrderDetails.pay" class="list-group-item list-group-item-action active" aria-current="true">
    주문내역
  </a>
  <a href="./ProfileReviewList.pro" class="list-group-item list-group-item-action">리뷰</a>
  <a href="./Support.sup" class="list-group-item list-group-item-action">고객센터</a>
</div>	
</div>
</div>
<!-- 사이드바 -->

		<section class="p-3 mb-2 bg-light text-dark"> 
		<h1 class="h1">Order List</h1>
		<c:choose>
		<c:when test="${empty purchaseList}">
		<br><br>
			<h2>주문내역이 없습니다</h2>
        </c:when>
                <c:otherwise>
                    
		<c:forEach var="item" items="${purchaseList}" varStatus="loop" >

			<!-- 주문내역 반복출력 -->
			<div id="simpleCheck" class="row g-3">
				<div id="sec ${item.detail_id}">
			<input type="hidden" value="${item.detail_id}" name="detail_id">
			<c:choose>
			<c:when test="${loop.first || !item.purchaseid.equals(purchaseList[loop.index - 1].purchaseid)}">
				<div class="pid ${item.detail_id}" id="order-${item.purchaseid}">주문번호 : ${item.purchaseid}</div> 
			</c:when>
			<c:otherwise><!--쥬문번호가 같을 때 출력 안함 --></c:otherwise>
			</c:choose>
			
			
				<img src="./upload/product/${item.image}" id="productImg">
				<div class="pidz ${item.detail_id}">상품명 : ${item.name }</div>
				<span class="pidz ${item.detail_id}">주문일 : ${item.orderdate}</span>
				<div class="pidz ${item.detail_id}">개수 : ${item.quantity }개</div>

<!-- Button trigger modal -->
<span class="buttons">
<button type="button" class="btn btn-primary btn-sm  ${item.detail_id}" data-bs-toggle="modal" data-bs-target="#exampleModal${item.detail_id}">
  상세보기
</button>
<!-- Button trigger modal -->
<c:choose>
	<c:when test="${item.nowday == -1}">
<button type="button" class="btn btn-primary btn-sm ${item.detail_id}" onclick="location.href='./ProfileReviewWrite.pro?purchaseid=${item.purchaseid}'">
 	리뷰쓰기
</button>
	</c:when>
	<c:otherwise>
<button type="button" class="btn btn-outline-danger btn-sm ${item.detail_id}" data-bs-toggle="modal" data-bs-target="#exampleModal2${item.detail_id}"
   onclick="setupRefund('${item.purchaseid}',${item.quantity},${item.price},${item.detail_id})">
 	결제취소
</button>
	</c:otherwise>
</c:choose>
</span>
			</div>
			<!-- 상세보기 -->
<!-- Modal -->
<div class="modal fade" id="exampleModal${item.detail_id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="exampleModalLabel">Order Details</h4>
					<div class="fs-5 text">주문번호 : ${item.purchaseid}</div>
      </div>
      <div class="modal-body">
					<img src="./upload/product/${item.image}" id="productImgDetail">
					<ul>
					<li>상품명 : ${item.name }</li>
					<li>주문일 : ${item.orderdate}</li>
					<li>가격 :
					<span class="price">${item.price }</span> 원
					</li>
					<li>개수 : ${item.quantity }개</li>
					<li>결제 금액 : 
					<span class="total-price">${item.price * item.quantity }</span>원
					</li>
					<li>픽업 날짜 : ${item.date}</li>
					<li>픽업 시간 : ${item.starttime} ~ ${item.endtime }</li>
					<li>픽업 주소 : ${item.fulladdr} ${item.address}</li>
					</ul>
      </div>
        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Close</button>
		</div>
      </div>
    </div>
  </div>
		</c:forEach>
		<br>
			<!-- 부트스트랩 페이지 블럭 -->
		<nav aria-label="Page navigation example">
  				<ul class="pagination justify-content-center">
  				<c:if test="${startPage > requestScope.pageBlock }">
  				  <li class="page-item"><a class="page-link" href="./OrderDetails.pay?pageNum=${startPage - pageBlock }#list">이전</a></li>
				</c:if>
    			<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
    			     		<c:choose>
                <c:when test="${i == pageNum}">
                    <li class="page-item active"> <!-- 현재 페이지 표시 -->
                        <a class="page-link" href="./OrderDetails.pay?pageNum=${i }#list">${i }</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link" href="./OrderDetails.pay?pageNum=${i }#list">${i }</a>
                    </li>
                </c:otherwise>
            </c:choose>
				</c:forEach>
				<c:if test="${endPage < pageCount }">
    			  <li class="page-item"><a class="page-link" href="./OrderDetails.pay?pageNum=${startPage + pageBlock }#list">다음</a></li>
				</c:if>		
  				</ul>
			</nav>
			<!-- 부트스트랩 페이지 블럭 -->
			
			
                </c:otherwise>
		</c:choose>
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
	<!-- 결제내역 보이게 하기 -->
	<script>
	$(document).ready(function() { // 금액에 , 찍기
	    $('.price, .total-price').each(function() {
	        var price = parseInt($(this).text());
	        $(this).text(price.toLocaleString('ko-KR'));
	    });
	
	});
</script>
	<br>
	<!-- 푸터들어가는곳 CSS 위치 조절 필요-->
	<jsp:include page="../inc/bottom.jsp"></jsp:include>
<script src="./pjs/refund.js"></script>
</body>
</html>
