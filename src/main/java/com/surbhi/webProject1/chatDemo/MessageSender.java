package com.surbhi.webProject1.chatDemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletResponse;

class MessageSender implements Runnable {
	 
    protected boolean running = true;
    protected final ArrayList<String> messages = new ArrayList<String>();
    private ServletResponse connection;
    @SuppressWarnings("unused")
	synchronized void setConnection(final ServletResponse connection){
    	System.out.println("SET CONNECTION");
        this.connection = connection;
        notify();
    }
    public void send(String message) {
    	System.out.println("SEND");
        synchronized (messages) {
        	System.out.println("MESSAGE SYNCHRONIZED");
            messages.add(message);
            System.out.println("Message added #messages=" + messages.size());
            messages.notify();
        }
    }
    public void run() {
    	System.out.println("RUN");
        while (running) {
        	System.out.println("RUNING");
            if (messages.size() == 0) {
            	System.out.println("MESSAGE SIZE 0");
                try {
                    synchronized (messages) {
                        messages.wait();
                    }
                } catch (InterruptedException e) {
                    // Ignore
                }
            }
            String[] pendingMessages = null;
            synchronized (messages) {
                pendingMessages = messages.toArray(new String[0]);
                messages.clear();
            }
            try {
                if (connection == null){
                    try{
                        synchronized(this){
                            wait();
                        }
                    } catch (InterruptedException e){
                        // Ignore
                    }
                }
                
                PrintWriter writer = connection.getWriter();
                for (int j = 0; j < pendingMessages.length; j++) {
                	System.out.println("pendingMessages[j]");
                    final String forecast = pendingMessages[j] + "<br>";
                    writer.println(forecast);
                    System.out.println("Writing:" + forecast);
                }
                writer.flush();
                writer.close();
                connection = null;
                System.out.println("Closing connection");
            } catch (IOException e) {
            	System.out.println("IOExeption sending message" + e);
            }
        }
    }
	public void stop() {
		stop();
		
	}
}
