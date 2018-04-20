package com.surbhi.webProject1.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Notify extends BaseObject{
private String userId;
private String notification;
private String link;
private Date date;
//private String friendId;
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getNotification() {
	return notification;
}
public void setNotification(String notification) {
	this.notification = notification;
}
/*public String getFriendId() {
	return friendId;
}
public void setFriendId(String friendId) {
	this.friendId = friendId;
}*/

public String getLink() {
	return link;
}
public void setLink(String link) {
	this.link = link;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
}
