<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<!-- 파비콘 적용 -->
<link rel="icon" href="./fooding/images/fooding_log.png" type="image/x-icon">
<jsp:include page="../inc/top_logout.jsp" />
<link href="./fooding/css/insertForm.css" rel="stylesheet" />
<link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
      crossorigin="anonymous"
    />
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js" ></script>
<script type="text/javascript">

// 유효성 관련 정규식
var getId = RegExp(/^[a-z0-9]{6,15}$/i); // 아이디 6~15자 숫자 또는 영문으로 구성
var getPw = RegExp(/^(?=.*\d)(?=.*[a-z])[a-zA-Z0-9]{8,15}$/i); // 8~15자, 숫자&영문 반드시 포함
	// -> RegExp는 생략 해도된다! 똑같은 효과를 나타냄
var getEmail = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;

// 아이디 중복확인 여부
var isIdConfirmed = false;




// 유효성 검사 메서드
function check(){
	
	var id = document.fr.id.value;
	var pw = document.fr.pw.value;
	var pw2 = document.fr.pw2.value;  		
	var name = document.fr.name.value;
	var phone = document.fr.phone.value;
	var email = document.fr.email.value;
	
 	// 아이디 유효성 검사
	if(id == ""){
		alert(' 아이디를 입력하세요! ');
		return false;
	}
	
	else if(!getId.test(id)){
		alert(' 아이디 형식에 맞게 입력해주세요. ');
		return false;
	}
 	
	// 아이디 중복체크 확인
	else if(!isIdConfirmed){
		alert("아이디 중복확인을 해주세요.");
		$("#id").focus();
		return false;
	} // ID
	
	
	// 비밀번호
	else if(pw == ""){
		alert(' 비밀번호를 입력하세요! ');
		document.fr.pw.focus();
		return false;
	}
 	
	// 비밀번호 형식
	else if(!getPw.test(pw)){
		alert(' 비밀번호 형식에 맞게 입력해주세요. ');
		return false;
	}  // PW 
 	
 	
	// 이름
	else if(name == ""){
		alert(' 이름을 입력하세요! ');
		document.fr.name.focus();
		return false;
	} // NA
	
	
 	// 전화번호
	else if(phone == ""){
		alert(' 전화번호를 입력해주세요! ');
		document.fr.phone.focus();
		return false;
	} // PH
	
	
 	
 	// 이메일
	else if(email == ""){
		alert(' 이메일을 입력하세요! ');
		document.fr.email.focus();
		return false;
	}// EM 
 	
	
	// 이메일 형식
	else if(!getEmail.test(email)){
		alert(' 이메일 형식에 맞게 입력해주세요! ');
		return false;
	}
	
	
	
 	// 비밀번호 형식
	else if(!getPw.test(pw)){
		alert(' 비밀번호 형식에 맞게 입력해주세요. ');
		return false;
	}  // id
 	
 	
 		
	// 비밀번호 일치
	else if(pw != pw2){
		alert(' 비밀번호가 일치하지 않습니다! ');
		document.fr.pw2.focus();
		return false;
	} // PW2
	
 		
} // 유효성 검사 ----------------------------------------



//전화번호에 자동으로 하이픈 넣기, 중복확인 후 아이디 수정시
$(function(){
	// 하이픈
	$("#phone").on("keyup", function(){
		var telVal = $(this).val(); // 자신의 값()
		telVal = telVal.replace(/[^0-9]/g, ''); // 숫자 이외의 문자 제거
		var telLen = telVal.length;
		if(telLen > 3){
			telVal = telVal.substring(0,3) + "-" + telVal.substring(3);
		}
		if(telLen > 7){
			telVal = telVal.substring(0,8) + "-" +telVal.substring(8);
		}
		$(this).val(telVal);
	});
	
	// 중복확인 후 아이디 수정
	$("#id").on("keyup", function(){
		isIdConfirmed = false;
	});

}); // 하이픈


// 아이디 중복확인
$(function() {
	$("#confirmId").on("click", function() {
		let idValue = $("#id").val();
	
		if (idValue === "") {
			alert("아이디를 입력해주세요.");
			return false;
		}
	
	
		// 아이디 유효성검사
		if(!getId.test($("#id").val())){
			alert("8~15자 영문, 숫자를 입력해주세요.");
			$("#id").focus();
			return false;
		}
		
		
		$.ajax({
			type: "POST",
			url: "./MemberConfirmIdAction.mem",
			dataType: "JSON",
			success: function (data) {
			console.log(data);
			
				let checkId = false;
		
				for (let i = 0; i < data.memberList.length; i++) {
					if (data.memberList[i].id === idValue) {
						checkId = true;
						break;
					}
				}
			
				if (checkId) {
					alert("이미 사용중인 아이디 입니다.");
					isIdConfirmed = false;
				} else {
					alert("사용 가능한 아이디 입니다.");
					isIdConfirmed = true;
				}
			}
		}); // ajax
	}); // 중복관리 버튼

});

</script>
</head>
<body class="insertBody">
	
	<div class="insertFm">
	<h2>푸딩 회원가입</h2>
		<form action="./MemberJoinAction.mem" method="post" name="fr" onsubmit="return check();">
            
		<label id="sad" for="id" class="required-label">아이디</label>
			<div class="id-container">
				<input type="text" name="id" id="id" placeholder="숫자 또는 영문 6-15 자" class="in">
				<input type="button" value="중복확인" id="confirmId" class="confirmIdButton">
			</div>
					
			<label id="sad" for="pw" class="required-labe">비밀번호</label>
				<input type="password" name="pw" id="pw" placeholder="숫자, 영문 조합 8-15 자" class="in">
				
			<label id="sad" for="pw2" class="required-labe">비밀번호 확인</label>
				<input type="password" name="pw2" id="pw2" placeholder="비밀번호 확인" class="in">
				
			<label id="sad" for="name" class="required-labe">이름</label>
				<input type="text" name="name" id="name"  placeholder="이름을 입력하세요!" class="in"> 
				
			<label id="sad" for="phone" class="required-labe1">전화번호</label>
				<input type="text" name="phone" id="phone"  placeholder="숫자만 입력하세요  ex(01012345678)" class="in">
				
			<label id="sad" for="email" class="required-labe2">이메일</label>
				<input type="email" name="email" id="email"  placeholder="ex(abc@abc.com & abc@abc.co.kr)" class="in">
				
				<button
				  type="button"
				  class="btn btn-primary condition-modal-btn"
				  data-bs-toggle="modal"
				  data-bs-target="#staticBackdrop"
				>
					약관확인
				</button>
					
				<input type="submit" value="회원가입" id="insertBtn" class="sub-btn">
			</form>
		</div>
          <!-- Button trigger modal -->
   

	<!-- Modal -->
	<div
		class="modal fade"
		id="staticBackdrop"
		data-bs-backdrop="static"
		data-bs-keyboard="false"
		tabindex="-1"
		aria-labelledby="staticBackdropLabel"
		aria-hidden="true"
	>
	<div class="modal-dialog modal-dialog-scrollable">
		<!-- 여기 변경 -->
		<div class="modal-content">
		 <div class="modal-header">
			<h1 class="modal-title fs-5" id="staticBackdropLabel">
				푸딩이용약관
			</h1>
			<button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
			></button>
		</div>
		<div class="modal-body" id="insertModal">
		
		<div>
		<div class="form-check">
        <label class="form-check-label" for="all-check">전체 동의</label>
        <input type="checkbox" class="form-check-input service-check" id="all-check" />
        </div>
    	</div>
    	
    	  <hr>
    	  
    	  
    <div>
    	
        <div class="form-check">
        <input class="form-check-input service-check" type="checkbox" id="required1">
        <label class="form-check-label" for="required1">
            [필수] 푸딩 이용약관
        </label>
    	</div>
        
		<div class="content-box">
		<div class="inner-content">
		
        제 1 조 (목적)<br>
		
		
		이 약관은 푸딩 주식회사 ("회사" 또는 "푸딩")가
		제공하는 푸딩 및 푸딩 관련 제
		반 서비스의 이용과 관련하여 회사와 회원과의 권리, 
		의무 및 책임사항, 기타 필요한 사항
		을 규정함을 목적으로 합니다.<br>
		
		
		제 2 조 (정의)<br>
		
		
		이 약관에서 사용하는 용어의 정의는 다음과 같습니다.<br>
		
		①"서비스"라 함은 구현되는 단말기(PC, TV, 휴대형단말기 
		등의 각종 유무선 장치를 포함)와 상관없이 "회원"이 이용할 
		수 있는 푸딩 및 푸딩 관련 제반 서비스를 의미합니다.<br>
		
		②"회원"이라 함은 회사의 "서비스"에 접속하여 이 약관에 
		따라 "회사"와 이용계약을 체결하고 "회사"가 제공하는 "서비스"를 
		이용하는 고객을 말합니다.<br>
		
		③"아이디(ID)"라 함은 "회원"의 식별과 "서비스" 이용을 위하여 
		"회원"이 정하고 "회사"가 승인하는 문자와 숫자의 조합을 의미합니다.<br>
		
		④"비밀번호"라 함은 "회원"이 부여 받은 "아이디와 일치되는 
		"회원"임을 확인하고 비밀보호를 위해 "회원" 자신이 정한 문자 
		또는 숫자의 조합을 의미합니다.<br>
		
		⑤"유료서비스"라 함은 "회사"가 유료로 제공하는 각종 
		온라인디지털콘텐츠(각종 정보콘텐츠, VOD, 아이템 기타 유료콘텐츠를 포함) 
		및 제반 서비스를 의미합니다.<br>
		
		⑥"포인트"라 함은 서비스의 효율적 이용을 위해 회사가 
		임의로 책정 또는 지급, 조정할 수 있는 재산적 가치가 없는 
		"서비스" 상의 가상 데이터를 의미합니다.<br>
		
		⑦"게시물"이라 함은 "회원"이 "서비스"를 이용함에 있어 
		"서비스상"에 게시한 부호ㆍ문자ㆍ음성ㆍ음향ㆍ화상ㆍ동영상 
		등의 정보 형태의 글, 사진, 동영상 및 각종 파일과 링크 등을 의미합니다. <br>
		
		</div>
		</div>
		
    </div>
    
      <hr>
    
    <div>
    
        <div class="form-check">
        <input class="form-check-input service-check" type="checkbox" id="required2">
        <label class="form-check-label" for="required2">
            [필수] 개인정보 수집 및 이용
        </label>
    	</div>
        
        <div class="content-box">
		<div class="inner-content">
		
		개인정보보호법에 따라 푸딩에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.<br>

		1. 수집하는 개인정보<br>
		이용자는 회원가입을 하지 않아도 정보 검색, 뉴스 보기 등 대부분의 푸딩 
		서비스를 회원과 동일하게 이용할 수 있습니다. 이용자가 메일, 캘린더, 카페, 
		블로그 등과 같이 개인화 혹은 회원제 서비스를 이용하기 위해 회원가입을 할 경우, 
		푸딩은 서비스 이용을 위해 필요한 최소한의 개인정보를 수집합니다.<br>
		
		회원가입 시점에 푸딩이 이용자로부터 수집하는 개인정보는 아래와 같습니다.<br>
		- 회원 가입 시 필수항목으로 아이디, 비밀번호, 이름, 휴대전화번호를, 
		선택항목으로 본인확인 이메일주소를 수집합니다. 실명 인증된 아이디로 가입 시,
		 암호화된 동일인 식별정보(CI), 중복가입 확인정보(DI), 내외국인 정보를 함께 수집합니다. 
		 만14세 미만 아동의 경우, 법정대리인 정보(법정대리인의 이름, 생년월일, 성별, 중복가입확인정보(DI), 
		 휴대전화번호)를 추가로 수집합니다.<br>
		- 비밀번호 없이 회원 가입 시에는 필수항목으로 아이디,
		 이름, 휴대전화번호를, 선택항목으로 비밀번호를 수집합니다.<br>
		- 단체 회원가입 시 필수 항목으로 단체아이디, 비밀번호, 단체이름
		, 이메일주소, 휴대전화번호를, 선택항목으로 단체 대표자명을 수집합니다.
		서비스 이용 과정에서 이용자로부터 수집하는 개인정보는 아래와 같습니다.<br>
		- 회원정보 또는 개별 서비스에서 프로필 정보(별명, 프로필 사진)를 설정할 수 있습니다.
		 회원정보에 별명을 입력하지 않은 경우에는 마스킹 처리된 아이디가 별명으로 자동 입력됩니다.<br>
		- 푸딩 내의 개별 서비스 이용, 이벤트 응모 및 경품 신청 과정에서 해당 서비스의 이용자에 한해
		 추가 개인정보 수집이 발생할 수 있습니다. 추가로 개인정보를 수집할 
		 경우에는 해당 개인정보 수집 시점에서 이용자에게 ‘수집하는 개인정보 항목, 
		 개인정보의 수집 및 이용목적, 개인정보의 보관기간’에 대해 안내 드리고 동의를 받습니다.<br>
		
		
		
		서비스 이용 과정에서 IP 주소, 쿠키, 서비스 이용 기록, 기기정보, 위치정보가 생성되어 수집될 수 있습니다. 
		또한 이미지 및 음성을 이용한 검색 서비스 등에서 이미지나 음성이 수집될 수 있습니다.<br>
		구체적으로 1) 서비스 이용 과정에서 이용자에 관한 정보를 자동화된 방법으로 생성하여 이를 저장(수집)하거나, 2) 이용자 기기의 고유한 정보를 원래의 값을 확인하지 못 하도록 안전하게 변환하여 수집합니다.
		서비스 이용 과정에서 위치정보가 수집될 수 있으며,
		푸딩에서 제공하는 위치기반 서비스에 대해서는 '푸딩 위치기반서비스 이용약관'에서 자세하게 규정하고 있습니다.
		이와 같이 수집된 정보는 개인정보와의 연계 여부 등에 따라 개인정보에 해당할 수 있고, 개인정보에 해당하지 않을 수도 있습니다.<br>
		2. 수집한 개인정보의 이용<br>
		푸딩 및 푸딩 관련 제반 서비스(모바일 웹/앱 포함)의 회원관리, 서비스 개발・제공 및 향상, 안전한 인터넷 이용환경 구축 등 아래의 목적으로만 개인정보를 이용합니다.<br>
		
		- 회원 가입 의사의 확인, 연령 확인 및 법정대리인 동의 진행, 이용자 및 법정대리인의 본인 확인, 이용자 식별, 회원탈퇴 의사의 확인 등 회원관리를 위하여 개인정보를 이용합니다.<br>
		- 콘텐츠 등 기존 서비스 제공(광고 포함)에 더하여, 인구통계학적 분석, 서비스 방문 및 이용기록의 분석, 개인정보 및 관심에 기반한 이용자간 관계의 형성, 지인 및 관심사 등에 기반한 맞춤형 서비스 제공 등 신규 서비스 요소의 발굴 및 기존 서비스 개선 등을 위하여 개인정보를 이용합니다. 신규 서비스 요소의 발굴 및 기존 서비스 개선 등에는 정보 검색, 다른 이용자와의 커뮤니케이션, 콘텐츠 생성·제공·추천, 상품 쇼핑 등에서의 인공지능(AI) 기술 적용이 포함됩니다.<br>
		- 법령 및 푸딩 이용약관을 위반하는 회원에 대한 이용 제한 조치, 부정 이용 행위를 포함하여 서비스의 원활한 운영에 지장을 주는 행위에 대한 방지 및 제재, 계정도용 및 부정거래 방지, 약관 개정 등의 고지사항 전달, 분쟁조정을 위한 기록 보존, 민원처리 등 이용자 보호 및 서비스 운영을 위하여 개인정보를 이용합니다.<br>
		- 유료 서비스 제공에 따르는 본인인증, 구매 및 요금 결제, 상품 및 서비스의 배송을 위하여 개인정보를 이용합니다.<br>
		- 이벤트 정보 및 참여기회 제공, 광고성 정보 제공 등 마케팅 및 프로모션 목적으로 개인정보를 이용합니다.<br>
		- 서비스 이용기록과 접속 빈도 분석, 서비스 이용에 대한 통계, 서비스 분석 및 통계에 따른 맞춤 서비스 제공 및 광고 게재 등에 개인정보를 이용합니다.<br>
		- 보안, 프라이버시, 안전 측면에서 이용자가 안심하고 이용할 수 있는 서비스 이용환경 구축을 위해 개인정보를 이용합니다.<br>
		
    	</div>
    	</div>
    
    </div>
    
      <hr>
    
    <div>
        <div class="form-check">
        <input class="form-check-input service-check" type="checkbox" id="optional">
        <label class="form-check-label" for="optional">
            [선택] 실명 인증된 아이디로 가입
        </label>
    	</div>
    	
    	<div class="content-box">
		<div class="inner-content">
    	
      실명 인증된 아이디로 가입하시면 본인인증이 필요한 서비스(푸딩 페이, 쇼핑, 멤버십 등)를 가입 후 바로 이용하실 수 있어요.<br>
      </div>
      </div>
      
      
    </div>
    
		</div>
			
		<div class="modal-footer">
			<button
				type="button"
				class="btn btn-secondary check-btn"
				data-bs-dismiss="modal"
				>
				닫기
		</button>
            </div>
          </div>
        </div>
      </div>
  
    
	<script>
	 let checkButton = document.querySelector('.check-btn'); // 닫기버튼
     let allCheck = document.getElementById('all-check'); // 전체 동의 체크박스
     let required1Check = document.getElementById('required1'); // 필수 동의1 체크박스
     let required2Check = document.getElementById('required2'); // 필수 동의2 체크박스
     let optionalCheck = document.getElementById('optional'); // 선택 동의 체크박스
     let subButton = document.querySelector('.sub-btn');

     // 전체 동의 체크박스 상태 변경 시
     allCheck.addEventListener('change', () => {
         required1Check.checked = allCheck.checked;
         required2Check.checked = allCheck.checked;
         optionalCheck.checked = allCheck.checked;
     });

     // 각 체크박스의 상태 변경 시
     required1Check.addEventListener('change', updateAllCheck);
     required2Check.addEventListener('change', updateAllCheck);
     optionalCheck.addEventListener('change', updateAllCheck);

     function updateAllCheck() {
         allCheck.checked = required1Check.checked && required2Check.checked && optionalCheck.checked;
     }

     subButton.addEventListener('click', function (event) {
         if (!required1Check.checked || !required2Check.checked) {
             alert("[약관]필수 동의 항목을 모두 체크해 주세요.");
             event.preventDefault();
         }
     });
    
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"
	  ></script>
</body>
</html>