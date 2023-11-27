package com.fooding.profile.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooding.profile.db.ProfileDAO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class ProfileDuplicatePhoneAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t M : ProfileDuplicatePhone_execute() 호출 ");
		
		int member_id = Integer.parseInt(request.getParameter("member_id"));
		System.out.println("member_id : " + member_id);
		String phone = request.getParameter("phone");
		System.out.println("phone : " + phone);
		
		if(phone.length() != 13) {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print("올바른 전화번호 형식이 아닙니다.");
			return null;
		}
		
		ProfileDAO dao = new ProfileDAO();
		int result = dao.profilePhoneDuplicate(member_id, phone);
		
		String myPhone = dao.profileMyPhone(member_id);
		if(myPhone.equals(phone)) {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print("현재 등록하신 번호입니다. 사용 가능합니다.");
			return null;
		}
		
		if(result != 0) {
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print("중복된 전화번호입니다.");
		return null;
		}else {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print("사용 가능한 전화번호입니다.");			
		}
		return null;
	}

}
