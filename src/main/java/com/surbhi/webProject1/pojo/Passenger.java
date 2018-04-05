package com.surbhi.webProject1.pojo;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)

public class Passenger extends BaseObject{
	
	private String LoginuserId;
	private String name;
	private int tickets;
	private String email;
	private String place;
	private Date date;
	private Date time;
	private int totalPay;
	
	public String getName() {
		return name;
		}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getTickets() {
		return tickets;
	}
	public void setTickets(int tick) {
		this.tickets = tick;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getDate() {
//		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
//		String strDate = dateFormat.format(date);
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getTime() {
		/*DateFormat timeFormat = new SimpleDateFormat("HH:mm");  
		String strTime = timeFormat.format(time);*/
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getTotalPay() {
		return totalPay;
	}
	public void setTotalPay(int totalPay) {
		this.totalPay = totalPay;
	}
	
	public String getLoginuserId() {
		return LoginuserId;
	}
	public void setLoginuserId(String loginuserId) {
		LoginuserId = loginuserId;
	}
}
