package com.fooding.support.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.support.db.SupportDAO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;
import com.fooding.util.JSMoveFunction;

public class SupportEventDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t M : SupportEventDeleteAction_execute() 호출 ");
		
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
		
		// 전달 정보
		String pageNum = request.getParameter("pageNum");
		int table_id = Integer.parseInt(request.getParameter("table_id"));
		
		// 공지사항 삭제 메서드 호출 (DAO)
		SupportDAO dao = new SupportDAO();
		int result = dao.supportEventDelete(table_id);
		
		if(result == 1) {
			JSMoveFunction.alertLocation(response, "이벤트가 삭제되었습니다.", "./SupportEventList.sup?pageNum=" + pageNum);
			return null;
		}else {
			JSMoveFunction.alertHistory(response, "삭제가 실패했습니다.");
		}
		return null;
	}
}
