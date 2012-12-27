package net.fast.lbcs.admin.item;

import java.util.List;

public class ServiceItemAttributes {
	private List<ServiceItemAttribute> attrs;
	
	public ServiceItemAttributes() {}

	public ServiceItemAttributes(List<ServiceItemAttribute> attrs) {
		super();
		this.attrs = attrs;
	}

	public List<ServiceItemAttribute> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<ServiceItemAttribute> attrs) {
		this.attrs = attrs;
	}

	@Override
	public String toString() {
		return "ServiceItemAttributes [attrs=" + attrs + "]";
	}

	
}
