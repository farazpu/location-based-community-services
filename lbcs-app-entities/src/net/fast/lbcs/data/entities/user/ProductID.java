package net.fast.lbcs.data.entities.user;

import org.simpleframework.xml.Default;

@Default
public class ProductID {
	private String id;
	public ProductID() {}
	public ProductID(String id) {
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
