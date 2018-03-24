package com.identity.services.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.ws.rs.core.Response;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.identity.services.dtos.Card;
import com.identity.services.dtos.CardRequest;
import com.identity.services.dtos.CardResponse;
import com.identity.services.dtos.Company;
import com.identity.services.dtos.CompanyResponse;
import com.identity.services.dtos.Customer;
import com.identity.services.dtos.LoginResponse;
import com.identity.services.dtos.PCard;
import com.identity.services.dtos.PCardInstance;
import com.identity.services.dtos.User;
import com.identity.services.dtos.UserCardsResponse;
import com.identity.services.dtos.UsersResponse;
import com.identity.utils.Constants;
import com.identity.utils.Tools;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class IdentityDAO {
	
	private static final Logger LOG = Logger.getLogger(IdentityDAO.class);
	
//	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private String query1;
	
	@Autowired
	private String query2;
	
	@Autowired
	private String query3;
	
	@Autowired
	private String query4;
	
	@Autowired
	private String query5;
	
	@Autowired
	private String query6;
	
	@Autowired
	private String query7;
	
	@Autowired
	private String query8;
	
	@Autowired
	private String query9;
	
	@Autowired
	private String query10;
	
	@Autowired
	private String query11;
	
	@Autowired
	private String query12;
	
	@Autowired
	private String query13;
	
	public DataSource getDataSource() {return dataSource;}

	public void setDataSource(DataSource dataSource) {this.dataSource = dataSource;}

	public Response getPCards(Long userId)
	{

		LOG.info("*********LOG********** Entrada servicio getUserCards con entrada: "+userId);
	    
		UserCardsResponse respuesta=new UserCardsResponse();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		
		try 
		{
	        preparedStatement = dataSource.getConnection()
                    .prepareStatement(query10);

	        preparedStatement.setLong(1,userId);
	        
	        resultSet=preparedStatement.executeQuery();
            
            int cont=0;
            while (resultSet!=null && resultSet.next()) 
            {
            	cont++;
            	LOG.info("*********LOG********** Procesando registro: "+cont);
            	
            	User user=new User();
            	user.setId(resultSet.getLong("USERID"));
            	PCard card=new PCard();
            	card.setImageUri(resultSet.getString("IMAGE_URI"));
            	card.setId(resultSet.getLong("ID_PCARD"));
            	card.setAlias(resultSet.getString("ALIAS"));
            	card.setCanBeShared((resultSet.getString("SHAREABLE").equalsIgnoreCase("0"))? false: true);
            	card.setOwner(user);
            	card.setTemplate(resultSet.getLong("ID_TEMPLATE"));
            	card.setType(resultSet.getString("TYPE"));
            	           	
            	respuesta.getCards().add(card);
   			 
            }
            
            preparedStatement.close();
            
            return Response.status(Response.Status.OK).entity(respuesta).build();
	            
		}
		catch (SQLException e) 
		{
			respuesta.setSalida("Error SQL: "+e.getErrorCode()+"*********"+e.getMessage()+"*********"+e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
		}
		catch (Exception ex) {

			respuesta.setSalida("Error SQL: "+ex.getMessage()+"*********"+ex);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
		}
		

	}
	
	public Response getAttachedPCards(Long userId)
	{

		LOG.info("*********LOG********** Entrada servicio getAttachedPCards con entrada: "+userId);
	    
		List<PCardInstance> respuesta=new ArrayList<PCardInstance>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		
		try 
		{
	        preparedStatement = dataSource.getConnection()
                    .prepareStatement(query13);

	        preparedStatement.setLong(1,userId);
	        
	        resultSet=preparedStatement.executeQuery();
            
            int cont=0;
            while (resultSet!=null && resultSet.next()) 
            {
            	cont++;
            	LOG.info("*********LOG********** Procesando registro: "+cont);
            	
            	User userOwner=new User();
            	userOwner.setId(resultSet.getLong("OWNER"));
            	PCard card=new PCard();
            	card.setImageUri(resultSet.getString("IMAGE_URI"));
            	card.setId(resultSet.getLong("ID_PCARD"));
            	card.setAlias(resultSet.getString("ALIAS"));
            	card.setCanBeShared((resultSet.getString("SHAREABLE").equalsIgnoreCase("0"))? false: true);
            	card.setOwner(userOwner);
            	card.setTemplate(resultSet.getLong("ID_TEMPLATE"));
            	card.setType(resultSet.getString("TYPE"));
            	
            	User user=new User();
            	user.setId(resultSet.getLong("USERID"));
            	
            	PCardInstance pcardInstance=new PCardInstance();
            	pcardInstance.setIdPCard(card);
            	pcardInstance.setUserId(user);
            	pcardInstance.setId(resultSet.getLong("ID_INST_PCARD"));
            	           	
            	respuesta.add(pcardInstance);
   			 
            }
            
            preparedStatement.close();
            
            return Response.status(Response.Status.OK).entity(respuesta).build();
	            
		}
		catch (SQLException e) 
		{
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL: "+e.getErrorCode()+"*********"+e.getMessage()+"*********"+e).build();
		}
		catch (Exception ex) 
		{
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL: "+ex.getMessage()+"*********"+ex).build();
		}
		

	}
	
	public CardResponse getCard(CardRequest peticion, String userId)
	{

		LOG.info("*********LOG********** Entrada servicio getCard con entrada: "+peticion);
	    
		CardResponse respuesta=new CardResponse();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		
		try 
		{
	        preparedStatement = dataSource.getConnection()
                    .prepareStatement(query9);

	        preparedStatement.setString(1,userId);
	        preparedStatement.setString(2,peticion.getId());
	        
	        resultSet=preparedStatement.executeQuery();
            
            
            
            int cont=0;
            while (resultSet!=null && resultSet.next()) 
            {
            	cont++;
            	LOG.info("*********LOG********** Procesando registro: "+cont);
            	
            	if(cont==1)
            	{
	            	Card card=new Card();
	            	card.setImageURI(resultSet.getString("URI"));
	            	card.setId(resultSet.getString("ID_PCARD"));
	            	
	            	respuesta.setCard(card);
            	}
   			 
            }
            
            preparedStatement.close();
            
    		return respuesta;
	            
		}
		catch (SQLException e) 
		{
			respuesta.setSalida("Error SQL: "+e.getErrorCode()+"*********"+e.getMessage()+"*********"+e);
			return respuesta;
		}
		catch (Exception ex) {

			respuesta.setSalida("Error SQL: "+ex.getMessage()+"*********"+ex);
			return respuesta;
		}
		

	}
	
	
	public Response attachPCard(PCard pcard, Long userId)
	{
		LOG.info("*********LOG********** Entrada servicio addPCard con entrada: "+pcard);
		
		PreparedStatement preparedStatement = null;
	    ResultSet resultSet=null;
	    
	    if(pcard==null || pcard.getId()==null)
 		{
 	    	return Response.status(Response.Status.BAD_REQUEST).entity("KO - Parametros obligatorios no informados").build();
 			
 		}
	    
	    Response pcards=this.getPCards(userId);
	    
	    if(pcards.getStatus()==Response.Status.OK.getStatusCode())
	    {
	    	for(PCard item: ((UserCardsResponse) pcards.getEntity()).getCards())
	    	{
	    		if(item.getId()==pcard.getId())
	    			return Response.status(Response.Status.BAD_REQUEST).entity("KO - La PCard pertenece al usuario, no puede ser asociada").build();
	    	}
	    }
	    else
	    	return pcards;
	    
	    
	    Response pcardsAttached=this.getAttachedPCards(userId);
	    
	    if(pcardsAttached.getStatus()==Response.Status.OK.getStatusCode())
	    {
	    	for(PCardInstance item: ((List<PCardInstance>) pcardsAttached.getEntity()))
	    	{
	    		if(item.getId()==pcard.getId())
	    			return Response.status(Response.Status.BAD_REQUEST).entity("KO - La PCard ya esta asociada al usuario").build();
	    	}
	    }
	    else
	    	return pcardsAttached;
	    
	    
	    
	    try 
		{
	        
	        preparedStatement = dataSource.getConnection()
                    .prepareStatement(query12);


	        preparedStatement.setLong(1,pcard.getId());
	        preparedStatement.setLong(2,userId);
            preparedStatement.executeUpdate();
            
        	resultSet=preparedStatement.getGeneratedKeys();
        	
        	Long id=null;
            while (resultSet!=null && resultSet.next()) 
            {
            	id=resultSet.getLong(1);   			 
            }
            
            
            preparedStatement.close();
            
            PCardInstance pcardInstance=new PCardInstance();
            pcardInstance.setId(id);
            
            return Response.status(Response.Status.OK).entity(pcardInstance).build();
	            
		}
		catch (MySQLIntegrityConstraintViolationException e) {
	
			return Response.status(Response.Status.BAD_REQUEST).entity("ERROR: PCard Inexistente").build();
		}
		catch (SQLException e) 
		{
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL: "+e.getErrorCode()+"*********"+e.getMessage()+"*********"+e).build();
		}
		catch (Exception ex) {

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL: "+ex.getMessage()+"*********"+ex).build();
		}
	    
	    
	    
	    
	}
	
	public Response addPCard(PCard pcard)
	{
		LOG.info("*********LOG********** Entrada servicio addPCard con entrada: "+pcard);
		
		PreparedStatement preparedStatement = null;
	    ResultSet resultSet=null;
	    
	    if(pcard==null || 
	       Tools.isBlank(pcard.getType())||
	       Tools.isBlank(pcard.getImageUri())|| 
	       pcard.getOwner()==null|| pcard.getOwner().getId()==null ||
	       pcard.getTemplate()==null)
		{
	    	return Response.status(Response.Status.BAD_REQUEST).entity("KO - Parametros obligatorios no informados").build();
			
		}
	    
	    if(!Tools.isValidURL(pcard.getImageUri()))
	    	return Response.status(Response.Status.BAD_REQUEST).entity("KO - URL con formato invalido").build();
	    
	    //De momento los metemos a 0 ya que no tenemos definidos los templates ni los tipos
	    pcard.setTemplate(0l);
	    pcard.setType("0");
	    pcard.setCanBeShared(false);
	    
	    
	    try 
		{
	        
	        preparedStatement = dataSource.getConnection()
                    .prepareStatement(query11);


	        preparedStatement.setLong(1,pcard.getOwner().getId());
	        preparedStatement.setString(2,pcard.getType().trim());
	        preparedStatement.setString(3,pcard.getImageUri().trim());
	        preparedStatement.setLong(4,pcard.getTemplate());
	        preparedStatement.setString(5,(pcard.isCanBeShared()) ? "1" : "0" );
	        preparedStatement.setString(6,pcard.getAlias());
            preparedStatement.executeUpdate();
            

        	resultSet=preparedStatement.getGeneratedKeys();
        	
        	Long id=null;
            while (resultSet!=null && resultSet.next()) 
            {
            	id=resultSet.getLong(1);   			 
            }
            
            
            preparedStatement.close();
            
            pcard=new PCard();
            pcard.setId(id);
            
            return Response.status(Response.Status.OK).entity(pcard).build();
	            
		}
		catch (MySQLIntegrityConstraintViolationException e) {
	
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR: Usuario, Tipo o Plantilla inexistente").build();
		}
		catch (SQLException e) 
		{
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL: "+e.getErrorCode()+"*********"+e.getMessage()+"*********"+e).build();
		}
		catch (Exception ex) {

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL: "+ex.getMessage()+"*********"+ex).build();
		}
		
	}
	
	public LoginResponse addUser(User user)
	{
	
		LOG.info("*********LOG********** Entrada servicio addUser con entrada: "+user);
	
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet=null;
	    LoginResponse usersResponse=new LoginResponse();
		
		if(Tools.isBlank(user.getMail())||Tools.isBlank(user.getPassword())||Tools.isBlank(user.getType())||
				(!user.getType().equalsIgnoreCase(Constants.USER_CUSTOMER) && !user.getType().equalsIgnoreCase(Constants.USER_COMPANY)))
		{
			usersResponse.setSalida("KO - Parametros obligatorios no informados");
			usersResponse.setSuccess(false);
			return usersResponse;
			
		}
		
		try 
		{
	        
	        preparedStatement = dataSource.getConnection()
                    .prepareStatement(query1);


	        preparedStatement.setString(1,user.getMail().trim());
	        preparedStatement.setString(2,user.getPassword().trim());
	        preparedStatement.setString(3,user.getType().trim());
            preparedStatement.executeUpdate();
            

        	resultSet=preparedStatement.getGeneratedKeys();
        	
        	Long id=null;
            while (resultSet!=null && resultSet.next()) 
            {
            	id=resultSet.getLong(1);   			 
            }
            
            
            preparedStatement.close();
            

            
        	
            

            user.setPassword("******");
            user.setId(id);
            List<User> usuariosSalida=new ArrayList<User>();
            usuariosSalida.add(user);
            usersResponse.setUsers(usuariosSalida);
            usersResponse.setSalida("OK");
            usersResponse.setSuccess(true);
            return usersResponse;
	            
		}
		catch (MySQLIntegrityConstraintViolationException e) {
	
			usersResponse.setSalida("ERROR: Usuario existente");
			usersResponse.setSuccess(false);
			return usersResponse;
		}
		catch (SQLException e) 
		{
			usersResponse.setSalida("Error SQL: "+e.getErrorCode()+"*********"+e.getMessage()+"*********"+e);
			usersResponse.setSuccess(false);
			return usersResponse;
		}
		catch (Exception ex) {

			usersResponse.setSalida("Error SQL: "+ex.getMessage()+"*********"+ex);
			usersResponse.setSuccess(false);
			return usersResponse;
		}
	
	}
	
	public Response getUser(User user, boolean fromLogin)
	{
		LOG.info("*********LOG********** Entrada servicio getUser con entrada: "+user);
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    LoginResponse usersResponse=new LoginResponse();
	    
		
        try 
        {
			preparedStatement = dataSource.getConnection()
                    .prepareStatement(query6);
			preparedStatement.setString(1,user.getMail().trim());
            resultSet = preparedStatement.executeQuery();
            
            
            
    		ArrayList<User> users=new ArrayList<User>();
    		int cont=0;
    		 
            while (resultSet.next()) 
            {
            	cont++;
            	LOG.info("*********LOG********** Procesando registro: "+cont);
            	
            	User client=new User();
            	
            	client.setId(resultSet.getLong("USERID"));
            	
            	client.setPassword((fromLogin)?resultSet.getString("PASSWORD"):null);
            	client.setType(resultSet.getString("TYPE"));
            	client.setMail(resultSet.getString("MAIL"));
            	client.setName(resultSet.getString("NAME"));
            	client.setSurname(resultSet.getString("SURNAME"));
            	client.setAlias(resultSet.getString("ALIAS"));
            	client.setGender(resultSet.getString("GENDER"));
            	client.setBirthDate(resultSet.getDate("BIRTH_DATE"));
            	client.setCreateDate(resultSet.getDate("CREATE_DATE"));
            	            	
            	users.add(client);
            	
            	LOG.info("*********LOG********** Registro procesado: "+client);
            }
            
            resultSet.close();
            
            usersResponse.setUsers(users);
            usersResponse.setSalida("OK");
            usersResponse.setSuccess(true);
            
            return Response.status(Response.Status.OK).entity(usersResponse).build();
            
	        
	        
	        
		} catch (SQLException e) {

			usersResponse.setSalida("Error SQL: "+e.getErrorCode()+"*********"+e.getMessage()+"*********"+e);
			usersResponse.setSuccess(false);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(usersResponse).build();
		}
        catch (Exception ex) {

			usersResponse.setSalida("Error SQL: "+ex.getMessage()+"*********"+ex);
			usersResponse.setSuccess(false);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(usersResponse).build();
		}

        
	}
	
	public UsersResponse getUsers(User user)
	{
		LOG.info("*********LOG********** Entrada servicio getUsers sin argumentos");
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    UsersResponse usersResponse=new UsersResponse();
		
	    return null;
//		try 
//        {
//			preparedStatement = dataSource.getConnection()
//                    .prepareStatement(query7);
//			preparedStatement.setString(1,user.getCodigo_usuario());
//			
//            resultSet = preparedStatement.executeQuery();
//            
//            
//	        
//           
//    		ArrayList<Customer> users=new ArrayList<Customer>();
//    		
//    		LOG.info("*********LOG**********"+resultSet.getFetchSize());
//    		int cont=0;
//    		 
//            while (resultSet.next()) 
//            {
//            	
//            	LOG.info("*********LOG********** Procesando registro: "+cont++);
//            	
//            	Customer client=new Customer();
//            	
//            	client.setCodigo_usuario(resultSet.getString("CODIGO_USUARIO"));
//            	client.setNombre_1(resultSet.getString("NOMBRE_1"));
//            	client.setNombre_2(resultSet.getString("NOMBRE_2"));
//            	client.setApellidos_1(resultSet.getString("APELLIDOS_1"));
//            	client.setApellidos_2(resultSet.getString("APELLIDOS_2"));
//            	client.setFecha_nacimiento(resultSet.getString("FECHA_NACIMIENTO"));
//            	client.setEdad(resultSet.getString("EDAD"));
//            	client.setSexo(resultSet.getString("SEXO"));
//            	client.setLugar_de_nacimiento(resultSet.getString("LUGAR_DE_NACIMIENTO"));
//            	client.setProvincia_nacimiento(resultSet.getString("PROVINCIA_NACIMIENTO"));
//            	client.setDireccion_habitual(resultSet.getString("DIRECCION_HABITUAL"));
//            	client.setTipo_de_via(resultSet.getString("TIPO_DE_VIA"));
//            	client.setNombre_de_la_calle(resultSet.getString("NOMBRE_DE_LA_CALLE"));
//            	client.setNumero(resultSet.getString("NUMERO"));
//            	client.setBloque(resultSet.getString("BLOQUE"));
//            	client.setPortal(resultSet.getString("PORTAL"));
//            	client.setPlanta(resultSet.getString("PLANTA"));
//            	client.setPlanta_letra(resultSet.getString("PLANTA_LETRA"));
//            	client.setCodigo_postal(resultSet.getString("CODIGO_POSTAL"));
//            	client.setMunicipio(resultSet.getString("MUNICIPIO"));
//            	client.setProvincia(resultSet.getString("PROVINCIA"));
//            	client.setPais_nombre(resultSet.getString("PAIS_NOMBRE"));
//            	client.setCodigo_pais(resultSet.getString("CODIGO_PAIS"));
//            	
//            	
//            	users.add(client);
//            	
//            	LOG.info("*********LOG********** Registro procesado: "+client);
//            }
//            
//            resultSet.close();
//            
//            usersResponse.setUsers(users);
//            usersResponse.setSalida("OK");
//            
//            return usersResponse;
//	        
//		} catch (SQLException e) {
//
//			usersResponse.setSalida("Error SQL: "+e.getErrorCode()+"*********"+e.getMessage()+"*********"+e);
//			return usersResponse;
//		}

        
	}
	
	public CompanyResponse getCompanies()
	{
		LOG.info("*********LOG********** Entrada servicio getCompanies sin argumentos");
		PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    CompanyResponse companyResponse=new CompanyResponse();
		
		return null;
        
//        try 
//        {
//			preparedStatement = dataSource.getConnection()
//                    .prepareStatement(query8);
//            resultSet = preparedStatement.executeQuery();
//            
//    		ArrayList<Company> companies=new ArrayList<Company>();
//    		
//    		LOG.info("*********LOG**********"+resultSet.getFetchSize());
//    		int cont=0;
//    		 
//            while (resultSet.next()) 
//            {
//            	
//            	LOG.info("*********LOG********** Procesando registro: "+cont++);
//            	
//            	Company company=new Company();
//            	
//            	company.setCodigo_usuario(resultSet.getString("CODIGO_USUARIO"));
//            	company.setNombre_empresa(resultSet.getString("NOMBRE_EMPRESA"));
//            	company.setNombre_acr(resultSet.getString("NOMBRE_ACR"));
//            	company.setApellidos_1(resultSet.getString("APELLIDOS_1"));
//            	company.setApellidos_2(resultSet.getString("APELLIDOS_2"));
//            	company.setFecha_establecimiento(resultSet.getString("FECHA_ESTABLECIMIENTO"));
//            	company.setProvicia_actividad(resultSet.getString("PROVICIA_ACTIVIDAD"));
//            	company.setDireccion_habitual(resultSet.getString("DIRECCION_HABITUAL"));
//            	company.setTipo_de_via(resultSet.getString("TIPO_DE_VIA"));
//            	company.setNombre_de_la_calle(resultSet.getString("NOMBRE_DE_LA_CALLE"));
//            	company.setNumero(resultSet.getString("NUMERO"));
//            	company.setBloque(resultSet.getString("BLOQUE"));
//            	company.setPortal(resultSet.getString("PORTAL"));
//            	company.setPlanta(resultSet.getString("PLANTA"));
//            	company.setPlanta_letra(resultSet.getString("PLANTA_LETRA"));
//            	company.setCodigo_postal(resultSet.getString("CODIGO_POSTAL"));
//            	company.setMunicipio(resultSet.getString("MUNICIPIO"));
//            	company.setProvincia(resultSet.getString("PROVINCIA"));
//            	company.setPais_nombre(resultSet.getString("PAIS_NOMBRE"));
//            	company.setCodigo_pais(resultSet.getString("CODIGO_PAIS"));
//            	
//            	
//            	companies.add(company);
//            	
//            	LOG.info("*********LOG********** Registro procesado: "+company);
//            }
//            
//            resultSet.close();
//            
//            companyResponse.setCompanies(companies);
//            companyResponse.setSalida("OK");
//            
//            return companyResponse;
//            
//	        
//		} catch (SQLException e) {
//
//			companyResponse.setSalida("Error SQL: "+e.getErrorCode()+"*********"+e.getMessage()+"*********"+e);
//			return companyResponse;
//		}

        
	}
	
	public CompanyResponse addCompany(Company company)
	{
		LOG.info("*********LOG********** Entrada servicio addCompany con entrada: "+company);
		
		PreparedStatement preparedStatement = null;
	    CompanyResponse companyResponse=new CompanyResponse();
		
	    return null;
		
//		try 
//		{
//	        
//	        preparedStatement = dataSource.getConnection()
//                    .prepareStatement(query4);
//	        
//	        preparedStatement.setString(1,company.getNombre_empresa());
//	        preparedStatement.setString(2,company.getNombre_acr());
//	        preparedStatement.setString(3,company.getApellidos_1());
//	        preparedStatement.setString(4,company.getApellidos_2());
//	        preparedStatement.setString(5,company.getFecha_establecimiento());
//	        preparedStatement.setString(6,company.getLugar_de_actividad());
//	        preparedStatement.setString(7,company.getProvincia());
//	        preparedStatement.setString(8,company.getDireccion_habitual());
//	        preparedStatement.setString(9,company.getTipo_de_via());
//	        preparedStatement.setString(10,company.getNombre_de_la_calle());
//	        preparedStatement.setString(11,company.getNumero());
//	        preparedStatement.setString(12,company.getBloque());
//	        preparedStatement.setString(13,company.getPortal());
//	        preparedStatement.setString(14,company.getPlanta());
//	        preparedStatement.setString(15,company.getPlanta_letra());
//	        preparedStatement.setString(16,company.getCodigo_postal());
//	        preparedStatement.setString(17,company.getMunicipio());
//	        preparedStatement.setString(18,company.getProvincia());
//	        preparedStatement.setString(19,company.getPais_nombre());
//	        preparedStatement.setString(20,company.getCodigo_pais());
//	        preparedStatement.setString(21,company.getCodigo_usuario());
//	        int afectedRows=preparedStatement.executeUpdate();
//            
//            if(afectedRows==0)
//            	companyResponse.setSalida("KO - Usuario inexistente");
//            else
//            	companyResponse.setSalida("OK");
//            
//            return companyResponse;	        
//		        
//		}
//		catch (MySQLIntegrityConstraintViolationException e) {
//
//			companyResponse.setSalida("ERROR: Usuario existente");
//			return companyResponse;
//		}
//		catch (SQLException e) {
//
//			companyResponse.setSalida("Error SQL: "+e.getErrorCode()+"*********"+e.getMessage()+"*********"+e);
//				return companyResponse;
//			}
		
		
	}
	
	public UsersResponse addCustomer(Customer user)
	{
		LOG.info("*********LOG********** Entrada servicio addCustomer con entrada: "+user);
		
		PreparedStatement preparedStatement = null;
		UsersResponse usersResponse=new UsersResponse();
		
		return null;
		
//		if(user==null)
//		{
//			usersResponse.setSalida("KO - PArámetros Invalidos");
//			return usersResponse;
//		}
//		
//		if(user.getSexo()!=null && !user.getSexo().isEmpty() &&
//		   !user.getSexo().equals(Constants.SEXO_HOMBRE) &&
//		   !user.getSexo().equals(Constants.SEXO_MUJER))
//		{
//			usersResponse.setSalida("KO - PArámetros Invalidos");
//			return usersResponse;
//		}
//		
//		
//		String query="UPDATE CLIENTE SET ";
//		
//		if(user.getNombre_1()!=null)query+="NOMBRE_1=?,";
//		if(user.getNombre_2()!=null)query+="NOMBRE_2=?,";
//		if(user.getApellidos_1()!=null)query+="APELLIDOS_1=?,";
//		if(user.getApellidos_2()!=null)query+="APELLIDOS_2=?,";
//		if(user.getFecha_nacimiento()!=null)query+="FECHA_NACIMIENTO=?,";
//		if(user.getEdad()!=null)query+="EDAD=?,";
//		if(user.getSexo()!=null)query+="SEXO=?,";
//		if(user.getLugar_de_nacimiento()!=null)query+="LUGAR_DE_NACIMIENTO=?,";
//		if(user.getProvincia_nacimiento()!=null)query+="PROVINCIA_NACIMIENTO=?,";
//		if(user.getDireccion_habitual()!=null)query+="DIRECCION_HABITUAL=?,";
//		if(user.getTipo_de_via()!=null)query+="TIPO_DE_VIA=?,";
//		if(user.getNombre_de_la_calle()!=null)query+="NOMBRE_DE_LA_CALLE=?,";
//		if(user.getNumero()!=null)query+="NUMERO=?,";
//		if(user.getBloque()!=null)query+="BLOQUE=?,";
//		if(user.getPortal()!=null)query+="PORTAL=?,";
//		if(user.getPlanta()!=null)query+="PLANTA=?,";
//		if(user.getPlanta_letra()!=null)query+="PLANTA_LETRA=?,";
//		if(user.getCodigo_postal()!=null)query+="CODIGO_POSTAL=?,";
//		if(user.getMunicipio()!=null)query+="MUNICIPIO=?,";
//		if(user.getProvincia()!=null)query+="PROVINCIA=?,";
//		if(user.getPais_nombre()!=null)query+="PAIS_NOMBRE=?,";
//		if(user.getCodigo_pais()!=null)query+="CODIGO_PAIS=?,";
//		
//		if(query.endsWith(",")) query=query.substring(0, query.length()-1);
//		
//		
//		query+=" WHERE CODIGO_USUARIO=?";
//		
//		
//		try 
//		{
//				preparedStatement = dataSource.getConnection()
//	                    .prepareStatement(query);
//				
//				int i=1;
//
//				if(user.getNombre_1()!=null) preparedStatement.setString(i++,user.getNombre_1());
//				if(user.getNombre_2()!=null) preparedStatement.setString(i++,user.getNombre_2());
//				if(user.getApellidos_1()!=null) preparedStatement.setString(i++,user.getApellidos_1());
//				if(user.getApellidos_2()!=null) preparedStatement.setString(i++,user.getApellidos_2());
//				if(user.getFecha_nacimiento()!=null) preparedStatement.setString(i++,user.getFecha_nacimiento());
//				if(user.getEdad()!=null) preparedStatement.setString(i++,user.getEdad());
//				if(user.getSexo()!=null) preparedStatement.setString(i++,user.getSexo());
//				if(user.getLugar_de_nacimiento()!=null) preparedStatement.setString(i++,user.getLugar_de_nacimiento());
//				if(user.getProvincia_nacimiento()!=null) preparedStatement.setString(i++,user.getProvincia_nacimiento());
//				if(user.getDireccion_habitual()!=null) preparedStatement.setString(i++,user.getDireccion_habitual());
//				if(user.getTipo_de_via()!=null) preparedStatement.setString(i++,user.getTipo_de_via());
//				if(user.getNombre_de_la_calle()!=null) preparedStatement.setString(i++,user.getNombre_de_la_calle());
//				if(user.getNumero()!=null) preparedStatement.setString(i++,user.getNumero());
//				if(user.getBloque()!=null) preparedStatement.setString(i++,user.getBloque());
//				if(user.getPortal()!=null) preparedStatement.setString(i++,user.getPortal());
//				if(user.getPlanta()!=null) preparedStatement.setString(i++,user.getPlanta());
//				if(user.getPlanta_letra()!=null) preparedStatement.setString(i++,user.getPlanta_letra());
//				if(user.getCodigo_postal()!=null) preparedStatement.setString(i++,user.getCodigo_postal());
//				if(user.getMunicipio()!=null) preparedStatement.setString(i++,user.getMunicipio());
//				if(user.getProvincia()!=null) preparedStatement.setString(i++,user.getProvincia());
//				if(user.getPais_nombre()!=null) preparedStatement.setString(i++,user.getPais_nombre());
//				if(user.getCodigo_pais()!=null) preparedStatement.setString(i++,user.getCodigo_pais());
//
//				preparedStatement.setString(i++,user.getCodigo_usuario());
//		        
//	            int afectedRows=preparedStatement.executeUpdate();
//	            
//	            if(afectedRows==0)
//	            	usersResponse.setSalida("KO - Usuario inexistente");
//	            else
//	            	usersResponse.setSalida("OK");
//	            
//	            return usersResponse;
//		        
//		}
//		catch (MySQLIntegrityConstraintViolationException e) {
//
//			usersResponse.setSalida("ERROR: Usuario existente");
//			return usersResponse;
//		}
//		catch (SQLException e) {
//
//				usersResponse.setSalida("Error SQL: "+e.getErrorCode()+"*********"+e.getMessage()+"*********"+e);
//				return usersResponse;
//			}
	}

}
