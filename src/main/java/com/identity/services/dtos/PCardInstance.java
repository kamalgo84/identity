package com.identity.services.dtos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PCardInstance")
public class PCardInstance {
   
    private Long id;
    
    private User user;

    private PCard pcard;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUserId() {
		return user;
	}

	public void setUserId(User userId) {
		this.user = userId;
	}

	public PCard getIdPCard() {
		return pcard;
	}

	public void setIdPCard(PCard idPCard) {
		this.pcard = idPCard;
	}

	@Override
	public String toString() {
		return "PCardInstance [id=" + id + ", userId=" + user + ", idPCard=" + pcard + "]";
	}

	
    
	

   
}
