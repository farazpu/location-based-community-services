package net.fast.lbcs.admin.controller;

import java.util.List;

import net.fast.lbcs.admin.item.ServiceItemCreationException;
import net.fast.lbcs.admin.item.ServiceItemDeleteException;
import net.fast.lbcs.admin.service.ServiceCreationException;
import net.fast.lbcs.admin.service.ServiceDeleteException;
import net.fast.lbcs.admin.service.ServiceEditException;
import net.fast.lbcs.data.entities.admin.Administrator;
import net.fast.lbcs.data.entities.admin.group.ServiceItemGroup;
import net.fast.lbcs.data.entities.admin.group.ServiceItemGroupID;
import net.fast.lbcs.data.entities.admin.item.ServiceItem;
import net.fast.lbcs.data.entities.admin.item.ServiceItemAttribute;
import net.fast.lbcs.data.entities.admin.item.ServiceItemAttributes;
import net.fast.lbcs.data.entities.admin.item.ServiceItemID;
import net.fast.lbcs.data.entities.admin.service.LocationService;
import net.fast.lbcs.data.entities.admin.service.ServiceID;

public abstract class AdminController {
	public abstract boolean login(String user, String password);
	public abstract boolean isLoggedIn();
	public abstract Administrator getCurrentUser();
	
	public abstract List<LocationService> listServices(int startIndex, int endIndex);
	public abstract LocationService getServiceById(ServiceID serviceId);
	public abstract LocationService createService(String name, String description) throws ServiceCreationException;
	public abstract void deleteService(ServiceID serviceId) throws ServiceDeleteException;
	public abstract LocationService editService(ServiceID serviceId, String name, String description) throws ServiceEditException;
	
	public abstract List<ServiceItem> listItems();
	public abstract ServiceItem createItem(ServiceID serviceId, String name, String description, ServiceItemGroupID groupId) throws ServiceItemCreationException;
	public abstract void deleteItem(ServiceItemID itemId) throws ServiceItemDeleteException;
	public abstract ServiceItem editItem(ServiceItemID itemId, ServiceID serviceId, String name, String description, ServiceItemGroupID groupId) throws ServiceEditException;
	
	public abstract List<ServiceItemGroup> listItemGroups();
	public abstract ServiceItemGroup createItemGroup(ServiceID serviceId, String name, String description) throws ServiceItemCreationException;
	public abstract void deleteItemGroup(ServiceItemGroupID itemGroupId) throws ServiceItemDeleteException;
	public abstract ServiceItem editGroup(ServiceItemGroupID itemGroupId, ServiceID serviceId, String name, String description) throws ServiceEditException;

	public abstract ServiceItemAttribute createItemAttribute(String name, String type, String validation, String context, ServiceID serviceId, ServiceItemID itemId);
	public abstract void deleteItemAttribute(String AttributeId);
	public abstract ServiceItem editAttribute(String AttributeId, String name, String type, String validation, String context, ServiceID serviceId, ServiceItemID itemId);

}
