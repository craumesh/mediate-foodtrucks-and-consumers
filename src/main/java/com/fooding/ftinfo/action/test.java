package com.fooding.ftinfo.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class test implements Action {
	
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();
		request.setAttribute("list", list);
		
		ActionForward forward = new ActionForward();
		forward.setPath("test.jsp");
		forward.setRedirect(false);
		
		return forward;
	}


}
