package com.fooding.admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.admin.db.AdminDAO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class AdminMemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t\t M : AdminProductListAction_execute() 호출 ");

		// 세션 제어
		HttpSession session = request.getSession();
//		session.setAttribute("id", "admin"); // 이 부분만 삭제하면 됨
		String id = (String) session.getAttribute("id");

		ActionForward forward = new ActionForward();
		if (id == null) {
			forward.setPath("./FtMainAction.man"); // main 주소
			forward.setRedirect(true);
			return forward;
		} else if (id != null && !id.equals("admin")) {
			forward.setPath("./FtMainAction.man"); // main 주소
			forward.setRedirect(true);
			return forward;
		}

		// 한글 처리 (배포 서술자 자동)

		// 카운트 계산
		AdminDAO dao = new AdminDAO();
		int count = dao.adminMemberCount();

		// 페이징 처리 1
		String pageNum = request.getParameter("pageNum"); // 페이지 번호 생성
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}

		int pageSize = 10; // 한 화면에 보일 사이즈 생성
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow * pageSize;

		// 메서드 호출 (DAO)
		ArrayList list = null;
		if (count > 0) {
			list = dao.adminMemberList(startRow, pageSize);
		}

		// 페이징 처리 2
		int pageCount = (count / pageSize) + ((count % pageSize) == 0 ? 0 : 1);
		int pageBlock = 3;
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;

		if (endPage > pageCount) {
			endPage = pageCount;
		}

		// request에 담아서 이동
		request.setAttribute("list", list); // 푸드트럭 목록
		request.setAttribute("count", count); // 푸드트럭 개수
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);

		// 페이지 이동
		forward.setPath("./fooding/khr/admin/adminMember.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
