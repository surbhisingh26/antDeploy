package com.surbhi.webProject1.chatDemo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.CometEvent;
import org.apache.catalina.CometProcessor;

public class TomcatWeatherServlet extends HttpServlet implements CometProcessor {
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MessageSender messageSender = null;
    private static final Integer TIMEOUT = 60 * 1000;
 
    @Override
    public void destroy() {
        messageSender.stop();
        messageSender = null;
 
    }
 
    @Override
    public void init() throws ServletException {
        messageSender = new MessageSender();
        Thread messageSenderThread =
                new Thread(messageSender, "MessageSender[" + getServletContext()
.getContextPath() + "]");
        messageSenderThread.setDaemon(true);
        messageSenderThread.start();
 
    }
 
    public void event(final CometEvent event) throws IOException, ServletException {
        HttpServletRequest request = event.getHttpServletRequest();
        HttpServletResponse response = event.getHttpServletResponse();
        if (event.getEventType() == CometEvent.EventType.BEGIN) {
        	System.out.println("BEGIN");
            request.setAttribute("org.apache.tomcat.comet.timeout", TIMEOUT);
            log("Begin for session: " + request.getSession(true).getId());
            messageSender.setConnection(response);
            Weatherman weatherman = new Weatherman(95118, 32408);
            new Thread(weatherman).start();
        } else if (event.getEventType() == CometEvent.EventType.ERROR) {
        	System.out.println("ERROR");
            log("Error for session: " + request.getSession(true).getId());
            event.close();
        } else if (event.getEventType() == CometEvent.EventType.END) {
        	System.out.println("END");
            log("End for session: " + request.getSession(true).getId());
            event.close();
        } else if (event.getEventType() == CometEvent.EventType.READ) {
        	System.out.println("READ");
            throw new UnsupportedOperationException("This servlet does not accept data");
        }
 
    }
}