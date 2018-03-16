package com.surbhi.webProject1.template;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

/**
 * Servlet implementation class HandleBars
 */
public class HandleBars extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleBars() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out = response.getWriter();
	
	TemplateLoader loader = new FileTemplateLoader("C:/soft/apache-tomcat-8.5.23/webapps/webProject1/hbs", ".hbs");
	//TemplateLoader loader = new ClassPathTemplateLoader("/hbs", ".hbs");
	Handlebars handlebars = new Handlebars(loader);
	Template template = handlebars.compile("home");
	 Map<String, Object> hmap = new HashMap<String, Object>();
	 hmap.put("fname","Surabhi");
	 hmap.put("lname", "Singh");
	 hmap.put("age",24);
	 hmap.put("address", "agra");
	out.print(template.apply(hmap));
	
	}

}
