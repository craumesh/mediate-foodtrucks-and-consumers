package com.fooding.payment.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicClassicHttpRequest;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import com.fooding.member.action.MemberPwFindAction;
import com.fooding.payment.db.MailDAO;
import com.fooding.payment.db.MailDAOImpl;
import com.fooding.payment.db.PaymentDAO;
import com.fooding.payment.db.PaymentDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Refund extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 세션 제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		PaymentDAO pdao = new PaymentDAO();
		MailDAO mdao = new MailDAOImpl();
		
		
		// ----------------- ajax로 보낸 데이터 받기 --------
		StringBuilder sb = new StringBuilder();
		String line;
		
		try {
			BufferedReader reader = request.getReader();
			while((line = reader.readLine()) != null){
				sb.append(line);
			}
		} catch (Exception e) { /* 에러 처리 */ }
		
		String jsonString = sb.toString();

		Map<String, String> paramsMap = new HashMap<>();
		String[] params = jsonString.split("&");
		for (String param : params) {
		    String[] keyValue = param.split("=");
		    String key = keyValue[0];
		    String value = "";
		    if (keyValue.length > 1) {
		        value = URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8);
		    }		    paramsMap.put(key, value);
		}

		String token = paramsMap.get("token");
		int mid = Integer.parseInt(paramsMap.get("mid"));
		String reason = paramsMap.get("reason");
		int money = Integer.parseInt(paramsMap.get("amount"));
		String istotal = "no";
		if(paramsMap.get("checktf") != null) {			
			 istotal = paramsMap.get("checktf");
		}
		// 이게 값이 1이 아니면? 단건취소
		// 근데 1이면? 전체취소 => 전체취소는 mid / amount 밖에 안넘어온다.
		
	//^^^^^^^^^^^^^^^^^^^^^ 디테일 아이디 구하기 ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
		int detail_id = 0;
		if(paramsMap.get("indexnumber") != null &&! paramsMap.get("indexnumber").equals("")) {
		 detail_id = Integer.parseInt(paramsMap.get("indexnumber"));
		}
	//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
		System.out.println("detail_id"+detail_id);
		
		System.out.println("ReFund : ["+token+" / "+money+" / "+mid+" / "+reason+" / "+detail_id+" / ] 전달 완료");
		// -------- 데이터베이스 조회하기 --------
		
		// 인덱스로 회원번호 조회하기
		int member_id = pdao.getMember_id(detail_id);
		// makeCheckSum(int mid) 불러오기
		int Sum = pdao.makeCheckSum(mid); // 한 주문번호의 총 금액
		
		// 고객의 이메일 찾기
		if(paramsMap.get("action").equals("admin")) {
			
			String email = pdao.getEmailForCustomer(mid); // 고객 이메일 찾기
			// 이메일 전송
			if(istotal.equals("false") ) { //관리자페이지에서 단건취소용
				pdao.sendSomeRefundEmail(email, detail_id, mid);
			}else {
				pdao.sendAllRefundEmail(email,detail_id,mid);	
			}
		}
		
		
		// ----------------- 아임포트에 환불요청하기 --------
		// 여기에 환불요청 들어가야한다.      
                String urlString = "https://api.iamport.kr/payments/cancel";
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                // 요청 방식 설정
                conn.setRequestMethod("POST");

                // 헤더 설정
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Authorization", token);

            	// 요청 본문 생성
            	JsonObject requestBody = new JsonObject();
            	requestBody.addProperty("reason", reason);
            	requestBody.addProperty("merchant_uid", mid);
            	requestBody.addProperty("amount", money);
            	requestBody.addProperty("checksum", Sum);

                // 요청 본문 전송
                conn.setDoOutput(true);
                try (OutputStream os = conn.getOutputStream()) {
                    os.write(requestBody.toString().getBytes());
                }

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

                        // code 값이 0인지 확인
                        if (code == 0) { // 환불요청 성공적
                        	System.out.println("환불 성공적"+code);

                        // -------환불이 끝난 뒤 purchase 테이블에서 환불한 내역 지우기--------
                        
                        // 회원번호, 주문번호, 수량, 주문일자, 운행일, 주소, 시간 같은거 지우기
                        if(istotal.equals("true")) { // 전체취소
                        	pdao.deletePurchase(mid);
                        }else { // 단건취소
                        	pdao.deletePurchase(member_id,mid,detail_id);
                        }
                        
                        
                        } else {
                            // 환불 요청이 실패한 경우
                            System.out.println("환불 요청이 실패하였습니다. 코드: " + code);
                            System.out.println(responseBody);
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write("{\"error\":\"환불 처리에 실패하였습니다. 고객센터에 연락부탁드립니다\"}");  
                        }
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(responseBody.toString()); // 응답받은 내용 보내기
                    }
                } else {  // 에러 발생
                	 System.out.println("POST request not worked");
                	    response.setContentType("application/json");
                	    response.setCharacterEncoding("UTF-8");
                	    response.getWriter().write("{\"error\":\"환불 처리에 실패하였습니다. 고객센터에 연락부탁드립니다\"}");  
                	    
                }
                conn.disconnect();

                
	}//post 끝
}// 클래스 끝

