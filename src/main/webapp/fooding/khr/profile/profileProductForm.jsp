<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lora:wght@500&display=swap" rel="stylesheet">

<link href="./fooding/khr/css/adminProductForm.css" rel="stylesheet">
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
</head>
<body style="background-color: #28282B;">
<!-- 세션 제어 (로그인 안 되어 있으면 메인 이동) -->
	<c:if test="${empty sessionScope.id }">
		<c:redirect url="./FtMainAction.man" />
		<!-- main 페이지 주소 -->
	</c:if>
<!-- 세션 제어 (로그인 안 되어 있으면 메인 이동) -->

<%-- <jsp:include page="../inc/top.jsp" /> --%>

<!-- 사이드바 -->	
<div class="row" style="float: left;" id="moving-element">
<div class="col-3" style="width: 250px">
<div class="list-group">
  <a href="./Profile.pro" class="list-group-item list-group-item-action">마이페이지</a>
  <a href="./ProfileMemberInfo.pro" class="list-group-item list-group-item-action active" aria-current="true">
    회원정보
  </a>
  <a href="./OrderDetails.pay" class="list-group-item list-group-item-action">주문내역</a>
  <a href="./ProfileReviewList.pro" class="list-group-item list-group-item-action">리뷰</a>
  <a href="./Support.sup" class="list-group-item list-group-item-action">고객센터</a>
</div>	
</div>
</div>
<!-- 사이드바 -->

<section class="p-3 mb-2 bg-light text-dark">
<br>
<h1>New Product</h1>
<br>
	<form action="./ProfileProductAddPro.pro" method="post" enctype="multipart/form-data">
  	<div class="row justify-content-center">
    	<label for="nickname" class="col-sm-2 col-form-label"><span>상품명</span></label>
    		<div class="col-sm-9">
      		<input type="text" class="form-control" name="product_name" id="product_name" placeholder="상품명을 입력하세요" required>
    	</div>
  	</div>
  	  <div class="row justify-content-center">
    	<label for="nickname" class="col-sm-2 col-form-label"><span>가격</span></label>
    		<div class="col-sm-5">
      		<input type="text" class="form-control" name="price" id="price" placeholder="가격을 입력하세요 (예시 : 50000)" required onblur="checkInput();">
    	</div>
  	</div>
	<div class="row justify-content-center">
    	<label for="score" class="col-sm-2 col-form-label"><span>대분류</span></label>
    	 <div class="col-sm-3">
			<select class="form-select mb-4" name="largeclass" id="largeclass" onchange="updateSubcategories();" required>
				<option value="">대분류 선택</option>
				<option value="스낵">스낵</option>
				<option value="음료">음료</option>
			</select>
		</div>
    	<label for="score" class="col-sm-2 col-form-label"><span>소분류</span></label>
    	 <div class="col-sm-3">
			<select class="form-select mb-4" name="smallclass" id="smallclass" required>
				<option value="">소분류 선택</option>
			</select>
		</div>
	</div>
  	<div class="form-floating">
  		<textarea class="form-control" placeholder="상품 정보를 입력해주세요" id="textarea" name="info" required></textarea>
  		<label for="textarea">상품 정보</label>
	</div>
		<div class="row justify-content-center">
    	<label for="score" class="col-sm-2 col-form-label"><span>판매 푸드트럭</span></label>
    	 <div class="col-sm-3">
			<select class="form-select mb-4" name="foodtruck_id">
				<c:forEach var="dto" items="${requestScope.list }">
				<option value="${dto.foodtruck_id }">${dto.foodtruck_name }</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="row justify-content-center">
 		<label for="image" class="col-sm-3 col-form-label"><span>이미지 업로드</span></label>
 			<div class="col-sm-8">
  			<input class="form-control" type="file" id="image" name="image" accept="image/*" required>
  		</div>
	</div>
	<div class="col-auto mx-auto">
    	<button type="submit" class="btn btn-primary btn-sm">등록하기</button>
    	<button type="button" class="btn btn-secondary btn-sm" onclick="history.back();">이전 페이지로</button>
    	<br>
    	<br>
    	<h1>fooding.</h1>
  	</div>
  	</form>
</section>
<br>

<script>
function updateSubcategories() {
    var mainClassSelect = document.getElementById("largeclass");
    var subClassSelect = document.getElementById("smallclass");
    var selectedOption = mainClassSelect.options[mainClassSelect.selectedIndex].value;
    
    // 소분류 옵션을 초기화
    subClassSelect.innerHTML = "";

    // 선택된 대분류에 따라 소분류 옵션을 추가
    if (selectedOption === "스낵") {
        var snackOptions = [
            { text: "우동", value: "우동" },
            { text: "떡볶이", value: "떡볶이" },
            { text: "핫도그", value: "핫도그" },
            { text: "라면", value: "라면" },
            { text: "튀김", value: "튀김" },
            { text: "국수", value: "국수" },
            { text: "핫바", value: "핫바" },
            { text: "한식", value: "한식" },
            { text: "중식", value: "중식" },
            { text: "일식", value: "일식" },
            { text: "양식", value: "양식" },
            { text: "간식", value: "간식" }
        ];

        for (var i = 0; i < snackOptions.length; i++) {
            var option = document.createElement("option");
            option.text = snackOptions[i].text;
            option.value = snackOptions[i].value;
            subClassSelect.add(option);
        }
    } else if (selectedOption === "음료") {
        var drinkOptions = [
            { text: "커피", value: "커피" },
            { text: "우유", value: "우유" },
            { text: "탄산음료", value: "탄산음료" },
            { text: "에이드", value: "에이드" },
            { text: "생과일주스", value: "생과일주스" },
            { text: "제로음료", value: "제로음료" }
        ];

        for (var i = 0; i < drinkOptions.length; i++) {
            var option = document.createElement("option");
            option.text = drinkOptions[i].text;
            option.value = drinkOptions[i].value;
            subClassSelect.add(option);
        }
    }
}


</script>
<script>
        function checkInput() {
            var input = document.getElementById('price').value;

            // 입력값이 숫자가 아닌 경우에만 경고 창을 띄웁니다.
            if (isNaN(input)) {
                alert('숫자가 아닌 입력입니다.');
            }
        }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<%-- <jsp:include page="../inc/bottom.jsp" /> --%>
</body>
</html>