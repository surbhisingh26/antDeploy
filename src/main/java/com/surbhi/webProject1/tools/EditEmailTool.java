package com.surbhi.webProject1.tools;

import com.surbhi.webProject1.requestService.EmailService;

public class EditEmailTool {
public static void main(String args[]){
	EmailService email = new EmailService();
	email.editemail("5ae1db07e2e9a7132cd89334", "viewCount", "4");
}
}
