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

import org.springframework.beans.factory.annotation.Autowired;

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
			
			if(userSession.getAttribute("Autenticado")==null || userSession.getAttribute("Autenticado")==Boolean.FALSE)
			{
//				requestMod.getSession().setAttribute("requestedPage", requestMod.getRequestURL().toString());
//	            RequestDispatcher noPermited = request.getRequestDispatcher("/forbidden.html");
//	            noPermited.forward(request, response);
	            
	            responseMod.sendRedirect(this.entorno+"/forbidden.html");
			}
			else
			{
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
