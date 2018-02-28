package webProject1.requests;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream.Filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * Servlet implementation class MyFilter
 */
public class MyFilter implements Filter{  
    static int count=0;  
    public void init(FilterConfig arg0) throws ServletException {}  
  
    public void doFilter(ServletRequest req, ServletResponse res,  
            FilterChain chain) throws IOException, ServletException {  
      
        PrintWriter out=res.getWriter();  
        chain.doFilter(req,res);  
          
        out.print("<br/>Total visitors "+(++count));  
        out.close();  
          
    }  
    public void destroy() {}

	public boolean accept(Object entry) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}  
}  

