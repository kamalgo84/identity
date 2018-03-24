package com.identity.services.enums;

public enum Gender {

	MALE("M", "Male"),

	FEMALE("F", "female");
	
	private String code;

	private String description;

	private Gender(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
