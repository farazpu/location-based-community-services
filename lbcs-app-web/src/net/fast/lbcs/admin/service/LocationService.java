package net.fast.lbcs.admin.service;

import java.util.Date;
import java.util.List;

import net.fast.lbcs.admin.group.ServiceItemGroup;
import net.fast.lbcs.admin.item.ServiceItem;

public class LocationService {
	private ServiceID id;
	private String name;
	private String desciption;
	private Date lastModified;
	private Date created;
	
	
	private List<ServiceItem> items;
	private List<ServiceItemGroup> groups;

	
	public ServiceID getId() {
		return id;
	}
	public void setId(ServiceID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public List<ServiceItem> getItems() {
		return items;
	}
	public void setItems(List<ServiceItem> items) {
		this.items = items;
	}
	public List<ServiceItemGroup> getGroups() {
		return groups;
	}
	public void setGroups(List<ServiceItemGroup> groups) {
		this.groups = groups;
	}
	
	
	
	
	
}
