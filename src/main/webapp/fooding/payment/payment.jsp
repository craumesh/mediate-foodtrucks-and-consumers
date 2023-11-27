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
<title> 결제 </title>
<!-- 파비콘 적용 -->
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<!-- 아이콘 -->
 <script src="https://kit.fontawesome.com/38bf29a217.js" crossorigin="anonymous"></script>
<!-- css -->
<link rel="stylesheet" href="./fooding/css/team2.css">
<link rel="stylesheet" href="./fooding/css/payment.css">
<!-- iamport.payment.js -->
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<!-- 포트원 결제연동 소스 -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
<meta charset="UTF-8">
<!-- help Ajaxtry -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>결제창</title>

</head>
<body>

	<!-- 헤더 들어가는곳 -->
	<!-- Navigation-->
   		 <jsp:include page="../inc/top.jsp" />
   <!-- Navigation-->
	<!-- 헤더 들어가는곳 -->
<!-- 본문들어가는곳(결제페이지) -->
<br><br>
		<section class="p-3 pt-4 mb-2 bg-light text-dark">
		<h1 class="orderPay mt-2">주문/결제</h1>
			<form action="./PaymentResult.pay" method="post" id="mypayment" class="mypayment">
				<!-- 주문번호 -->
				<input type="hidden" id="purchase_id" name="purchase_id" value="">
				<c:forEach var="dto" items="${purchaseList}" varStatus="loop">
				<div class="box">
					<c:choose><c:when
							test="${loop.first || !dto.foodtruckName.equals(purchaseList[loop.index - 1].foodtruckName)}">
							<h3 class="pickup name"><i class="bi bi-truck">&nbsp;&nbsp;</i>${dto.foodtruckName}</h3>
						</c:when>
						<c:otherwise><!--트럭명이 같을 때 출력 안함 --></c:otherwise>
					</c:choose>
				<!-- 이거 트럭고유번호를 입력해줘야함 -->
					<!-- 토큰값 -->
					<input type="hidden" id="token" name="token" value="">
					<!-- 장바구니 번호 -->
					<input type="hidden" name="cart_id" value="${dto.cart_id}">
					
					<div class="pickup">픽업 일자 : ${dto.date}</div>
					<span class="pickup"> 픽업 시간 : ${dto.stoptime } </span>
					<span class="pickup"> 픽업 위치: ${dto.address }</span>
					<hr>
		<div id="tjsdmf">
			<img src="./upload/product/${dto.image}" class="productIMG" onclick="location.href='./FtInfoMainAction.fti?foodtruck_id=${dto.foodtruck_id}'" title="'${dto.foodtruckName}' 상세페이지로 이동">
							
		<br>
			<div class="pickup add"> 상품명 : ${dto.name}</div>
			<div class="pickup add"> 수 량 : ${dto.quantity}</div>
			<div class="pickup add price"">${dto.price * dto.quantity} </div>
			
		</div>
				</div>
		<br>
				</c:forEach>
			</form>
		</section>
<!-- 결제방법 선택하기(2~3개) 아이콘 왜 안나오지-->
<section id="section" class="p-3 mb-2 bg-light text-dark">	
<div class="mypayment">
	<h2 id="choice">결제방법</h2>
    <div class="form-check">
      <input class="form-check-input" type="radio" name="pay"
        id="flexRadioDefault1" value="INIBillTst"/>
      <label class="form-check-label" for="flexRadioDefault1"> 카드결제 </label>
    </div>
    <div class="form-check">
      <input class="form-check-input" type="radio" name="pay"
        value="kakaopay" id="flexRadioDefault2" />
      <label class="form-check-label" for="flexRadioDefault2">
        카카오페이 <img class="picon kakao" src="./upload/pay/kakao.png">
      </label>
    </div>
    <div class="form-check">
      <input class="form-check-input" type="radio" name="pay"
        value="tosspay" id="flexRadioDefault3" />
      <label class="form-check-label" for="flexRadioDefault3">
       토스페이 <img class="picon toss" src="./upload/pay/Toss.png">
       </label>
    </div>
</div>			
	</section>
	
 		<section id="mon" class="mon p-3 mb-2 bg-light text-dark">
 		<br>
				<!-- 총 주문금액(=결제금액)  -->
				<c:forEach var="dto" items="${purchaseList}">
					<c:set var="total" value="${(dto.price * dto.quantity)+total}" />
					<c:set var="test" value="${dto.name}" />
				</c:forEach>
				<h1>
					결제 금액 : <span class="totalM ms-2"></span> 원
				</h1>
				<div class="fs-6"> ※ 픽업일자 당일에는 결제취소가 불가능합니다</div>
				<div class="fs-6"> ※ 결제취소는 주문내역에서 가능하며, 픽업일자 하루전까지 가능합니다.</div>
				<br>

				<!-- 결제하기 버튼 라디오버튼 값에 따라 결제수단 변경 -->
<span class="container text-center">
  <span class="row">
    <span class="d-grid gap-2 col-8">
<button type="button" class="btn btn-primary btn-lg m-0" onclick="findSubject()">결제하기</button>
    </span>
    <span class="d-grid gap-2 col">
<button type="button" class="btn btn-outline-primary btn-lg m-0" onclick="cartBack()">장바구니로</button>
    </span>
  </span>
 			</span>
</section>
<br><br>

	<script>
		let money = '<c:out value="${total}"/>'; // 결제금액
		$(document).ready(function() { // 금액에 , 찍기
		    $('.pickup.add.price').each(function() {
		        var price = parseInt($(this).text());
		        $(this).text(price.toLocaleString('ko-KR'));
		        $(this).prepend("가격 : ")
		    });
		});
		
		const DisMoney = money.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
		$('.totalM').append(DisMoney);
		        
		// 결제금액 콤마찍기
		
		let name = '${combinedName}';
		let email = '${member.email}'; // 구매자 이메일
		let userName = '${member.name}'; // 구매자 이름

	</script>


	<!-- 본문들어가는곳(결제페이지) 끝 -->

	<!-- 푸터들어가는곳 CSS 위치 조절 필요-->
	 <jsp:include page="../inc/bottom.jsp"></jsp:include>
	
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="./pjs/pay.js"></script>
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
	<!-- 푸터들어가는곳 -->
</body>
</html>