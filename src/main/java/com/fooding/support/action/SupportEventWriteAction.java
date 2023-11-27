package com.fooding.support.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.support.db.SupportDAO;
import com.fooding.support.db.SupportDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class SupportEventWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t M : SupportEventWriteAction_execute() 호출 ");

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
			forward.setPath("./Support.sup");
			forward.setRedirect(true);
			return forward;
		}

		// 한글 처리 (배포 서술자 자동)
		
		// 전달 정보 저장
		SupportDTO dto = new SupportDTO();
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		
		// 메서드 호출 (DAO)
		SupportDAO dao = new SupportDAO();
		dao.supportEventInsert(dto);
		
		// 페이지 이동
		forward.setPath("./SupportEventList.sup");
		forward.setRedirect(true);
		return forward;
	}

}
