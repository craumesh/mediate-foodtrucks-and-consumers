package com.fooding.profile.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.admin.db.AdminDAO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;
import com.google.gson.Gson;

public class ProfileFoodtruckWayAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("\t\t M : ProfileFoodtruckWayAddAction_execute() 호출 ");
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
		
		forward.setPath("./fooding/khr/profile/profileFoodtruckWayForm.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
