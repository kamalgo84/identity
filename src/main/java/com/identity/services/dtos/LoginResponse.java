package com.identity.services.dtos;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="loginResponse")
public class LoginResponse {
	
	private List<User> users;
	private String salida;

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
		return "UsersResponse [users=" + users + ", salida=" + salida + "]";
	}

	
	



}
