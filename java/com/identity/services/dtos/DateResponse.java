package com.identity.services.dtos;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="dateResponse")
public class DateResponse {
	
	private String date;

	public DateResponse() {

		this.date=new Date().toString();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "DateResponse [date=" + date + "]";
	}
	
	

}
