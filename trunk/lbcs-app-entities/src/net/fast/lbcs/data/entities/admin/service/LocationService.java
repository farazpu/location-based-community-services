package net.fast.lbcs.data.entities.admin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.simpleframework.xml.Default;

import net.fast.lbcs.data.entities.MyDate;
import net.fast.lbcs.data.entities.admin.group.ServiceItemGroup;
import net.fast.lbcs.data.entities.admin.group.ServiceItemGroupID;
import net.fast.lbcs.data.entities.admin.item.ServiceItem;
import net.fast.lbcs.data.entities.admin.item.ServiceItemID;


@Default
public class LocationService {
	private ServiceID id;
	private String name;
	private String desciption;
	private MyDate lastModified;
	private MyDate created;
	
	
	private List<ServiceItem> items = new ArrayList<ServiceItem>() ;
	private List<ServiceItemGroup> groups = new ArrayList<ServiceItemGroup>();

	public LocationService() {}
	
	
	
	public LocationService(ServiceID id, String name, String desciption,
			MyDate lastModified, MyDate created, List<ServiceItem> items,
			List<ServiceItemGroup> groups) {
		super();
		this.id = id;
		this.name = name;
		this.desciption = desciption;
		this.lastModified = lastModified;
		this.created = created;
		this.items = items;
		this.groups = groups;
	}

	public LocationService(ServiceID id, String name, String desciption,
			Date lastModified, Date created, List<ServiceItem> items,
			List<ServiceItemGroup> groups) {
		super();
		this.id = id;
		this.name = name;
		this.desciption = desciption;
		this.lastModified = new MyDate(lastModified);
		this.created = new MyDate(created);
		this.items = items;
		this.groups = groups;
	}


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
	public MyDate getLastModified() {
		return lastModified;
	}
	public void setLastModified(MyDate lastModified) {
		this.lastModified = lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = new MyDate(lastModified);
	}
	public MyDate getCreated() {
		return created;
	}
	public void setCreated(MyDate created) {
		this.created = created;
	}
	public void setCreated(Date created) {
		this.created = new MyDate(created);
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


	public ServiceItem getItemById(ServiceItemID serviceItemId)
	{
		for(ServiceItem si : items) {
			if(si.getId().getId().equals(serviceItemId.getId()))
				return si;
		}
		return null;
	}
	
	public ServiceItemGroup getItemGroupById(ServiceItemGroupID serviceItemGroupId)
	{
		for(ServiceItemGroup sig : groups) {
			if(sig.getId().getId().equals(serviceItemGroupId.getId()))
				return sig;
		}
		return null;
	}

	
	@Override
	public String toString() {
		return "LocationService [id=" + id + ", name=" + name + ", desciption="
				+ desciption + ", lastModified=" + lastModified + ", created="
				+ created + ", items=" + items + ", groups=" + groups + "]\r\n";
	}
	
	
	
	
	
}
