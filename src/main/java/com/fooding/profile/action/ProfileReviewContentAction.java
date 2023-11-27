package com.fooding.profile.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.profile.review.db.ProfileReviewDAO;
import com.fooding.profile.review.db.ProfileReviewDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class ProfileReviewContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t M : ProfileReviewContentAction_execute() 호출 ");
		
		// 세션 제어
		HttpSession session = request.getSession();
//		session.setAttribute("id", "admin"); // 이 부분만 삭제하면 됨
		String id = (String) session.getAttribute("id");

		ActionForward forward = new ActionForward();

		// 한글 처리 (배포 서술자 자동)
		
		// 전달 정보 저장
		int review_id = Integer.parseInt(request.getParameter("review_id"));
		String pageNum = request.getParameter("pageNum");
		String search = request.getParameter("search");
		System.out.println("review_id : " + review_id + ", pageNum : " + pageNum + ", search : " + search);
		
		ProfileReviewDAO dao = new ProfileReviewDAO();		
		// 리뷰 보기 메서드 호출 (DAO)
		// DTO 저장
		ProfileReviewDTO dto = dao.profileReviewContent(review_id);
		int member_id = dto.getMember_id();
		
		// 상품 목록 메서드 호출 (DAO)
		ArrayList list = dao.profileReviewProductList(dto.getPurchaseid());
		
		// 리뷰의 member_id와 id의 member_id가 같은지 조회 (DAO)
		int result = dao.profileEqualMemberId(id, review_id);
		System.out.println("리뷰의 member_id와 현재 로그인된 id의 member_id 일치 여부 : " + result);
		
		// 패턴 3 -> request에 담아서 이동
		request.setAttribute("dto", dto);
		request.setAttribute("list", list);
		request.setAttribute("review_id", review_id);
		request.setAttribute("pageNum", pageNum);
//		request.setAttribute("search", search);
		request.setAttribute("result", result);
		
		forward.setPath("./fooding/khr/profile/reviewContent.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
