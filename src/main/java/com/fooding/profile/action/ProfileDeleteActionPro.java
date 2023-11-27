package com.fooding.profile.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.profile.db.ProfileDAO;
import com.fooding.profile.db.ProfileDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;
import com.fooding.util.JSMoveFunction;

public class ProfileDeleteActionPro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t M : ProfileDeleteActionPro_execute() 호출 ");
		
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
		
		// 전달 정보 저장 (DTO)
		ProfileDTO dto = new ProfileDTO();
		int member_id = Integer.parseInt(request.getParameter("member_id"));
		String pw = request.getParameter("pw");
		dto.setReason(request.getParameter("reason"));
		
		// 메서드 호출 (DAO)
		ProfileDAO dao = new ProfileDAO();
		if(!pw.equals(dao.profilePassword(member_id))) {
			JSMoveFunction.alertHistory(response, "비밀번호가 일치하지 않습니다. 회원 탈퇴가 실패했습니다.");
			return null;
		}
		
//		int result = dao.profileDelete(dto);
		
		int result = dao.profileChangeActivation(member_id, dto);
		System.out.println("result : " + result);
		
		// 페이지 이동
		if(result == 1) {
			session.invalidate();
			JSMoveFunction.alertLocation(response, "회원 탈퇴가 성공했습니다. 메인 페이지로 이동합니다.", "./FtMainAction.man");
			return null;
		}else {
			JSMoveFunction.alertHistory(response, "회원 탈퇴가 실패했습니다.");
		}
		return null;
	}

}
