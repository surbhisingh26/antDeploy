package com.surbhi.webProject1.tools;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.surbhi.webProject1.app.Utility;
import com.surbhi.webProject1.requests.EmailActions;

public class Mail {
	public static void main(String args[]) throws ServletException, IOException{
		//sendMail();
		Utility uti = new Utility();
		Map<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("name", "viveka");
		hmap.put("frindName", "Surabhi");
		uti.getHbsAsString("EmailTemplate", hmap);
	}

	public static void sendMail() {
		EmailActions email = new EmailActions();
		HttpServletRequest request=null;
		HttpServletResponse response = null;
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("name", "Surbhi");
		//Utility utility = new Utility();
		//utility.getHbs(response,"EmailTemplate",input);
		try {
			email.send(request,response,"surbhi.singh.ss05@gmail.com","http://localhost:8080/webProject1/friend","");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
