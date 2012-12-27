package net.fast.lbcs.admin;

import java.io.Serializable;

public class Administrator implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String password;

	public Administrator() {
	}
	
	
	
	public Administrator(String id, String password) {
		this.id = id;
		this.password = password;
	}

	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public String toString() {
		return "Administrator [id=" + id + ", password=" + password + "]\r\n";
	}
	
	
	
	
	
}
