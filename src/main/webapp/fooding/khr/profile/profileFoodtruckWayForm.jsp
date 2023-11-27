<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>푸드트럭 경로등록</title>
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lora:wght@500&display=swap" rel="stylesheet">

<link href="./fooding/khr/css/profileFoodtruckWayForm.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href='https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css'>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
	
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
<body>
<jsp:include page="../inc/top.jsp" />

	<!-- 세션 제어 -->
	<c:if test="${empty sessionScope.id }">
		<c:redirect url="./FtMainAction.man" /> <!-- main 페이지 주소 -->
	</c:if>
	<!-- 세션 제어 -->
	
<!-- 사이드바 -->	
<div class="row" style="float: left; margin-top: 50px;" id="moving-element">
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
<h1>New Way</h1>
<br>
	<!-- <form action="./AdminFoodtruckWayAddAction.adm" method="post" enctype="application/json" id="wayForm">	 -->
		<div class="row justify-content-center">
	    <label for="foodtruck_id" class="col-sm-3 col-form-label"><span>판매 푸드트럭 번호</span></label>
	    	<div class="col-sm-3">
	      		<input type="text" class="form-control" name="foodtruck_id" id="foodtruck_id" value="${param.foodtruck_id }" readonly>
	    	</div>
	  	</div>	  
<!-- 		<div class="row justify-content-center"> -->
<!-- 	    	<label for="score" class="col-sm-2 col-form-label"><span>판매 푸드트럭</span></label> -->
<!-- 	    	 <div class="col-sm-3"> -->
<%-- 	    	 	<label for="score" class="col-sm-2 col-form-label"><span id="foodtruck_id">${param.foodtruck_id }</span></label> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	  	<div class="row justify-content-center"> -->
<!-- 	    	<label for="nickname" class="col-sm-2 col-form-label"><span>운행일</span></label> -->
<!-- 	    	<div class="col-sm-3"> -->
<!-- 	      		<input type="text" class="form-control" name="drive_date" id="drive_date" placeholder="운행일을 지정하세요" required> -->
<!-- 	    	</div> -->
<!-- 	  	</div>	  	 -->
<!-- 	    <input type="button" class="add-button" value="추가"> -->
	    <div class="content-container">
			<div class="row justify-content-center">
	    	<label for="drive_date" class="col-sm-2 col-form-label"><span>운행일</span></label>
	    	<div class="col-sm-3">
	      		<input type="text" class="form-control" name="drive_date" id="drive_date" placeholder="운행일자를 지정하세요" required>
	    	</div>
	    	</div>
	   	<div style="border: 2px solid black; width: 400px; margin: 0 auto 10px; border-radius: 5px;">	
	    <div class="row justify-content-center">
   	    	<label for="location" class="col-sm-3 col-form-label"><span>정차지</span></label>
	  	</div>
	  	<div class="row justify-content-center">
   	    	<div class="col-sm-6" id="save-container"></div>
   	    </div>
   	    </div>
	    	<div class="row justify-content-center">
			   	<label for="place_name" class="col-sm-2 col-form-label"><span>정차지명</span></label>
			   	<div class="col-sm-3">
			     	<input type="text" class="form-control" name="place_name" id="place_name" placeholder="정차지명을 입력하세요" required>
			   	</div>
		   	</div>  	
	   		<div class="row justify-content-center">
	   	    	<label for="largeclass" class="col-sm-2 col-form-label"><span>지역 (시 / 도)</span></label>
	   	    	 <div class="col-sm-3">
	   				<select class="form-select mb-4" name="largeclass" id="largeclass" required>
	   					<option value="">선택</option>
	   					<option value="서울">서울</option>
	   					<option value="경기">경기</option>
	   					<option value="인천">인천</option>
	   					<option value="대구">대구</option>
	   					<option value="부산">부산</option>
	   					<option value="광주">광주</option>
	   					<option value="대전">대전</option>
	   					<option value="울산">울산</option>
	   					<option value="세종">세종</option>
	   					<option value="강원">강원</option>
	   					<option value="경남">경남</option>
	   					<option value="경북">경북</option>
	   					<option value="전남">전남</option>
	   					<option value="전북">전북</option>
	   					<option value="충남">충남</option>
	   					<option value="충북">충북</option>
	   					<option value="제주">제주</option>
	   				</select>
	   			</div>
	   		</div>
	   	  	<div class="position-relative top-0 start-50 translate-middle-x" id="map" style="height:350px;"></div>
	   	  	<br>
	   	  	<div class="info">
	   	  	<div class="hAddr">
	   	        <span class="title">주소정보</span>
	   	        <span id="addr"></span>
	   	    </div>
	   	  	<div class="row justify-content-center">
	   	    	<label for="nickname" class="col-sm-2 col-form-label"><span>좌표</span></label>
	   	    	<div class="col-sm-7">
	   	    		<label for="score" class="col-form-label"><span id="clickLatlng"></span></label>
	   		    </div>
	   		</div>
	   		</div>
	   	  	<div class="row justify-content-center">
	   	    	<label for="start_time" class="col-sm-2 col-form-label"><span>시작시간</span></label>
	   	    	<div class="col-sm-3">
	   		      	<input type="time" class="form-control" name="start_time" id="start_time" required>
	   		    </div>
<!-- 	   		</div> -->
<!-- 	   		<div class="row justify-content-center"> -->
	   			<label for="end_time" class="col-sm-2 col-form-label"><span>종료시간</span></label>
	   		    <div class="col-sm-3">
	   		      	<input type="time" class="form-control" name="end_time" id="end_time" required>
	   	    	</div>
	   	<div class="col-sm-2">
	    	<button type="button" class="add-button btn btn-danger">경로 추가</button>
	    </div>
	   	    </div>
	    </div>
<!-- 	    <input type="button" class="add-button" value="추가"> -->
		<br>
		<div class="col-auto mx-auto">
	    	<button type="button" class="btn btn-primary btn-sm" onclick="submitConfirm();">저장하기</button>
	    	<button type="button" class="btn btn-secondary btn-sm" onclick="history.back();">이전 페이지로</button>
	    	<br>
	    	<br>
	    	<h1>fooding.</h1>
	  	</div>
  	<!-- </form> -->
</section>
<br>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f6593940ec361770de51ddc68ebfecfa&libraries=services"></script>
<script>
	var resultArr = JSON.parse('${wayMapJson}');
	var formDataMap = new Map();
	var formData = {
		place_name: '',
		latlng: '',
		address: '',
		start_time: '',
		end_time: ''
	};
	
	var idx;
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    	mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 4 // 지도의 확대 레벨
	}

	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();
	
	var marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
	    infowindow = new kakao.maps.InfoWindow({zindex:1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다
	    
	var userConfirmed = false;
	var datePicker = document.getElementById('drive_date');
	var flatpickrInstance = flatpickr(datePicker);
	    
	if(resultArr.length > 0){
		flatpickrInstance = flatpickr(datePicker, {
		    defaultDate: '${param.date}'
		});
		for (var i = 0; i < resultArr.length; i++) {
		    var innerMap = resultArr[i];	
		    var splitLatLng = innerMap.latlng.split(", ");
    	    var latLng = new kakao.maps.LatLng(splitLatLng[0], splitLatLng[1]);
		    formData = {
	       			place_name: innerMap.place_name,
	       			latlng: innerMap.latlng,
	       			address: innerMap.address,
	       			start_time: innerMap.start_time,
	       			end_time: innerMap.end_time
	        	};
		    idx = generateUniqueId();
        	formDataMap.set(idx, formData);
        	addWayLabel(formDataMap);
		}			    
	}	
	
	// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
	kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
		searchAddr(mouseEvent.latLng);
	});	
	
	document.getElementById('drive_date').addEventListener('click',function() {
		var date = document.getElementById('drive_date').value;
		if (date) {
	        alert("운행일 변경 시 현재 저장된 경로가 모두 제거됩니다");
	    }
	});
	
	document.getElementById('drive_date').addEventListener('change',function() {
		deleteSaveAll();
		alert("운행일이 변경되었습니다");
	});
		
	document.getElementById('largeclass').addEventListener('change',function() {
		var selectedOption = this.options[this.selectedIndex];
		selectedCity = selectedOption.text;
		
		geocoder.addressSearch(selectedCity, function(result, status) {
		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		        map.setCenter(coords);	        
		     }	    
		});
	});

    document.addEventListener('DOMContentLoaded', function() {
        // 버튼에 클릭 이벤트 리스너 추가
        document.querySelector('.add-button').addEventListener('click', function() {
        	if(document.getElementById('place_name').value === "") {
				alert(" Error - 올바르지 않은 운행지명이 포함되어있습니다! ");
				return;
			} else if(document.getElementById('clickLatlng').textContent === "") {
				alert(" Error - 올바르지 않은 좌표가 포함되어있습니다! ");
				return;
			} else if(document.getElementById('start_time').value === "") {
				alert(" Error - 올바르지 않은 시작시간이 포함되어있습니다! ");
				return;
			} else if(document.getElementById('end_time').value === "") {
				alert(" Error - 올바르지 않은 종료시간이 포함되어있습니다! ");
				return;
			} else if(document.getElementById('addr').textContent === "") {
				alert(" Error - 올바르지 않은 주소가 포함되어있습니다! ");
				return;
			} else {
	        	formData = {
	       			place_name: document.getElementById('place_name').value,
	       			latlng: document.getElementById('clickLatlng').textContent,
	       			address: document.getElementById('addr').textContent,
	       			start_time: document.getElementById('start_time').value,
	       			end_time: document.getElementById('end_time').value
	        	};  
			}
        	
        	idx = generateUniqueId();
        	formDataMap.set(idx, formData);
        	addWayLabel(formDataMap)
        });
    });
    
    function searchAddr(latLng){
    	 searchDetailAddrFromCoords(latLng, function(result, status) {
 	        if (status === kakao.maps.services.Status.OK) {
 	        	var detailAddr = '<div>' + result[0].address.address_name + '</div>';
 	            
 	            var content = '<div class="bAddr">' + detailAddr + '</div>';
 	                 
	         	    marker.setPosition(latLng);
	         	   	marker.setMap(map);		
	         	  	document.getElementById("largeclass").value = result[0].address.address_name.split(" ")[0];
		            
		            // 현재 좌표로 주소를 검색해서 지도에 표시합니다
		            searchAddrFromCoords(latLng, displayCenterInfo);
		            
		            var message = latLng.getLat() + ', ' + latLng.getLng();
		    	    
		    	    var resultDiv = document.getElementById('clickLatlng').innerHTML = message;
		
		            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
		            infowindow.setContent(content);
		            infowindow.open(map, marker);		
 	        }
 	    });
    }
    
    function addWayLabel(formDataMap){
    	console.log(formDataMap);
    	
    	var newSpan = document.createElement('span');
    	if(document.getElementById('place_name').value == ""){
    		formDataMap.forEach(function(value, key) {
    	        newSpan.textContent = value.place_name;
    	        return; // forEach는 break가 없기 때문에 return을 사용하여 루프 종료
    	    });
    	} else {
    		newSpan.textContent = document.getElementById('place_name').value;
    	}

    	// 새로운 label 요소 생성
    	var newLabel = document.createElement('label');
    	newLabel.setAttribute('for', 'score');
    	newLabel.className = 'col-form-label';
    	
    	var newLine = document.createElement('label');
    	newLine.className = 'col-sm-5';
    	newLine.id = "Line" + idx;

    	// 생성한 span을 label에 추가
    	newLabel.appendChild(newSpan);
    	newLine.appendChild(newLabel);
    	
    	var editButton = document.createElement('input');
    	var delButton = document.createElement('input');

    	// input 요소의 타입을 설정
    	editButton.type = 'button';
    	delButton.type = 'button';

    	// input 요소의 다른 속성 설정 (예: id, value, onclick)
    	editButton.className = 'edit_button btn btn-secondary btn-sm';
    	editButton.value = '수정';
    	editButton.id = "e" + idx;
    	editButton.onclick = function() {
    	    var buttonId = this.id; // this.id를 사용하여 버튼의 id에 접근
    	    
    	    // 나머지 코드
    	    console.log('클릭한 버튼 id:', buttonId);
    	    var sub_buttonId = buttonId.substring(1);
    	    
    	    document.getElementById('place_name').value = formDataMap.get(sub_buttonId).place_name;
    	    document.getElementById('largeclass').selectedIndex = 0;
    	    document.getElementById('clickLatlng').textContent = formDataMap.get(sub_buttonId).latlng;
    	    document.getElementById('addr').textContent = formDataMap.get(sub_buttonId).addr;
    	    document.getElementById('start_time').value = formDataMap.get(sub_buttonId).start_time;
    	    document.getElementById('end_time').value = formDataMap.get(sub_buttonId).end_time;
    	    
    	    var splitLatLng = formDataMap.get(sub_buttonId).latlng.split(", ");
    	    var latLng = new kakao.maps.LatLng(splitLatLng[0], splitLatLng[1]);
    	    searchAddr(latLng);
    	    map.setCenter(latLng);
    	    
    	    deleteSave(sub_buttonId);
    	};
    	
    	delButton.className = 'del_button btn btn-secondary btn-sm';
    	delButton.value = '삭제';
    	delButton.id = "d" + idx;
    	delButton.onclick = function() {
			var buttonId = this.id; // this.id를 사용하여 버튼의 id에 접근
			var delConfirmed = confirm("정말 삭제하시겠습니까?");
			
    	    console.log('클릭한 버튼 id:', buttonId);
			
			if (delConfirmed) {	        	    
        	    var sub_buttonId = buttonId.substring(1);
        	    deleteSave(sub_buttonId);
		    }
    	};

    	// save-container에 새로운 label을 추가
    	var saveContainer = document.getElementById('save-container');
    	saveContainer.appendChild(newLine);
    	saveContainer.appendChild(editButton);
    	saveContainer.appendChild(delButton);
    	
		document.getElementById('place_name').value = '';
		document.getElementById('largeclass').selectedIndex = 0;
		document.getElementById('clickLatlng').textContent = '';
		document.getElementById('addr').textContent = '';
		document.getElementById('start_time').value = '';
		document.getElementById('end_time').value = '';
		marker.setMap(null);
		infowindow.close(map, marker);
    }
    
    function submitConfirm() {
    	var isConfirmed = confirm('이대로 등록하시겠습니까?');
    	  // 사용자가 확인 버튼을 눌렀을 때의 처리
    	  if (isConfirmed) {
    		  submitContent();
    	  }
    }
    
    function submitContent() {
                       
    	var jsonData = JSON.stringify(Array.from(formDataMap.entries()));
    	var foodtruck_id = document.getElementById('foodtruck_id').value;
    	var date = document.getElementById('drive_date').value;
    	
        var data = {
                formData: jsonData,
                foodtruck_id: foodtruck_id,
                date: date
            };

        // Ajax를 사용하여 서버로 JSON 데이터 전송
        var xhr = new XMLHttpRequest();
        xhr.open('POST', './ProfileFoodtruckWayAddPro.pro', true);
        xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');

        xhr.onload = function () {
            if (xhr.status >= 200 && xhr.status < 300) {
                // 성공적으로 처리된 경우
                console.log(xhr.responseText);
                if (xhr.responseText === "Success") {
                    alert("등록 완료!");
                    location.href="./ProfileFoodtruckList.pro";
                } else {
                    // 서버에서 에러 메시지를 전송한 경우
                    alert(xhr.responseText);
                }
            } else {
                // 오류가 발생한 경우
                alert("Data Insert Error!");
            }
        };
        console.log("Sent JSON data: " + JSON.stringify(data));
        xhr.send(JSON.stringify(data));
    }
    
    function deleteSave(buttonId) {
        // 제거할 요소들의 ID를 구성합니다.
        var lineId = "Line" + buttonId;
        var editButtonId = "e" + buttonId;
        var delButtonId = "d" + buttonId;

        // 요소들을 가져옵니다.
        var lineToRemove = document.getElementById(lineId);
        var editButtonToRemove = document.getElementById(editButtonId);
        var delButtonToRemove = document.getElementById(delButtonId);

        // editButton과 delButton을 각각 삭제합니다.
        editButtonToRemove.parentNode.removeChild(editButtonToRemove);
        delButtonToRemove.parentNode.removeChild(delButtonToRemove);

        // div도 삭제합니다.
        lineToRemove.parentNode.removeChild(lineToRemove);
        formDataMap.delete(buttonId);
    }
    
    function deleteSaveAll() {
        // 부모 요소를 찾습니다.
        var parentElement = document.getElementById("save-container"); // 여기에는 실제 부모 요소의 ID를 넣어야 합니다.

        // 부모 요소의 모든 자식 요소를 삭제합니다.
        while (parentElement.firstChild) {
            parentElement.removeChild(parentElement.firstChild);
        }

        // 추가로 필요한 작업을 수행할 수 있습니다.
        formDataMap.clear(); // 예시: 맵의 모든 항목을 지우는 경우
    }
    
 	// 사용되지 않은 ID 생성
    function generateUniqueId() {
        return 'uniqueId' + Math.floor(Math.random() * 1000);
    }
	
	function searchAddrFromCoords(coords, callback) {
	    // 좌표로 행정동 주소 정보를 요청합니다
	    geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
	}
	
	function searchDetailAddrFromCoords(coords, callback) {
	    // 좌표로 법정동 상세 주소 정보를 요청합니다
	    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
	}
	
	// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
	function displayCenterInfo(result, status) {
	    if (status === kakao.maps.services.Status.OK) {
	        var infoDiv = document.getElementById('addr');
	
	        for(var i = 0; i < result.length; i++) {
	            // 행정동의 region_type 값은 'H' 이므로
	            if (result[i].region_type === 'H') {
	                infoDiv.innerHTML = result[i].address_name;
	                break;
	            }
	        }
	    }    
	}
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<%-- <jsp:include page="../inc/bottom.jsp" /> --%>
<jsp:include page="../inc/bottom.jsp" />
</body>
</html>