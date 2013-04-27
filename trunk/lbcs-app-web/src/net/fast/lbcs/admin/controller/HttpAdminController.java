package net.fast.lbcs.admin.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.fast.lbcs.admin.item.ServiceItemCreationException;
import net.fast.lbcs.admin.item.ServiceItemDeleteException;
import net.fast.lbcs.admin.service.ServiceCreationException;
import net.fast.lbcs.admin.service.ServiceDeleteException;
import net.fast.lbcs.admin.service.ServiceEditException;
import net.fast.lbcs.data.DataSource;
import net.fast.lbcs.data.DataSourceFactory;
import net.fast.lbcs.data.entities.MyDate;
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
import net.fast.lbcs.data.entities.user.Product;
import net.fast.lbcs.data.entities.user.ProductID;
import net.fast.lbcs.data.entities.user.ProductValueReview;

public class HttpAdminController extends AdminController{


	private static final String USER_ATTR = HttpAdminController.class.getName() + ".user";
	
	private HttpServletRequest request;
	
	
	
	
	
	public HttpAdminController(HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public boolean login(String user, String password) {
		DataSource source = DataSourceFactory.getDataSource();
		Administrator admin = source.queryAdministratorByUserIdAndPassword(user, password);
		if(admin != null) {
			request.getSession(true).setAttribute(USER_ATTR, admin);
			return true;
		}

		return false;
	}

	@Override
	public boolean isLoggedIn() {
		return (getCurrentUser() != null);
	}

	@Override
	public Administrator getCurrentUser() {
		return (Administrator) request.getSession(true).getAttribute(USER_ATTR);
	}

	@Override
	public List<LocationService> listServices(int startIndex, int endIndex) {
		
		DataSource source = DataSourceFactory.getDataSource();
		return source.getAllServices(startIndex,endIndex);

	}

	@Override
	public LocationService createService(String name, String description)
			throws ServiceCreationException {
		DataSource source = DataSourceFactory.getDataSource();
		return source.createLocationService(name, description);
	}

	@Override
	public void deleteService(ServiceID serviceId)
			throws ServiceDeleteException {
		DataSource source = DataSourceFactory.getDataSource();
		source.deleteLocationService(serviceId);		
	}

	@Override
	public LocationService editService(ServiceID serviceId, String name, String description)
			throws ServiceEditException {
		DataSource source = DataSourceFactory.getDataSource();
		return source.editService(serviceId, name, description);
		
	}

	@Override
	public List<ServiceItem> listItems() {
		return null;
	}

	@Override
	public ServiceItem createItem(ServiceID serviceId, String name,
			String description, ServiceItemGroupID groupId)
			throws ServiceItemCreationException {
		
		DataSource source = DataSourceFactory.getDataSource();
		return source.createItem(serviceId, name, description, groupId);

	}

	@Override
	public void deleteItem(ServiceID serviceId, ServiceItemID itemId)
			throws ServiceItemDeleteException {
		DataSource source = DataSourceFactory.getDataSource();
		source.deleteServiceItem(serviceId, itemId);		
	}

	@Override
	public ServiceItem editItem(ServiceItemID itemId, ServiceID serviceId, String name, String description, ServiceItemGroupID groupId) throws ServiceEditException {
		DataSource source = DataSourceFactory.getDataSource();
		return source.editItem(itemId, serviceId, name, description, groupId);
	}

	@Override
	public List<ServiceItemGroup> listItemGroups() {
		return null;
	}

	@Override
	public ServiceItemGroup createItemGroup(ServiceID serviceId, String name,
			String description) throws ServiceItemCreationException {
		DataSource source = DataSourceFactory.getDataSource();
		return source.createGroup(serviceId, name, description);
	}

	@Override
	public void deleteItemGroup(ServiceID serviceId, ServiceItemGroupID itemGroupId)
			throws ServiceItemDeleteException {
		DataSource source = DataSourceFactory.getDataSource();
		source.deleteServiceGroup(serviceId, itemGroupId);		
	}

	@Override
	public ServiceItemGroup editGroup(ServiceItemGroupID itemGroupId, ServiceID serviceId, String name, String description) throws ServiceEditException {
		DataSource source = DataSourceFactory.getDataSource();
		return source.editGroup(itemGroupId, serviceId, name, description);
	}

	@Override
	public LocationService getServiceById(ServiceID serviceId) {
		DataSource source = DataSourceFactory.getDataSource();
		if(serviceId==null)
			return null;
		return source.getServiceById(serviceId);		
	}


	@Override
	public ServiceItemAttribute createItemAttribute(String name, String type,
			String flag, ServiceID serviceId, ServiceItemID itemId, String ruleId) {

		DataSource source = DataSourceFactory.getDataSource();
		return source.createItemAttribute(name, type, ruleId, flag, serviceId, itemId);
	}

	@Override
	public void deleteItemAttribute(ServiceID serviceId, ServiceItemID itemId, String attributeId) {
		DataSource source = DataSourceFactory.getDataSource();
		source.deleteItemAttribute(serviceId, itemId, attributeId);
	}

	@Override
	public ServiceItemAttribute editAttribute(String AttributeId, String name, String ruleId,
			String type, String flag, ServiceID serviceId, ServiceItemID itemId) {
		DataSource source = DataSourceFactory.getDataSource();
		return source.editAttribute(AttributeId, name, type, flag, serviceId, itemId, ruleId);
	}

	@Override
	public Validation createValidation(String name, String type, List<String> params) {
		DataSource source = DataSourceFactory.getDataSource();
		return source.createValidation(name, type, params);
	}

	@Override
	public boolean deleteValidation(String validationId) {
		DataSource source = DataSourceFactory.getDataSource();
		return source.deleteValidation(validationId);
	}

	@Override
	public Validation updateValidation(String validationId, String name,
			String type, List<String> params) {
		DataSource source = DataSourceFactory.getDataSource();
		return source.updateValidation(validationId, name, type, params);
	}

	@Override
	public List<Validation> getAllValidations() {
		DataSource source = DataSourceFactory.getDataSource();
		return source.getAllValidations();
	}

	@Override
	public List<ValidationTypeDetail> getValidationTypeDetails() {
		DataSource source = DataSourceFactory.getDataSource();
		return source.getValidationTypeDetails();
	}
	
	
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	//////////                   PRODUCT RELATED                   //////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////

	@Override
	public void updateProductReviewStatus(ProductID productId, ServiceID serviceId,
			ServiceItemID itemId, String username, String status){
		DataSource source = DataSourceFactory.getDataSource();
		source.updateProductReviewStatus(productId, serviceId, itemId, username, status);
		
	}


	@Override
	public void updateProductValueReviewStatus(ProductID productId, ServiceID serviceId,
			ServiceItemID itemId, String attributeId, String username,
			String status){
		DataSource source = DataSourceFactory.getDataSource();
		source.updateProductValueReviewStatus(productId, serviceId, itemId, attributeId, username, status);
	}

	

	@Override
	public void updateNotificationStatus(String id, String status){
		DataSource source = DataSourceFactory.getDataSource();
		source.updateNotificationStatus(id, status);
	}

	@Override
	public void deleteNotification(String id){
		DataSource source = DataSourceFactory.getDataSource();
		source.deleteNotification(id);
	}

	
	@Override
	public List<NotificationToAdmin> getNotifications(){
		DataSource source = DataSourceFactory.getDataSource();
		return source.getNotifications();
	}

	@Override
	public void updateProductRating(ProductID productId, ServiceID serviceId,
			ServiceItemID itemId, String rating) {
		DataSource source = DataSourceFactory.getDataSource();
		source.updateProductRating(productId, serviceId, itemId, rating);
		
	}

	@Override
	public void adjustProductValueReview(ProductID productId,
			ServiceID serviceId, ServiceItemID itemId, String attributeId,
			String username, String value) {
		DataSource source = DataSourceFactory.getDataSource();
		source.adjustProductValueReview(productId, serviceId, itemId, attributeId, username, value);
	}

	@Override
	public void updateProductAttributeValue(ProductID productId, ServiceID serviceId, 
			ServiceItemID itemId, String attributeId, String value) {
		DataSource source = DataSourceFactory.getDataSource();
		source.updateProductAttributeValue(productId, serviceId, itemId, attributeId, value);
	}

	
}
