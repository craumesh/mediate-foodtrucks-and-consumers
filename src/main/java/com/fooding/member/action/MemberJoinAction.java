package com.fooding.member.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooding.member.db.MemberDAO;
import com.fooding.member.db.MemberDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println(" M : MemberJoinAction_execute() 호출 ");
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		// 전달정보 (파라메터) 저장 + 회원가입일
		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.setPhone(request.getParameter("phone"));
		dto.setEmail(request.getParameter("email"));
		dto.setRegdate(new Timestamp(System.currentTimeMillis()));
		
		System.out.println(" M : "+dto);

		// DB연결 - SQL 실행
		// MemberDAO 객체 생성
		MemberDAO dao = new MemberDAO();
		// 회원가입 메서드를 호출
		dao.insertMember(dto);
				
		// 페이지 이동(로그인페이지) => 컨트롤러에서만 가능
		ActionForward forward = new ActionForward();
		forward.setPath("./MemberLogin.mem");
		forward.setRedirect(true);	
				
		System.out.println(" M : "+forward);
				
		return forward;
	}//exeucte()

}
