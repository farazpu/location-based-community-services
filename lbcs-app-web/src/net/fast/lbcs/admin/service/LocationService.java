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
	
}
