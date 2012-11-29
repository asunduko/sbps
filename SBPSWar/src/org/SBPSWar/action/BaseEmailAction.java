package org.SBPSWar.action;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.SBPSWar.domain.UserProfile;


public class BaseEmailAction {
	
	public static Session getSession(){
		
		String host = "s155.eatj.com"; 
    	Properties props = System.getProperties();
    	props.put("mail.smtp.host", host);
    	props.put("mail.smtp.auth", "true"); 
    	SMTPAuthenticator auth = new SMTPAuthenticator(); 
    	Session session = Session.getDefaultInstance(props, auth); 
		
    	return session;
	}
	
	private static class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("asundy28", "rbi631");
		}
	}
	
	public static String getServerName(){
		
		String serverName = System.getProperty("serverName");
		
		if(serverName.equals("localhost")){
			serverName = "localhost:8080";
			
		}
		System.out.println( " serverName is now =  " + serverName);
		return serverName;
	}
	
	public void generalContactMessage(UserProfile from,UserProfile to, String subject, String communication ) {

		
		Session session = BaseEmailAction.getSession();
    	
    	try {
   
    		
    		String sentBy = from.getUserName();
    		InternetAddress toAddress = new InternetAddress(to.getUserName());

	    	MimeMessage message = new MimeMessage(session);
	    	message.setFrom(new InternetAddress("sbpsAdmin@SBPS.com"));
	    	message.addRecipient(Message.RecipientType.TO,toAddress);
	    		
	    	MimeMultipart multipart = new MimeMultipart("related");
	    	
	    	// first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            
            String htmlText = "<H3>Dear user,</H3> \n";
	    	    	
	    	message.setSubject(subject);	
	
	    	htmlText = htmlText + "<H3>You have been contacted by " + sentBy 
	    				+ " who's sent you a communication regarding: " + communication
	    				+ " </H3>" ;
	    			     		
	    	messageBodyPart.setContent(htmlText, "text/html");

            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // put everything together
            message.setContent(multipart);

	    	
	    	Transport.send(message);
	    	System.out.println(" Exiting category e-mail sender");
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("e = "+e.getStackTrace());
    		System.out.println("Exiting category e-mail sender after errr");
    	}
		
	}

}
