package com.surbhi.webProject1.app;

import java.util.Properties;    
import javax.mail.*;    
import javax.mail.internet.*;    
public class SendEmail{  
    public void send(String mailTo,String link){  
          //Get properties object   
    	final String from = "surbhi.singh.ss05@gmail.com";
    	final String password="as192118020809";
    	
          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          //get Session   
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(from,password);  
           }    
          });    
          //compose message    
          try {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(mailTo));    
           message.setSubject("Friend Request");    
           message.setText("You have a friend request\nClick on the link below to respond to friend request\n\n"+link);    
           //send message  
           Transport.send(message);    
           System.out.println("message sent successfully");    
          } catch (MessagingException e) {throw new RuntimeException(e);}    
             
    }  
}  
