
package com.fooding.orderDetail.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.orderDetail.db.orderDetailDAO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class orderDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그인 세션 제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id==null) {
			forward.setPath("./MemberLogin.mem");
			forward.setRedirect(true);
			return forward;
		}
		
			
		// 기존에 저장된 데이터 가져와서 화면에 출력
		orderDetailDAO odao= new orderDetailDAO();
		// 0. 멤버번호 조회 - getMemberId(string id)
		int idNum = odao.getMemberId(id);
		
		int count = odao.getPurchaseCount(id);
		int purchaseid = 0;
		int count2 = 0;
		if(request.getParameter("purchaseid") != null) {
			purchaseid = Integer.parseInt(request.getParameter("purchaseid"));
			// 주문번호가 몇번째 글인지 계산하는 메서드 getPurchaseCount(idNum, purchaseid)
			count2 = odao.getPurchaseCount(idNum, purchaseid);
		}
		int Num = 0;
		if(request.getParameter("purchaseid") != null) {
			Num =  (count - count2) / 10;
		}
		//****************페이징처리1****************/
		String pageNum = null;
		if(request.getParameter("pageNum") != null) {
			pageNum = request.getParameter("pageNum");
		}else {
			pageNum = String.valueOf(Num+1);
		}
		
		
		int pageSize = 10; // 한 페이지에 출력 할 글의 개수 설정
		int currentPage = Integer.parseInt(pageNum); // 시작행
		int startRow = (currentPage - 1)* pageSize + 1;
		int endRow = startRow * pageSize; // 끝행
		//****************페이징처리1****************/
		
		// 오늘 날짜 구하기(오늘 날짜 전에는 환불하는 버튼 보이게 하기 위해서)
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
		// DAO - 주문내역정보 모두(list)를 가져오는 메서드
		ArrayList purchaseList = new ArrayList();
		if(count > 0) {
		purchaseList = odao.getPurchaseList(startRow, pageSize, idNum, today);
		}
		System.out.println(" 주문내역사이즈 : "+purchaseList.size());

		
		//**********페이징처리 2 *********************

		int pageCount = count/pageSize + (count%pageSize == 0? 0:1);
					
		// 한 화면에 보여줄 페이지 블럭 개수
		int pageBlock = 5;
					
		int startPage = ((currentPage-1)/pageBlock) * pageBlock + 1;
					
		// 페이지블럭 마지막번호 (글이 꽉 차있을때)
		int endPage = startPage + pageBlock +1;
		// 페이지의 글이 없는 경우
		if(endPage > pageCount){ // 마지막페이지의 개수로 맞추기
			endPage = pageCount;
					}// result != 0
			
			//**********페이징처리 2 *********************
		
		
		// 페이징 처리에 필요한 정보 모두를 request영억에 저장해서 전달
		request.setAttribute("purchaseList", purchaseList);
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("today", today);
		// 총 6개 request에 담아서 보내기
		
		
		forward = new ActionForward();
		forward.setPath("./fooding/payment/paymentResult.jsp"); // 결제내역창 주소 입력해야함
		forward.setRedirect(false); // 전체출력이라 데이터 넘길거 없음
		
		return forward;
	}

}
