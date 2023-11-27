package com.fooding.ftinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooding.ftinfo.db.FtInfoDAO;
import com.fooding.ftinfo.db.FtInfoDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;
import com.fooding.util.JSMoveFunction;

public class FtInfoMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : FtInfoMainAction_execute() 호출");
		
		int foodtruck_id = Integer.valueOf(request.getParameter("foodtruck_id"));
				
		FtInfoDAO dao = new FtInfoDAO();
		
		int count[] = dao.foodtruckHasProduct(foodtruck_id);
		System.out.println("count : " + count);
		if(count[0] == 0) {
			JSMoveFunction.alertHistory(response, "아직 상품이 등록되지 않아 전 페이지로 이동합니다.");
			return null;
		} else if(count[1] == 0) {
			JSMoveFunction.alertHistory(response, "아직 경로가 등록되지 않아 전 페이지로 이동합니다.");
			return null;
		}
		
		FtInfoDTO dto = dao.CallFtInfo(foodtruck_id);
		dto = dao.CallFtProduct(dto, foodtruck_id);
		dto = dao.CallFtDate(dto, foodtruck_id);
		dto = dao.CallFtDateInfo(dto, foodtruck_id);
		System.out.println(" M : " + dto.toString());		
		request.setAttribute("dto", dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./fooding/ftInfo/ftInfoMain.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
