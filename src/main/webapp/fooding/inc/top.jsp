<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>fooding.</title>
    <link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Google fonts-->
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link href="https://fonts.googleapis.com/css2?family=Newsreader:ital,wght@0,600;1,600&amp;display=swap" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css2?family=Mulish:ital,wght@0,300;0,500;0,600;0,700;1,300;1,500;1,600;1,700&amp;display=swap" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css2?family=Kanit:ital,wght@0,400;1,400&amp;display=swap" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="./fooding/css/styles.css" rel="stylesheet" />

    <!-- 구글폰트 (변경가능)-->
 <link rel="preconnect" href="https://fonts.googleapis.com">
 <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
 <link href="https://fonts.googleapis.com/css2?family=Bagel+Fat+One&family=Black+Han+Sans&family=Lato:wght@700&family=Noto+Sans+KR&family=Playpen+Sans&display=swap" rel="stylesheet">

    <!-- 테마 기본 설정 끝 -->

     <!-- css -->
     <link rel="stylesheet" href="./fooding/css/team2.css">
</head>
<body>
   <!-- Navigation-->
   <nav class="navbar navbar-expand-lg navbar-light fixed-top shadow-sm fooding-nav" id="mainNav">
    <div class="container px-5">
        <a class="navbar-brand fw-bold" href="./FtMainAction.man">fooding.</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="bi-list"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto me-4 my-3 my-lg-0">
               	<c:if test="${!empty sessionScope.id && sessionScope.id.equals('admin') }">
                <li class="nav-item"><a class="nav-link me-lg-3" href="./Admin.adm"><span style="color: red;"><b>관리자 모드</b></span></a></li>
                </c:if>
                <li class="nav-item"><a class="nav-link me-lg-3" href="./FtkAction.ftk">주문하기</a></li>
                <li class="nav-item"><a class="nav-link me-lg-3" href="./Profile.pro">마이페이지</a></li>
                <li class="nav-item"><a class="nav-link me-lg-3" href="./Support.sup">고객센터</a></li>
                <li class="nav-item"><a class="nav-link me-lg-3" href="./CartList.car">장바구니</a></li>
            </ul>
            <button class="btn btn-primary rounded-pill px-3 mb-2 mb-lg-0" data-bs-toggle="modal" data-bs-target="#feedbackModal" onclick="location.href='./ProfileLogout.pro';">
                <span class="d-flex align-items-center">
                    <i class="bi-chat-text-fill me-2"></i>
                    <span class="small">로그아웃</span>
                </span>
            </button>
        </div>
    </div>
</nav>

   <!-- Bootstrap core JS-->
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
   <!-- Core theme JS-->
   <script src="./fooding/js/scripts.js"></script>
   <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
   <!-- * *                               SB Forms JS                               * *-->
   <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
   <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
   <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>