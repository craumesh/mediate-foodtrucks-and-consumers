package com.fooding.admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.admin.db.AdminDAO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class AdminProductAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t\t M : AdminProductAddAction_execute() 호출 ");
		
		// 세션 제어
		HttpSession session = request.getSession();
//		session.setAttribute("id", "admin"); // 이 부분만 삭제하면 됨
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./FtMainAction.man"); // main 주소
			forward.setRedirect(true);
			return forward;
		}else if(id != null && !id.equals("admin")) {
			forward.setPath("./FtMainAction.man"); // main 주소
			forward.setRedirect(true);
			return forward;
		}
		
		// 한글 처리 (배포 서술자 자동)
		
		// 푸드트럭 목록 메서드 호출 (DAO)
		AdminDAO dao = new AdminDAO();
		ArrayList list =  dao.adminFoodtruckList();
		
		// 패턴 3 - request로 넘김
		request.setAttribute("list", list);
		
		// 페이지 이동
		forward.setPath("./fooding/khr/admin/adminProductForm.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
