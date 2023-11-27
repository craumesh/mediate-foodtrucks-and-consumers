package com.fooding.ftk.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class FtkFrontController extends HttpServlet{

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		/*******************************1. 가상주소 계산 시작 *******************************/
		System.out.println(" C : 1. 가상주소 계산 시작--------------- ");
		String requestURI = request.getRequestURI();
		System.out.println(" requestURI : "+requestURI );
		String CTXPath = request.getContextPath();
		System.out.println(" CTXPath : "+CTXPath );
		String command = requestURI.substring(CTXPath.length());
		System.out.println(" command : "+command );
		System.out.println(" C : 1. 가상주소 계산 끝----------------- ");
		/*******************************1. 가상주소 계산 끝 *******************************/
		
		
		/*******************************2. 가상주소 매핑 시작 *******************************/
		System.out.println("\n\n C : 2. 가상주소 매핑 시작------------------");
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/FtkAction.ftk")) {
			System.out.println(" C : /FtkAction.ftk 매핑" );
			System.out.println(" C : 패턴2 - DB처리O, 페이지 이동");
			
			action = new FtkAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		else if(command.equals("/FtkList.ftk")) {
			System.out.println(" C : /FtkList.ftk 호출");
			System.out.println(" C : 패턴 3 - DB사용O, 페이지 출력");
			
			// FtkListAction() 객체 생성
			action = new FtkListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("\n\n C : 2. 가상주소 매핑 끝------------------");
		/*******************************2. 가상주소 매핑 시작 *******************************/

		
		/***********************3. 가상주소 이동 시작**************************/
		System.out.println("\n\n C : 3. 가상주소 이동 시작------------------");
		if(forward != null) {
			if(forward.isRedirect()) { // true
				System.out.println("\t C : 이동주소 : "+forward.getPath());
				System.out.println("\t C : 이동방법 : sendRedirect() 방식 ");
				response.sendRedirect(forward.getPath());
			}else { // false
				System.out.println("\t C : 이동주소 : "+forward.getPath());
				System.out.println("\t C : 이동방법 : forward() 방식 ");
				RequestDispatcher dis = 
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}			
		}	
		
		System.out.println(" C : 3. 가상주소 이동 끝------------------");
		/***********************3. 가상주소 이동 끝**************************/
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 System.out.println("\n\n C :FtFrontMainController_doGet() 호출 ");
		 doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : FtFrontMainController_doPost() 호출 ");
		doProcess(request, response);
	}
	
}