package com.surbhi.webProject1.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class User extends BaseObject{

	private String uType;
	private String name;
	private String email;
	private String username;
	private String gender;
	private Date dob;
	private String country;
	private String city;
	private String mobile;
	private String password;
	private String bgcolor;
	private String imagepath;
	private Date lastLoggedInAt;
	private Boolean loggedIn;
	private String reference;
	private String referenceId;
	private int points;
	public String getName() {
		return name;
	}
	
	public void setName(String fname,String lname) {
		this.name = fname+" "+lname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getuType() {
		return uType;
	}
	public void setuType(String uType) {
		this.uType = uType;
	}
	public String getBgcolor() {
		return bgcolor;
	}
	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}
	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public void setEmail(String email) {
		this.email = email;
		
	}
	public String getEmail() {
		return email;
	}

	public Date getLastLoggedInAt() {
		return lastLoggedInAt;
	}

	public void setLastLoggedInAt(Date lastLoggedInAt) {
		this.lastLoggedInAt = lastLoggedInAt;
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
}