package com.fooding.profile.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.profile.review.db.ProfileReviewDAO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;
import com.fooding.util.JSMoveFunction;

public class ProfileReviewDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t M : ProfileReviewDeleteAction_execute() 호출 ");
		
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
		
		// 리뷰 삭제 메서드 호출 (DAO)
		ProfileReviewDAO dao = new ProfileReviewDAO();
		// 기존 이미지 있는지 확인 (DAO)
		String image = dao.profileReviewImage(review_id);
		
		int result = 0;
		if(image != null) {
			result = dao.profileReviewDelete(review_id);
		}else {
			result = dao.profileReviewDelete(review_id);
		}
		
		if(result == 1) {
			// 기존 이미지 삭제
			String realPath = request.getRealPath("/upload/review");
			File file = new File(realPath + "/" + image);
			file.delete();
			JSMoveFunction.alertLocation(response, "리뷰가 삭제되었습니다.", "./ProfileReviewList.pro?pageNum=" + pageNum + "&search=" + search);
		}else {
			JSMoveFunction.alertHistory(response, "삭제가 실패했습니다.");
		}
		return null;
	}

}
