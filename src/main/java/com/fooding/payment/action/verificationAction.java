package com.fooding.payment.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.payment.db.PaymentDAO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class verificationAction extends HttpServlet implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		// -------------데이터 받기 --------
		
		PaymentDAO pdao = new PaymentDAO();
		
		int sum = (int)request.getAttribute("Sum");
		int merchant_uid = (int)request.getAttribute("purchaseid");
		String token = (String)request.getAttribute("token");
		String status = "-started";
		
		System.out.println("verification : ["+token+" / "+merchant_uid+" / "+sum+" ] 전달 완료");
		
		// 로그인 세션 제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id==null) {
			forward.setPath("./MemberLogin.mem");
			forward.setRedirect(true);
			return forward;
		}
	/*   - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/
		 String urlString ="https://api.iamport.kr/payments/find/"+merchant_uid+"/?sorting=-started";
         URL url = new URL(urlString);
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();

         // 요청 방식 설정
         conn.setRequestMethod("GET");

         // 헤더 설정
         conn.setRequestProperty("Content-Type", "application/json");
         conn.setRequestProperty("Authorization", token);

         int amount=0;
        // 응답 받기
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line1;
                StringBuilder responseBody = new StringBuilder();
                while ((line1 = reader.readLine()) != null) {
                    responseBody.append(line1);
                }
                // 응답 본문을 JSON 객체로 변환
                JsonObject responseJson = new JsonParser().parse(responseBody.toString()).getAsJsonObject();

                // code 값 가져오기
                int code = responseJson.get("code").getAsInt();
                JsonObject responseObj = responseJson.get("response").getAsJsonObject();
                amount = responseObj.get("amount").getAsInt();
                
                // code 값이 0인지 확인
                if (code == 0) { // 단건조회 완료
                	if(sum == amount) { // DB금액과 단건조회 금액이 같은 경우
                		System.out.println("(*σ´∀`)σ 결제금액과 DB금액이 같음");
                	}
                }else{
                	System.out.println("결제금액과 DB금액이 다릅니다");

                    String urlString1 = "https://api.iamport.kr/payments/cancel";
                    URL url2 = new URL(urlString1);
                    HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
                    // 요청 방식 설정
                    conn.setRequestMethod("POST");
                    // 헤더 설정
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Authorization", token);
                    
                	// 요청 본문 생성
                	JsonObject requestBody = new JsonObject();
                	requestBody.addProperty("reason", "검증실패");
                	requestBody.addProperty("merchant_uid", merchant_uid);
                	requestBody.addProperty("amount", amount);
                	requestBody.addProperty("checksum", amount);
                	
                	// 결제데이터 지우기
                	pdao.deletePurchase(merchant_uid);
                	
                	//알럿
                	response.setContentType("text/html; charset=UTF-8");
                	PrintWriter out = response.getWriter();
                	out.println("<script>");
                	out.println(" alert('WHAT ARE YOU DOING NOW'); ");
                	out.println("</script>");
              }// try 끝
            }
        
            }
        
        forward = new ActionForward();
        forward.setPath("./OrderDetails.pay");
        forward.setRedirect(true);
        return forward;
	}//post 끝
}
