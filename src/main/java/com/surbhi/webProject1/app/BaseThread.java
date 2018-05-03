package com.surbhi.webProject1.app;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.surbhi.webProject1.model.User;
import com.surbhi.webProject1.requestService.UserService;


public class BaseThread {
	Thread thread;
	public void start(){
		System.out.println("Starting base thread" + Thread.currentThread().getName());
		//TestThread t = new TestThread();
		
		/*for(int i=0;i<2;i++){
		thread = new Thread(t);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//}
		ExecutorService executor = Executors.newFixedThreadPool(2);
		System.out.println("Started base thread" + Thread.currentThread().getName());
		UserService userservice = new UserService();
		List<User> users = userservice.getAllUsers();
		for(User user:users)
		{
			System.out.println("IN LOOP...........");
        
        System.out.println(user.getName());
       
        
        Runnable worker = new TestThread(user);  
        executor.execute(worker);
		}
		 executor.shutdown();
	}
	
	public void stop(){
		
		if(thread!=null){
			System.out.println("Stoping base thread" + Thread.currentThread().getName());
			thread.interrupt();
			System.out.println(" base thread stopped" + Thread.currentThread().getName());
		}
	}
}
class TestThread implements Runnable {
	
	   private User user;
	   String msg;
	 Boolean reminder = false;
	 UserService userservice = new UserService();
	public TestThread(User user){  
	        this.user=user;  
	        
	    }  
	
	public void run() {
		try{
			Thread.sleep(5000);
			System.out.println("Start " + Thread.currentThread().getName() + user.getName() + " " + msg);
			System.out.println("BEFORE " + user.getName() +  " On " + Thread.currentThread().getName() + "\t" + user.getReminder());
			reminder = true;
			user = userservice.updateReminder(user.getId(), reminder);
			System.out.println("AFTER " + user.getName() +  " On " + Thread.currentThread().getName() + "\t" + user.getReminder());
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}