package com.surbhi.webProject1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Invite extends BaseObject {
	
	private String senderId;
	private String recieverEmail;
	public String getRecieverEmail() {
		return recieverEmail;
	}
	public void setRecieverEmail(String recieverEmail) {
		this.recieverEmail = recieverEmail;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	
}