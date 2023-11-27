package com.fooding.ftk.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooding.ft.db.FTDAO;
import com.fooding.ft.db.FTDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class FtkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		FTDAO fdao = new FTDAO();
        List<FTDTO> ftInfo = fdao.getInfoFoodTrucks();
		
        // 운행 중인 푸드트럭 정보를 JSP 페이지로 전달
        request.setAttribute("ftInfo", ftInfo);
        
        // 페이지 이동준비
     	ActionForward forward = new ActionForward();
     	forward.setPath("./fooding/ft/ft.jsp");
		forward.setRedirect(false);
     		
     	return forward;
    }
	
}



