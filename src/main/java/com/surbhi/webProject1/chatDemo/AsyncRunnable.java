package com.surbhi.webProject1.chatDemo;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletResponse;

class AsyncRunnable implements Runnable {
	 
    private final AsyncContext asyncContext;
 
    public AsyncRunnable(AsyncContext asyncContext) {
        this.asyncContext = asyncContext;
    }
 
    public void run() {
        HttpServletResponse res = (HttpServletResponse) asyncContext.getResponse();
        PrintWriter pw;
        try {
            System.out.println("About to sleep " + 5 + " seconds");
            // faking a lengthy process
            Thread.sleep(5000L); // sleep 5 seconds
 
            pw = res.getWriter();
            res.setContentType("application/json");
            pw.print("{ \"serverDate\" : " + new Date().getTime() + " }");
            //res.flushBuffer();
            asyncContext.complete();
            System.out.println("After calling complete");
        } 
        catch (Exception e) {
            e.printStackTrace();
            
        }
    }
}
