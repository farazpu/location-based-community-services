package net.fast.lbcs.data.entities.admin.item;

import org.simpleframework.xml.Default;

@Default
public class ServiceItemAttribute {
	private String id;
	private String name;
	private String validation;
	private String type;
	private String context;
	
	public ServiceItemAttribute() {}
	
	
	public ServiceItemAttribute(String id, String name, String validation, 
			String type, String context) {
		this.name = name;
		this.validation = validation;
		this.id = id;
		this.type = type;
		this.context = context;
	}





	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Override
	public String toString() {
		return "ServiceItemAttribute [name=" + name + ", validation="
				+ validation + ", id=" + id + ", type=" + type + ", context="
				+ context + "]";
	}

	
}
