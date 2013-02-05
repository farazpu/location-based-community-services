package net.fast.lbcs.data;


import java.util.List;

import net.fast.lbcs.admin.service.ServiceEditException;
import net.fast.lbcs.data.entities.admin.Administrator;
import net.fast.lbcs.data.entities.admin.group.ServiceItemGroup;
import net.fast.lbcs.data.entities.admin.group.ServiceItemGroupID;
import net.fast.lbcs.data.entities.admin.item.ServiceItem;
import net.fast.lbcs.data.entities.admin.item.ServiceItemAttribute;
import net.fast.lbcs.data.entities.admin.item.ServiceItemID;
import net.fast.lbcs.data.entities.admin.service.LocationService;
import net.fast.lbcs.data.entities.admin.service.ServiceID;
import net.fast.lbcs.data.entities.user.User;

public interface DataSource {

	Administrator queryAdministratorByUserIdAndPassword(String user,
			String password);

	List<LocationService> getAllServices(int startIndex, int endIndex);
	LocationService getServiceById(ServiceID serviceId);
	LocationService createLocationService(String name, String description);
	boolean deleteLocationService(ServiceID serviceId);
	LocationService editService(ServiceID serviceId, String name, String description);
	
	ServiceItemGroup createGroup(ServiceID serviceID, String name, String description);
	boolean deleteServiceGroup(ServiceItemGroupID groupId);
	ServiceItemGroup editGroup(ServiceItemGroupID itemGroupId, ServiceID serviceId, String name, String description);
	
	
	ServiceItem createItem(ServiceID serviceId, String name, String description, 
			ServiceItemGroupID groupId);
	ServiceItem getItemById( ServiceItemID serviceItemId);
	boolean deleteServiceItem(ServiceItemID ItemId);
	ServiceItem editItem(ServiceItemID itemId, ServiceID serviceId, String name, String description, ServiceItemGroupID groupId);
	
	
	ServiceItemAttribute createItemAttribute(String name, String Type, 
			String validation, String context, ServiceID serviceId, ServiceItemID itemId);
	boolean deleteItemAttribute(String attributeId);
	ServiceItemAttribute editAttribute(String AttributeId, String name, String type, String validation, String context, ServiceID serviceId, ServiceItemID itemId);

	
	User queryUserByUserIdAndPassword(String user, String password);

}
