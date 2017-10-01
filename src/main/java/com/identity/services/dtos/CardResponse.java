package com.identity.services.dtos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CardResponse {

	public CardResponse() {
		super();
	}
	
	private String image;
	private String id;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "CardResponse [image=" + image + ", id=" + id + "]";
	}
	
	
	
}
