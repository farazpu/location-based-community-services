package net.fast.lbcs.admin.item;

import net.fast.lbcs.admin.group.ServiceItemGroup;

public class ServiceItem {
	
	private ServiceItemID id;
	private String name;
	private ServiceItemAttributes attrs;
	private ServiceItemGroup group;
	
	public ServiceItem() {}
	
	

	public ServiceItem(ServiceItemID id, String name, ServiceItemAttributes attrs,
			ServiceItemGroup group) {
		super();
		this.id = id;
		this.name = name;
		this.attrs = attrs;
		this.group = group;
	}



	public ServiceItemID getId() {
		return id;
	}

	public void setId(ServiceItemID id) {
		this.id = id;
	}

	public ServiceItemAttributes getAttrs() {
		return attrs;
	}

	public void setAttrs(ServiceItemAttributes attrs) {
		this.attrs = attrs;
	}

	public ServiceItemGroup getGroup() {
		return group;
	}

	public void setGroup(ServiceItemGroup group) {
		this.group = group;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	@Override
	public String toString() {
		return "\r\nServiceItem [id=" + id + ", name=" + name + ", attrs=" + attrs
				+ ", group=" + group + "]\r\n";
	} 
	
	
	
}
