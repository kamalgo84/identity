package com.identity.services.dtos;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="loginResponse")
public class LoginResponse {
	
	private List<User> users;
	private String salida;
	private boolean success;
	
	

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}

	@Override
	public String toString() {
		return "LoginResponse [users=" + users + ", salida=" + salida + ", success=" + success + "]";
	}

	

	
	



}
