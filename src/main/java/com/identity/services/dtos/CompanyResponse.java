package com.identity.services.dtos;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="usersResponse")
public class CompanyResponse {
	
	private List<Company> companies;
	private String salida;
	public List<Company> getCompanies() {
		return companies;
	}
	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
	public String getSalida() {
		return salida;
	}
	public void setSalida(String salida) {
		this.salida = salida;
	}
	@Override
	public String toString() {
		return "CompanyResponse [companies=" + companies + ", salida=" + salida + "]";
	}

	



}
