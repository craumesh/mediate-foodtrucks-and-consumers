package com.fooding.profile.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.profile.db.ProfileDAO;
import com.fooding.profile.db.ProfileDTO;
import com.fooding.profile.purchase.db.ProfilePurchaseDAO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class ProfileAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t\t M : ProfileAction_execute() 호출 ");
		
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
		
		// 전달 정보 저장 (DAO)
		ProfileDTO dto = new ProfileDTO();
		
		// 회원 정보 메서드 호출 (DAO)
		ProfileDAO dao = new ProfileDAO();
		dto = dao.profileInfo(id);
		
		// 주문 목록 개수 계산 메서드 호출
		ProfilePurchaseDAO pdao = new ProfilePurchaseDAO();
		int count = pdao.profilePurchaseCount(id);
		System.out.println("\t\t 글 개수 : " + count);
		
		// 페이징 처리 1
		String pageNum = request.getParameter("pageNum"); // 페이지 번호 생성
		if(pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		
		int pageSize = 5; // 한 화면에 보일 사이즈 생성
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow * pageSize;
		
		// 주문 목록 메서드 호출 (DAO)
		ArrayList list = null;
		if(count > 0) {
			list = pdao.profilePurchaseList(id, startRow, pageSize);
		}
		
		// 페이징 처리 2
		int pageCount = (count / pageSize) + ((count % pageSize) == 0? 0 : 1);
		int pageBlock = 3;
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		// 푸드트럭 있는지 체크 (DAO)
		// id로 member_id 조회
		int member_id = dao.profileMember_id(id);
		int count2 = dao.profileFoodtruckCount(member_id);
		System.out.println("푸드트럭 개수 : " + count2);

		// request에 담아서 이동
		request.setAttribute("count2", count2);
		request.setAttribute("member_id", member_id);
		
		request.setAttribute("dto", dto);
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		// 페이지 이동 (티켓)
		forward.setPath("./fooding/khr/profile/profile.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
