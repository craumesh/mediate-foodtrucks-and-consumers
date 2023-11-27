package com.fooding.payment.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Token extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("++++++++++ 토큰 발급 받기 +++++++++++++");
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpRequest = new HttpPost("https://api.iamport.kr/users/getToken");

        try {
            // 설정할 헤더 정보 추가
            httpRequest.addHeader("Content-Type", "application/json");
            
            // POST 요청에 전송할 JSON 데이터 설정
            String json = "{\"imp_key\": \"1500428451315773\", \"imp_secret\": \"T1rLNPm2YQzEDqYOEmXYGFthFdwEi15aqlhCtkrB2C2pJW455YVrfltaCLqiO7IJw1z8PS0tLtWzcr6B\"}";
            StringEntity params = new StringEntity(json);
            httpRequest.setEntity(params);

            // POST 요청 보내기
            ClassicHttpResponse response = (ClassicHttpResponse)httpClient.execute(httpRequest);
            int statusCode = response.getCode();
            System.out.println("Response Code : " + statusCode);

            // 데이터 받기
            String jsonResponse = null; // 요청받은 데이터를 저장할 변수
            String accessToken = null; // 토큰 저장할 변수
            try { // 데이터 추출하기
            	HttpEntity entity = response.getEntity();     
            	// getEntity 오류가 계속 났는데 그냥 HttpResponse를 쓰는게 아니라 ClassicHttpResponse를 사용하면 된다.
            	if (entity != null) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    StringBuilder jsonResponseBuilder = new StringBuilder();
                   
                    String line;
                    while ((line = reader.readLine()) != null) {
                        jsonResponseBuilder.append(line);
                    }
                    
                    jsonResponse = jsonResponseBuilder.toString(); // 받은 결과를 toString(한문장)으로 표현하고 있음
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            // JSON 파싱
            if (jsonResponse != null) {
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
                // access_token 필드 추출
                if (jsonObject != null && jsonObject.has("response")) {
                    JsonObject responseToken = jsonObject.getAsJsonObject("response");
                    if (responseToken.has("access_token")) {
                        accessToken = responseToken.get("access_token").getAsString();
                    }
                }
            }
            System.out.println("(^▽^)/ GET! " + accessToken);
            resp.getWriter().write(accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }//doPost 끝ㄴ

}// 클래스 끝
