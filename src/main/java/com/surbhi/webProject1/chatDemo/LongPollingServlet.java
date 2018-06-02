package com.surbhi.webProject1.chatDemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(name = "LongPollingServlet",
        asyncSupported = true,
        urlPatterns = {"/longPollingServlet"})
public class LongPollingServlet extends HttpServlet {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("LongPollingServlet.doGet()");
 
        boolean asyncSupported = req.isAsyncSupported();
        System.out.println("asyncSupported 1: " + asyncSupported);
        // Tomcat only
        req.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
        asyncSupported = req.isAsyncSupported();
        System.out.println("asyncSupported 2: " + asyncSupported);
        if (asyncSupported) {
            AsyncContext asyncCtx = req.startAsync(req, res);  // req.startAsync();
            asyncCtx.setTimeout(0); // => disable timeout
            AsyncRunnable thread = new AsyncRunnable(asyncCtx);
            asyncCtx.start(thread);
        }
    }
}
 
