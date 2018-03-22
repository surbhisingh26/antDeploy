package com.surbhi.webProject1.tomcat;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServerRestart
 */
public class ServerRestart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServerRestart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		    try{
		       
				/*Socket s = new Socket("localhost",8005);
		        if(s.isConnected()){
		            PrintWriter print = new PrintWriter(s.getOutputStream(),true);
//		            Runtime.getRuntime().exec(System.getProperty("catalina.home")+"\\bin\\shutdown.bat");
		            //Stop tomcat if it is already started
		            print.println("SHUTDOWN");
		            print.close();
		            RestartServer rs = new RestartServer();
			        rs.startTomcat();
		            s.close();
		        }*/
		    	/*ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "start");
		    	File dir = new File("C:/soft/apache-tomcat-8.5.23/bin/restart.bat");
		    	pb.directory(dir);
		    	Process p = pb.start();*/
		        //Run tomcat
		        /*RestartServer rs = new RestartServer();
		        rs.startTomcat();*/
		        //String command = "C:/soft/apache-tomcat-8.5.23/bin/startup.bat";//for linux use .sh
		       // Process child = Runtime.getRuntime().exec(command);
		        String command = "C://soft//apache-tomcat-8.5.23//bin//restart.bat";//for linux use .sh
		    	String[] commands = {"cmd.exe", "/c", "start", "\"Tomcat\"",command};
			     Process child = Runtime.getRuntime().exec(commands);
		    	//Runtime.getRuntime().exec("cmd.exe", "/c", "./com/projct/util/server.bat");
			     }catch (Exception ex){
		        ex.printStackTrace();
		    }
		}
	}


