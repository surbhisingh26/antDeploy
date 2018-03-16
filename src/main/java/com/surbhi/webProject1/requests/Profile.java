package com.surbhi.webProject1.requests;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Profile
 */
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html"); 
		PrintWriter out=response.getWriter();  
        
		HttpSession session=request.getSession(false);
		
		String name=(String)session.getAttribute("name");  
		
        if(name!=null){  
        
       
        out.print("<p style='margin-top:50px;margin-left:20px'>Hello, "+name+" Welcome to Profile<p>"); 
        request.getRequestDispatcher("/webProject1/header").include(request, response);
        //out.print("<a href ='home.jsp'>Home</a>");        
        }  
        else{  
            out.print("<p style='margin-top:50px;margin-left:20px'>Please login first");  
            request.getRequestDispatcher("webProject1/login").include(request, response);  
        }  
        out.close();  
    } 
	
    }  

	


