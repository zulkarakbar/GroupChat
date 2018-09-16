package chatroom.com.zulkar.chatroom.chatutility;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import chatroom.com.zulkar.chatroom.model.Chatter;

@RestController
@RequestMapping("/authenticate")
public class SendMail {
	@Autowired
	private static String USER_NAME = "znain368@gmail.com"; // GMail user name (just the part before "@gmail.com")
	private static String PASSWORD = "farhat@zee123"; // GMail password

	@RequestMapping(value = "/OTP", method = RequestMethod.POST)
	public void getOTP(@RequestBody Chatter chatter) {

		String mailId = chatter.getEmail();
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER_NAME, PASSWORD);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USER_NAME));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailId));
			message.setSubject("OTP");
			Random rand = new Random();
			String randomNumber = String.valueOf(rand.nextInt((999999 - 100000) + 1)) + 100000;
			message.setText(randomNumber);
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public void addUser() {

	}
}
