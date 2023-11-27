package com.fooding.support.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.support.db.SupportDAO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class SupportAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t M : ProfileUpdateAction_execute() 호출 ");

		// 세션 제어
		HttpSession session = request.getSession();
//		session.setAttribute("id", "admin"); // 이 부분만 삭제하면 됨
		String id = (String) session.getAttribute("id");

		ActionForward forward = new ActionForward();

		// 한글 처리 (배포 서술자 자동)
		
		// 공지 개수 확인 (DAO)
		SupportDAO dao = new SupportDAO();
		int count = dao.supportNoticeCount();
		System.out.println(count);
		
		// 이벤트 개수 확인 (DAO)
		int ecount = dao.supportEventCount();
		
		// FAQ 개수 확인 (DAO)
		int fcount = dao.supportFaqCount();
		
		// 공지 목록 메서드 호출 (DAO)
		ArrayList list = null;
		ArrayList elist = null;
		ArrayList flist = null;
		if(count > 0) {
			list = dao.supportNoticeList(1, 5);
			System.out.println("리스트 : " + list);
		}
		
		if(ecount > 0) {
			elist = dao.supportEventList(1, 5);
		}
		
		if(fcount > 0) {
			flist = dao.supportFaqList(1, 5);
		}
		
		// 패턴 3 - request에 담아서 전달
		request.setAttribute("count", count);
		request.setAttribute("ecount", ecount);
		request.setAttribute("fcount", fcount);
		request.setAttribute("list", list);
		request.setAttribute("elist", elist);
		request.setAttribute("flist", flist);
		
		forward.setPath("./fooding/khr/support/support.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
