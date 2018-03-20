package com.surbhi.webProject1.pojo;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(pattern = "dd/MM/yyyy")
public class Passenger {
	private String Loginuser;
	private String name;
	private int tickets;
	private String email;
	private Date date;
	private Date time;
	private int totalPay;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
		String strDate = dateFormat.format(date);
		return strDate;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTime() {
		DateFormat timeFormat = new SimpleDateFormat("HH:mm");  
		String strTime = timeFormat.format(time);
		return strTime;
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
	@Override
	public String toString() {
		return "Passenger [LoginUser=" + Loginuser + ", name=" + name + ", tickets=" + tickets + ", email=" + email
				+ ", date=" + date + ", time=" + time + ", TotalPay=" + totalPay + "]";
	}
	public String getLoginuser() {
		return Loginuser;
	}
	public void setLoginuser(String loginuser) {
		Loginuser = loginuser;
	}
}
