package com.fooding.ftk.action;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fooding.ft.db.FTDAO;
import com.fooding.ft.db.FTDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class FtkListAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, 
    		HttpServletResponse response) throws ServletException, IOException {

		// 전달정보 검색어 정보 저장
		String search = request.getParameter("search");
		
		// 기존에 저장된 푸드트럭 정보를 가져와서 화면에 출력
		FTDAO fdao = new FTDAO();
		int count = 0;
		if(search == null) { // 검색어 X
			System.out.println(" M : 검색어 없음! ");
			count = fdao.getFtCount();
		}else { // 검색어 O - 검색결과 O / X
			System.out.println(" M : 검색어 있음! ("+search+")");
			count = fdao.getFtCount(search);
		}

		// DAO - 푸드트럭 정보 모두(list)를 가져오는 메서드 호출
		ArrayList ftList;

		ftList = fdao.getFtList(search);
		
		// 리스트를 출력 => 연결된 뷰페이지에서 출력하도록 정보 전달
		request.setAttribute("ftList", ftList);
		
		// 페이지 이동 준비
		ActionForward forward = new ActionForward();
		forward.setPath("./fooding/ft/ft.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}