/*package com.surbhi.webProject1.requests;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

*//**
 * Servlet implementation class Profile
 *//*
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();  
        
		HttpSession session=request.getSession(false);
		
		String name=(String)session.getAttribute("name"); 
		
		
        if(name!=null){  
        
        	TemplateLoader loader = new FileTemplateLoader("C:/soft/apache-tomcat-8.5.23/webapps/webProject1/Templates", ".hbs");
    		Handlebars handlebars = new Handlebars(loader);
    		Template template = handlebars.compile("header");
    		Map<String, Object> hmap = new HashMap<String, Object>();
    		
    			hmap.put("login",false);
    			hmap.put("name", name);
    		
    		out.print(template.apply(hmap));
        out.print("<p style='margin-top:50px;margin-left:20px'>Hello, "+name+" Welcome to Profile<p>"); 
        
        //out.print("<a href ='home.jsp'>Home</a>");        
        }  
        else{  
            out.print("<p style='margin-top:50px;margin-left:20px'>Please login first");  
            request.getRequestDispatcher("/login").include(request, response);  
        }  
        out.close();  
    } 
	
    }  

	


*/