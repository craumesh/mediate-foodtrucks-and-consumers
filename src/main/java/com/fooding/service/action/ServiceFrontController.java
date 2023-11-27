package com.fooding.service.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooding.util.Action;
import com.fooding.util.ActionForward;

@WebServlet("*.ser")
public class ServiceFrontController extends HttpServlet{

	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : MemberFrontController_doProcess() 호출 ");
		System.out.println(" C : GET/POST방식 모두 처리 ");
		
		System.out.println("\n -----------------1. 가상주소 계산 시작 --------------------");
		//System.out.println(request.getRequestURL());
		//System.out.println(request.getRequestURI()); // URI = URL - (프로토콜 - IP - 포트번호)
		
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI : "+ requestURI);
		String CTXPath = request.getContextPath();
		System.out.println(" C : CTXPath : "+CTXPath);
		String command = requestURI.substring(CTXPath.length());
		System.out.println(" C : command : "+command);		
		
		System.out.println(" -----------------1. 가상주소 계산 끝 --------------------");
		
		
		System.out.println("\n -----------------2. 가상주소 매핑 시작 --------------------");
		
		System.out.println("\n\n C : 2. 가상주소 매핑 시작------------------");
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/ServiceInfo.ser")) {
			System.out.println(" C :/ServiceInfo.ser 호출 ");
			System.out.println("\t C : 패턴 1 - DB사용X, 페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./fooding/service/service.jsp");
			forward.setRedirect(false);					
		}		
		
		System.out.println(" -----------------2. 가상주소 매핑 끝 --------------------");
		
		
		
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
		System.out.println(" -----------------3. 가상주소 이동 끝 --------------------");
		
	}//doProcess
	
	// alt shift s + v
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 System.out.println("\n\n C : MemberFrontController_doGet() 호출 ");
		 doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : MemberFrontController_doPost() 호출 ");
		doProcess(request, response);
	}

}// MemberFrontController