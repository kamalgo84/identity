package com.identity.services.implementations;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.identity.services.dao.IdentityDAO;
import com.identity.services.dtos.CardRequest;
import com.identity.services.dtos.CardResponse;
import com.identity.services.dtos.DateResponse;
import com.identity.services.dtos.PCard;
import com.identity.services.dtos.User;
import com.identity.utils.Constants;

@Path("/identityServices")
public class IdentityServicesImpl
{
	private static final Logger LOG = Logger.getLogger(IdentityServicesImpl.class);
	
	@Autowired
	private String uploadPath;

	public IdentityServicesImpl() 
	{
		super();
				
	}
	
//	@POST
//    @Path("/addCompany")
//	@Consumes("application/json")
//	@Produces("application/json")
//    public CompanyResponse addCompany(@Context ServletContext servletContext,Company company)
//	{
//		LOG.info("*********LOG********** Entrada servicio addCompany con entrada: "+company);
//		
//		ApplicationContext ctx =
//                WebApplicationContextUtils.getWebApplicationContext(servletContext);
//		IdentityDAO dao= ctx.getBean("identityDAO",IdentityDAO.class);
//	
//		return dao.addCompany(company);	
//	}
	
//	@POST
//    @Path("/addUser")
//	@Consumes("application/json")
//	@Produces("application/json")
//    public LoginResponse addUser(@Context ServletContext servletContext,User user)
//	{
//		LOG.info("*********LOG********** Entrada servicio addUser con entrada: "+user);
//		
//		
//		ApplicationContext ctx =
//                WebApplicationContextUtils.getWebApplicationContext(servletContext);
//		IdentityDAO dao= ctx.getBean("identityDAO",IdentityDAO.class);
//	
//		return dao.addUser(user);
//	}
	
//	@POST
//    @Path("/addCustomer")
//	@Consumes("application/json")
//	@Produces("application/json")
//    public UsersResponse addCustomer(@Context HttpServletRequest request, @Context ServletContext servletContext,Customer user)
//	{
//		LOG.info("*********LOG********** Entrada servicio addCustomer con entrada: "+user);
//		
//		user.setCodigo_usuario(null);
//		
//		ApplicationContext ctx =
//                WebApplicationContextUtils.getWebApplicationContext(servletContext);
//		IdentityDAO dao= ctx.getBean("identityDAO",IdentityDAO.class);
//	
//		
//		
//		User userInfo=(User)request.getSession().getAttribute(Constants.USER_INFO);
//		
//		if(userInfo!=null && 
//		   userInfo.getCodigo_usuario()!=null && 
//		   !userInfo.getCodigo_usuario().isEmpty())
//			user.setCodigo_usuario(userInfo.getCodigo_usuario());
//		else
////			throw new WebApplicationException();
//			//TODO Metemos usuario de pruebas para hacer tests de los serviocios, esto deberia ser una excepcion
//			user.setCodigo_usuario(Constants.MOCK_CUSTOMER);
//		
//		return dao.addCustomer(user);
//	
//	}

	@POST
    @Path("/getUser")
	@Consumes("application/json")
	@Produces("application/json")
    public Response getUser(@Context HttpServletRequest request, @Context ServletContext servletContext)
	{
		LOG.info("*********LOG********** Entrada servicio getUser con entrada: Sin Argumentos");
		
		ApplicationContext ctx =
                WebApplicationContextUtils.getWebApplicationContext(servletContext);
		IdentityDAO dao= ctx.getBean("identityDAO",IdentityDAO.class);
		
	    return dao.getUser((User)request.getSession().getAttribute(Constants.USER_INFO),false);
        
	}
	
//	@POST
//    @Path("/getUsers")
//	@Consumes("application/json")
//	@Produces("application/json")
//    public UsersResponse getUsers(@Context HttpServletRequest request,@Context ServletContext servletContext)
//	{
//		LOG.info("*********LOG********** Entrada servicio getUsers sin argumentos");
//		
//		User userInfo=(User)request.getSession().getAttribute(Constants.USER_INFO);
//		
//		if(userInfo==null || 
//		   userInfo.getId()==null)
//		{
////			throw new WebApplicationException();
//			//TODO Metemos usuario de pruebas para hacer tests de los serviocios, esto deberia ser una excepcion
//			userInfo=new User();
//			userInfo.setId(Constants.MOCK_CUSTOMER);
//		}
//		
//		ApplicationContext ctx =
//                WebApplicationContextUtils.getWebApplicationContext(servletContext);
//		IdentityDAO dao= ctx.getBean("identityDAO",IdentityDAO.class);
//	
//
//	    return dao.getUsers(userInfo);
//		
//        
//	}
//	
//	@POST
//    @Path("/getCompanies")
//	@Consumes("application/json")
//	@Produces("application/json")
//    public CompanyResponse getCompanies(@Context ServletContext servletContext)
//	{
//		LOG.info("*********LOG********** Entrada servicio getCompanies sin argumentos");
//		
//		ApplicationContext ctx =
//                WebApplicationContextUtils.getWebApplicationContext(servletContext);
//		IdentityDAO dao= ctx.getBean("identityDAO",IdentityDAO.class);
//	
//		return dao.getCompanies();
//       
//	}

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
	public CardResponse getCard(CardRequest peticion,@Context HttpServletRequest request, @Context ServletContext servletContext) {
		
		LOG.info("*********LOG********** Entrada servicio getCard con entrada: "+peticion);
		
		ApplicationContext ctx =
                WebApplicationContextUtils.getWebApplicationContext(servletContext);
		IdentityDAO dao= ctx.getBean("identityDAO",IdentityDAO.class);
	
		return dao.getCard(peticion,(String)request.getSession().getAttribute(Constants.USER_ID));
	}
	
	@POST
    @Path("/addPCard")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addPCard(PCard peticion,@Context HttpServletRequest request, @Context ServletContext servletContext) {
		
		LOG.info("*********LOG********** Entrada servicio addPCard con entrada: "+peticion);
		
		ApplicationContext ctx =
                WebApplicationContextUtils.getWebApplicationContext(servletContext);
		IdentityDAO dao= ctx.getBean("identityDAO",IdentityDAO.class);
		
		peticion.setOwner((User)request.getSession().getAttribute(Constants.USER_INFO));
	
		return dao.addPCard(peticion);
	}
	
	@POST
    @Path("/attachPCard")
	@Consumes("application/json")
	@Produces("application/json")
	public Response attachPCard(PCard peticion,@Context HttpServletRequest request, @Context ServletContext servletContext) {
		
		LOG.info("*********LOG********** Entrada servicio attachPCard con entrada: "+peticion);
		
		ApplicationContext ctx =
                WebApplicationContextUtils.getWebApplicationContext(servletContext);
		IdentityDAO dao= ctx.getBean("identityDAO",IdentityDAO.class);
		
		return dao.attachPCard(peticion,((User)request.getSession().getAttribute(Constants.USER_INFO)).getId());
	}
	
	@POST
    @Path("/getPCards")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getPCards(@Context HttpServletRequest request, @Context ServletContext servletContext) {
		
		LOG.info("*********LOG********** Entrada servicio getUserCards con entrada vacia");
		
		ApplicationContext ctx =
                WebApplicationContextUtils.getWebApplicationContext(servletContext);
		IdentityDAO dao= ctx.getBean("identityDAO",IdentityDAO.class);
	
		return dao.getPCards(((User)request.getSession().getAttribute(Constants.USER_INFO)).getId());
	}
	
	@POST
    @Path("/getAttachedPCards")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getAttachedPCards(@Context HttpServletRequest request, @Context ServletContext servletContext) {
		
		LOG.info("*********LOG********** Entrada servicio getAttachedPCards con entrada vacia");
		
		ApplicationContext ctx =
                WebApplicationContextUtils.getWebApplicationContext(servletContext);
		IdentityDAO dao= ctx.getBean("identityDAO",IdentityDAO.class);
	
		return dao.getAttachedPCards(((User)request.getSession().getAttribute(Constants.USER_INFO)).getId());
	}
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/uploadFile")
	public Response uploadFile(@Context ServletContext servletContext, MultipartFormDataInput attachment)
	{
		LOG.info("*********LOG********** Entrada servicio uploadFile con entrada: "+attachment);
		
		ApplicationContext ctx =
                WebApplicationContextUtils.getWebApplicationContext(servletContext);
		String UPLOADED_FILE_PATH= ctx.getBean("uploadPath",String.class);
					
		String fileName = "";

			Map<String, List<InputPart>> uploadForm = attachment.getFormDataMap();
			List<InputPart> inputParts = uploadForm.get("uploadedFile");

			for (InputPart inputPart : inputParts) {

			 try {

				MultivaluedMap<String, String> header = inputPart.getHeaders();
				fileName = getFileName(header);

				//convert the uploaded file to inputstream
				InputStream inputStream = inputPart.getBody(InputStream.class,null);

				byte [] bytes = IOUtils.toByteArray(inputStream);

				//constructs upload file path
				fileName = UPLOADED_FILE_PATH + fileName;

				writeFile(bytes,fileName);

				System.out.println("Done");

			  } catch (IOException e) {
				e.printStackTrace();
			  }

			}

			return Response.status(200)
			    .entity("uploadFile is called, Uploaded file name : " + fileName).build();

		}



		
		
      


/**
 * header sample
 * {
 * 	Content-Type=[image/png],
 * 	Content-Disposition=[form-data; name="file"; filename="filename.extension"]
 * }
 **/
//get uploaded filename, is there a easy way in RESTEasy?
private String getFileName(MultivaluedMap<String, String> header) {

	String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

	for (String filename : contentDisposition) {
		if ((filename.trim().startsWith("filename"))) {

			String[] name = filename.split("=");

			String finalFileName = name[1].trim().replaceAll("\"", "");
			return finalFileName;
		}
	}
	return "unknown";
}

//save to somewhere
private void writeFile(byte[] content, String filename) throws IOException {

	File file = new File(filename);

	if (!file.exists()) {
		file.createNewFile();
	}

	FileOutputStream fop = new FileOutputStream(file);

	fop.write(content);
	fop.flush();
	fop.close();
}
	
//	@POST
//    @Path("/getCardBytes")
//	@Consumes("application/json")
//	@Produces("application/json")
//	public CardBytesResponse getCardBytes(CardRequest peticion) {
//
//		CardBytesResponse respuesta=new CardBytesResponse();
//		
//
//		File imgPath = new File("/var/lib/openshift/58fa6cb289f5cf70bb000010/app-root/runtime/repo/src/main/webapp/images/jbosscorp_logo.png");
//		 
//		 
//		BufferedImage bufferedImage;
//		
//		
//		
//		try 
//		{
//			if(peticion.getId().equalsIgnoreCase("0123456789"))
//			{
//				 sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
//				 bufferedImage = ImageIO.read(imgPath);
//				 // get DataBufferBytes from Raster
//				 WritableRaster raster = bufferedImage .getRaster();
//				 DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
//				 
//				 byte[] encoded = Base64.encodeBytesToBytes(data.getData());
//				 String encoded2=encoder.encode(data.getData());
//				 
//				 
//				 String pdfBase64String =
//						 StringUtils.newStringUtf8(org.apache.
//						 commons.codec.binary.Base64.encodeBase64(data.getData()));
//				 
//				
//				 respuesta.setImageString(pdfBase64String);
//				 respuesta.setImageBase64String(encoded2);
//				 respuesta.setImageBytes(data.getData());
//				 respuesta.setId("0123456789");
//				 return respuesta;
//			}
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//		
//		
//		 return null;
//	}


}
