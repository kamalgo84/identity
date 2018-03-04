package com.identity.services.dtos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AddCardRequest {
	
	private String id;

	public AddCardRequest() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CardRequest [id=" + id + "]";
	}
	
	

}
