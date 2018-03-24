package com.identity.services.dtos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CardResponse {

	public CardResponse() {
		super();
	}
	
	private Card card;
	private String salida;
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public String getSalida() {
		return salida;
	}
	public void setSalida(String salida) {
		this.salida = salida;
	}
	@Override
	public String toString() {
		return "CardResponse [card=" + card + ", salida=" + salida + "]";
	}
	
	
	
	
}
