package com.fooding.profile.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.profile.review.db.ProfileReviewDAO;
import com.fooding.profile.review.db.ProfileReviewDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;
import com.fooding.util.JSMoveFunction;

public class ProfileReviewWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t M : ProfileReviewWriteAction_execute() 호출 ");
		
		// 세션 제어
		HttpSession session = request.getSession();
//		session.setAttribute("id", "admin"); // 이 부분만 삭제하면 됨
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./FtMainAction.man"); // main 주소
			forward.setRedirect(true);
			return forward;
		}
		
		// 한글 처리 (배포 서술자 자동)
		
		// 전달 정보 저장
		int purchaseid = Integer.parseInt(request.getParameter("purchaseid"));
//		String pageNum = request.getParameter("pageNum");
		System.out.println("\t purchaseid : " + purchaseid);
		
		// 이미 리뷰를 작성했는지 확인 (DAO)
		ProfileReviewDAO dao = new ProfileReviewDAO();
		int result = dao.profileHasReview(purchaseid);
		
		if(result == 1) {
			JSMoveFunction.alertHistory(response, "이미 리뷰가 작성된 주문 건입니다."); // 이미 리뷰 있음
			return null;
		}else {
			
		// 주문 번호 상품 조회 메서드 (DAO)
		// 전달 정보 저장
		ArrayList list = dao.profileReviewProductList(purchaseid);
		
		// 패턴 3 -> request로 정보 전달
		request.setAttribute("list", list);
		request.setAttribute("purchaseid", purchaseid);
//		request.setAttribute("pageNum", pageNum);
		
		// 페이지 이동
		forward.setPath("./fooding/khr/profile/reviewForm.jsp");
		forward.setRedirect(false);
		}
		return forward;
	}
}
