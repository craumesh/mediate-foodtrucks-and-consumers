package com.fooding.profile.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.profile.db.ProfileDAO;
import com.fooding.profile.db.ProfileDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;
import com.fooding.util.JSMoveFunction;

public class ProfileUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t M : ProfileUpdateProAction_execute() 호출 ");
		
		// 세션 제어
		HttpSession session = request.getSession();
//		session.setAttribute("id", "admin");  // 이 부분만 삭제하면 됨
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
		dto.setId(id);
		if(request.getParameter("pw3") != "") { // 비밀번호 변경 폼에 올바르게 작성이 되어있을 시
			dto.setPw(request.getParameter("pw3"));			
		}else {
			dto.setPw(request.getParameter("pw")); // 변경 안 할 때
		}
		dto.setName(request.getParameter("name"));
		dto.setPhone(request.getParameter("phone"));
		String phone = request.getParameter("phone");
		dto.setEmail(request.getParameter("email"));
		String email = request.getParameter("email");
		int member_id = Integer.parseInt(request.getParameter("member_id"));
		
//		if(phone.length() != 13) {
//			JSMoveFunction.alertHistory(response, "올바른 전화번호 형태가 아닙니다. 이전 페이지로 돌아갑니다.");
//			return null;
//		}
//		
//		if(email.indexOf("@") == -1 || email.indexOf("@") == 0 
//				|| email.indexOf(".") == -1 || email.indexOf(".") < 3 
//				|| email.indexOf("@") >= email.indexOf(".") 
//				|| email.indexOf("@") != email.lastIndexOf("@") 
//				|| email.indexOf(".") != email.lastIndexOf(".")) {
//			JSMoveFunction.alertHistory(response, "올바른 이메일 형태가 아닙니다. 이전 페이지로 돌아갑니다.");
//			return null;
//		}
		
		// 수정 메서드 호출 (DAO)
		ProfileDAO dao = new ProfileDAO();
		int duplicatePhone = dao.profilePhoneDuplicate(member_id, phone);
		if(duplicatePhone != 0) {
			JSMoveFunction.alertHistory(response, "중복된 전화번호입니다. 이전 페이지로 돌아갑니다.");
			return null;
		}
		
		int duplicateEmail = dao.profileEmailDuplicate(member_id, email);
		if(duplicateEmail != 0) {
			JSMoveFunction.alertHistory(response, "중복된 이메일입니다. 이전 페이지로 돌아갑니다.");
			return null;
		}
		
		
		
		int result = dao.profileUpdate(dto);
		
		// 페이지 이동
		if(result == 1) {
			JSMoveFunction.alertLocation(response, "회원 정보가 수정되었습니다.", "./ProfileMemberInfo.pro");
			return null;
		}else {
			JSMoveFunction.alertHistory(response, "수정이 실패했습니다.");
		}
		return null;
	}
}
