package net.fast.lbcs.admin.controller;

import java.util.List;

import net.fast.lbcs.admin.item.ServiceItemCreationException;
import net.fast.lbcs.admin.item.ServiceItemDeleteException;
import net.fast.lbcs.admin.service.ServiceCreationException;
import net.fast.lbcs.admin.service.ServiceDeleteException;
import net.fast.lbcs.admin.service.ServiceEditException;
import net.fast.lbcs.data.DataSource;
import net.fast.lbcs.data.DataSourceFactory;
import net.fast.lbcs.data.entities.ValidationTypeDetail;
import net.fast.lbcs.data.entities.admin.Administrator;
import net.fast.lbcs.data.entities.admin.group.ServiceItemGroup;
import net.fast.lbcs.data.entities.admin.group.ServiceItemGroupID;
import net.fast.lbcs.data.entities.admin.item.ServiceItem;
import net.fast.lbcs.data.entities.admin.item.ServiceItemAttribute;
import net.fast.lbcs.data.entities.admin.item.ServiceItemAttributes;
import net.fast.lbcs.data.entities.admin.item.ServiceItemID;
import net.fast.lbcs.data.entities.admin.item.Validation;
import net.fast.lbcs.data.entities.admin.service.LocationService;
import net.fast.lbcs.data.entities.admin.service.ServiceID;
import net.fast.lbcs.data.entities.user.NotificationToAdmin;
import net.fast.lbcs.data.entities.user.ProductID;

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
	public abstract void deleteItem(ServiceID serviceId, ServiceItemID itemId) throws ServiceItemDeleteException;
	public abstract ServiceItem editItem(ServiceItemID itemId, ServiceID serviceId, String name, String description, ServiceItemGroupID groupId) throws ServiceEditException;
	
	public abstract List<ServiceItemGroup> listItemGroups();
	public abstract ServiceItemGroup createItemGroup(ServiceID serviceId, String name, String description) throws ServiceItemCreationException;
	public abstract void deleteItemGroup(ServiceID serviceId, ServiceItemGroupID itemGroupId) throws ServiceItemDeleteException;
	public abstract ServiceItemGroup editGroup(ServiceItemGroupID itemGroupId, ServiceID serviceId, String name, String description) throws ServiceEditException;

	public abstract ServiceItemAttribute createItemAttribute(String name, String type, String flag, ServiceID serviceId, ServiceItemID itemId, String ruleId);
	public abstract void deleteItemAttribute(ServiceID serviceId, ServiceItemID itemId, String AttributeId);
	public abstract ServiceItemAttribute editAttribute(String AttributeId, String name, String ruleId, String type, String flag, ServiceID serviceId, ServiceItemID itemId);

	public abstract Validation createValidation(String name, String type, List<String> params);
	public abstract boolean deleteValidation(String validationId);
	public abstract Validation updateValidation(String validationId, String name, String type, List<String> params);
	public abstract List<Validation> getAllValidations();
	public abstract List<ValidationTypeDetail> getValidationTypeDetails();


	public abstract void updateProductReviewStatus(ProductID productId, ServiceID serviceId,
			ServiceItemID itemId, String username, String status);
	public abstract void updateProductValueReviewStatus(ProductID productId, ServiceID serviceId,
			ServiceItemID itemId, String attributeId, String username, String status);
	public abstract void updateNotificationStatus(String id, String status);
	public abstract void deleteNotification(String id);
	public abstract List<NotificationToAdmin> getNotifications();
	
	
	public abstract void updateProductRating(ProductID productId, ServiceID serviceId,
			ServiceItemID itemId, String rating);
	public abstract void adjustProductValueReview(ProductID productId, ServiceID serviceId,
			ServiceItemID itemId, String attributeId, String username, String value);
	public abstract void updateProductAttributeValue(ProductID productId, ServiceID serviceId,
			ServiceItemID itemId, String attributeId, String value);



}
