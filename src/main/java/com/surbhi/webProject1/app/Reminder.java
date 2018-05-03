package com.surbhi.webProject1.app;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;



import com.surbhi.webProject1.model.User;
import com.surbhi.webProject1.requestService.EmailService;
import com.surbhi.webProject1.requestService.UserService;
import com.surbhi.webProject1.requests.EmailActions;

public class Reminder  {
	Timer timer;
	public Reminder(){

	}

	public Reminder(int seconds) {
		
		timer = new Timer();
		timer.scheduleAtFixedRate(new RemindTask(), seconds*1000*60*60*24, seconds*1000*60*60*24*10);

	}

	class RemindTask extends TimerTask{

		public void run() {

			try {
				
				sendMail();
				

			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			// System.out.println("Time's up!");
			System.out.println("Task performed on: " + new Date() + "n" +
					"Thread's name: " + Thread.currentThread().getName());
		}
	}

	//    public static void main(String args[]) throws InterruptedException {
	//    	
	//    	int n=2;
	//    	 for(int i=0;i<n;i++){
	//    	 new Reminder(5);
	//    	 Thread.sleep(2000);
	//    	 }
	//     
	//        System.out.println("Task scheduled. " + Thread.currentThread().getName());
	//    }
	public void sendMail() throws ServletException, IOException{
		UserService userservice = new UserService();
		
		List<User> users =  userservice.getAllUsers();
		EmailService emailservice = new EmailService();
		//EmailActions emailaction = new EmailActions();
		EmailActions email = new EmailActions();
		long count=0;
		String status = "Pending";
		Boolean reminder = false;
		Boolean subscription;

		for(User user:users){
				System.out.println("Username.............." + user.getUsername());

			System.out.println(user.getName());
			subscription = emailservice.checkEmailSubscription(user.getEmail());
			if(subscription==true){
			System.out.println("UID...................." + user.getId());
			count = userservice.checkPendingRequests(user.getId());
				System.out.println("Count is ..............." + count);
			if(count>2){

			String id = emailservice.email("PendingRequest","Pending Request",user.getEmail(),"surbhi.singh.ss05@gmail.com",status);
			email.send(null,user.getName(),"singh.surabhi.055@gmail.com","PendingRequest","Pending Request",id,"pendingRequests",count,Thread.currentThread().getName());
				status="Sent";
			
			System.out.println(user.getReminder());
			System.out.println("BEFORE " + user.getName() +  " On " + Thread.currentThread().getName() + "\t" + user.getReminder());
			reminder = true;
			user = userservice.updateReminder(user.getId(), reminder);
			System.out.println("AFTER " + user.getName() +  " On " + Thread.currentThread().getName() + "\t" + user.getReminder());
			
			}

			else{
    				System.out.println("......................." + user.getName() +  "On " + Thread.currentThread().getName());
    				reminder = true;
        			userservice.updateReminder(user.getId(), reminder);
    				status="Failed";
    				emailservice.email("PendingRequest","Pending Request",user.getEmail(),"surbhi.singh.ss05@gmail.com",status);
    			}

		

		}
		}
		 for(User user:users){
			userservice.updateReminder(user.getId(), false);
		  }

	}

}

