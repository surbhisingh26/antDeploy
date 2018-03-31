package com.surbhi.webProject1.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(pattern = "dd/MM/yyyy")
public class Registration {
	
	private String uType;
	private String name;
	private String username;
	private String gender;
	private Date dob;
	private String country;
	private String city;
	private String mobile;
	private String password;
	private String bgcolor;
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
	@Override
	public String toString() {
		return "Registration [uType=" + uType + ",username=" + username + ", name=" + name + ",  gender=" + gender
				+ ", dob=" + dob + ", country=" + country + ", city=" + city + ", mobile=" + mobile + ", password="
				+ password + ", bgcolor=" + bgcolor + "]";
	}
	
}
