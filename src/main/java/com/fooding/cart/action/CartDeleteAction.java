package com.fooding.cart.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.cart.db.CartDAO;
import com.fooding.cart.db.CartDTO;
import com.fooding.cart.db.CartListDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;
import com.fooding.util.JSMoveFunction;

public class CartDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		// 1. 로그인 세션 제어
		// 로그인 세션제어
				HttpSession session = request.getSession();
				
				
				
				String id = (String) session.getAttribute("id");
				
				ActionForward forward = new ActionForward();
				if(id == null) {
					forward.setPath("./MemberLogin.mem");
					forward.setRedirect(true);
					return forward;
				}
				
				// 2. 정보수집
				// 한글처리 
				request.setCharacterEncoding("utf-8");
				
				// 전달된 정보 저장
				int memberId = Integer.parseInt(request.getParameter("member_id"));
				String[] cartIdStrings = request.getParameterValues("cart_id");
				
				int[] cartId = new int[cartIdStrings.length];
				

				if (cartIdStrings != null ) {
 
					    for (int i = 0; i < cartIdStrings.length; i++) {
					        try {
					            cartId[i] = Integer.parseInt(cartIdStrings[i]);
					           
					        } catch (NumberFormatException e) {
					        	System.err.println("CartDeleteAction 배열처리 오류발생: " + e.getMessage());
							    e.printStackTrace(); 
					        }
					    }
					    				   
					} else {
					    System.out.println("CartDeleteAction null 오류발생 ");
					}
				
				
				//dto > arraylist 전달하기 
				//ArrayList<CartListDTO> cartList = new ArrayList<CartListDTO>();
				ArrayList<CartDTO> cartList = new ArrayList<CartDTO>();
				
				for(int i=0; i<cartId.length; i++) {
					CartDTO listDto = new CartDTO();
					
					listDto.setCart_id(cartId[i]);
					
					cartList.add(listDto);
											
				}
				
				// 삭제처리 dao 호출 deleteCart(int memberId,List cartList)
				CartDAO dao = new CartDAO();
				dao.deleteCart(memberId, cartList);
				
				// 다음페이지 정보
				//forward.setPath("./CartList.car");
				//forward.setRedirect(true);
				
				// jsmove로 처리해주기 삭제완료하면 alert창 나오게 하기 , return 값에 따라서 삭제 처리하기 
				
				JSMoveFunction.alertLocation(response, "삭제완료!", "./CartList.car");
				
				return null;
				
				
		
	}

}
