package com.fooding.profile.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooding.admin.db.AdminDAO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class ProfileFoodtruckWayDelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int foodtruck_id = Integer.valueOf(request.getParameter("foodtruck_id"));
		String date = request.getParameter("date");
		
		AdminDAO dao = new AdminDAO();
		dao.adminFoodtruckWayDel(foodtruck_id, date);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./ProfileFoodtruckContent.pro?foodtruck_id="+foodtruck_id);
		forward.setRedirect(true);
		return forward;
	}

}
