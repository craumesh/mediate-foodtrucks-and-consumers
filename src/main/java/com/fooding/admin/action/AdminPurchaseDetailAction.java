package com.fooding.admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.admin.db.AdminDAO;
import com.fooding.profile.purchase.db.ProfilePurchaseDAO;
import com.fooding.profile.review.db.ProfileReviewDAO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class AdminPurchaseDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t M : AdminPurchaseListDetailAction_execute() 호출 ");

		// 세션 제어
		HttpSession session = request.getSession();
//		session.setAttribute("id", "admin"); // 이 부분만 삭제하면 됨
		String id = (String) session.getAttribute("id");

		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./FtMainAction.man"); // main 주소
			forward.setRedirect(true);
			return forward;
		}else if (id != null && !id.equals("admin")) {
			forward.setPath("./FtMainAction.man"); // main 주소
			forward.setRedirect(true);
			return forward;
		}

		// 한글 처리 (배포 서술자 자동)

		// 전달 정보 저장
		String pageNum = request.getParameter("pageNum");
		int purchaseid = Integer.parseInt(request.getParameter("purchaseid"));
		System.out.println("purchaseid : " + purchaseid);

		// 주문 상세 내역 조회 메서드 호출 (DAO)
		AdminDAO dao = new AdminDAO();
		ArrayList list = dao.adminPurchaseDetail(purchaseid);
		System.out.println(list);
		
//		// 주문 번호 리뷰 존재하는지 확인 (DAO)
//		ProfileReviewDAO rdao = new ProfileReviewDAO();
//		int result = rdao.profileHasReview(purchaseid);
//		
//		// 리뷰가 존재한다면 리뷰 번호 확인 (DAO)
//		int review_id = rdao.profileReviewId(purchaseid);

		// 패턴 3 (request)
		request.setAttribute("list", list);
		request.setAttribute("purchaseid", purchaseid); // 주문번호 전달
		request.setAttribute("pageNum", pageNum); // 페이지번호 전달
//		request.setAttribute("result", result);
//		request.setAttribute("review_id", review_id); // 리뷰번호

		// 페이지 이동
		forward.setPath("./fooding/khr/admin/adminPurchaseDetail.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
