package com.fooding.profile.action;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.profile.purchase.db.ProfilePurchaseDAO;
import com.fooding.profile.review.db.ProfileReviewDAO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class ProfilePurchaseDetailAction2 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t M : ProfilePurchaseListDetailAction_execute() 호출 ");

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
		String pageNum = request.getParameter("pageNum");
		int purchaseid = Integer.parseInt(request.getParameter("purchaseid"));
		System.out.println("purchaseid : " + purchaseid);

		// 주문 상세 내역 조회 메서드 호출 (DAO)
		ProfilePurchaseDAO dao = new ProfilePurchaseDAO();
		ArrayList list = dao.profilePurchaseDetail(purchaseid);
		System.out.println(list);
		
		// 주문 번호 리뷰 존재하는지 확인 (DAO)
		ProfileReviewDAO rdao = new ProfileReviewDAO();
		int result = rdao.profileHasReview(purchaseid);
		
		// 리뷰가 존재한다면 리뷰 번호 확인 (DAO)
		int review_id = rdao.profileReviewId(purchaseid);

		// 현재 연월일 확인
		LocalDateTime now = LocalDateTime.now();
		System.out.println("now : " + now);
		
		// 수령일 중 가장 늦은 날 확인
		String lateDate = dao.profilePurchaseDate(purchaseid);
		System.out.println("lateDate : " + lateDate);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime date = null;
		int difference = 0;
		
		if(lateDate != null) {
		date = LocalDateTime.parse(lateDate, dtf);
		System.out.println("date : " + date);
		
		difference = now.compareTo(date);
		System.out.println("difference : " + difference);
		}
		
		// 패턴 3 (request)
		request.setAttribute("list", list);
		request.setAttribute("purchaseid", purchaseid); // 주문번호 전달
		request.setAttribute("pageNum", pageNum); // 페이지번호 전달
		request.setAttribute("result", result);
		request.setAttribute("review_id", review_id); // 리뷰번호
		request.setAttribute("now", now); // 현재 연월일
		request.setAttribute("date", date); // 수령일
		request.setAttribute("difference", difference); // 차이

		// 페이지 이동
		forward.setPath("./fooding/khr/profile/purchaseDetail2.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
