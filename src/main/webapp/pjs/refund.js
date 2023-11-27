/**
 * 
 */
 	// 결제 취소 버튼 클릭 시 모달 창 표시
	function setupRefund(id, quantity, price, idx) {
		console.log(idx);
	    // 결제건 주문번호
	    var refundId = id;
	    var refundQuantity = quantity;
	    var refundPrice = price;

	    var index = idx;
	    
	    var amountz = refundQuantity * refundPrice;
	    
	    var result = confirm('결제를 취소하시겠습니까?');
	    if (result) {
 // 토큰 받아오는 AJAX 요청
        $.ajax({
            url: './check.ajx',  // 토큰 받아오는 서블릿 URL
            type: 'post',
            success: function(token) {
                // 토큰 받아오기 성공 시 환불 처리 AJAX 요청
                $.ajax({
                    url: './refund.jsn',  // 환불 처리 서블릿 URL
                    type: 'post',
                    data: {
                    	action : "customer",
                        token: token,  
                        mid: refundId,  
                        reason: "결제취소", 
                        amount: amountz,
                        price : refundPrice,
                        indexnumber: index
                    },
                    success: function(data) {
                    	 console.log(data);
                    	 if(data.code == 0) { // 코드가 0일때 성공함
                        alert('환불 처리가 완료되었습니다.');
                    	      // 화면에서 버튼 빼앗기!
                        $('.btn.btn-primary.'+index).remove();
                        $('.btn.btn-outline-danger.'+index).remove();
                        $('.pidz.'+index).css('color','gray'); // 글자 회색으로 변경
                    	    }
                    },
                    error: function() {
                        // 환불 처리 실패 시
                        alert('환불 처리에 실패하였습니다. 고객센터에 문의 부탁드립니다');
                    }
                }); // 환불 ajax
            },
            error: function() {
                alert('토큰을 받아오는 데 실패하였습니다.');
            }
        });// 토큰 ajax끝
    }
}//function 끝

function setupAdRefund(id, total,idx,istotal) {
	    // 결제건 주문번호
	    var refundId = id;
	    var amountz = total;
	  	var index = idx;
		
	    
	    var result = confirm('결제를 취소하시겠습니까?');
	    if (result) {
	    	$("body").addClass("loading");
 		// 토큰 받아오는 AJAX 요청
        $.ajax({
            url: './check.ajx',  // 토큰 받아오는 서블릿 URL
            type: 'post',
            success: function(token) {
                // 토큰 받아오기 성공 시 환불 처리 AJAX 요청
                $.ajax({
                    url: './refund.jsn',  // 환불 처리 서블릿 URL
                    type: 'post',
                    data: {
                    	action : "admin",
                        token: token,  
                        mid: refundId,  
                        reason: "결제취소", 
                        amount: amountz,
                        indexnumber : index,
                        checktf : istotal
                    },
                    success: function(data) {
                    	 console.log(data);
                    	 if(data.code == 0) { // 코드가 0일때 성공함
                       		 alert('환불 처리가 완료되었습니다.');
                       		 $("body").removeClass("loading");
                       			 if(istotal){ // 전체취소 일 때 
                       			    location.reload();
                        			location.href="./AdminPurchase.adm";
                       			 }else{ // 결제취소(개별취소)일 때
                        			location.reload();
                       			 }
                    	    }
                    },
                    error: function() {
                        // 환불 처리 실패 시
                        alert('환불 처리에 실패하였습니다. 고객센터에 문의 부탁드립니다');
                    }
                }); // 환불 ajax
            },
            error: function() {
                alert('토큰을 받아오는 데 실패하였습니다.');
            }
        });// 토큰 ajax끝
    }
}//function 끝

