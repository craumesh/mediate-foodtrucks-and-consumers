package com.fooding.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class JSMoveFunction {
	
	public static void alertLocation(HttpServletResponse response, String msg, String url) {
		System.out.println(" util : JSMoveFunction_alertLocation() 호출 ");
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('"+ msg +"'); ");
			out.println(" location.href='"+ url +"'; ");
			out.println("</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void alertBack(HttpServletResponse response, String msg) {
		System.out.println(" util : JSMoveFunction_alertBack() 호출 ");
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('"+ msg +"'); ");
			out.println(" history.back(); ");
			out.println("</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void alertHistory(HttpServletResponse response, String msg) {
		System.out.println(" util : JSMoveFunction_alertHistory() 호출 ");
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('"+ msg +"'); ");
			out.println(" history.back(); ");
			out.println("</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void alertGo(HttpServletResponse response, String msg, int page) {
		System.out.println(" util : JSMoveFunction_alertBack() 호출 ");
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('"+ msg +"'); ");
			out.println(" history.go(" + page + ")");
			out.println("</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
