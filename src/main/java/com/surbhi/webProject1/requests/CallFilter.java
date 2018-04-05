package com.surbhi.webProject1.requests;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class CallFilter implements Filter{
	

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("filtering path: " + path);
		if(!path.contains(".")) {
			request.getRequestDispatcher("/paths" + path).forward(request, response);
			
		}
		
		chain.doFilter(request, response);
		
	}
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
