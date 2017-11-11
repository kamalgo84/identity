package com.identity.services.dtos;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="usersResponse")
public class UsersResponse {
	
	private List<Customer> users;
	private String salida;

	public List<Customer> getUsers() {
		return users;
	}

	public void setUsers(List<Customer> users) {
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
