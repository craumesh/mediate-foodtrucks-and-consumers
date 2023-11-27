<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title> 서비스 정보 </title>
  <!-- 파비콘 적용 -->
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
 <!--  <link href="./fooding/css/service.css" rel="stylesheet"> -->
  <link href="./fooding/css/service.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  
  <style>
  body {
      position: relative; 
  }
  
  
  </style>
</head>


<body data-bs-spy="scroll" data-bs-target=".navbar" data-bs-offset="50">

   
<!-- 로그인 했을 때 -->
    <c:if test="${!empty sessionScope.id }">
    <jsp:include page="../inc/top_another.jsp" />
    </c:if>
<!-- 로그인 했을 때 -->

<!-- 로그인 X -->
 <c:if test="${empty sessionScope.id }">
    <jsp:include page="../inc/top_logout_another.jsp" />
    </c:if>
<!-- 로그인 X -->

 
<nav class="navbar navbar-expand-sm bg-dark navbar-dark ">
  <div class="container-fluid">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="#section1">fooding 1</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#section2">2</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#section3">3</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#section4">4</a>
      </li>
      
    </ul>
  </div>
</nav>


<div id="section1" class="container-fluid  service-box" >
<img src="./upload/foodtruck/푸딩배너.png" style="height: 400px; margin-bottom: 100px"> 
  <h1>푸딩소개</h1>
  <p>Food riding</p>
  <p>푸딩은 오늘도 열심히 달리고 있습니다.</p>
  <p>푸딩은 푸드트럭 운영에 도움이 되도록 다양한 음식을 제공하고 있습니다. <br>
     원하는 지점에서 음식을 받을 수 있도록 다양한 서비스를 제공하고 있습니다.
  </p>
</div>

<div id="section2" class="container-fluid service-box" >
  <h1>다양한 경로</h1>
  <p>어디서든 접근할 수 있도록 다양한 경로를 제공하고 있습니다.</p>
  <p>We offer a variety of routes that can be accessed from anywhere.</p>
</div>

<div id="section3" class="container-fluid  service-box">
  <h1>신선한 재료</h1>
  <p>믿을 수 있는 신선한 재료를 통해서 만든 음식을 제공하고 있습니다.</p>
  <p>We provide food made from reliable fresh ingredients.</p>
</div>

<div id="section4" class="container-fluid service-box" >
    <h1>다양한 음식</h1>
    <p>푸딩은 다양하고 특색있는 음식을 제공하기 때문에 다른 푸드트럭과 차별점을 둘 수 있습니다.</p>
    <p>Fooding can be differentiated from other food trucks because it offers a variety of distinctive foods.</p>
  </div>

<jsp:include page="../inc/bottom.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>