package com.identity.services.dtos;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="User")
public class User {
	
	private String codigo_usuario;
	private String password;
	private String type;
	public String getCodigo_usuario() {
		return codigo_usuario;
	}
	public void setCodigo_usuario(String codigo_usuario) {
		this.codigo_usuario = codigo_usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "User [codigo_usuario=" + codigo_usuario + ", password=" + password + ", type=" + type + "]";
	}
	
	
	
}
