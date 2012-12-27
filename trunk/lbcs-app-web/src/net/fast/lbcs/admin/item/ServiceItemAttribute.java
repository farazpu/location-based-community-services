package net.fast.lbcs.admin.item;

public class ServiceItemAttribute {
	private String name;
	private Validation validation;
	
	public ServiceItemAttribute() {}

	public ServiceItemAttribute(String name, Validation validation) {
		super();
		this.name = name;
		this.validation = validation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Validation getValidation() {
		return validation;
	}

	public void setValidation(Validation validation) {
		this.validation = validation;
	}

	@Override
	public String toString() {
		return "ServiceItemAttribute [name=" + name + ", validation="
				+ validation + "]\r\n";
	}

	
}
