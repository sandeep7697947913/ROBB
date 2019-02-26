package utils;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.Transport;

public class EmailSender{
	public static void sendRegistrationKey(String toEmail,String text){
		Properties props = new Properties();

		props.put("mail.transport.protocol","smtp");
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.host","smtp.gmail.com");
		props.put("mail.smtp.port","587");

		Session session = Session.getInstance(props,new EmailAuthenticator());

		MimeMessage message = new MimeMessage(session);

		try{
			message.setFrom(new InternetAddress("khemrajyadav.sy@gmail.com"));
			
			message.addRecipients(Message.RecipientType.TO,toEmail);
			
			message.setSubject("Secret-Key");

			message.setContent(text,"text/html");

			Transport.send(message);
		}catch(MessagingException e){
			e.printStackTrace();
		}	
	}

	public static void sendOTP(String toEmail,String text){
		Properties props = new Properties();

		props.put("mail.transport.protocol","smtp");
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.host","smtp.gmail.com");
		props.put("mail.smtp.port","587");

		Session session = Session.getInstance(props,new EmailAuthenticator());

		MimeMessage message = new MimeMessage(session);

		try{
			message.setFrom(new InternetAddress("khemrajyadav.sy@gmail.com"));
			
			message.addRecipients(Message.RecipientType.TO,toEmail);
			
			message.setSubject("One Time Password");

			message.setContent(text,"text/html");

			Transport.send(message);
		}catch(MessagingException e){
			e.printStackTrace();
		}
	}
}


class EmailAuthenticator extends Authenticator{
	public PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication("khemrajyadav.sy@gmail.com","7000073053");
	}
}