package net.fast.lbcs.data.entities.admin.item;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Default;

@Default
public class ServiceItemAttributes {
	private List<ServiceItemAttribute> attrs = new ArrayList<ServiceItemAttribute>();
	
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
