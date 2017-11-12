package com.identity.services.implementations;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.log4j.Logger;
import org.jboss.resteasy.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.identity.services.dao.IdentityDAO;
import com.identity.services.dtos.CardBytesResponse;
import com.identity.services.dtos.CardRequest;
import com.identity.services.dtos.CardResponse;
import com.identity.services.dtos.Company;
import com.identity.services.dtos.CompanyResponse;
import com.identity.services.dtos.DateResponse;
import com.identity.services.dtos.LoginResponse;
import com.identity.services.dtos.User;
import com.identity.services.dtos.Customer;
import com.identity.services.dtos.UsersResponse;
import com.identity.utils.Constants;
import com.identity.utils.Tools;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

@Path("/identityServices")
public class IdentityServicesImpl
{
	private static final Logger LOG = Logger.getLogger(IdentityServicesImpl.class);

	public IdentityServicesImpl() 
	{
		super();
				
	}
	
	@POST
    @Path("/addCompany")
	@Consumes("application/json")
	@Produces("application/json")
    public CompanyResponse addCompany(@Context ServletContext servletContext,Company company)
	{
		LOG.info("*********LOG********** Entrada servicio addCompany con entrada: "+company);
		
		ApplicationContext ctx =
                WebApplicationContextUtils.getWebApplicationContext(servletContext);
		IdentityDAO dao= ctx.getBean("identityDAO",IdentityDAO.class);
	
		return dao.addCompany(company);	
	}
	
	@POST
    @Path("/addUser")
	@Consumes("application/json")
	@Produces("application/json")
    public LoginResponse addUser(@Context ServletContext servletContext,User user)
	{
		LOG.info("*********LOG********** Entrada servicio addUser con entrada: "+user);
		
		
		ApplicationContext ctx =
                WebApplicationContextUtils.getWebApplicationContext(servletContext);
		IdentityDAO dao= ctx.getBean("identityDAO",IdentityDAO.class);
	
		return dao.addUser(user);
	}
	
	@POST
    @Path("/addCustomer")
	@Consumes("application/json")
	@Produces("application/json")
    public UsersResponse addCustomer(@Context HttpServletRequest request, @Context ServletContext servletContext,Customer user)
	{
		LOG.info("*********LOG********** Entrada servicio addCustomer con entrada: "+user);
		
		user.setCodigo_usuario(null);
		
		ApplicationContext ctx =
                WebApplicationContextUtils.getWebApplicationContext(servletContext);
		IdentityDAO dao= ctx.getBean("identityDAO",IdentityDAO.class);
	
		
		
		User userInfo=(User)request.getSession().getAttribute(Constants.USER_INFO);
		
		if(userInfo!=null && 
		   userInfo.getCodigo_usuario()!=null && 
		   !userInfo.getCodigo_usuario().isEmpty())
			user.setCodigo_usuario(userInfo.getCodigo_usuario());
		else
//			throw new WebApplicationException();
			//TODO Metemos usuario de pruebas para hacer tests de los serviocios, esto deberia ser una excepcion
			user.setCodigo_usuario(Constants.MOCK_CUSTOMER);
		
		return dao.addCustomer(user);
	
	}

	@POST
    @Path("/getUser")
	@Consumes("application/json")
	@Produces("application/json")
    public LoginResponse getUser(@Context ServletContext servletContext,User user)
	{
		LOG.info("*********LOG********** Entrada servicio getUser con entrada: "+user);
		
		ApplicationContext ctx =
                WebApplicationContextUtils.getWebApplicationContext(servletContext);
		IdentityDAO dao= ctx.getBean("identityDAO",IdentityDAO.class);
	

	    return dao.getUser(user);
        
	}
	
	@POST
    @Path("/getUsers")
	@Consumes("application/json")
	@Produces("application/json")
    public UsersResponse getUsers(@Context HttpServletRequest request,@Context ServletContext servletContext)
	{
		LOG.info("*********LOG********** Entrada servicio getUsers sin argumentos");
		
		User userInfo=(User)request.getSession().getAttribute(Constants.USER_INFO);
		
		if(userInfo==null || 
		   userInfo.getCodigo_usuario()==null || 
		   !userInfo.getCodigo_usuario().isEmpty())
		{
//			throw new WebApplicationException();
			//TODO Metemos usuario de pruebas para hacer tests de los serviocios, esto deberia ser una excepcion
			userInfo=new User();
			userInfo.setCodigo_usuario(Constants.MOCK_CUSTOMER);
		}
		
		ApplicationContext ctx =
                WebApplicationContextUtils.getWebApplicationContext(servletContext);
		IdentityDAO dao= ctx.getBean("identityDAO",IdentityDAO.class);
	

	    return dao.getUsers(userInfo);
		
        
	}
	
	@POST
    @Path("/getCompanies")
	@Consumes("application/json")
	@Produces("application/json")
    public CompanyResponse getCompanies(@Context ServletContext servletContext)
	{
		LOG.info("*********LOG********** Entrada servicio getCompanies sin argumentos");
		
		ApplicationContext ctx =
                WebApplicationContextUtils.getWebApplicationContext(servletContext);
		IdentityDAO dao= ctx.getBean("identityDAO",IdentityDAO.class);
	
		return dao.getCompanies();
       
	}

	@POST
    @Path("/getServerDate")
	@Consumes("application/json")
	@Produces("application/json")
	public DateResponse getServerDate() {
		
		return new DateResponse();
	}

	@POST
    @Path("/getCard")
	@Consumes("application/json")
	@Produces("application/json")
	public CardResponse getCard(CardRequest peticion) {
		
		LOG.info("*********LOG********** Entrada servicio getCard con entrada: "+peticion);
		

		CardResponse respuesta=new CardResponse();
		
		if(peticion.getId().equalsIgnoreCase("0123456789"))
		{
			 
			 respuesta.setImage("http://identity-identity.193b.starter-ca-central-1.openshiftapps.com/images/jbosscorp_logo.png");
			 respuesta.setId("0123456789");
		}
		else
		{
			 respuesta=null;
		}
		
		LOG.info("*********LOG********** Salida servicio getCard con entrada: "+respuesta);
			 
		return respuesta;

	}
	
	@POST
    @Path("/getCardBytes")
	@Consumes("application/json")
	@Produces("application/json")
	public CardBytesResponse getCardBytes(CardRequest peticion) {

		CardBytesResponse respuesta=new CardBytesResponse();
		

		File imgPath = new File("/var/lib/openshift/58fa6cb289f5cf70bb000010/app-root/runtime/repo/src/main/webapp/images/jbosscorp_logo.png");
		 
		 
		BufferedImage bufferedImage;
		
		
		
		try 
		{
			if(peticion.getId().equalsIgnoreCase("0123456789"))
			{
				 sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
				 bufferedImage = ImageIO.read(imgPath);
				 // get DataBufferBytes from Raster
				 WritableRaster raster = bufferedImage .getRaster();
				 DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
				 
				 byte[] encoded = Base64.encodeBytesToBytes(data.getData());
				 String encoded2=encoder.encode(data.getData());
				 
				 
				 String pdfBase64String =
						 StringUtils.newStringUtf8(org.apache.
						 commons.codec.binary.Base64.encodeBase64(data.getData()));
				 
				
				 respuesta.setImageString(pdfBase64String);
				 respuesta.setImageBase64String(encoded2);
				 respuesta.setImageBytes(data.getData());
				 respuesta.setId("0123456789");
				 return respuesta;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		 return null;
	}


}
