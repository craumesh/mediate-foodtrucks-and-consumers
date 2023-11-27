package com.fooding.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooding.util.Action;
import com.fooding.util.ActionForward;


/**
 *  Controller  
 *  Member 대한 정보 처리를 모두 수행하는 컨트롤러 
	
	http://localhost:8088/MVC7/member
	http://localhost:8088/MVC7/MemberLogin.me
 *  
 */
public class MemberFrontController extends HttpServlet{

	

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
		
		Action action = null;
		ActionForward forward = null;
		
		//---------------------------------------
		// 로그인 
		
		
//		http://localhost:8088/MVC7/MemberJoin.me
		if(command.equals("/MemberLogin.mem")) {
			System.out.println(" C : /MemberLogin.mem 매핑 ");
			System.out.println(" C : 패턴1 - DB처리X, 뷰페이지 이동 ");
			
			forward = new ActionForward();
			forward.setPath("./fooding/member/loginForm.jsp");
			forward.setRedirect(false);
			
			System.out.println(" C : "+forward);			
		}
		
		
		
		else if(command.equals("/MemberLoginAction.mem")) {
			System.out.println(" C : /MemberLoginAction.mem 호출 ");
			System.out.println(" C : 패턴 2 - DB사용O, 페이지이동");
			
			// MemberLoginAction() 객체생성
			action = new MemberLoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 처리결과를 가지고 이동
		}
		
		//-------------------------------------------------------------
		// 로그인 메인 페이지
		
		else if(command.equals("/Main.mem")) {
			System.out.println(" C : /Main.mem 호출");
			System.out.println(" C : 패턴 1 - DB사용X, 페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./fooding/member/main.jsp");
			forward.setRedirect(false);
		}
		
		
		//-----------------------------------------------------------------------
		// 회원가입
		
//		http://localhost:8088/team2/MemberJoin.me
		else if(command.equals("/MemberJoin.mem")) {
			System.out.println(" C : /MemberJoin.mem 매핑 ");
			System.out.println(" C : 패턴1 - DB처리X, 뷰페이지 이동 ");
			
			forward = new ActionForward();
			forward.setPath("./fooding/member/insertForm.jsp");
			forward.setRedirect(false);
			
			System.out.println(" C : "+forward);			
		}
		
		
		else if(command.equals("/MemberJoinAction.mem")) {
			System.out.println(" C : /MemberJoinAction.mem 매핑" );
			System.out.println(" C : 패턴2 - DB처리O, 페이지 이동");
			
			// MemberJoinAction 객체생성
			action = new MemberJoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}

		
		//--------------------------------------------------------------------------
		// ID 중복확인
		else if(command.equals("/MemberConfirmIdAction.mem")) {
			System.out.println("C : /MemberConfirmId.mem 호출");
			
			action = new MemberConfirmIdAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		//--------------------------------------------------------------------------
		// ID 찾기
		else if(command.equals("/MemberIdFind.mem")) {
			System.out.println("C : /MemberIDFind.mem 매핑");
			System.out.println(" C : 패턴1 - DB처리X, 뷰페이지 이동 ");
			
			forward = new ActionForward();
			forward.setPath("./fooding/member/idFind.jsp");
			forward.setRedirect(false);
			
			System.out.println(" C : "+forward);		
		}
		
		
		else if(command.equals("/MemberIdFindAction.mem")) {
			System.out.println(" C : /MemberJoinAction.mem 매핑" );
			System.out.println(" C : 패턴2 - DB처리O, 페이지 이동");
			
			// MemberIdFindAction 객체생성
			action = new MemberIdFindAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
		else if(command.equals("/FindId.mem")) {
			System.out.println("C : /FindId.mem 매핑");
			System.out.println(" C : 패턴1 - DB처리X, 뷰페이지 이동 ");
			
			forward = new ActionForward();
			forward.setPath("./fooding/member/findIdResult.jsp");
			forward.setRedirect(false);
			
			System.out.println(" C : "+forward);	
			
		}
			

		//--------------------------------------------------------------------------
		// PW 찾기
		else if(command.equals("/MemberPwFind.mem")) {
			System.out.println("C : /MemberPwFind.mem 매핑");
			System.out.println(" C : 패턴1 - DB처리X, 뷰페이지 이동 ");
			
			forward = new ActionForward();
			forward.setPath("./fooding/member/pwFind.jsp");
			forward.setRedirect(false);
			
			System.out.println(" C : "+forward);		
		}
	
		
		else if(command.equals("/MemberPwFindAction.mem")) {
			System.out.println(" C : /MemberPwFindAction.mem 호출" );
			System.out.println(" C : 패턴2 - DB처리O, 페이지 이동");
			
			// MemberIdFindAction 객체생성
			action = new MemberPwFindAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
		
		else if(command.equals("/FindPw.mem")) {
			System.out.println("C : /FindPw.mem 매핑");
			System.out.println(" C : 패턴1 - DB처리X, 뷰페이지 이동 ");
			
			forward = new ActionForward();
			forward.setPath("./fooding/member/findPwResult.jsp");
			forward.setRedirect(false);
			
			System.out.println(" C : "+forward);	
			
		}
		
		//--------------------------------------------------------------------------
		// PW 찾기
		
		
		
		
		
		
		
		
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
