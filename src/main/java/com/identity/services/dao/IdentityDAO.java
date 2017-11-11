package com.identity.services.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.identity.services.dtos.Company;
import com.identity.services.dtos.CompanyResponse;
import com.identity.services.dtos.Customer;
import com.identity.services.dtos.LoginResponse;
import com.identity.services.dtos.User;
import com.identity.services.dtos.UsersResponse;
import com.identity.services.implementations.IdentityServicesImpl;
import com.identity.utils.Tools;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class IdentityDAO {
	
	private static final Logger LOG = Logger.getLogger(IdentityDAO.class);
	
//	@Autowired
	private BasicDataSource dataSource;
	
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
	
	public BasicDataSource getDataSource() {return dataSource;}

	public void setDataSource(BasicDataSource dataSource) {this.dataSource = dataSource;}

	public UsersResponse addUser(User user)
	{
	
		LOG.info("*********LOG********** Entrada servicio addUser con entrada: "+user);
	
	    PreparedStatement preparedStatement = null;
	    PreparedStatement preparedStatement2 = null;
		UsersResponse usersResponse=new UsersResponse();
		
		if(Tools.isBlank(user.getCodigo_usuario())||Tools.isBlank(user.getPassword())||Tools.isBlank(user.getType())||
				(!user.getType().equalsIgnoreCase("C") && !user.getType().equalsIgnoreCase("E")))
		{
			usersResponse.setSalida("KO - Parametros obligatorios no informados");
			return usersResponse;
			
		}
		
		
		try 
		{
	        
	        preparedStatement = dataSource.getConnection()
                    .prepareStatement(query1);


	        preparedStatement.setString(1,user.getCodigo_usuario());
	        preparedStatement.setString(2,user.getPassword());
	        preparedStatement.setString(3,user.getType());
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            
            if(user.getType().equalsIgnoreCase("C"))
            {
            
            	preparedStatement2=dataSource.getConnection()
	                    .prepareStatement(query2);
            	preparedStatement2.setString(1,user.getCodigo_usuario());
            	preparedStatement2.executeUpdate();
            }
            else
            {
            	
    	        preparedStatement2=dataSource.getConnection()
	                    .prepareStatement(query3);
    	        preparedStatement2.setString(1,user.getCodigo_usuario());
           		preparedStatement2.executeUpdate();
            }
            
            preparedStatement2.close();
            usersResponse.setSalida("OK");
            return usersResponse;
	            
		}
		catch (MySQLIntegrityConstraintViolationException e) {
	
			usersResponse.setSalida("ERROR: Usuario existente");
			return usersResponse;
		}
		catch (SQLException e) {
	
				usersResponse.setSalida("Error SQL: "+e.getErrorCode()+"*********"+e.getMessage()+"*********"+e);
				return usersResponse;
			}
	
	}
	
	public LoginResponse getUser(User user)
	{
		LOG.info("*********LOG********** Entrada servicio getUser con entrada: "+user);
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    LoginResponse usersResponse=new LoginResponse();
		
        try 
        {
			preparedStatement = dataSource.getConnection()
                    .prepareStatement(query6);
			preparedStatement.setString(1,user.getCodigo_usuario());
            resultSet = preparedStatement.executeQuery();
            
            
            
    		ArrayList<User> users=new ArrayList<User>();
    		
    		LOG.info("*********LOG**********"+resultSet.getFetchSize());
    		int cont=0;
    		 
            while (resultSet.next()) 
            {
            	
            	LOG.info("*********LOG********** Procesando registro: "+cont++);
            	
            	User client=new User();
            	
            	client.setCodigo_usuario(resultSet.getString("CODIGO_USUARIO"));
            	client.setPassword(resultSet.getString("PASSWORD"));
            	            	
            	users.add(client);
            	
            	LOG.info("*********LOG********** Registro procesado: "+client);
            }
            
            resultSet.close();
            
            usersResponse.setUsers(users);
            usersResponse.setSalida("OK");
            
            
            return usersResponse;
            
            
	       // return writeResultSet(resultSet);
	        
	        
	        
		} catch (SQLException e) {

			usersResponse.setSalida("Error SQL: "+e.getErrorCode()+"*********"+e.getMessage()+"*********"+e);
			return usersResponse;
		}

        
	}
	
	public UsersResponse getUsers()
	{
		LOG.info("*********LOG********** Entrada servicio getUsers sin argumentos");
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    UsersResponse usersResponse=new UsersResponse();
		
		try 
        {
			preparedStatement = dataSource.getConnection()
                    .prepareStatement(query7);
            resultSet = preparedStatement.executeQuery();
            
            
	        
           
    		ArrayList<Customer> users=new ArrayList<Customer>();
    		
    		LOG.info("*********LOG**********"+resultSet.getFetchSize());
    		int cont=0;
    		 
            while (resultSet.next()) 
            {
            	
            	LOG.info("*********LOG********** Procesando registro: "+cont++);
            	
            	Customer client=new Customer();
            	
            	client.setCodigo_usuario(resultSet.getString("CODIGO_USUARIO"));
            	client.setNombre_1(resultSet.getString("NOMBRE_1"));
            	client.setNombre_2(resultSet.getString("NOMBRE_2"));
            	client.setApellidos_1(resultSet.getString("APELLIDOS_1"));
            	client.setApellidos_2(resultSet.getString("APELLIDOS_2"));
            	client.setFecha_nacimiento(resultSet.getString("FECHA_NACIMIENTO"));
            	client.setEdad(resultSet.getString("EDAD"));
            	client.setSexo(resultSet.getString("SEXO"));
            	client.setLugar_de_nacimiento(resultSet.getString("LUGAR_DE_NACIMIENTO"));
            	client.setProvincia_nacimiento(resultSet.getString("PROVINCIA_NACIMIENTO"));
            	client.setDireccion_habitual(resultSet.getString("DIRECCION_HABITUAL"));
            	client.setTipo_de_via(resultSet.getString("TIPO_DE_VIA"));
            	client.setNombre_de_la_calle(resultSet.getString("NOMBRE_DE_LA_CALLE"));
            	client.setNumero(resultSet.getString("NUMERO"));
            	client.setBloque(resultSet.getString("BLOQUE"));
            	client.setPortal(resultSet.getString("PORTAL"));
            	client.setPlanta(resultSet.getString("PLANTA"));
            	client.setPlanta_letra(resultSet.getString("PLANTA_LETRA"));
            	client.setCodigo_postal(resultSet.getString("CODIGO_POSTAL"));
            	client.setMunicipio(resultSet.getString("MUNICIPIO"));
            	client.setProvincia(resultSet.getString("PROVINCIA"));
            	client.setPais_nombre(resultSet.getString("PAIS_NOMBRE"));
            	client.setCodigo_pais(resultSet.getString("CODIGO_PAIS"));
            	
            	
            	users.add(client);
            	
            	LOG.info("*********LOG********** Registro procesado: "+client);
            }
            
            resultSet.close();
            
            usersResponse.setUsers(users);
            usersResponse.setSalida("OK");
            
            return usersResponse;
	        
		} catch (SQLException e) {

			usersResponse.setSalida("Error SQL: "+e.getErrorCode()+"*********"+e.getMessage()+"*********"+e);
			return usersResponse;
		}

        
	}
	
	public CompanyResponse getCompanies()
	{
		LOG.info("*********LOG********** Entrada servicio getCompanies sin argumentos");
		PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    CompanyResponse companyResponse=new CompanyResponse();
		
		
        
        try 
        {
			preparedStatement = dataSource.getConnection()
                    .prepareStatement(query8);
            resultSet = preparedStatement.executeQuery();
            
    		ArrayList<Company> companies=new ArrayList<Company>();
    		
    		LOG.info("*********LOG**********"+resultSet.getFetchSize());
    		int cont=0;
    		 
            while (resultSet.next()) 
            {
            	
            	LOG.info("*********LOG********** Procesando registro: "+cont++);
            	
            	Company company=new Company();
            	
            	company.setCodigo_usuario(resultSet.getString("CODIGO_USUARIO"));
            	company.setNombre_empresa(resultSet.getString("NOMBRE_EMPRESA"));
            	company.setNombre_acr(resultSet.getString("NOMBRE_ACR"));
            	company.setApellidos_1(resultSet.getString("APELLIDOS_1"));
            	company.setApellidos_2(resultSet.getString("APELLIDOS_2"));
            	company.setFecha_establecimiento(resultSet.getString("FECHA_ESTABLECIMIENTO"));
            	company.setProvicia_actividad(resultSet.getString("PROVICIA_ACTIVIDAD"));
            	company.setDireccion_habitual(resultSet.getString("DIRECCION_HABITUAL"));
            	company.setTipo_de_via(resultSet.getString("TIPO_DE_VIA"));
            	company.setNombre_de_la_calle(resultSet.getString("NOMBRE_DE_LA_CALLE"));
            	company.setNumero(resultSet.getString("NUMERO"));
            	company.setBloque(resultSet.getString("BLOQUE"));
            	company.setPortal(resultSet.getString("PORTAL"));
            	company.setPlanta(resultSet.getString("PLANTA"));
            	company.setPlanta_letra(resultSet.getString("PLANTA_LETRA"));
            	company.setCodigo_postal(resultSet.getString("CODIGO_POSTAL"));
            	company.setMunicipio(resultSet.getString("MUNICIPIO"));
            	company.setProvincia(resultSet.getString("PROVINCIA"));
            	company.setPais_nombre(resultSet.getString("PAIS_NOMBRE"));
            	company.setCodigo_pais(resultSet.getString("CODIGO_PAIS"));
            	
            	
            	companies.add(company);
            	
            	LOG.info("*********LOG********** Registro procesado: "+company);
            }
            
            resultSet.close();
            
            companyResponse.setCompanies(companies);
            companyResponse.setSalida("OK");
            
            return companyResponse;
            
	        
		} catch (SQLException e) {

			companyResponse.setSalida("Error SQL: "+e.getErrorCode()+"*********"+e.getMessage()+"*********"+e);
			return companyResponse;
		}

        
	}
	
	public CompanyResponse addCompany(Company company)
	{
		LOG.info("*********LOG********** Entrada servicio addCompany con entrada: "+company);
		
		PreparedStatement preparedStatement = null;
	    CompanyResponse companyResponse=new CompanyResponse();
		
		
		try 
		{
	        
	        preparedStatement = dataSource.getConnection()
                    .prepareStatement(query4);
	        
	        preparedStatement.setString(1,company.getNombre_empresa());
	        preparedStatement.setString(2,company.getNombre_acr());
	        preparedStatement.setString(3,company.getApellidos_1());
	        preparedStatement.setString(4,company.getApellidos_2());
	        preparedStatement.setString(5,company.getFecha_establecimiento());
	        preparedStatement.setString(6,company.getLugar_de_actividad());
	        preparedStatement.setString(7,company.getProvincia());
	        preparedStatement.setString(8,company.getDireccion_habitual());
	        preparedStatement.setString(9,company.getTipo_de_via());
	        preparedStatement.setString(10,company.getNombre_de_la_calle());
	        preparedStatement.setString(11,company.getNumero());
	        preparedStatement.setString(12,company.getBloque());
	        preparedStatement.setString(13,company.getPortal());
	        preparedStatement.setString(14,company.getPlanta());
	        preparedStatement.setString(15,company.getPlanta_letra());
	        preparedStatement.setString(16,company.getCodigo_postal());
	        preparedStatement.setString(17,company.getMunicipio());
	        preparedStatement.setString(18,company.getProvincia());
	        preparedStatement.setString(19,company.getPais_nombre());
	        preparedStatement.setString(20,company.getCodigo_pais());
	        preparedStatement.setString(21,company.getCodigo_usuario());
	        int afectedRows=preparedStatement.executeUpdate();
            
            if(afectedRows==0)
            	companyResponse.setSalida("KO - Usuario inexistente");
            else
            	companyResponse.setSalida("OK");
            
            return companyResponse;	        
		        
		}
		catch (MySQLIntegrityConstraintViolationException e) {

			companyResponse.setSalida("ERROR: Usuario existente");
			return companyResponse;
		}
		catch (SQLException e) {

			companyResponse.setSalida("Error SQL: "+e.getErrorCode()+"*********"+e.getMessage()+"*********"+e);
				return companyResponse;
			}
		
		
	}
	
	public UsersResponse addCustomer(Customer user)
	{
		LOG.info("*********LOG********** Entrada servicio addCustomer con entrada: "+user);
		
		 PreparedStatement preparedStatement = null;
		UsersResponse usersResponse=new UsersResponse();
		
		
		try 
		{
				preparedStatement = dataSource.getConnection()
	                    .prepareStatement(query5);

		        preparedStatement.setString(1,user.getNombre_1());
		        preparedStatement.setString(2,user.getNombre_2());
		        preparedStatement.setString(3,user.getApellidos_1());
		        preparedStatement.setString(4,user.getApellidos_2());
		        preparedStatement.setString(5,user.getFecha_nacimiento());
		        preparedStatement.setString(6,user.getEdad());
		        preparedStatement.setString(7,user.getSexo());
		        preparedStatement.setString(8,user.getLugar_de_nacimiento());
		        preparedStatement.setString(9,user.getProvincia_nacimiento());
		        preparedStatement.setString(10,user.getDireccion_habitual());
		        preparedStatement.setString(11,user.getTipo_de_via());
		        preparedStatement.setString(12,user.getNombre_de_la_calle());
		        preparedStatement.setString(13,user.getNumero());
		        preparedStatement.setString(14,user.getBloque());
		        preparedStatement.setString(15,user.getPortal());
		        preparedStatement.setString(16,user.getPlanta());
		        preparedStatement.setString(17,user.getPlanta_letra());
		        preparedStatement.setString(18,user.getCodigo_postal());
		        preparedStatement.setString(19,user.getMunicipio());
		        preparedStatement.setString(20,user.getProvincia());
		        preparedStatement.setString(21,user.getPais_nombre());
		        preparedStatement.setString(22,user.getCodigo_pais());
		        preparedStatement.setString(23,user.getCodigo_usuario());
	            int afectedRows=preparedStatement.executeUpdate();
	            
	            if(afectedRows==0)
	            	usersResponse.setSalida("KO - Usuario inexistente");
	            else
	            	usersResponse.setSalida("OK");
	            
	            return usersResponse;
		        
		}
		catch (MySQLIntegrityConstraintViolationException e) {

			usersResponse.setSalida("ERROR: Usuario existente");
			return usersResponse;
		}
		catch (SQLException e) {

				usersResponse.setSalida("Error SQL: "+e.getErrorCode()+"*********"+e.getMessage()+"*********"+e);
				return usersResponse;
			}
	}

}
