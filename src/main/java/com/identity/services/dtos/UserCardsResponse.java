package com.identity.services.dtos;


import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserCardsResponse {

	public UserCardsResponse() {
		super();
		this.cards=new ArrayList<PCard>();
	}
	
	private ArrayList<PCard> cards;
	private String salida;
	
	
	public ArrayList<PCard> getCards() {
		return cards;
	}
	public String getSalida() {
		return salida;
	}
	public void setSalida(String salida) {
		this.salida = salida;
	}
	@Override
	public String toString() {
		return "UserCardsResponse [cards=" + cards + ", salida=" + salida + "]";
	}
	
	
	
}
