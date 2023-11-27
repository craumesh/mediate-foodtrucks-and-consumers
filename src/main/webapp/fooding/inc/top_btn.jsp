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
<title>fooding</title>
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
/* top 버튼 */
   #topBtnContainer {
       position: fixed;
       bottom: 20px;
       right: 20px;
       display: none;
   }

   #topBtn {
       background-color: #5F87E1;
       color: #fff;
       border: none;
       border-radius: 5px;
       padding: 8px 18px;
       cursor: pointer;
       font-size: 16px;
   }
</style>

</head>

<body>

<div id="topBtnContainer">
    <button id="topBtn" title="Go to top">Top</button>
</div>

<script>
    // DOMContentLoaded 이벤트가 발생했을 때 실행
    document.addEventListener('DOMContentLoaded', function () {
        // top 버튼과 버튼을 감싸는 컨테이너 요소의 참조를 가져옴
        var topBtnContainer = document.getElementById('topBtnContainer');
        var topBtn = document.getElementById('topBtn');

        // 스크롤 중인지 여부를 나타내는 플래그 변수
        var isScrolling = false;

        // 윈도우의 스크롤 이벤트에 대한 리스너 추가
        window.addEventListener('scroll', function () {
            // 스크롤 중이 아닌 경우에만 실행
            if (!isScrolling) {
                // 스크롤 중임을 표시
                isScrolling = true;

                // 일정 시간 후에 스크롤 이벤트를 처리하는 부분
                setTimeout(function () {
                    // 현재 스크롤 위치가 300px 이상인 경우
                    if (document.body.scrollTop > 300 || document.documentElement.scrollTop > 300) {
                        // top 버튼 컨테이너를 보이게 설정
                        topBtnContainer.style.display = 'block';
                    } else {
                        // 스크롤 위치가 300px 미만인 경우, top 버튼 컨테이너를 숨김
                        topBtnContainer.style.display = 'none';
                    }

                    // 스크롤 중임을 다시 false로 설정하여 다음 스크롤 이벤트를 기다림
                    isScrolling = false;
                }, 200); // 200ms 딜레이 추가
            }
        });

        // top 버튼 클릭 시 페이지를 상단으로 이동하는 이벤트 핸들러 추가
        topBtn.addEventListener('click', function () {
            document.body.scrollTop = 0;
            document.documentElement.scrollTop = 0;
        });
    });
</script>

<!-- top 버튼 -->
<div id="topBtnContainer">
    <button id="topBtn" title="Go to top">Top</button>
</div>
<!-- top 버튼 -->

</body>
</html>