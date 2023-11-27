package com.fooding.member.action;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.member.db.MemberDAO;
import com.fooding.member.db.MemberDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;

public class MemberPwFindAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberPwFindAction.execute() 호출");
		
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		// 전달정보 저장(name,email)
		MemberDTO dto = new  MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setEmail(request.getParameter("email"));
		
		// DAO 객체 생성 -> 비밀번호 확인 메서드
		MemberDAO dao = new MemberDAO();
		
		int result = dao.pwFindMember(dto);
		System.out.println(" M : result : "+result);
		
		HttpSession session = request.getSession();
		
		if (result == 1) {
		    // 임시 비밀번호 생성 generateRandomPassword  함수를 com.itwillbs.util에서 만들어줌
		    String temporaryPassword =  generateRandomNumber(10); // 10자리의 랜덤 숫자 생성
		    System.out.println("임시 비밀번호: " + temporaryPassword); // 임시 비밀번호를 콘솔에 출력
		    // 임시 비밀번호를 데이터베이스에 저장 메서드 (dao의 메서드를 사용하여 저장)
		    boolean passwordSaved = dao.saveTemporaryPassword(dto, temporaryPassword); 
		    // DAO 메서드

		    if (passwordSaved) {
		        // 임시 비밀번호를 이메일로 전송
		        sendTemporaryPasswordEmail(dto.getEmail(), temporaryPassword);

		        // 세션에 결과 저장
		        session.setAttribute("temporaryPassword", 1);
		        System.out.println("확인완료");
		    }
		} else {
		    session.setAttribute("temporaryPassword", null);
		    System.out.println("null");
		}
	
		
		// 페이지이동
		ActionForward forward = new ActionForward();
		forward.setPath("./FindPw.mem");
		forward.setRedirect(true);
		return forward;
	}

	
	
	// 난수생성 메서드
	public static String generateRandomNumber(int length) {
	    if (length <= 0) {
	        throw new IllegalArgumentException("Length should be greater than 0");
	    }
	
	    Random random = new Random();
	    StringBuilder sb = new StringBuilder();
	
	    for (int i = 0; i < length; i++) {
	        int randomNumber = random.nextInt(10); // 0부터 9까지의 난수 생성
	        sb.append(randomNumber);
	    }
	
	    return sb.toString();
	}
	
	// 이메일 전송 메서드
	private void sendTemporaryPasswordEmail(String email, String temporaryPassword) {
		// 이메일 전송 설정
		String host = "smtp.gmail.com";
		String port = "587";
		String username = "akkad1478@gmail.com";
		String password = "yyqnmmhboltxwhro";

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", host);

		// 이메일 메시지 작성
		String subject = "푸딩 - 임시 비밀번호 입니다.";
		String content = "임시 비밀번호: " + temporaryPassword +
		 "\n ※임시 비밀번호 이니 로그인 시 비밀번호를 변경해주세요※";

		Session session = Session.getInstance(props, new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(username, password);
		    }
		});

		try {
		    Message message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(username));
		    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		    message.setSubject(subject);
		    message.setText(content);

		    // 이메일 전송
		    Transport.send(message);
		    System.out.println("이메일 전송 완료");

		} catch (MessagingException e) {
		    e.printStackTrace();
		    System.out.println("이메일 전송 오류");
		}
		
		
		
		
	}

}
