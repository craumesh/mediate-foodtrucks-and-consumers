package com.fooding.ftinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.ftinfo.db.FtInfoDAO;
import com.fooding.ftinfo.db.FtInfoDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;
import com.fooding.util.JSMoveFunction;

public class FTInfoSendAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : FTInfoSendAction_execute() 호출");

		HttpSession session = request.getSession();
		String id = null;
		try {
			id = (String)session.getAttribute("id");	
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(id == null) {
				JSMoveFunction.alertLocation(response, "먼저 로그인 해주세요!", "./MemberLogin.mem");
			}
		}		
		
		int truck_id = Integer.valueOf(request.getParameter("truck_id"));
		String date = null;
		String time = null;
		String place = null;
		String product = null;
		int quantity = 0;
		
		ActionForward forward = new ActionForward();
		
		try {
			date = request.getParameter("date");
			time = request.getParameter("time");
			place = request.getParameter("place");
			product = request.getParameter("product");
			quantity = Integer.valueOf(request.getParameter("hidden_quantity"));		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(time.equals("") || place.equals("")) {
				JSMoveFunction.alertHistory(response, "시간이 선택되지 않았습니다!");
			} else if(product.equals("") || product.equals("선택")) {
				JSMoveFunction.alertHistory(response, "상품이 선택되지 않았습니다!");
			} else {
				FtInfoDAO dao = new FtInfoDAO();		
				int member_id = dao.CallMemberId(id);
				int product_id = dao.CallProductIndex(truck_id, product);
				System.out.println(" M : member_id - " + member_id + " / product_id - " + product_id);				
				dao.InsertToCart(member_id,product_id,quantity,date,place,time);

				forward.setPath("/CartList.car");
				forward.setRedirect(false);
			}
		}					
		return forward;
	}

}
