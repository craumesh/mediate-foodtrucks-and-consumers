package com.fooding.support.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.support.db.SupportDAO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class SupportNoticeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t M : SupportNoticeList_execute() 호출 ");

		// 세션 제어 [세션에 아이디를 저장하되 여기서 제어는 하지말 것. 뷰 페이지에서 할 것 (admin)]
		HttpSession session = request.getSession();
//		session.setAttribute("id", "admin"); // 이 부분만 삭제하면 됨
		String id = (String) session.getAttribute("id");

		ActionForward forward = new ActionForward();

		// 한글 처리 (배포 서술자 자동)
		
		// 공지사항 개수 확인하는 메서드 호출 (DAO)
		SupportDAO dao = new SupportDAO();
		int count = dao.supportNoticeCount();
		
		String pageNum = request.getParameter("pageNum"); // 페이지 번호 생성
		if(pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		
		int pageSize = 10; // 한 화면에 보일 사이즈 생성
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow * pageSize;
		
		// 공지사항 리스트 불러오는 메서드 호출 (DAO)
		ArrayList list = null;
		if(count > 0) {
			list = dao.supportNoticeList(startRow, pageSize);
		}
		
		// 페이징 처리 2
		int pageCount = (count / pageSize) + ((count % pageSize) == 0? 0 : 1);
		int pageBlock = 3;
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		// 패턴 3 - request에 담아서 이동
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		// 페이지 이동
		forward.setPath("./fooding/khr/support/noticeList.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
