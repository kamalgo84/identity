package com.identity.front.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.identity.services.dao.IdentityDAO;
import com.identity.services.dtos.ErrorInfo;
import com.identity.services.dtos.LoginResponse;
import com.identity.services.dtos.RespuestaJSON;
import com.identity.services.dtos.User;
import com.identity.services.dtos.UsersResponse;
import com.identity.utils.Tools;

@Controller
public class MainController {
	
	@Autowired
	HashMap<String,String> map;
	
	@Autowired
	IdentityDAO identityDAO;

	
	@RequestMapping(value = "/login2", method= RequestMethod.POST)
	public ModelAndView showLogin(HttpServletRequest request, 
							HttpServletResponse response,
							@RequestParam(value="user", required = true) String user,
							@RequestParam(value="password", required = true) String password
	) 
	{
		
		
	    //ModelAndView mav = new ModelAndView("index.html");
	   // mav.addObject("login", new Login());
		
//		final String cookieName = "IdentityCookie";
//	    final String cookieValue = "398753470530";  // you could assign it some encoded value
//	    final Boolean useSecureCookie = false;
//	    final int expiryTime = 60 * 60 * 24;  // 24h in seconds
//	    final String cookiePath = "/";
//
//	    Cookie cookie = new Cookie(cookieName, cookieValue);
//
//	    cookie.setSecure(useSecureCookie);  // determines whether the cookie should only be sent using a secure protocol, such as HTTPS or SSL
//
//	    cookie.setMaxAge(expiryTime);  // A negative value means that the cookie is not stored persistently and will be deleted when the Web browser exits. A zero value causes the cookie to be deleted.
//
//	    cookie.setPath(cookiePath);  // The cookie is visible to all the pages in the directory you specify, and all the pages in that directory's subdirectories
//
//	    response.addCookie(cookie);
	    
	    //Establecer cookie en plan manual
	   // response.addHeader("set-cookie", "Cabecera=loko");
		
	    //mav.getModel().put("cookie", cookie);
		
//		ModelAndView mav=new ModelAndView("redirect:/identity_app/home");
//	    return mav;
		
		
				
		password = Tools.generateMD5Signature("URTY2R56C"+password + "DJUHRTFDE");
		
		if(password.equals(map.get(user)))
		{	
		
			HttpSession userSession=request.getSession();
			userSession.setAttribute("Autenticado", Boolean.TRUE);
		    
			return new ModelAndView("redirect:/identity_app/home");
		}
		else
		{
			return new ModelAndView("redirect:/identity_app/home");
		}

	    
	}
	
	
	@RequestMapping(value = "/logout", method= RequestMethod.POST)
	public @ResponseBody
	RespuestaJSON showLogout(HttpServletRequest request, 
							HttpServletResponse response) 
	{
		
		
	    //ModelAndView mav = new ModelAndView("index.html");
	   // mav.addObject("login", new Login());
		
//		final String cookieName = "IdentityCookie";
//	    final String cookieValue = "398753470530";  // you could assign it some encoded value
//	    final Boolean useSecureCookie = false;
//	    final int expiryTime = 60 * 60 * 24;  // 24h in seconds
//	    final String cookiePath = "/";
//
//	    Cookie cookie = new Cookie(cookieName, cookieValue);
//
//	    cookie.setSecure(useSecureCookie);  // determines whether the cookie should only be sent using a secure protocol, such as HTTPS or SSL
//
//	    cookie.setMaxAge(expiryTime);  // A negative value means that the cookie is not stored persistently and will be deleted when the Web browser exits. A zero value causes the cookie to be deleted.
//
//	    cookie.setPath(cookiePath);  // The cookie is visible to all the pages in the directory you specify, and all the pages in that directory's subdirectories
//
//	    response.addCookie(cookie);
	    
	    //Establecer cookie en plan manual
	   // response.addHeader("set-cookie", "Cabecera=loko");
		
		request.getSession().setAttribute("Autenticado", Boolean.FALSE);
		
		request.getSession().invalidate();
		
		RespuestaJSON respuesta=new RespuestaJSON();
		respuesta.setSuccess(true);
		
		return respuesta;
		
	    
//	    ModelAndView mav=new ModelAndView("/WEB-INF/views/logout.jsp");
	    //mav.getModel().put("cookie", cookie);
//	    return mav;
	    
	}
	
	
	@RequestMapping(value = "/login", method= RequestMethod.POST)
	public @ResponseBody
	RespuestaJSON login(HttpServletRequest request, 
							HttpServletResponse response,
							@RequestParam(value="user", required = true) String user,
							@RequestParam(value="password", required = true) String password
	)
	{
		
		RespuestaJSON respuesta=new RespuestaJSON();
		
		
		password = Tools.generateMD5Signature("URTY2R56C"+password + "DJUHRTFDE");
		
		User userIn=new User();
		userIn.setCodigo_usuario(user);
		
		LoginResponse respuestaDAO=identityDAO.getUser(userIn);
				

		if(password.equals(map.get(user)) || 
		  (respuestaDAO.getSalida().equalsIgnoreCase("OK") && respuestaDAO.getUsers()!=null && respuestaDAO.getUsers().size()>0 && password.equals(respuestaDAO.getUsers().get(0).getPassword())))
		{	
		
			HttpSession userSession=request.getSession();
			userSession.setAttribute("Autenticado", Boolean.TRUE);
		    
			respuesta.setSuccess(true);
		}
		else
		{
			respuesta.setSuccess(false);
			respuesta.setErrorInfo(new ErrorInfo(respuestaDAO.getSalida()));
		}
		
		
		return respuesta;
		
	}
	
	@RequestMapping(value = "/addUser", method= RequestMethod.POST)
	public @ResponseBody
	RespuestaJSON addUser(HttpServletRequest request, 
							HttpServletResponse response,
							@RequestParam(value="isCompany", required = true) boolean isCompany,
							@RequestParam(value="user", required = true) String user,
							@RequestParam(value="password", required = true) String password
	)
	{
		password = Tools.generateMD5Signature("URTY2R56C"+password + "DJUHRTFDE");
		
		RespuestaJSON respuesta=new RespuestaJSON();
		User userIn=new User();
		userIn.setCodigo_usuario(user);
		userIn.setPassword(password);
		userIn.setType((isCompany) ? "E" : "C");
		
		UsersResponse respuestaDAO=identityDAO.addUser(userIn);
		
		
		
		if(respuestaDAO.getSalida().equalsIgnoreCase("OK"))
		{	
		
			HttpSession userSession=request.getSession();
			userSession.setAttribute("Autenticado", Boolean.TRUE);
		    
			respuesta.setSuccess(true);
		}
		else
		{
			respuesta.setErrorInfo(new ErrorInfo(respuestaDAO.getSalida()));
			respuesta.setSuccess(false);
		}
		
		
		return respuesta;
		
	}
	
	
	@RequestMapping(value = "/identity_app/home", method = RequestMethod.GET)
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response
	//,@ModelAttribute("login") Login login
	) 
	{
		 ModelAndView mav=new ModelAndView("/identity_app/index.html");
		 return mav;
	}
  
}