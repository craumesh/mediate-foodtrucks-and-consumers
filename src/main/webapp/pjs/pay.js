/**
 * 
 */
 		// 상품번호 생성
		function createOrderNum() {
			const date = new Date();
			const day = String(date.getDate()).padStart(2, "0");

			let orderNum = day;
			for (let i = 0; i < 6; i++) {
				orderNum += Math.floor(Math.random() * 10);
			}
			return orderNum; // 총 8자리 숫자
		}
		
		// 상품번호(merchant_uid)
		const purchase_id = createOrderNum();
		document.getElementById("purchase_id").value = purchase_id;


 
 // 장바구니로 돌아가기
		function cartBack() {
			history.back();
			}
		

		// 결제수단 미선택시 알림창 알려주기
		function findSubject() {
			var arrRadio = document.getElementsByName("pay");
			var selected = false;
			for (var i = 0; i < arrRadio.length; i++) {
				if (arrRadio[i].checked) {
					selected = true;
					break;
				}
			}
			if (selected) {
				requestPay();
			} else {
				alert('결제수단을 선택하세요');
			}
		}
		
		

		// 포트원(구 아임포트) API
		function requestPay() {
			const userCode = "imp75410442";
			IMP.init(userCode);

			// 라디오 버튼 선택에 따라 pg 값을 동적으로 설정
			var selectedPG = document
					.querySelector('input[name="pay"]:checked').value;

			IMP.request_pay({
				pg : selectedPG, // 라디오 버튼마다 결제방식 달라짐
				pay_method : "card",// card는 고정
				merchant_uid : purchase_id, //상품번호+주문날짜
				name : name, // 상품명 순서대로 2개까지 표시 후 '외 남은상품수'
				amount : money,
				buyer_email : email,
				buyer_name : userName
			}, function(data) {
				if (data.success) { // 결제성공후
					var msg = "결제 완료";
					$.ajax({
			            url: './check.ajx',  // 토큰 받아오는 서블릿 URL
			            type: 'post',
			            success: function(token) {
			        document.getElementById("token").value = token;
					// 폼 데이터 submit 실행
					document.getElementById("mypayment").submit();

			            }});
				} else { // 결제취소할때, 중복결제하려고 할 때
					var msg = "결제를 취소하셨습니다";
				}
				alert(msg);
			});
		}
		