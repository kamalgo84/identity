package com.identity.services.dtos;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Card")
public class Card {
	
	private String imageURI;
	private String id;
	public String getImageURI() {
		return imageURI;
	}
	public void setImageURI(String imageURI) {
		this.imageURI = imageURI;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Card [imageURI=" + imageURI + ", id=" + id + "]";
	}
	
	
	
}
