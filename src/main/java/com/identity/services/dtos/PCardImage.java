package com.identity.services.dtos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Image")
public class Image {

    private Long id;

    private String imageUri;

    PCard pcard;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	public PCard getPcard() {
		return pcard;
	}

	public void setPcard(PCard pcard) {
		this.pcard = pcard;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", imageUri=" + imageUri + ", pcard=" + pcard + "]";
	}

    
}
