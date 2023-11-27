package com.fooding.admin.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooding.admin.db.AdminDAO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;
import com.google.gson.Gson;

public class AdminFoodtruckWayAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("\t\t M : AdminFoodtruckWayAddAction_execute() 호출 ");
		// TODO Auto-generated method stub
		String date = null;
		AdminDAO dao = new AdminDAO();
		ArrayList<String> wayInfoArr = new ArrayList<String>();
		ArrayList<Map<String, String>> resultArr = new ArrayList<Map<String, String>>();
		
		int foodtruck_id = Integer.valueOf(request.getParameter("foodtruck_id"));
		date = request.getParameter("date");
		if(date != null) {
			wayInfoArr = dao.adminFoodtruckWayCall(foodtruck_id, date);
			for(int i = 0; i < wayInfoArr.size(); i++) {
				String wayInfoStr = wayInfoArr.get(i);
				System.out.println("\t\t M : " + wayInfoStr);
				Map<String, String> wayMap = new HashMap<String, String>();
				wayMap.put("place_name", wayInfoStr.split(", ")[0]);
				wayMap.put("latlng", wayInfoStr.split(", ")[1] + ", " + wayInfoStr.split(", ")[2]);
				wayMap.put("address", wayInfoStr.split(", ")[3]);
				wayMap.put("start_time", wayInfoStr.split(", ")[4]);
				wayMap.put("end_time", wayInfoStr.split(", ")[5]);
				resultArr.add(wayMap);
			}
		}
		Gson gson = new Gson();
		String jsonString = gson.toJson(resultArr);

		request.setAttribute("wayMapJson", jsonString);
		
		ActionForward forward = new ActionForward();
		forward.setPath("AdminFoodtruckWayAdd.adm");
		forward.setRedirect(false);
		return forward;
	}

}
