package net.fast.lbcs.data.entities.user;

import net.fast.lbcs.data.entities.MyDate;

import org.simpleframework.xml.Default;

@Default
public class NotificationToAdmin {
	
	private String id;
	private String service;
	private String username;
	private String text;
	private MyDate date;
	private String status;
	
	
	
	public NotificationToAdmin() {
	}



	public NotificationToAdmin(String id,String service, String username, String text,
			MyDate date, String status) {
		this.id=id;
		this.service = service;
		this.username = username;
		this.text = text;
		this.date = date;
		this.status = status;
	}

	public NotificationToAdmin(String id, String service, String username, String text,
			String date, String status) {
		this.id=id;
		this.service = service;
		this.username = username;
		this.text = text;
		this.date = new MyDate(date);
		this.status = status;
	}


	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getService() {
		return service;
	}



	public void setService(String service) {
		this.service = service;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getText() {
		return text;
	}



	public void setText(String text) {
		this.text = text;
	}



	public MyDate getDate() {
		return date;
	}



	public void setDate(MyDate date) {
		this.date = date;
	}

	public void setDate(String date) {
		this.date = new MyDate(date);
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "Notification [id=" + id + ", service=" + service + ", username=" + username
				+ ", text=" + text + ", date=" + date + ", status=" + status
				+ "]";
	}
	
	
	


}
