package com.fooding.admin.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooding.util.Action;
import com.fooding.util.ActionForward;

@WebServlet("*.adm")
public class AdminFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		if(command.equals("/Admin.adm")) {
			System.out.println("\t\t C : /Admin.adm 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] O, 페이지 출력 ");
			action = new AdminAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminFoodtruckAdd.adm")) {
			System.out.println("\t\t C : /AdminFoodtruckAdd.adm 호출 ");
			System.out.println("\t\t C : 패턴 1 - DB [데이터] X, 뷰 페이지 이동 ");
			forward = new ActionForward();
			forward.setPath("./fooding/khr/admin/adminFoodtruckForm.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/AdminFoodtruckAddPro.adm")) {
			System.out.println("\t\t C : /AdminFoodtruckAddPro.adm 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리, 페이지 이동 ");
			action = new AdminFoodtruckAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminFoodtruckWayAdd.adm")) {
			System.out.println("\t\t C : /AdminFoodtruckWayAdd.adm 호출 ");
			System.out.println("\t\t C : 패턴 1 - DB [데이터] X, 뷰 페이지 이동 ");
			forward = new ActionForward();
			forward.setPath("./fooding/khr/admin/adminFoodtruckWayForm.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/AdminFoodtruckWayAddAction.adm")) {
			System.out.println("\t\t C : /AdminFoodtruckWayAddAction.adm 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리, 페이지 이동 ");
			action = new AdminFoodtruckWayAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminFoodtruckWayAddProAction.adm")) {
			System.out.println("\t\t C : /AdminFoodtruckWayAddProAction.adm 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리, 페이지 이동 ");
			action = new AdminFoodtruckWayAddProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminFoodtruckWayDelAction.adm")) {
			System.out.println("\t\t C : /AdminFoodtruckWayDelAction.adm 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리, 페이지 이동 ");
			action = new AdminFoodtruckWayDelAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminProductAdd.adm")) {
			System.out.println("\t\t C : /AdminProductAdd.adm 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");
			action = new AdminProductAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminProductAddPro.adm")) {
			System.out.println("\t\t C : /AdminProductAddPro.adm 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리, 페이지 이동 ");
			action = new AdminProductAddProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminFoodtruck.adm")) {
			System.out.println("\t\t C : /AdminFoodtruck.adm 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");
			action = new AdminFoodtruckListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(command.equals("/AdminProduct.adm")) {
			System.out.println("\t\t C : /AdminProduct.adm 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");
			action = new AdminProductListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(command.equals("/AdminMember.adm")) {
			System.out.println("\t\t C : /AdminMember.adm 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");
			action = new AdminMemberListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminMemberDelete.adm")) {
			System.out.println("\t\t C : /AdminMemberDelete.adm 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리, 페이지 이동 ");		
			action = new AdminMemberDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminFoodtruckContent.adm")) {
			System.out.println("\t\t C : /AdminFoodtruckContent.adm 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");
			action = new AdminFoodtruckContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminProductDelete.adm")) {
			System.out.println("\t\t C : /AdminProductDelete.adm 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리, 페이지 이동 ");
			action = new AdminProductDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminFoodtruckDelete.adm")) {
			System.out.println("\t\t C : /AdminFoodtruckDelete.adm 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리, 페이지 이동 ");
			action = new AdminFoodtruckDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminPurchase.adm")) {
			System.out.println("\t\t C : /AdminPurchase.adm 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");
			action = new AdminPurchaseListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminPurchaseDetail.adm")) {
			System.out.println("\t\t C : /AdminPurchaseDetail.adm 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");	
			action = new AdminPurchaseDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminFoodtruckUpdate.adm")) {
			System.out.println("\t\t C : /AdminFoodtruckUpdate.adm 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");	
			action = new AdminFoodtruckUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminFoodtruckUpdatePro.adm")) {
			System.out.println("\t\t C : /AdminFoodtruckUpdatePro.adm 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리, 페이지 이동 ");
			action = new AdminFoodtruckUpdateProAction();
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
		if(forward != null) {
			if(forward.isRedirect()) { // true : response.sendRedirect();
				System.out.println("\t\t C : response.sendRedirect() 방식 이동 ");
				response.sendRedirect(forward.getPath());
				
			}else { // false : forward();
				System.out.println("\t\t C : dis.forward() 방식 이동 ");
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
		System.out.println(" 3. 가상 주소 이동 종료 ");
		// 3. 가상 주소 이동 종료
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
