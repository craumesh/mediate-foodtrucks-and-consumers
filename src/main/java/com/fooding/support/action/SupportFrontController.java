package com.fooding.support.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooding.util.Action;
import com.fooding.util.ActionForward;

@WebServlet("*.sup")
public class SupportFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 가상 주소 계산 시작
		System.out.println("\n 1. 가상 주소 계산 시작 ");
		String requestURI = request.getRequestURI();
		System.out.println("\t\t requestURI : " + requestURI);
		String CTXPath = request.getContextPath();
		System.out.println("\t\t CTXPath : " + CTXPath);

		String command = requestURI.substring(CTXPath.length());
		System.out.println("\t\t command : " + command);
		System.out.println(" 1. 가상 주소 계산 종료 ");
		// 1. 가상 주소 계산 종료

		// 2. 가상 주소 매핑 시작
		System.out.println("\n 2. 가상 주소 매핑 시작 ");
		ActionForward forward = null;
		Action action = null;
		if(command.equals("/Support.sup")) {
			System.out.println("\t\t C : /Support.sup 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");	
			action = new SupportAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/SupportFaqForm.sup")) {
			System.out.println("\t\t C : /SupportFaqForm.sup 호출 ");
			System.out.println("\t\t C : 패턴 1 - DB [데이터] 처리 X, 페이지 이동 ");	
			forward = new ActionForward();
			forward.setPath("./fooding/khr/support/faqForm.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/SupportFaqWrite.sup")) {
			System.out.println("\t\t C : /SupportFaqWrite.sup 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리 O, 페이지 이동 ");	
			action = new SupportFaqWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/SupportNoticeForm.sup")) {
			System.out.println("\t\t C : /SupportNoticeForm.sup 호출 ");
			System.out.println("\t\t C : 패턴 1 - DB [데이터] 처리 X, 페이지 이동 ");	
			forward = new ActionForward();
			forward.setPath("./fooding/khr/support/noticeForm.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/SupportNoticeWrite.sup")) {
			System.out.println("\t\t C : /SupportNoticeWrite.sup 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리 O, 페이지 이동 ");
			action = new SupportNoticeWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/SupportNoticeList.sup")) {
			System.out.println("\t\t C : /SupportNoticeList.sup 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리 O, 페이지 출력 ");
			action = new SupportNoticeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/SupportFaqList.sup")) {
			System.out.println("\t\t C : /SupportFaqList.sup 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리 O, 페이지 출력 ");
			action = new SupportFaqListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/SupportNoticeContent.sup")) {
			System.out.println("\t\t C : /SupportNoticeContent.sup 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리 O, 페이지 출력 ");
			action = new SupportNoticeContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/SupportFaqContent.sup")) {
			System.out.println("\t\t C : /SupporFaqContent.sup 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리 O, 페이지 출력 ");
			action = new SupportFaqContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/SupportNoticeUpdate.sup")) {
			System.out.println("\t\t C : /SupporFaqContent.sup 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리 O, 페이지 출력 ");
			action = new SupportNoticeUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/SupportNoticeUpdatePro.sup")) {
			System.out.println("\t\t C : /SupportNoticeUpdatePro.sup 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리 O, 페이지 이동 ");
			action = new SupportNoticeUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/SupportFaqUpdate.sup")) {
			System.out.println("\t\t C : /SupportFaqUpdate.sup 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리 O, 페이지 출력 ");
			action = new SupportFaqUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/SupportFaqUpdatePro.sup")) {
			System.out.println("\t\t C : /SupportFaqUpdatePro.sup 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리 O, 페이지 출력 ");
			action = new SupportFaqUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/SupportNoticeDelete.sup")) {
			System.out.println("\t\t C : /SupportNoticeDelete.sup 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리 O, 페이지 이동 ");
			action = new SupportNoticeDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/SupportFaqDelete.sup")) {
			System.out.println("\t\t C : /SupportFaqDelete.sup 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리 O, 페이지 이동 ");
			action = new SupportFaqDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/SupportEventList.sup")) {
			System.out.println("\t\t C : /SupportEventDelete.sup 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리 O, 페이지 출력 ");
			action = new SupportEventListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/SupportEventContent.sup")) {
			System.out.println("\t\t C : /SupportEventContent.sup 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리 O, 페이지 출력 ");	
			action = new SupportEventContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/SupportEventForm.sup")) {
			System.out.println("\t\t C : /SupportEventForm.sup 호출 ");
			System.out.println("\t\t C : 패턴 1 - DB [데이터] 처리 X, 뷰 페이지 이동 ");				
			
			forward = new ActionForward();
			forward.setPath("./fooding/khr/support/eventForm.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/SupportEventDelete.sup")) {
			System.out.println("\t\t C : /SupportEventDelete.sup 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리 O, 뷰 페이지 이동 ");	
			action = new SupportEventDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/SupportEventUpdate.sup")) {
			System.out.println("\t\t C : /SupportEventUpdate.sup 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리 O, 페이지 출력 ");	
			action = new SupportEventUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/SupportEventUpdatePro.sup")) {
			System.out.println("\t\t C : /SupportEventUpdatePro.sup 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리 O, 페이지 이동 ");	
			action = new SupportEventUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/SupportEventWrite.sup")) {
			System.out.println("\t\t C : /SupportEventWrite.sup 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리 O, 페이지 이동 ");	
			action = new SupportEventWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println(" 2. 가상 주소 매핑 종료 ");
		// 2. 가상 주소 매핑 종료

		// 3. 가상 주소 이동 시작
		System.out.println("\n 3. 가상 주소 이동 시작 ");
		if (forward != null) {
			if (forward.isRedirect()) { // true : response.sendRedirect();
				System.out.println("\t\t C : response.sendRedirect() 방식 이동 ");
				response.sendRedirect(forward.getPath());

			} else { // false : forward();
				System.out.println("\t\t C : dis.forward() 방식 이동 ");
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
		System.out.println(" 3. 가상 주소 이동 종료 ");
		// 3. 가상 주소 이동 종료
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
}
