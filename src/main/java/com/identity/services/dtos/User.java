package com.identity.services.dtos;


import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="User")
public class User {

    private Long id;

    String name;

    String surname;

    String alias;

    String mail;

    String gender; // todo: pass this to a enum
    
    String type;
    
    String Password;
    
    Date birthDate;
    
    Date createDate;
    
   


	public User() {
		super();
		this.type="C";
	}


    public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	 public Date getCreateDate() {
			return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", alias=" + alias + ", mail=" + mail
				+ ", gender=" + gender + ", birthDate=" + birthDate + "]";
	}

    
}
