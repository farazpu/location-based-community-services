package net.fast.lbcs.data;


import java.util.List;

import net.fast.lbcs.admin.service.ServiceEditException;
import net.fast.lbcs.data.entities.ValidationTypeDetail;
import net.fast.lbcs.data.entities.admin.Administrator;
import net.fast.lbcs.data.entities.admin.group.ServiceItemGroup;
import net.fast.lbcs.data.entities.admin.group.ServiceItemGroupID;
import net.fast.lbcs.data.entities.admin.item.ServiceItem;
import net.fast.lbcs.data.entities.admin.item.ServiceItemAttribute;
import net.fast.lbcs.data.entities.admin.item.ServiceItemID;
import net.fast.lbcs.data.entities.admin.item.Validation;
import net.fast.lbcs.data.entities.admin.service.LocationService;
import net.fast.lbcs.data.entities.admin.service.ServiceID;
import net.fast.lbcs.data.entities.user.Location;
import net.fast.lbcs.data.entities.user.Product;
import net.fast.lbcs.data.entities.user.ProductAttribute;
import net.fast.lbcs.data.entities.user.ProductID;
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
	boolean deleteServiceGroup(ServiceID serviceId, ServiceItemGroupID groupId);
	ServiceItemGroup editGroup(ServiceItemGroupID itemGroupId, ServiceID serviceId, String name, String description);
	
	
	ServiceItem createItem(ServiceID serviceId, String name, String description, 
			ServiceItemGroupID groupId);
	boolean deleteServiceItem(ServiceID serviceId, ServiceItemID ItemId);
	ServiceItem editItem(ServiceItemID itemId, ServiceID serviceId, String name, String description, ServiceItemGroupID groupId);
	
	
	ServiceItemAttribute createItemAttribute(String name, String type, String ruleId, 
			String flag, ServiceID serviceId, ServiceItemID itemId);
	boolean deleteItemAttribute(ServiceID serviceId, ServiceItemID itemId, String attributeId);
	ServiceItemAttribute editAttribute(String AttributeId, String name, String type, String flag, ServiceID serviceId, ServiceItemID itemId, String ruleId);

	Product createProduct(ServiceID serviceId, ServiceItemID serviceItemId,
			ProductID productId, Location location, List<ProductAttribute> attrList, String productName);

	
	
	Validation createValidation(String name, String type , List<String> params);
	boolean deleteValidation(String validationId);
	Validation updateValidation(String validationId, String name, String type, List<String> params);
	List<Validation> getAllValidations();
	Validation getValidationById(String validationId);
	List<ValidationTypeDetail> getValidationTypeDetails();
	
	User queryUserByUserIdAndPassword(String user, String password);

}
