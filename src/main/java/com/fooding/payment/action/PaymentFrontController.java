package com.fooding.payment.action;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooding.orderDetail.action.orderDetailAction;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;


public class PaymentFrontController extends HttpServlet{

	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		System.out.println("\n -----------------1. 가상주소 계산 시작 --------------------");
		String requestURI = request.getRequestURI();
		String CTXPath = request.getContextPath();
		String command = requestURI.substring(CTXPath.length());
		System.out.println(" ᕕ( ᐛ )ᕗ : 이동할 가상주소 : "+command);		
		
		System.out.println("\n -----------------2. 가상주소 매핑 시작 --------------------");
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/Payment.pay")) { // 디비정보 가져와서 출력페이지 내용 보여야함
			System.out.println(" ᕕ( ᐛ )ᕗ : /Payment.pay 매핑\n " );
		
			action = new PaymentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else if(command.equals("/PaymentResult.pay")) { // 디비에 정보를 저장하고 이동해야함
			System.out.println(" ᕕ( ᐛ )ᕗ : /PaymentResult.pay 호출 \n ");
		
			action = new PaymentAfterAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/OrderDetails.pay")) { // PaymentAfterAction() 에서 정보를 받고 디비에서 정보를 꺼내와야함
			System.out.println(" ᕕ( ᐛ )ᕗ : /OrderDetails.pay 매핑\n " );
			
			action = new orderDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/Verification.pay")) { // PaymentAfterAction() 에서 정보를 받고 디비에서 정보를 꺼내와야함
			System.out.println(" ᕕ( ᐛ )ᕗ : /verification.pay 매핑\n " );
			
			action = new verificationAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	
		System.out.println("\n -----------------3. 가상주소 이동 시작 --------------------");
		
		if(forward != null) { //이동정보가 존재할때 
			
			if(forward.isRedirect()) { // true
				System.out.println(" C : "+forward.getPath()+"로, 이동방식 : sendRedirect() ");
				
				response.sendRedirect(forward.getPath());
			}else { // false
				System.out.println(" C : "+forward.getPath()+"로, 이동방식 : forward() ");
				RequestDispatcher dis 
				           = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);				
			}
			
		}		
		
	}
	
	
	
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 System.out.println("\n\n C : PaymentFrontController_doGet() 호출 ");
		 doProcess(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n\n C : PaymentFrontController_doPost() 호출 ");
		 doProcess(request, response);

	}
	

}// controller end
