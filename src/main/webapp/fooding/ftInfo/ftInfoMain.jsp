<%@page import="com.fooding.ftinfo.db.FtInfoDTO"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>푸드트럭 정보</title>
<!-- 파비콘 적용 -->
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<link href="./fooding/css/ftinfo.css" rel="stylesheet"> 
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
</head>
<body>

	<!-- 임시 데이터 입력 -->
	<%
	FtInfoDTO dto = (FtInfoDTO)request.getAttribute("dto");
	StringBuilder menuAsString = new StringBuilder();
	List<String> menuArr = new ArrayList<String>();
	for (int i = 0; i < dto.getPdname().size(); i++) {
		menuArr.add(dto.getPdname().get(i) + ", " + dto.getPrice().get(i));
	}

	menuAsString.append("[");
	for (int i = 0; i < menuArr.size(); i++) {
		menuAsString.append("\"").append(menuArr.get(i)).append("\"");
		if (i < menuArr.size() - 1) {
			menuAsString.append(", ");
		}
	}
	menuAsString.append("]");

 	StringBuilder placeAsString = new StringBuilder();
	placeAsString.append("[");
	for (int i = 0; i < dto.getAddress().size(); i++) {
		placeAsString.append("\"").append(dto.getAddress().get(i)).append("\"");
		if (i < dto.getAddress().size() - 1) {
			placeAsString.append(", ");
		}
	}
	placeAsString.append("]"); 

	StringBuilder dateAsString = new StringBuilder();
	dateAsString.append("[");
	for (int i = 0; i < dto.getDate().size(); i++) {
		dateAsString.append("\"").append(dto.getDate().get(i)).append("\"");
		if (i < dto.getDate().size() - 1) {
			dateAsString.append(", ");
		}
	}
	dateAsString.append("]");
	%>
	<!-- 임시 데이터 입력 -->
	
	<!-- 로그인 했을 때 -->
    <c:if test="${!empty sessionScope.id }">
    	<jsp:include page="../inc/top_another.jsp" />
    </c:if>
	<!-- 로그인 했을 때 -->
	<!-- 로그인 안 했을 때 -->
    <c:if test="${empty sessionScope.id }">
    	<jsp:include page="../inc/top_logout_another.jsp" />
    </c:if>
	<!-- 로그인 안 했을 때 -->


	<!-- JQUERY import -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	  integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	  crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
	<!-- JQUERY import -->
	
	
	<div class="ftInfoBox">	
		<img class="accimg1" src="./upload/foodtruck/fooding_log.png">	
		<!-- 푸드트럭 이름 출력 -->	
		<h1 class="ftName">${dto.getFtname() }</h1>
		<!-- 푸드트럭 이름 출력 -->	
		
		<div class="ftInfoContainer">
			<!-- 푸드트럭 이미지 출력 -->	
			<img class="ftImage" src="./upload/foodtruck/${dto.getFtimage() }" alt="이곳에 푸드트럭 이미지를 추가하세요!">
			<!-- 푸드트럭 이미지 출력 -->
		
			<!-- 푸드트럭 설명 출력 -->
			<p class="ftInfoText" readonly="readonly">${dto.getFtinfo() }</p>
			<!-- 푸드트럭 설명 출력 -->
		</div>		
		
			
		<!-- 푸드트럭 메뉴판 출력 -->	    
	    <script>
	    $(document).ready(function(){
	    	$('.bxslider').bxSlider({
	    		pagerCustom: '#bx-pager'
	    	});
		});
	    </script>	
	    
	    
	    <div id="maincontaner">
	    	<div id="drivecontaner">	
				<div id="drive">
					<label id="drivetitle"> 운행 정보 </label>					
				</div>
				
				
				<!-- 카카오맵 출력 -->	
				<div id="map"></div>
				<!-- 카카오맵 출력 -->					
					
				
				<div id="daydrop">
					<!-- 운행일 드롭다운 출력 -->
					<select size="1" name="dateSelecter" id="dateSelecter"></select>
					<!-- 운행일 드롭다운 출력 -->
					
					
					<!-- 운행 시간 라디오 버튼 -->
					<div id="timeRadioButton"></div>
					<!-- 운행 시간 라디오 버튼 -->
				</div>
			</div>
	    
		    <div id="sliderBox">
		   		<label id="menutext"> 메뉴 </label>
			    <ul class="bxslider">
			    	<c:forEach var="i" begin="0" end="${dto.getPdname().size()-1 }" step="1">
						  <li>
							  	<img src="./upload/product/${dto.getPdimage().get(i) }" />
						  		<div id="pdtextbox">
							  		<h5 id="pdtitle">${dto.getPdname().get(i) } - ${dto.getPrice().get(i) }원</h5>
							  		<h5>${dto.getPdinfo().get(i) }</h5>
						  		</div>
						  </li>
				  	</c:forEach>
				</ul>
				
				<div id="bx-pager">
					<c:forEach var="i" begin="0" end="${dto.getPdname().size()-1 }" step="1">
				  		<a data-slide-index="${i}" href=""><img src="./upload/product/${dto.getPdimage().get(i) }" /></a>
				  	</c:forEach>
				</div>
			</div>
			<!-- 푸드트럭 메뉴판 출력 -->
		</div>
		
		
		<!-- 운행일 드롭다운 -->
		<script>
			const dateArr =	<%=dateAsString.toString()%>;
	
			var	stopPlaceArr;
	
			// select 요소 가져오기
			const dateElement = document.getElementById("dateSelecter");
	
			let selectedDate = ''; // 선택한 날짜를 저장할 변수
			
			// 메뉴와 가격 데이터를 기반으로 옵션 추가
			for (let i = 0; i < dateArr.length; i++) {
				const option = document.createElement("option");
				option.value = i;
				option.text = dateArr[i];
				dateElement.appendChild(option);
			}		
		</script>
		<!-- 운행일 드롭다운 -->	
		
		
		<!-- 카카오맵 -->	
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f6593940ec361770de51ddc68ebfecfa"></script>
		<script>
			var MARKER_WIDTH = 33, // 기본, 클릭 마커의 너비
			MARKER_HEIGHT = 45, // 기본, 클릭 마커의 높이
			OFFSET_X = 12, // 기본, 클릭 마커의 기준 X좌표
			OFFSET_Y = MARKER_HEIGHT, // 기본, 클릭 마커의 기준 Y좌표
			OVER_MARKER_WIDTH = 40, // 오버 마커의 너비
			OVER_MARKER_HEIGHT = 45, // 오버 마커의 높이
			OVER_OFFSET_X = 13, // 오버 마커의 기준 X좌표
			OVER_OFFSET_Y = OVER_MARKER_HEIGHT, // 오버 마커의 기준 Y좌표
			SPRITE_MARKER_URL = './upload/foodtruck/marker_number_3color.png', // 스프라이트 마커 이미지 URL
			SPRITE_WIDTH = 108, // 스프라이트 이미지 너비
			SPRITE_HEIGHT = 691, // 스프라이트 이미지 높이
			SPRITE_GAP = 3; // 스프라이트 이미지에서 마커간 간격
	
			var markerSize = new kakao.maps.Size(MARKER_WIDTH, MARKER_HEIGHT), // 기본, 클릭 마커의 크기
			markerOffset = new kakao.maps.Point(OFFSET_X, OFFSET_Y), // 기본, 클릭 마커의 기준좌표
			overMarkerSize = new kakao.maps.Size(OVER_MARKER_WIDTH,OVER_MARKER_HEIGHT), // 오버 마커의 크기
			overMarkerOffset = new kakao.maps.Point(OVER_OFFSET_X,OVER_OFFSET_Y), // 오버 마커의 기준 좌표
			spriteImageSize = new kakao.maps.Size(SPRITE_WIDTH, SPRITE_HEIGHT); // 스프라이트 이미지의 크기
			
			var map;
			var content;
			var overlay = new kakao.maps.CustomOverlay();
			var lines = [];
			var selectedMarker = null; // 클릭한 마커를 담을 변수
	
			if(stopPlaceArr == null){
				stopPlaceArr = <%=placeAsString.toString()%>;
			}	
			
			ReMarking();
			
			function ReMarking(){
				console.log(stopPlaceArr);
				const placeName = new Array();
				const latitude = new Array();
				const longitude = new Array();		
				var bounds = new kakao.maps.LatLngBounds(); 
				
				var stopMarker = [];
				var container;
				var options;
	
				for (var i = 0; i < stopPlaceArr.length; i++) {
					placeName[i] = stopPlaceArr[i].split(", ")[0];
					latitude[i] = stopPlaceArr[i].split(", ")[1];
					longitude[i] = stopPlaceArr[i].split(", ")[2];
				}
				console.log(placeName);
				console.log(latitude);
				console.log(longitude);
				
				for (var i = 0; i < stopPlaceArr.length; i++) {
					stopMarker[i] = {
						title : placeName[i],
						latlng : new kakao.maps.LatLng(latitude[i], longitude[i])
					}
					bounds.extend(stopMarker[i].latlng);
				}						
	
				container = document.getElementById('map');
				
				options = {
					center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
					level : 3
				// 지도의 확대 레벨
				};
				map = new kakao.maps.Map(container, options); // 지도 생성
				
 				addLine(stopMarker);
				map.setBounds(bounds); 
	
				// 지도 위에 마커를 표시합니다
				for (var i = 0; i < stopMarker.length; i++) {
					var gapX = (MARKER_WIDTH + SPRITE_GAP), // 스프라이트 이미지에서 마커로 사용할 이미지 X좌표 간격 값
					originY = (MARKER_HEIGHT + SPRITE_GAP) * i, // 스프라이트 이미지에서 기본, 클릭 마커로 사용할 Y좌표 값
					overOriginY = (OVER_MARKER_HEIGHT + SPRITE_GAP) * i, // 스프라이트 이미지에서 오버 마커로 사용할 Y좌표 값
					normalOrigin = new kakao.maps.Point(0, originY), // 스프라이트 이미지에서 기본 마커로 사용할 영역의 좌상단 좌표
					clickOrigin = new kakao.maps.Point(gapX, originY), // 스프라이트 이미지에서 마우스오버 마커로 사용할 영역의 좌상단 좌표
					overOrigin = new kakao.maps.Point(gapX * 2, overOriginY); // 스프라이트 이미지에서 클릭 마커로 사용할 영역의 좌상단 좌표
	
					// 마커를 생성하고 지도위에 표시합니다
					addMarker(stopMarker[i], normalOrigin, overOrigin, clickOrigin, i);				
				}
			}
			
			function addLine(markers){
				var linePath = [];
				for(var i = 0; i < markers.length; ++i){
					linePath.push(markers[i].latlng);
				}
				
				var polyline = new kakao.maps.Polyline({
					path: linePath, // 선 좌표 배열
					strokeWeight: 5, // 선 두께
					strokeColor: '#273D59', // 선 색상
					strokeOpacity: 1, // 선 불투명도
					strokeStyle: 'solid', // 선 스타일
					endArrow: true
				});
				
				var polyoutline = new kakao.maps.Polyline({
					path: linePath, // 선 좌표 배열
					strokeWeight: 15, // 선 두께
					strokeColor: '#fff', // 선 색상
					strokeOpacity: 1, // 선 불투명도
					strokeStyle: 'solid' // 선 스타일
				});

				console.log(polyline);
				
				polyoutline.setMap(map);
				polyline.setMap(map);
			}
						
			function addMarker(position, normalOrigin, overOrigin, clickOrigin, index) {
				
				// 기본 마커이미지, 오버 마커이미지, 클릭 마커이미지를 생성합니다
				var normalImage = createMarkerImage(markerSize, markerOffset,
						normalOrigin), overImage = createMarkerImage(
						overMarkerSize, overMarkerOffset, overOrigin), clickImage = createMarkerImage(
						markerSize, markerOffset, clickOrigin);
	
				// 마커를 생성하고 이미지는 기본 마커 이미지를 사용합니다
				var marker = new kakao.maps.Marker({
					map : map,
					position : position.latlng,
					title : position.title,
					image : normalImage
				});
				marker.index = index;				
	
				// 마커 객체에 마커아이디와 마커의 기본 이미지를 추가합니다
				marker.normalImage = normalImage;
	
				// 마커에 mouseover 이벤트를 등록합니다
				kakao.maps.event.addListener(marker, 'mouseover', function() {
					// 클릭된 마커가 없고, mouseover된 마커가 클릭된 마커가 아니면
					// 마커의 이미지를 오버 이미지로 변경합니다
					if (!selectedMarker || selectedMarker !== marker) {
						marker.setImage(overImage);
					}
				});
	
				// 마커에 mouseout 이벤트를 등록합니다
				kakao.maps.event.addListener(marker, 'mouseout', function() {
					// 클릭된 마커가 없고, mouseout된 마커가 클릭된 마커가 아니면
					// 마커의 이미지를 기본 이미지로 변경합니다
					if (!selectedMarker || selectedMarker !== marker) {
						marker.setImage(normalImage);
					}
				});
	
				// 마커에 click 이벤트를 등록합니다
				kakao.maps.event.addListener(marker,'click',function(currentValue) {					
					if (marker.index == currentValue) {
						// 클릭된 마커가 없고, click 마커가 클릭된 마커가 아니면
						// 마커의 이미지를 클릭 이미지로 변경합니다
						if (!selectedMarker || selectedMarker !== marker) {
							// 클릭된 마커 객체가 null이 아니면
							// 클릭된 마커의 이미지를 기본 이미지로 변경하고
							!!selectedMarker && selectedMarker.setImage(selectedMarker.normalImage);
							// 현재 클릭된 마커의 이미지는 클릭 이미지로 변경합니다
							marker.setImage(clickImage);
							overlay.setMap(null);
							
							content = '<div class="mapoverlay">' + 
				            '    <div class="overlaybox">' +
				            '        <div class="overlayimg">' +
				            '             <img src="./upload/foodtruck/fooding_log.png" width="60" height="53">' +
				            '        </div>' +			            
				            '        <div class="overlaytitle">' + 
				            '            '+ marker.getTitle() +
				            '        </div>' + 
				            '    </div>' +    
				            '</div>'; 
						}						
						
						overlay = new kakao.maps.CustomOverlay({
						    content: content,
						    map: map,
						    position: marker.getPosition()       
						});
						
						overlay.setMap(map);
	
						// 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다					
						selectedMarker = marker;
						var timeRadio = $(':radio[name="stopPlaceTime"]:checked').attr('id');
						$('#time').val(timeRadio);
						console.log(marker.getPosition());
						$('#place').val(marker.getTitle());
					}
	
					if (currentValue == null) {
						triggerEvent(marker.index);
					}
				});
				
	
				$("input[name='stopPlaceTime']").on('change', function() {
					var currentValue = $(this).val();
					kakao.maps.event.trigger(marker, 'click', currentValue);
				});
			}
	
			// 마커 클릭 시 해당 마커의 고유값과 일치하는 value값을 가진 radio 버튼 체크
			function triggerEvent(eventIndex) {
				// 이벤트가 발생했을 때 그 인덱스와 동일한 값을 가진 라디오 버튼을 클릭합니다.
				const targetRadioButton = document.querySelector('input[type="radio"][value="' + eventIndex.toString() + '"]');
				if (targetRadioButton) {
					targetRadioButton.checked = true; // 라디오 버튼을 체크 상태로 변경
					// 라디오 버튼이 변경됐음을 시뮬레이트하기 위해 change 이벤트를 발생시킵니다.
					const changeEvent = new Event('change', {
						bubbles : true
					});
					targetRadioButton.dispatchEvent(changeEvent); // 라디오 버튼에 변경 이벤트 전달
				} else {
					console.log('값이 ' + eventIndex.toString() + '인 라디오 버튼을 찾을 수 없습니다.');
				}
			}
	
			function createMarkerImage(markerSize, offset, spriteOrigin) {
				var markerImage = new kakao.maps.MarkerImage(SPRITE_MARKER_URL, // 스프라이트 마커 이미지 URL
				markerSize, // 마커의 크기
				{
					offset : offset, // 마커 이미지에서의 기준 좌표
					spriteOrigin : spriteOrigin, // 스트라이프 이미지 중 사용할 영역의 좌상단 좌표
					spriteSize : spriteImageSize
				// 스프라이트 이미지의 크기
				});
	
				return markerImage;
			}
	
			document.getElementById('dateSelecter').addEventListener('input',function() {
				var selectedOption = this.options[this.selectedIndex];
				var selectedDate = selectedOption.text; // 선택한 텍스트를 selectedDate 변수에 저장
				var ftNum = ${param.foodtruck_id };
				console.log(selectedDate);
				$('#date').val(selectedDate);
				$('#truck_id').val(ftNum);
				
					$.ajax({
	                type: 'GET',
	                url: './FTInfoMainProAction.fti',
	                data: { selectedDate: selectedDate,
	                		ftNum: ftNum },
	                success: function(response) {
	                	var timeTable = response.time;
	                	stopPlaceArr = response.address;
	                    var timeRadioButton = document.getElementById('timeRadioButton');
	                    // 새 라디오 버튼을 생성
	                    var newRadioButtonsContainer = document.createElement('div');
	    				
	                    for (var i = 0; i < timeTable.length; i++) {
	                        var radioButton = document.createElement('input');
	                        radioButton.type = 'radio';
	                        radioButton.name = 'stopPlaceTime';
	                        radioButton.value = i;
	                        radioButton.id = timeTable[i];
	
	                        var label = document.createElement('label');
	                        label.appendChild(radioButton);
	                        label.appendChild(document.createTextNode(timeTable[i]));
	                        label.appendChild(document.createElement('br'));
	
	                        newRadioButtonsContainer.appendChild(label); // 새 라디오 버튼 컨테이너에 추가
	                    }	   		
	
	                    if (timeRadioButton) {
	                        // 기존 라디오 버튼을 삭제
	                        while (timeRadioButton.firstChild) {
	                            timeRadioButton.removeChild(timeRadioButton.firstChild);
	                        }
	
	                        // 새 라디오 버튼을 기존 위치에 추가
	                        timeRadioButton.appendChild(newRadioButtonsContainer);
	                    	ReMarking();
	                    } else {
	                        console.error("기존 라디오 버튼을 담을 컨테이너가 없습니다.");
	                    }
	                },
	                error: function() {
	                    console.error('요청 실패');
	                }
	     		}); 
			});
			
			// 페이지 로딩이 완료되었을 때 실행되는 함수
			document.addEventListener("DOMContentLoaded", function() {
	
			    // 드롭다운 엘리먼트를 가져옵니다.
			    var dropdown = document.getElementById("dateSelecter");
	
			    // 원하는 옵션을 선택합니다.
			    var desiredOptionValue = "0"; // 변경하고 싶은 옵션의 값
	
			    // 드롭다운을 변경하고 싶은 값으로 설정합니다.
			    dropdown.value = desiredOptionValue;
	
			    // 변경된 값에 대한 이벤트를 발생시킬 수도 있습니다.
			    // 이벤트를 수동으로 발생시키는 것은 사용 사례에 따라 다를 수 있습니다.
			    var changeEvent = new Event('input');
			    dropdown.dispatchEvent(changeEvent);
			});			
		</script>
		<!-- 카카오맵 -->
	
	
		<div id="ordercontaner">
			<div>
				<label> 상품 </label>
				<!-- 푸드트럭 메뉴 드롭다운 출력 -->
				<select size="1" name="menuSelecter" id="menuSelecter">
					<option value="-1" selected>선택</option>
				</select>
				<!-- 푸드트럭 메뉴 드롭다운 출력 -->
			</div>
			
			
			<div>
				<!--  주문 수량 선택란 출력 -->
				<label> 수량 </label>
				<input type="number" name="quantity" min="1" max="99" value="1" id="quantity" oninput="calculate()">
				<!--  주문 수량 선택란 출력 -->
			</div>
			
			
			<div>
			<!-- 현재 주문 가격 출력 -->			
				<label>	합계 </label>
				<span id="output">0 원</span>
				
			<!-- 현재 주문 가격 출력 -->	
			</div>		
			
			<form action="./FTInfoSendAction.fti" method="post">
				<input type="hidden" name="truck_id" id="truck_id"/>
				<input type="hidden" name="date" id="date"/>
				<input type="hidden" name="time" id="time"/>
				<input type="hidden" name="place" id="place"/>
				<input type="hidden" name="product" id="product"/>
				<input type="hidden" name="hidden_quantity" id="hidden_quantity"/>
				<input type="submit" value="주문 담기" id="orderbutton"/>
			</form>			
		</div>
	
	
		<!-- 푸드트럭 메뉴 드롭다운 -->
		<script>
			const menuArr = <%=menuAsString.toString()%>;
			const menuNameArr = new Array();
			const menuPriceArr = new Array();
	
			for (var i = 0; i < menuArr.length; i++) {
				menuNameArr[i] = menuArr[i].split(", ")[0];
				menuPriceArr[i] = menuArr[i].split(", ")[1];
			}
	
			// select 요소 가져오기
			const menuElement = document.getElementById("menuSelecter");
	
			// 메뉴와 가격 데이터를 기반으로 옵션 추가
			for (let i = 0; i < menuArr.length; i++) {
				const option = document.createElement("option");
				option.value = i;
				option.dataset.price = menuPriceArr[i];
				option.text = menuArr[i];
				menuElement.appendChild(option);
			}
		</script>
		<!-- 푸드트럭 메뉴 드롭다운 -->
	
	
		<!-- 현재 주문 가격 -->
		<script>
			var selectedPrice = 0;
	
			/* 메뉴 선택 시 변경 이벤트를 감지하여 선택된 상품의 가격을 저장하고 현재 상품 가격 계산 함수를 호출 */
			document.getElementById('menuSelecter').addEventListener('change',function() {
						var selectedOption = this.options[this.selectedIndex];
						selectedPrice = selectedOption.getAttribute('data-price');
						var selectedProduct = selectedOption.text;
						console.log(selectedProduct.split(", ")[0]);
						$('#product').val(selectedProduct.split(", ")[0]);
						calculate();
					});
	
			/* 호출 시 현재 주문 수량과 선택된 상품의 가격을 곱하여 출력 */
			function calculate() {
				var inputElement = document.getElementById('quantity');
				var inputValue = inputElement.value;
				var outputValue = inputValue * selectedPrice;
				var outputElement = document.getElementById('output');
				var formattedOutputValue = outputValue.toLocaleString('ko-KR');		
				outputElement.textContent = formattedOutputValue + ' 원';
				$('#hidden_quantity').val(inputValue);
			}
		</script>
		<!-- 현재 주문 가격 -->		
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('#goToList').click(function(){
				location.href="./FtkAction.ftk";
			});
		});
	</script>
	
	<input type="button" value="목록" id="goToList">
	<jsp:include page="../inc/bottom.jsp" />
</body>
</html>