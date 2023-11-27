package com.fooding.ftinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooding.ftinfo.db.FtInfoDAO;
import com.fooding.ftinfo.db.FtInfoDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;
import com.google.gson.Gson;

public class FtInfoMainProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : FtInfoMainProAction_execute() 호출");
		
		response.setContentType("application/json");
		String selectedDate = request.getParameter("selectedDate");
		int ftNum = Integer.valueOf(request.getParameter("ftNum"));
		System.out.println(" M : ftNum - " + ftNum);
		if (selectedDate != null && !selectedDate.isEmpty()) {
			FtInfoDAO dao = new FtInfoDAO();
			FtInfoDTO dto = new FtInfoDTO();
			dto = dao.CallFtDateInfo(selectedDate, ftNum);
			System.out.println(" M : " + dto);
            Gson gson = new Gson();
            String jsonData = gson.toJson(dto);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonData);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 잘못된 요청에 대한 상태 코드 반환
        }
		
		return null;
	}

}
