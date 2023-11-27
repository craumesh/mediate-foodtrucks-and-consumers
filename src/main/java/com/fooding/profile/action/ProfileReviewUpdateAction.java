package com.fooding.profile.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.profile.review.db.ProfileReviewDAO;
import com.fooding.profile.review.db.ProfileReviewDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class ProfileReviewUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t M : ProfileReviewUpdateAction_execute() 호출 ");
		
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
		int review_id = Integer.parseInt(request.getParameter("review_id"));
		String pageNum = request.getParameter("pageNum");
		String search = request.getParameter("search");
		System.out.println("review_id : " + review_id + ", pageNum : " + pageNum + ", search : " + search);
		
		// 리뷰 정보 보기 메서드 호출 (DAO)
		// DTO에 저장
		ProfileReviewDAO dao = new ProfileReviewDAO();
		ProfileReviewDTO dto = new ProfileReviewDTO();
		dto = dao.profileReviewContent(review_id);
		
		ArrayList list = dao.profileReviewProductList(dto.getPurchaseid());
		
		// 패턴 3 - request에 담아서 페이지 이동
		request.setAttribute("dto", dto);
		request.setAttribute("list", list);
		request.setAttribute("review_id", review_id);
		request.setAttribute("pageNum", pageNum);
		
		// 페이지 이동
		forward.setPath("./fooding/khr/profile/reviewUpdateForm.jsp?search=" + search);
		forward.setRedirect(false);
		return forward;
	}

}
