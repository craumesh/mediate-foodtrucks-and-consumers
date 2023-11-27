package com.fooding.payment.action;

import java.util.ArrayList;
import java.util.Iterator;

import javax.management.ConstructorParameters;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.payment.db.PaymentDAO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class PaymentAfterAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 전달정보 저장하기
		//장바구니 번호
		String token = request.getParameter("token");
		String[] arr = request.getParameterValues("cart_id"); 
		
        // 주문번호 purchaseid
        String purchase_id = request.getParameter("purchase_id");
        int purchaseid=Integer.parseInt(purchase_id);
		
		// 로그인 세션 제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id==null) {
			forward.setPath("./MemberLogin.mem");
			forward.setRedirect(true);
			return forward;
		}
		
		// 결제처리? ( 일단 하나의 DTO에 모든 정보를 때려박은거)
		// 0. arrylist로 만들기
		PaymentDAO pdao = new PaymentDAO();
		ArrayList cart_id = pdao.stringToArrayList(arr); // 장바구니 번호 int로 변경
		
		// 장바구니 번호로 장바구니 정보 purchaseList로 만들기
		ArrayList cartList =  pdao.getPurchaseList(cart_id);
		
		//1. insert 메서드 호출하기
		pdao.insertPurchase(cartList, purchaseid);

		// 2. 장바구니 테이블 데이터 지우기 => 사용한 장바구니 데이터 (장바구니 번호 이용)
		pdao.deleteMember(cart_id);
		
		// 디비에 저장된 합계값
		int Sum = pdao.makeCheckSum(purchaseid);
		// 디비에 저장된 
		// 모든 처리 후 결제정보확인 페이지로 이동하기
		
		request.setAttribute("Sum", Sum);
		request.setAttribute("token", token);
		request.setAttribute("purchaseid",purchaseid);
		
		forward = new ActionForward();
		
//		forward.setPath("/Verification.pay"); // 결제검증 주소창
//		forward.setRedirect(false); // 결제검증하러가잣!
		forward.setPath("./OrderDetails.pay"); //결제검증을 할 필요가 없어졋음
		forward.setRedirect(true);
		
		return forward;
		
	
	}
}
