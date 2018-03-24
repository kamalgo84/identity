package com.identity.services.dtos;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CardBytesResponse {

	public CardBytesResponse() {
		super();
	}
	
	private String imageString;
	private String imageBase64String;
	private byte[] imageBytes;
	private String id;
	
	public String getImageString() {
		return imageString;
	}
	public void setImageString(String imageString) {
		this.imageString = imageString;
	}
	public String getImageBase64String() {
		return imageBase64String;
	}
	public void setImageBase64String(String imageBase64String) {
		this.imageBase64String = imageBase64String;
	}
	public byte[] getImageBytes() {
		return imageBytes;
	}
	public void setImageBytes(byte[] imageBytes) {
		this.imageBytes = imageBytes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "CardBytesResponse [imageString=" + imageString + ", imageBase64String=" + imageBase64String
				+ ", imageBytes=" + Arrays.toString(imageBytes) + ", id=" + id + "]";
	}


	
	
	
}
