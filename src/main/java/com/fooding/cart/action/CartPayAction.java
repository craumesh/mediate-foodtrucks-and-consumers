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

public class CartPayAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       System.out.println(" M : CartPayAction_execute() 호출");
		
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
				String[] quantityStrings = request.getParameterValues("quantity");
				int[] cartId = new int[cartIdStrings.length];
				int[] quantity = new int[quantityStrings.length];

				if (cartIdStrings != null && quantityStrings != null &&
					    cartIdStrings.length == quantityStrings.length) {
					
					    
					    for (int i = 0; i < cartIdStrings.length; i++) {
					        try {
					            cartId[i] = Integer.parseInt(cartIdStrings[i]);
					            quantity[i] = Integer.parseInt(quantityStrings[i]);
					        } catch (Exception e) {
					        	System.err.println("CartPayAction 배열처리 오류발생: " + e.getMessage());
							    e.printStackTrace(); 
					        }
					    }
					    
					    

					   
					} else {
					    System.out.println("CartPayAction 오류발생:cartId,quantity 수 불일치  ");
					}
				
				//dto > arraylist 전달하기 
				ArrayList<CartDTO> cartList = new ArrayList<CartDTO>();
				
				for(int i=0; i<cartId.length; i++) {
					CartDTO cartDto = new CartDTO();
					cartDto.setCart_id(cartId[i]);
					cartDto.setQuantity(quantity[i]);
					
					cartList.add(cartDto);
					
				}
				
				
				
		// 3. 수량변경해서 주문하는 경우 처리해주기 > updateQuantity(int memberId,List cartList)
				CartDAO dao = new CartDAO();
				int result = dao.updateQuantity(memberId, cartList);
				
				System.out.println("수정된 수량:"+result);
				
				
				
				
	
				
				
		// 4.객체바인딩해서 전달하기 
				// request 영역에 정보를 저장
				//request.setAttribute("cartList", cartList);
				request.setAttribute("cartIdStrings", cartIdStrings);
				
		// ./cart/cart.jsp에 출력
				forward.setPath("/Payment.pay");
				forward.setRedirect(false);
				
				return forward;
				
		
	}

}
