package com.fooding.admin.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooding.admin.db.AdminDAO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AdminFoodtruckWayAddProAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("\t\t M : AdminFoodtruckWayAddProAction_execute() 호출 ");
		response.setCharacterEncoding("UTF-8");
		
		BufferedReader reader = request.getReader();
		StringBuilder jsonData = new StringBuilder();
		String line;

		while ((line = reader.readLine()) != null) {
		    jsonData.append(line);
		}

		// JSON 데이터 출력 (테스트용)
		System.out.println("Received JSON data: " + jsonData.toString());

		// Gson을 사용하여 JSON 데이터를 Map으로 변환
		Gson gson = new Gson();
		Map<String, Object> dataMap = gson.fromJson(jsonData.toString(), new TypeToken<Map<String, Object>>() {}.getType());

		// Map 데이터 출력 (테스트용)
		for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
		    System.out.println(entry.getKey() + ": " + entry.getValue());
		}

		// 필요한 값 추출
		String formData = (String)dataMap.get("formData");
		Map<String, Object> wayMap = gson.fromJson(formData.toString(), new TypeToken<Map<String, Object>>() {}.getType());
				
		// Map 키 추출
		Set<String> keys = wayMap.keySet();
				
		String sfoodtruck_id = (String)dataMap.get("foodtruck_id");		
		int foodtruck_id = Integer.valueOf(sfoodtruck_id);
		String date = (String)dataMap.get("date");
		
		if(date.equals("")) {
			response.getWriter().write(" Error - 운행일이 선택되지 않았습니다! ");
			return null;
		}		
			
		AdminDAO dao = new AdminDAO();	
		boolean result = true;	
		result = dao.adminFoodtruckWayCheck(foodtruck_id, date);		
		
		if(result) {				
			dao.adminFoodtruckWayDel(foodtruck_id, date);
			response.getWriter().write(wayAdd(wayMap, keys, dao, foodtruck_id, date));
		} else {
			response.getWriter().write(wayAdd(wayMap, keys, dao, foodtruck_id, date));
		}		
		return null;
	}
	
	public String wayAdd(Map<String, Object> wayMap, Set<String> keys, AdminDAO dao, int foodtruck_id, String date) {
		try {
			if(wayMap.size() > 0) {
				for (String key : keys) {
//					Map<String, String> innerMap = (Map<String, String>) wayMap.get(key);
//					if(innerMap.get("place_name").equals("") || innerMap.get("place_name") == null) {
//						throw new Exception("올바르지 않은 운행지명이 포함되어있습니다!");
//					} else if(innerMap.get("latlng").equals("") || innerMap.get("latlng") == null) {
//						throw new Exception("올바르지 않은 좌표가 포함되어있습니다!");
//					} else if(innerMap.get("start_time").equals("") || innerMap.get("start_time") == null) {
//						throw new Exception("올바르지 않은 시작시간이 포함되어있습니다!");
//					} else if(innerMap.get("end_time").equals("") || innerMap.get("end_time") == null) {
//						throw new Exception("올바르지 않은 종료시간이 포함되어있습니다!");
//					} else if(innerMap.get("address").equals("") || innerMap.get("address") == null) {
//						throw new Exception("올바르지 않은 주소가 포함되어있습니다!");
//					} else {
//	                    String address = innerMap.get("place_name") + ", " + innerMap.get("latlng");
//	                    String time = innerMap.get("start_time") + ", " + innerMap.get("end_time");
//	                    String largeclass = innerMap.get("address").split(" ")[0];
//	                    String smallclass = innerMap.get("address").split(" ")[1];
//	                    dao.adminFoodtruckWayAdd(foodtruck_id, date, address, time, largeclass, smallclass);
//	                }
					Map<String, String> innerMap = (Map<String, String>) wayMap.get(key);

	                // null 체크를 추가하여 미리 예외 발생을 방지
					String placeName = innerMap.get("place_name");
					String latlng = innerMap.get("latlng");
					String startTime = innerMap.get("start_time");
					String endTime = innerMap.get("end_time");
					String address = innerMap.get("address");

//					if (placeName == null || latlng == null || startTime == null || endTime == null || address == null ||
//					  placeName.equals("") || latlng.equals("") || startTime.equals("") || endTime.equals("") || address.equals("")) {
//					  throw new Exception("운행 정보가 올바르지 않습니다!");
//					}
					if (placeName == null || latlng == null || startTime == null || endTime == null ||
							placeName.equals("") || latlng.equals("") || startTime.equals("") || endTime.equals("")) {
						throw new Exception("운행 정보가 올바르지 않습니다!");
					}

					String fullAddress = placeName + ", " + latlng;
					String time = startTime + ", " + endTime;
					String largeClass = address.split(" ")[0];
					String smallClass = address.split(" ")[1];

					dao.adminFoodtruckWayAdd(foodtruck_id, date, fullAddress, address, time, largeClass, smallClass);
	            }
	        } else {
	            throw new Exception("운행 정보가 비어있습니다!");
	        }
	        return "Success";
	    } catch (Exception e) {
	        return "Error - " + e.getMessage();
	    }
	}
}