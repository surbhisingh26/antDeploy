package com.surbhi.webProject1.requests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import java.util.Properties;


import javax.mail.*;

import com.surbhi.webProject1.app.Pager;
import com.surbhi.webProject1.app.Utility;
import com.surbhi.webProject1.requestService.EmailService;

import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;    
public class EmailActions extends HttpServlet{  
	private static final long serialVersionUID = 1L;
	Utility utility = new Utility();
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmailActions() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmailActions emailaction = new EmailActions();


		String path = request.getPathInfo();
		System.out.println("path "+ path);


		if(path==null||path.equals("/")){
			Map<String, Object> hmap  = new HashMap<String, Object>();
			hmap = utility.checkSession(request);
			utility.getHbs(response,"home",hmap);
		}

		else{
			try {
				String ur = path.replace("/", "");
				Method method = EmailActions.class.getDeclaredMethod(ur,HttpServletRequest.class,HttpServletResponse.class);
				//				System.out.println("method is "+method);
				//				System.out.println("method name is "+method.getName());

				method.invoke(emailaction,request,response);
			} catch (Exception e) {

				e.printStackTrace();
			} 
		}
	}
    public void send(HttpServletRequest request, HttpServletResponse response,String mailTo,String purpose,String subject) throws ServletException, IOException{  
          //Get properties object   
    	Map<String, Object> hmap = new HashMap<String, Object>();
    	hmap = utility.checkSession(request);
		//String uid = (String) hmap.get("uid");
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
           message.setSubject(subject);   
           MimeMultipart multipart = new MimeMultipart();
           BodyPart messageBodyPart = new MimeBodyPart();
          
          System.out.println("Sending mail");
   		hmap.put("name", "Surbhi");
   		hmap.put(purpose,true);
   		Utility utility = new Utility();
   		utility.getHbs(response,"EmailTemplate",hmap);
        message.setContent("","text/html");
        //HTML mail content
        
        
        multipart.addBodyPart(messageBodyPart); 
        message.setContent(multipart);
           
           
           //send message  
           Transport.send(message);    
           System.out.println("message sent successfully");    
          } catch (MessagingException e) {throw new RuntimeException(e);}    
             
    }  
   
    public void emails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	Map<String, Object> hmap = new HashMap<String, Object>();
		
		final int pagerSize=10;
		String sortBy = request.getParameter("sortBy");
		String ascending = request.getParameter("ascending");
		System.out.println("Sort By " +sortBy);
		System.out.println("Ascending " + ascending);
		String limit=(String)request.getParameter("limit");
		if(limit==null){
			limit="10";
		}
		hmap = utility.checkSession(request);
		String uid = (String) hmap.get("uid");
		EmailService emailservice = new EmailService();
		System.out.println("after function "+uid);
		int pageLimit = Integer.parseInt(limit);
		System.out.println("page limit "+pageLimit);
		String page = (String)request.getParameter("page");
		if(page==null){
			page="1";
		}
		int currentPage = Integer.parseInt(page);

		if(uid==null){
			String msg = "Please login first!!!";
			hmap.put("message", msg);
			utility.getHbs(response,"message",hmap);
			utility.getHbs(response,"login",null);
		}
		else{
			if(sortBy==null)
				sortBy="date";
			if(ascending==null)
				ascending="true";
			hmap.put("sortBy", sortBy);
			hmap.put("ascending",ascending);
			hmap.putAll(emailservice.emails(uid,currentPage,pageLimit,sortBy,ascending));
			System.out.println("Sort By" +sortBy);
			System.out.println("Ascending " + ascending);

			long count = (Long) (hmap.get("count"));
			int totalPage = (int) Math.ceil((double)count/pageLimit);
			System.out.println("Total pages are "+totalPage);
			System.out.println(hmap.get("count"));
			hmap.put("link", "emails");
			if(count!=0){
			Pager pager = new Pager();
			hmap.putAll(pager.pager(currentPage,totalPage,pageLimit,pagerSize));
			}
			//System.out.println("Current page is ");
			utility.getHbs(response,"emails",hmap);

    	
    }
}  
}