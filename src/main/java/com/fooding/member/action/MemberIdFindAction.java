package com.fooding.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.member.db.MemberDAO;
import com.fooding.member.db.MemberDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class MemberIdFindAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberIdFindAction_execute() 호출");
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		// 전달정보 저장(name,email)
		MemberDTO dto = new  MemberDTO();
		dto.setName(request.getParameter("name"));
		dto.setEmail(request.getParameter("email"));
		
		// DAO 객체 생성 -> 아이디 찾기 메서드
		MemberDAO dao = new MemberDAO();
		
		String result = dao.idFindMember(dto);
		System.out.println(" M : result : "+result);
		
		
		if(result != null) {
			session.setAttribute("foundId", result);
			System.out.println("저장완료");
		}
		else {
			session.setAttribute("foundId", null);
			System.out.println("null");
		}
		
		
		// 페이지이동
		ActionForward forward = new ActionForward();
		forward.setPath("./FindId.mem");
		forward.setRedirect(true);
		return forward;

	}
}
