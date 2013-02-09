package net.fast.lbcs.data.entities.admin.group;

import java.util.Date;

import net.fast.lbcs.data.entities.MyDate;

import org.simpleframework.xml.Default;

@Default
public class ServiceItemGroup {
	private ServiceItemGroupID id;
	private String name = " ";
	private String description = " ";
	private MyDate dateCreated = new MyDate(new Date());
	private MyDate dateModified = new MyDate(new Date());
	
	public ServiceItemGroup() {}
	
	public ServiceItemGroup(ServiceItemGroupID id, String name,
			String description, MyDate dateCreated, MyDate dateModified) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
	}

	public ServiceItemGroup(ServiceItemGroupID id, String name,
			String description, Date dateCreated, Date dateModified) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.dateCreated = new MyDate(dateCreated);
		this.dateModified = new MyDate(dateModified);
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
	
	


	public MyDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(MyDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = new MyDate(dateCreated);
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

	@Override
	public String toString() {
		return "ServiceItemGroup [id=" + id + ", name=" + name
				+ ", description=" + description + "]\r\n";
	}
	
}
