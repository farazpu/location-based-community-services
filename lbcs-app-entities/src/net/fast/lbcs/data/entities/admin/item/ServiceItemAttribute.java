package net.fast.lbcs.data.entities.admin.item;

import org.simpleframework.xml.Default;

@Default
public class ServiceItemAttribute {
	private String id;
	private String name;
	private String validation;
	private String type;
	private String context;
	private String flag;
	
	public ServiceItemAttribute() {}
	
	
	public ServiceItemAttribute(String id, String name, String validation, 
			String type, String context, String flag) {
		this.name = name;
		this.validation = validation;
		this.id = id;
		this.type = type;
		this.context = context;
		this.flag = flag;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}


	@Override
	public String toString() {
		return "ServiceItemAttribute [id=" + id + ", name=" + name
				+ ", validation=" + validation + ", type=" + type
				+ ", context=" + context + ", flag=" + flag + "]";
	}

	
	
}
