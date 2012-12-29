package net.fast.lbcs.data.entities.admin.group;

import org.simpleframework.xml.Default;

@Default
public class ServiceItemGroup {
	private ServiceItemGroupID id;
	private String name;
	private String description;
	
	public ServiceItemGroup() {}
	
	public ServiceItemGroup(ServiceItemGroupID id, String name,
			String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public ServiceItemGroupID getId() {
		return id;
	}
	public void setId(ServiceItemGroupID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ServiceItemGroup [id=" + id + ", name=" + name
				+ ", description=" + description + "]\r\n";
	}
	
}
