package com.fooding.profile.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.profile.db.ProfileDAO;
import com.fooding.profile.db.ProfileDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class ProfileUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t M : ProfileUpdateAction_execute() 호출 ");
		
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
		
		// 정보 메서드 호출 (DAO)
		ProfileDAO dao = new ProfileDAO();
		
		// 전달 정보 저장 (DTO)
		ProfileDTO dto = dao.profileInfo(id);
		
		// request가 담아서 이동
		request.setAttribute("dto", dto);
		
		// 페이지 이동
		forward.setPath("./fooding/khr/profile/updateForm.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
