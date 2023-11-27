package com.fooding.profile.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.admin.db.AdminDTO;
import com.fooding.profile.db.ProfileDAO;
import com.fooding.profile.db.ProfileDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class ProfileProductAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t M : ProfileProductAddAction_execute() 호출 ");
		
		// 세션 제어
		HttpSession session = request.getSession();
//		session.setAttribute("id", "admin"); // 이 부분만 삭제하면 됨
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./FtMainAction.man"); // main 주소
			forward.setRedirect(true);
			return forward;
		}
		
		// 한글 처리 (배포 서술자 자동)
		
		// 전달 정보 저장
		int member_id = Integer.parseInt(request.getParameter("member_id"));
		
		// 푸드트럭 정보 조회
		ProfileDAO dao = new ProfileDAO();
		ArrayList list = dao.profileFoodtruckList(member_id);
		
		// 패턴 3 - request로 정보 전달
		request.setAttribute("list", list);
		System.out.println(list);
		
		// 페이지 이동
		forward.setPath("./fooding/khr/profile/profileProductForm.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
