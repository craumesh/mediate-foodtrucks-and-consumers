<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>푸딩</title>

<!-- 파비콘 적용 -->
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">

<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
	
<!-- Google fonts-->
<link rel="preconnect" href="https://fonts.gstatic.com" />
<link
	href="https://fonts.googleapis.com/css2?family=Newsreader:ital,wght@0,600;1,600&amp;display=swap"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css2?family=Mulish:ital,wght@0,300;0,500;0,600;0,700;1,300;1,500;1,600;1,700&amp;display=swap"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css2?family=Kanit:ital,wght@0,400;1,400&amp;display=swap"
	rel="stylesheet" />
	
<!-- Core theme CSS (includes Bootstrap)-->
<link href="./fooding/css/styles.css" rel="stylesheet" />

<link href="./fooding/css/fooding_main.css" rel="stylesheet" />
<script src="https://kit.fontawesome.com/38bf29a217.js"
	crossorigin="anonymous"></script>
	
<style>
/* 하단 캐러셀 버튼 위치 */
.btnf {
	margin-left: 175px;
	margin-right: 175px;
}
</style>

</head>

<body id="page-top">

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
			
	<!-- 상단 슬라이드 -->
	<div id="carouselExampleRide" class="carousel slide"
		data-bs-ride="true">
		<div class="carousel-inner">
		
			<div class="carousel-item active" id="box1">		
				  	<div class="box">
	                <div>
	                	<p class="box-title">신선한 재료</p>
	                </div>
	                	<img src="./fooding/images/fooding_log.png" style="height: 260px;" />
	            	</div>
			</div>
			
			<div class="carousel-item" id="box2">
					<div class="box">
	                <div>
	                	<p class="box-title">다양한 경로</p>
	                </div>
	                	<img src="./fooding/images/fooding_log.png" style="height: 260px;" />
	            	</div>			
			</div>
			
			<div class="carousel-item" id="box3">
					<div class="box">
						<img src="./fooding/images/배너_푸딩윈터 겨울 신상 메뉴 .gif" style="width: 100%;"/>
					</div>
			</div>
			
			<div class="carousel-item" id="box4">
					<div class="box">
						<img src="./fooding/images/민트최종.gif" style="width: 50%" />
					</div>
			</div>
			
			<div class="carousel-item" id="box5">
					<div class="box">
						<img src="./fooding/images/배너_푸딩으로 인싸되기.gif" style="width: 100%;"/>
					</div>
			</div>
		</div>

		<ol class="carousel-indicators">
			<li data-bs-target="#carouselExampleRide" data-bs-slide-to="0"
				class="active"></li>
			<li data-bs-target="#carouselExampleRide" data-bs-slide-to="1"></li>
			<li data-bs-target="#carouselExampleRide" data-bs-slide-to="2"></li>
			<li data-bs-target="#carouselExampleRide" data-bs-slide-to="3"></li>
			<li data-bs-target="#carouselExampleRide" data-bs-slide-to="4"></li>
		</ol>

		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleRide" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExampleRide" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>
	<!-- 상단 슬라이드 -->

	<!-- 메인 페이지 중간 메뉴 -->
	<section id="features">
		<div class="container px-5">
			<div class="row gx-5 align-items-center">
				<div class="col-lg-8 order-lg-1 mb-5 mb-lg-0">
					<div class="container-fluid px-5">
						<div class="row gx-5">
							<div class="col-md-6 mb-5 hidden-link">

								<a href="./FtkAction.ftk">
									<div class="text-center">
										<i class="fa-solid fa-truck icon-feature text-gradient d-block mb-3"></i>
										<h3 class="font-alt">푸드트럭</h3>
										<p class="text-muted mb-0">다양한 푸드트럭들을 만나보세요!</p>
									</div>
								</a>
							</div>
							<div class="col-md-6 mb-5 hidden-link">

								<a href="./ServiceInfo.ser">
									<div class="text-center">
										<i class="fa-solid fa-info icon-feature text-gradient d-block mb-3"></i>
										<h3 class="font-alt">서비스정보</h3>
										<p class="text-muted mb-0">푸딩에 더 깊게 빠져 보세요..!</p>
									</div>
								</a>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 mb-5 mb-md-0 hidden-link">

								<a href="./Support.sup">
									<div class="text-center">
										<i class="fa-solid fa-phone icon-feature text-gradient d-block mb-3"></i>
										<h3 class="font-alt">고객센터</h3>
										<p class="text-muted mb-0">궁금한 내용들을 찾아보세요!</p>
									</div>
								</a>
							</div>
							<div class="col-md-6 hidden-link">

								<a href="./SupportEventList.sup">
									<div class="text-center">
										<i class="fa-solid fa-gift icon-feature text-gradient d-block mb-3"></i>
										<h3 class="font-alt">이벤트정보</h3>
										<p class="text-muted mb-0">흥미로운 이벤트들을 확인하세요!</p>
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4 order-lg-0">
					
					<!-- 푸딩 로고 -->
					<div class="features-device-mockup">
						<a href="./FtMainAction.man"> <img
							src="./fooding/images/fooding_log.png">
						</a>
					</div>
				</div>
			</div>
		</div>
</section>
<!-- 메인 페이지 중간 메뉴 -->

<!-- 푸딩 새 소식 -->
<section class="bg-light">
    <div class="container px-5 hidden-link">
        <h2 class="news">푸딩새소식</h2>

            <div class="news-container">
            
                <div class="card" style="width: 18rem;" id="card1">
                    <img src="./fooding/images/겨울신메뉴이미지.jpg" class="news-img" style="height: 285px; object-fit: cover;"/>
        			<a href="./SupportEventContent.sup?table_id=4">
	                    <div class="card-body">
	                        <p class="card-text font-alt">
	                            <span class="fooding-news">푸딩, 겨울 신메뉴 등록<br></span> "겨울메뉴도
	                            푸딩에서"
	                        </p>
	                    </div>
            		</a>
                </div>
                
                <div class="card" style="width: 18rem;">
                    <img src="./fooding/images/리뷰이미지.jpg" class="news-img" />
            		<a href="./ProfileReviewList.pro">
	                    <div class="card-body">
	                        <p class="card-text font-alt">
	                            <span class="fooding-news">푸딩, '리뷰기능' 제공<br></span> "상품리뷰확인
	                            기능 도입"
	                        </p>
	                    </div>
            		</a>
                </div>
                
                <div class="card" style="width: 18rem;">
                    <img src="./fooding/images/이벤트이미지.jpg" class="news-img" style="height: 263px; object-fit: cover;"/>
            		<a href="./SupportEventContent.sup?table_id=6">
	                    <div class="card-body">
	                        <p class="card-text font-alt">
	                            <span class="fooding-news">이벤트<br></span> "추첨을 통해 푸딩 이용
	                            고객님에게 쿠폰 제공"
	                        </p>
	                    </div>
            		</a>
                </div>
        	</div>
    </div>
</section>
<!-- 푸딩 새 소식 -->

<!-- 모달창 -->
<div class="modal">
	<span class="close">&times;</span>
	<img class="modal_content" id="img01">
</div>

<div class="modal">
	<span class="close">&times;</span>
	<img class="modal_content" id="img02">
</div>

<div class="modal">
	<span class="close">&times;</span>
	<img class="modal_content" id="img03">
</div>

	<!-- 푸드트럭 종류소개 (캐러셀이용) -->
	<h1 class="foodinfo">food truck</h1>
	<section>
		<div id="carouselExampleIndicators"
			class="carousel slide foodcarousel-container" data-bs-ride="carousel">
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="0" class="active" aria-current="true"
					aria-label="Slide 1"></button>
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="1" aria-label="Slide 2"></button>
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="2" aria-label="Slide 3"></button>
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="3" aria-label="Slide 4"></button>
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="4" aria-label="Slide 5"></button>
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="5" aria-label="Slide 0"></button>
			</div>

			<div class="carousel-inner">
				<c:forEach items="${ftMainInfo}" var="ftInfo" varStatus="loop">
					<c:if test="${loop.index eq 0}">
						<div class="carousel-item active">
							<img src="./upload/foodtruck/${ftInfo.FImage}"
							   alt="..." style="width: 512px; height: 512px;"
							   onclick="location.href='./FtInfoMainAction.fti?foodtruck_id=${ftInfo.foodtruck_id}';"/>
							
						</div>
					</c:if>
				</c:forEach>
				<c:forEach items="${ftMainInfo}" var="ftInfo" varStatus="loop">
					<c:if test="${loop.index eq 1}">
						<div class="carousel-item">
							<img src="./upload/foodtruck/${ftInfo.FImage}"
							   alt="..." style="width: 512px; height: 512px;"
							   onclick="location.href='./FtInfoMainAction.fti?foodtruck_id=${ftInfo.foodtruck_id}';"/>
							
						</div>
					</c:if>
				</c:forEach>
				<c:forEach items="${ftMainInfo}" var="ftInfo" varStatus="loop">
					<c:if test="${loop.index eq 2}">
						<div class="carousel-item">
							<img src="./upload/foodtruck/${ftInfo.FImage}"
							   alt="..." style="width: 512px; height: 512px;"
							   onclick="location.href='./FtInfoMainAction.fti?foodtruck_id=${ftInfo.foodtruck_id}';"/>
							
						</div>
					</c:if>
				</c:forEach>
				<c:forEach items="${ftMainInfo}" var="ftInfo" varStatus="loop">
					<c:if test="${loop.index eq 3}">
						<div class="carousel-item">
							<img src="./upload/foodtruck/${ftInfo.FImage}"
							   alt="..." style="width: 512px; height: 512px;"
							   onclick="location.href='./FtInfoMainAction.fti?foodtruck_id=${ftInfo.foodtruck_id}';"/>
							
						</div>
					</c:if>
				</c:forEach>
				<c:forEach items="${ftMainInfo}" var="ftInfo" varStatus="loop">
					<c:if test="${loop.index eq 4}">
						<div class="carousel-item">
							<img src="./upload/foodtruck/${ftInfo.FImage}"
							   alt="..." style="width: 512px; height: 512px;"
							   onclick="location.href='./FtInfoMainAction.fti?foodtruck_id=${ftInfo.foodtruck_id}';"/>
							
						</div>
					</c:if>
				</c:forEach>
				<c:forEach items="${ftMainInfo}" var="ftInfo" varStatus="loop">
					<c:if test="${loop.index eq 5}">
						<div class="carousel-item">
							<img src="./upload/foodtruck/${ftInfo.FImage}"
							   alt="..." style="width: 512px; height: 512px;"
							   onclick="location.href='./FtInfoMainAction.fti?foodtruck_id=${ftInfo.foodtruck_id}';"/>
							
						</div>
					</c:if>
				</c:forEach>
			</div>
			
			<button class="carousel-control-prev btnf" type="button"
				data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next btnf" type="button"
				data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
			
		</div>
	</section>
	<!-- 푸드트럭 종류소개 (캐러셀이용) -->

	<!-- 하단 로고 -->
	<section class="bg-gradient-primary-to-secondary" id="download">
		<div class="container px-5 b-container">
			<h2 class="text-center text-white font-alt mb-4">Let's fooding!</h2>
			<a href="./FtMainAction.man"><img
				src="./fooding/images/fooding_log.png" class="fooding-img"></a>

		</div>
	</section>
	<!-- 하단 로고 -->

	<!-- Footer -->
	<footer>
		<div id="company-info">
			<%@ include file="../inc/bottom.jsp"%>
		</div>
	</footer>

	<!-- Bootstrap core JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS -->
	<script src="js/scripts.js"></script>
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	
	<!-- 모달 창  -->
<script>
    // 모달 창, 이미지, 닫기 버튼에 대한 요소 선택.
    const modal = document.querySelectorAll(".modal");
    const img = document.querySelectorAll(".news-img");
    const modal_img = document.querySelectorAll(".modal_content");
    const span = document.querySelectorAll(".close");

    // 이미지 클릭 시 모달 창을 열고 해당 이미지 모달에 표시.
    for(let i=0; i<modal.length;i++){
        img[i].addEventListener('click', ()=>{
            modalDisplay(i,"block");
            modal_img[i].src = img[i].src;
        });
    }

    // 닫기 버튼 클릭 시 모달 창 닫기.
    for(let i=0; i<modal.length;i++){
        span[i].addEventListener('click', ()=>{
            modalDisplay(i,"none");
        });
    }

    // 모달 외부 클릭 시 모달 창 닫기.
    for(let i=0; i<modal.length;i++){
        modal[i].addEventListener('click', ()=>{
            modalDisplay(i,"none");
        });
    }

    // 모달 창 열거나 닫는 함수 정의.
    function modalDisplay(i,text){
        modal[i].style.display = text;
    }
</script>
	<!-- 모달 창  -->

	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
	
</body>
</html>