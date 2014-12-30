package com.sau.socialsau.util;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {

	private static Logger logger = LoggerFactory.getLogger(MailUtil.class);

	public static void sendMail(String to, String confirmCode) {
		logger.trace("Send Email");
		// Properties can be saved to a stream or loaded from a stream. Each key and its corresponding value in the property list is a string
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		// เมล์ที่ส่ง
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("thanatorn.sendmail@gmail.com", "new123456789");
					}
				});
		try {
			// กำหนดรายละเอียดข้อความที่ส่ง ลง object
			Message message = new MimeMessage(session);
			message.setContent("Generate Code : " + "<b>" + confirmCode + "</b>" + 
					"<br><br>กรุณานำโค้ดไปแสดงตัวตนของทางผ่านทางหน้าเว็บ<br>เพื่อยืนยันตัวตนของท่านว่าเป็น <b>นักศึกษามหาวิทยาลัยเอเชียอาคเนย์</b>" + 
					"<br>หรือทำการยืนยันผ่านทางลิงค์  &nbsp;&nbsp; <a href='http://localhost:8080/socialsau/user/checkConfirmCode?confirmEmail=" + to + "&confirmCode=" + confirmCode + "'>ยืนยันอีเมล์</>"
					,"text/html; charset=UTF-8");
			// กำหนดผู้รับ
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
			message.setSubject("กรุณายืนยัน Code ผ่านทางหน้าเว็บ");
			
			// ส่ง
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.trace("Send Email Succes");
	}

	public static String generateConfirmCode(Integer length) {
		final String code = "0123456789abcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		StringBuffer strConfirmCode = new StringBuffer();
		for (int i = 0; i < length; i++) {
			strConfirmCode.append(code.charAt(random.nextInt(code.length())));
		}
		return strConfirmCode.toString();
	}

}
