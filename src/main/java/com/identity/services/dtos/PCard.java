package com.identity.services.dtos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PCard")
public class PCard {
   
    private Long id;
    
    private User owner;

    private String alias;

    private String type;

    private Long template; // TODO: by now is a string, but I supposte this will be a ref

    private String imageUri;

    private boolean canBeShared;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getTemplate() {
		return template;
	}

	public void setTemplate(Long template) {
		this.template = template;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	public boolean isCanBeShared() {
		return canBeShared;
	}

	public void setCanBeShared(boolean canBeShared) {
		this.canBeShared = canBeShared;
	}

	@Override
	public String toString() {
		return "PCard [id=" + id + ", owner=" + owner + ", alias=" + alias + ", type=" + type + ", template=" + template
				+ ", imageUri=" + imageUri + ", canBeShared=" + canBeShared + "]";
	}
    
    

	


	

   
}
