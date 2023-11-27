package com.fooding.admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.admin.db.AdminDAO;
import com.fooding.admin.db.AdminDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class AdminFoodtruckContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t\t M : AdminFoodtruckContentAction_execute() 호출 ");

		// 세션 제어
		HttpSession session = request.getSession();
//		session.setAttribute("id", "admin"); // 이 부분만 삭제하면 됨
		String id = (String) session.getAttribute("id");

		ActionForward forward = new ActionForward();
		if (id == null) {
			forward.setPath("./FtMainAction.man"); // main 주소
			forward.setRedirect(true);
			return forward;
		} else if (id != null && !id.equals("admin")) {
			forward.setPath("./FtMainAction.man"); // main 주소
			forward.setRedirect(true);
			return forward;
		}

		// 한글 처리 (배포 서술자 자동)
		
		// 전달 정보 저장
		int foodtruck_id = Integer.parseInt(request.getParameter("foodtruck_id"));
		String pageNum = request.getParameter("pageNum");
		System.out.println("foodtruck_id : " + foodtruck_id);
		
		// 메서드 호출 (DAO)
		AdminDAO dao = new AdminDAO();
		
		// DTO
		AdminDTO dto = dao.adminFoodtruckContent(foodtruck_id);
		System.out.println("dto : " + dto);
		
		ArrayList<String> wayInfoArr = dao.adminFoodtruckContentWay(foodtruck_id);
		
		// 패턴 3 - request에 담아서 이동
		request.setAttribute("dto", dto);
		request.setAttribute("wayInfoArr", wayInfoArr);
		
		// 페이지 이동
		forward.setPath("./fooding/khr/admin/adminFoodtruckContent.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
