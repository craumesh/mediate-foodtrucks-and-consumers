<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>fooding</title>
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
	
	<!-- 두번째 메뉴바 css -->
    <style>
	.second-navbar {
	    background-color: cornflowerblue;
	    border-bottom: 1px solid #e0e0e0;
	    padding: 12px 0;
	    width: 100%;
	    position: relative;
	    z-index: 1000;
	    transition: background-color 0.3s ease; /* 부드러운 배경색 전환을 위한 트랜지션 효과 */
	    display: flex;
	    justify-content: center;
	    box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
	}
	
	.second-navbar a {
	    text-decoration: none;
	    color: #333; /* 링크 색상 */
	    font-weight: bold;
	    transition: color 0.3s ease; /* 부드러운 색상 전환을 위한 트랜지션 효과 */
	    color: white;
	    margin: 0 26px; /* 단어 간의 간격을 조절 */
	}
	
	.second-navbar a:hover {
	    color: #ffc107; /* 호버 시 색상 변경 */
	}
    </style>
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
                <li class="nav-item"><a class="nav-link me-lg-3" href="./MemberJoin.mem">회원가입</a></li>
                <li class="nav-item"><a class="nav-link me-lg-3" href="./Support.sup">고객센터</a></li>
            </ul>
            <button class="btn btn-primary rounded-pill px-3 mb-2 mb-lg-0" data-bs-toggle="modal" data-bs-target="#feedbackModal" onclick="location.href='./MemberLogin.mem';">
                <span class="d-flex align-items-center">
                    <i class="bi-chat-text-fill me-2"></i>
                    <span class="small">로그인</span>
                </span>
            </button>
        </div>
    </div>
   </nav>
   

    <!-- Second Navbar -->
    <nav class="second-navbar">
        <a href="./FtkAction.ftk">푸드트럭</a>
        <a href="./ServiceInfo.ser">서비스 정보</a>
        <a href="./SupportEventList.sup">이벤트 정보</a>
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