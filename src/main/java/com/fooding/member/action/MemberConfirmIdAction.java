package com.fooding.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooding.member.db.MemberDAO;
import com.fooding.member.db.MemberDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MemberConfirmIdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : MemberIdCheckAction_execute() 실행");
		
		MemberDAO dao = new MemberDAO();
		
		
		List<MemberDTO> memberList = dao.memberList();
		
		
		JsonObject jsonObject = new JsonObject();
		
		
		JsonArray memberArray = new JsonArray();
		for(MemberDTO dto : memberList) {
			JsonObject memberObject = new JsonObject();
			memberObject.addProperty("id", dto.getId());
			memberArray.add(memberObject);
		}
		
		jsonObject.add("memberList", memberArray);
		
		
		// 위에서 저장한 객체 json 형태로 저장
		String jsonString = jsonObject.toString();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonString);
		
		System.out.println("회원 데이터 저장(json) 완료");
		
		
		return null;
	}

}