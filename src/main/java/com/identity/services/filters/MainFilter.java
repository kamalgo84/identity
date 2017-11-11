package com.identity.services.filters;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;

import org.springframework.beans.factory.annotation.Autowired;

import com.identity.services.dtos.User;
import com.identity.utils.Constants;

public class MainFilter implements Filter {

	
	public String entorno;
	
	
	public String getEntorno() {
		return entorno;
	}

	public void setEntorno(String entorno) {
		this.entorno = entorno;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest requestMod = ((HttpServletRequest) request);
		HttpServletResponse responseMod = ((HttpServletResponse) response);
		
		
		
		if(requestMod.getRequestURI().contains("identity_app"))
		{
			//TODO meter control de sesión
			System.out.println(""+requestMod.getRequestURI());
			//System.out.println(""+requestMod.getRequestURL());
			
			HttpSession userSession=requestMod.getSession();
			
			if(userSession.getAttribute(Constants.AUTHENTICATED)==null || 
			   userSession.getAttribute(Constants.AUTHENTICATED)==Boolean.FALSE)
			{
//				requestMod.getSession().setAttribute("requestedPage", requestMod.getRequestURL().toString());
//	            RequestDispatcher noPermited = request.getRequestDispatcher("/forbidden.html");
//	            noPermited.forward(request, response);
	            
	            responseMod.sendRedirect(this.entorno+"/forbidden.html");
			}
			else
			{
				
				if(requestMod.getRequestURI().equalsIgnoreCase("/identity_app/indexCompany.html"))
				{
					User userInfo=(User)userSession.getAttribute(Constants.USER_INFO);
					
					if(userInfo!=null && userInfo.getType()!=null && !userInfo.getType().isEmpty() && userInfo.getType().equalsIgnoreCase(Constants.USER_CUSTOMER))
						responseMod.sendRedirect("/identity_app/indexCustomer.html");
					else if(userInfo.getType().equalsIgnoreCase(Constants.USER_COMPANY))
						chain.doFilter(request, response);
					else
						throw new WebApplicationException();					
				}
				
				if(requestMod.getRequestURI().equalsIgnoreCase("/identity_app/indexCustomer.html"))
				{
					User userInfo=(User)userSession.getAttribute(Constants.USER_INFO);
					
					if(userInfo!=null && userInfo.getType()!=null && !userInfo.getType().isEmpty() && userInfo.getType().equalsIgnoreCase(Constants.USER_CUSTOMER))
						chain.doFilter(request, response);
					else if(userInfo.getType().equalsIgnoreCase(Constants.USER_COMPANY))
						responseMod.sendRedirect("/identity_app/indexCompany.html");
					else
						throw new WebApplicationException();
				}
				else
					chain.doFilter(request, response);
			}
			
			
			
			
		}
		else
		{
			chain.doFilter(request, response);
		}
		
		

	}

	@Override
	public void init(FilterConfig config) throws ServletException 
	{
		if(System.getProperty("entorno")==null || System.getProperty("entorno").equals(""))
			this.entorno="";
		else
			this.entorno=System.getProperty("entorno");

	}

}
