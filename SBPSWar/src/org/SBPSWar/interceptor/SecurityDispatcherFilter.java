package org.SBPSWar.interceptor;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.FilterDispatcher;


public class SecurityDispatcherFilter extends FilterDispatcher {
	
	/*private SessionFactory sesFactory;
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
		try {
			HibernateUtil.createSessionFactory();
			System.out.print(“application initializing successfully”);
		} catch (HibernateException e) {
			throw new ServletException(e);
		}

	}*/
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
	}	

	/**
	 * @see Filter#init(FilterConfig)
	 
	public void init(FilterConfig fConfig) throws ServletException {
		
		log.debug("Getting Hibernate Session Factory");
		this.sesFactory = HibernateSessionFactory.getSessionFactory();
	}
	*/
	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
				
				System.out.println("I'm on my way in");
		
			    // Call the next filter (continue request processing)
			    super.doFilter( request, response, chain);
			    
			    System.out.println("I'm on my way out");
			    
			    //System.out.print( " remoteAddr =  " + request.getRemoteAddr());
			    
			    //System.out.print( " remoteHost =  " + request.getRemoteHost());
			    
			    System.out.println( " serverName =  " + request.getServerName());
			    
			   // System.out.print( " realPath =  " + ((HttpServletRequest)request).getSession().getServletContext().getRealPath("/"));
			    
			    //System.out.print( " pathInfo =  " + ((HttpServletRequest)request).getPathInfo());
			    
			    //System.out.print( " translatedPath =  " + ((HttpServletRequest)request).getPathTranslated());
			    
			    System.setProperty("serverName", request.getServerName());

	}
	

}
