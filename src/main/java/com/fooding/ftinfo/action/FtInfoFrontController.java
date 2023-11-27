package com.fooding.ftinfo.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooding.util.Action;
import com.fooding.util.ActionForward;
import com.fooding.ftinfo.action.FtInfoMainAction;

public class FtInfoFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : FtInfoFrontController_doProcess()");
		
		/*************************************1. 가상주소 계산 시작*******************************************/
		System.out.println("\n\n C : 1. 가상주소 계산 시작================================");
		String requestURI = request.getRequestURI();
		System.out.println(" requestURI : " + requestURI);
		String CTXPath = request.getContextPath();
		System.out.println(" CTXPath : " + CTXPath);
		String command = requestURI.substring(CTXPath.length());
		System.out.println(" command : " + command);
		System.out.println(" C : 1. 가상주소 계산 종료================================");
		/*************************************1. 가상주소 계산 종료*******************************************/
		
		
		/*************************************2. 가상주소 매핑 시작*******************************************/
		System.out.println("\n\n C : 2. 가상주소 매핑 시작================================");
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/FtInfoMainAction.fti")) {
			System.out.println(" C : /FtInfoMainAction.fti 호출");
			System.out.println(" C : 패턴 3 - DB사용O, 페이지 출력");
			action = new FtInfoMainAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/FTInfoMainProAction.fti")) {
			System.out.println(" C : /FtInfoMainProAction.fti 호출");
			System.out.println(" C : 패턴 3 - DB사용O, 페이지 출력");
			action = new FtInfoMainProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/FTInfoSendAction.fti")) {
			System.out.println(" C : /FTInfoSendAction.fti 호출");
			System.out.println(" C : 패턴 2 - DB사용O, 페이지 이동");
			action = new FTInfoSendAction();
			try {
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
				}
		} else if(command.equals("/FTInfoSendProAction.fti")) {
			System.out.println(" C : /FTInfoSendProAction.fti 호출");
			System.out.println(" C : 패턴 1 - DB사용X, 페이지 이동");
			forward = new ActionForward();
			forward.setPath("./fooding/ftInfo/test.jsp");
			forward.setRedirect(false);
		}			
		
		System.out.println(" C : 2. 가상주소 매핑 종료================================");
		/*************************************2. 가상주소 매핑 종료*******************************************/
		
		
		/*************************************3. 가상주소 이동 시작*******************************************/
		System.out.println("\n\n C : 3. 가상주소 이동 시작================================");
		if(forward != null) {
			if(forward.isRedirect()) {
				System.out.println(" C : 이동주소 - " + forward.getPath());
				System.out.println(" C : 이동방법 - sendRedirect() 방식");
				response.sendRedirect(forward.getPath());
			} else {
				System.out.println(" C : 이동주소 - " + forward.getPath());
				System.out.println(" C : 이동방법 - forward() 방식");
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
		System.out.println(" C : 3. 가상주소 이동 종료================================");
		/*************************************3. 가상주소 이동 종료*******************************************/
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}

}
