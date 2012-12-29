package net.fast.lbcs.data.entities.admin.service;

import org.simpleframework.xml.Default;


@Default
public class ServiceID {

	private String id;

	public ServiceID() {
		this.id = "";
	}
	
	public ServiceID(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ServiceID [id=" + id + "]\r\n";
	}
	
	
	
	
}
