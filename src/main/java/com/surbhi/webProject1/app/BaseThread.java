package com.surbhi.webProject1.app;

public class BaseThread {

	Thread thread = null;
	public void start(){
		System.out.println("Starting base thread" + Thread.currentThread().getName());
		TestThread t = new TestThread();
		thread = new Thread(t);
		thread.start();
		System.out.println("Started base thread" + Thread.currentThread().getName());
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
	
	public void run() {
		try{
		for(int i = 0; i < 10; i++)
		System.out.println("Thread " + Thread.currentThread().getName() + " Running......");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}