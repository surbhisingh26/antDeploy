package com.surbhi.webProject1.tools;

import com.surbhi.webProject1.app.SendEmail;

public class Mail {
	public static void main(String args[]){
		SendEmail email = new SendEmail();
		email.send("surbhi.singh.ss05@gmail.com","http://localhost:8080/webProject1/friend","");
	}
}
