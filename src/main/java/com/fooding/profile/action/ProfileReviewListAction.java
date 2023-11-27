package com.fooding.profile.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.profile.review.db.ProfileReviewDAO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class ProfileReviewListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t M : ProfileReviewListAction_execute() 호출 ");

		// 세션 제어
		HttpSession session = request.getSession();
//		session.setAttribute("id", "admin"); // 이 부분만 삭제하면 됨
		String id = (String) session.getAttribute("id");

		ActionForward forward = new ActionForward();

		// 한글 처리 (배포 서술자 자동)
		String search = request.getParameter("search");

		// 리뷰 개수 확인
		ProfileReviewDAO dao = new ProfileReviewDAO();
		int count = 0;
		System.out.println("search : " + search);
		if(search == null || search == "") {
			count = dao.profileReviewCount(); // 전체 조회
			System.out.println("리뷰 개수 (count) : " + count);
		}else {
			count = dao.profileReviewCount(id); // 자기 리뷰만 조회
			System.out.println("리뷰 개수 (count) : " + count);
		}
		
		// 페이징 처리 1
		String pageNum = request.getParameter("pageNum"); // 페이지 번호 생성
		if(pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		
		int pageSize = 4; // 한 화면에 보일 사이즈 생성
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow * pageSize;
		
		// 리뷰 목록 조회 메서드 호출 (DAO)
		ArrayList list = null;
		if(count > 0 && (search == null || search == "")) {
			list = dao.profileReviewList(startRow, pageSize); // 전체 조회
		}else if(count > 0 && search != null) {
			list = dao.profileReviewList(id, startRow, pageSize); // 자기 리뷰만 조회
		}
		
		// 페이징 처리 2
			int pageCount = (count / pageSize) + ((count % pageSize) == 0? 0 : 1);
			int pageBlock = 3;
			int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
			int endPage = startPage + pageBlock - 1;
				
			if(endPage > pageCount) {
				endPage = pageCount;
			}
				
			// request에 담아서 이동
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("search", search);
		
		forward.setPath("./fooding/khr/profile/reviewList.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
