package net.fast.lbcs.data.entities.admin.item;

import java.util.Date;

import org.simpleframework.xml.Default;

import net.fast.lbcs.data.entities.MyDate;
import net.fast.lbcs.data.entities.admin.group.ServiceItemGroup;

@Default
public class ServiceItem {
	
	private ServiceItemID id;
	private String name;
	private ServiceItemAttributes attrs;
	private ServiceItemGroup group;
	private MyDate dateModified;
	private String description;
	
	public ServiceItem() {}
	
	

	public ServiceItem(ServiceItemID id, String name, ServiceItemAttributes attrs,
			ServiceItemGroup group, MyDate dateModified, String description) {
		super();
		this.id = id;
		this.name = name;
		this.attrs = attrs;
		this.group = group;
		this.dateModified = dateModified;
		this.description = description;
	}

	public ServiceItem(ServiceItemID id, String name, ServiceItemAttributes attrs,
			ServiceItemGroup group, Date dateModified, String description) {
		super();
		this.id = id;
		this.name = name;
		this.attrs = attrs;
		this.group = group;
		this.dateModified = new MyDate(dateModified);
		this.description = description;
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



	public MyDate getDateModified() {
		return dateModified;
	}



	public void setDateModified(MyDate dateModified) {
		this.dateModified = dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = new MyDate(dateModified);
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
		return "\r\nServiceItem [id=" + id + ", name=" + name + ", attrs=" + attrs
				+ ", group=" + group + "]\r\n";
	} 
	
	
	
}
