package com.fooding.support.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.support.db.SupportDAO;
import com.fooding.support.db.SupportDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class SupportNoticeContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t M : SupportNoticeContentAction_execute() 호출 ");
		
		// 세션 제어
		HttpSession session = request.getSession();
//		session.setAttribute("id", "admin"); // 이 부분만 삭제하면 됨
		String id = (String) session.getAttribute("id");

		ActionForward forward = new ActionForward();

		// 한글 처리 (배포 서술자 자동)
		
		// 전달 정보
		int table_id = Integer.parseInt(request.getParameter("table_id"));
		String pageNum = request.getParameter("pageNum");
		
		// 공지사항 정보 출력하는 메서드 호출 (DAO)
		SupportDAO dao = new SupportDAO();
		SupportDTO dto = new SupportDTO();
		dto = dao.supportNoticeContent(table_id);
		
		// 패턴 3
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		
		// 페이지 이동
		forward.setPath("./fooding/khr/support/noticeContent.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
