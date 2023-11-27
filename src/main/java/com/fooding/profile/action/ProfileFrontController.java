package com.fooding.profile.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooding.admin.action.AdminFoodtruckListAction;
import com.fooding.admin.action.AdminFoodtruckWayDelAction;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

//@WebServlet("*.pro")
public class ProfileFrontController extends HttpServlet {

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
		if(command.equals("/Profile.pro")) {
			System.out.println("\t\t C : /Profile.pro 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");
			action = new ProfileAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfileUpdate.pro")) {
			System.out.println("\t\t C : /ProfileUpdate.pro 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");
			action = new ProfileUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfileUpdatePro.pro")) {
			System.out.println("\t\t C : /ProfileUpdatePro.pro 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리, 페이지 이동 ");
			action = new ProfileUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfileDelete.pro")) {
			System.out.println("\t\t C : /ProfileDelete.pro 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] O, 페이지 출력 ");
			action = new ProfileDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/ProfileDeleteAction.pro")) {
			System.out.println("\t\t C : /ProfileDeleteAction.pro 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리, 페이지 이동 ");
			action = new ProfileDeleteActionPro();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfileMemberInfo.pro")) {
			System.out.println("\t\t C : /ProfileMemberInfo.pro 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");
			action = new ProfileMemberInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(command.equals("/ProfilePurchaseList.pro")) {
			System.out.println("\t\t C : /ProfilePurchaseList.pro 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");
			action = new ProfilePurchaseListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfilePurchaseDetail.pro")) {
			System.out.println("\t\t C : /ProfilePurchaseDetail.pro 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");	
			action = new ProfilePurchaseDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfileReviewList.pro")) {
			System.out.println("\t\t C : /ProfileReviewList.pro 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");	
			action = new ProfileReviewListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfileReviewWrite.pro")) {
			System.out.println("\t\t C : /ProfileReviewWrite.pro 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리 O, 페이지 출력 ");
			action = new ProfileReviewWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		else if(command.equals("/ProfileReviewWriteAction.pro")) {
			System.out.println("\t\t C : /ProfileReviewWriteAction.pro 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리 O, 페이지 이동 ");	
			action = new ProfileReviewWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfileReviewContent.pro")){
			System.out.println("\t\t C : /ProfileReviewContent.pro 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리 O, 페이지 이동 ");	
			action = new ProfileReviewContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfileReviewUpdate.pro")) {
			System.out.println("\t\t C : /ProfileReviewUpdate.pro 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리 O, 페이지 이동 ");	
			action = new ProfileReviewUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfilePurchaseDetail2.pro")) {
			System.out.println("\t\t C : /ProfilePurchaseDetail2.pro 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");	
			action = new ProfilePurchaseDetailAction2();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfileReviewUpdatePro.pro")) {
			System.out.println("\t\t C : /ProfileReviewUpdatePro.pro 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리, 페이지 이동 ");
			action = new ProfileReviewUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfileReviewDelete.pro")) {
			System.out.println("\t\t C : /ProfileReviewDelete.pro 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리, 페이지 이동 ");
			action = new ProfileReviewDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfileProductAdd.pro")) {
			System.out.println("\t\t C : /ProfileProductAdd.pro 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");
			action = new ProfileProductAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		else if(command.equals("/ProfileProductAddPro.pro")) {
			System.out.println("\t\t C : /ProfileProductAddPro.pro 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리, 페이지 이동 ");
			action = new ProfileProductAddProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfileProductList.pro")) {
			System.out.println("\t\t C : /ProfileProductList.pro 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");
			action = new ProfileProductListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfileProductDelete.pro")) {
			System.out.println("\t\t C : /ProfileProductDelete.pro 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리, 페이지 이동 ");		
			action = new ProfileProductDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfileLogout.pro")) {
			System.out.println("\t\t C : /ProfileLogout.pro 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리, 페이지 이동 ");		
			action = new ProfileLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfileDuplicatePhone.pro")) {
			System.out.println("\t\t C : /ProfileDuplicatePhone.pro 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");
			action = new ProfileDuplicatePhoneAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfileDuplicateEmail.pro")) {
			System.out.println("\t\t C : /ProfileDuplicateEmail.pro 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");	
			action = new ProfileDuplicateEmailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfileFoodtruckPurchaseList.pro")) {
			System.out.println("\t\t C : /ProfileFoodtruckPurchaseList.pro 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");		
			action = new ProfileFoodtruckPurchaseListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfileFoodtruckList.pro")) {
			System.out.println("\t\t C : /ProfileFoodtruckPurchaseList.pro 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");	
			action = new ProfileFoodtruckListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfileFoodtruckWayAdd.pro")) {
			System.out.println("\t\t C : /ProfileFoodtruckWayAdd.pro 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");	
			action = new ProfileFoodtruckWayAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfileFoodtruckWayAddPro.pro")) {
			System.out.println("\t\t C : /ProfileFoodtruckWayAddPro.pro 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리, 페이지 이동 ");	
			action = new ProfileFoodtruckWayAddProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfileFoodtruckContent.pro")) {
			System.out.println("\t\t C : /ProfileFoodtruckContent.pro 호출 ");
			System.out.println("\t\t C : 패턴 3 - DB [데이터] 처리, 페이지 출력 ");		
			action = new ProfileFoodtruckContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProfileFoodtruckWayDel.pro")) {
			System.out.println("\t\t C : /ProfileFoodtruckContent.pro 호출 ");
			System.out.println("\t\t C : 패턴 2 - DB [데이터] 처리, 페이지 이동 ");	
			action = new AdminFoodtruckWayDelAction();
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
