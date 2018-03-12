package com.surbhi.webProject1.pojo;

import java.util.Date;

public class Registration {
	private String name;
	private String username;
	private String gender;
	private String dob;
	private String country;
	private String city;
	private String mobile;
	private String password;
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "PojoRegistration [name=" + name + ", username=" + username + ", gender=" + gender + ", dob=" + dob
				+ ", country=" + country + ", city=" + city + ", mobile=" + mobile + ", password=" + password + "]";
	}
	public void setName(String fname,String lname) {
		this.name = fname+lname;
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
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
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
}
