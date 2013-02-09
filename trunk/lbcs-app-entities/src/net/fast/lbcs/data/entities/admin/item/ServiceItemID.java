package net.fast.lbcs.data.entities.admin.item;

import org.simpleframework.xml.Default;

@Default
public class ServiceItemID {
	private String id = " ";
	
	public ServiceItemID() {}

	public ServiceItemID(String id) {
		super();
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
		return "ServiceItemID [id=" + id + "]\r\n";
	}
	
	
}
