package com.surbhi.webProject1.tomcat;



public class RestartServer {
	public void startTomcat(){
	    try{
	       
			//Socket s = new Socket("localhost",8080);
	        /*if(s.isConnected()){
	            PrintWriter print = new PrintWriter(s.getOutputStream(),true);
	            //Stop tomcat if it is already started
	            print.println("SHUTDOWN");
	            print.close();
	            s.close();
	        }
	        //Run tomcat
*/	       //Runtime.getRuntime().exec(System.getProperty("catalina.home")+"\\bin\\startup.bat");
	    	String command = "C://soft//apache-tomcat-8.5.23//bin//startup.bat";//for linux use .sh
	    	 String[] commands = {"cmd", "/c", "start", "\"Tomcat\"",command};
		       Process child = Runtime.getRuntime().exec(commands);

	    }catch (Exception ex){
	        ex.printStackTrace();
	    }
	}

}
