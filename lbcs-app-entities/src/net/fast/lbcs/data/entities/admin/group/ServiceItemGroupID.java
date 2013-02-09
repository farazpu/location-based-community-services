package net.fast.lbcs.data.entities.admin.group;

import org.simpleframework.xml.Default;

@Default
public class ServiceItemGroupID {
	private String id = " ";

	public ServiceItemGroupID() {}
	
	public ServiceItemGroupID(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
