package com.fooding.payment.db;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailDAOImpl implements MailDAO {

	@Override
	public void sendRefundEmail(String email, int detail_id, int mid, String productName) {
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
					props.put("mail.smtp.ssl.trust", host);;
					
					// 이메일 메시지 작성
					String subject = "푸딩 - 주문번호 "+mid+"가 결제취소 되었습니다";
					String content = "안녕하세요. 푸드트럭 예약 사이트 Fooding 입니다"
							+ "\n 주문번호 "+mid+"의 "+productName+" 결제가 가게 사정에 의해 취소되었음을 알려드립니다. "
							+ "\n 결제금액은 바로 취소요청 되었으며 은행과 카드사의 사정에 따라 2~3일 뒤에 환불이 될 수 있습니다. "
							+ "\n 이용해주셔서 감사합니다";

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

	@Override
	public void sendAllRefundEmail(String email, int detail_id, int mid) {
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
					String subject = "푸딩 - 주문번호 "+mid+"가 결제취소 되었습니다";
					String content = "안녕하세요. 푸드트럭 예약 사이트 Fooding 입니다"
							+ "\n 주문번호 "+mid+"의 결제가 가게 사정에 의해 취소되었음을 알려드립니다. "
							+ "\n 결제금액은 바로 취소되며 은행과 카드사의 사정에 따라 2~3일 뒤에 환불이 될 수 있습니다. "
							+ "\n 이용해주셔서 감사합니다";

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
