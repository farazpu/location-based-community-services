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
import net.fast.lbcs.data.entities.admin.Administrator;
import net.fast.lbcs.data.entities.admin.group.ServiceItemGroup;
import net.fast.lbcs.data.entities.admin.group.ServiceItemGroupID;
import net.fast.lbcs.data.entities.admin.item.ServiceItem;
import net.fast.lbcs.data.entities.admin.item.ServiceItemAttribute;
import net.fast.lbcs.data.entities.admin.item.ServiceItemAttributes;
import net.fast.lbcs.data.entities.admin.item.ServiceItemID;
import net.fast.lbcs.data.entities.admin.service.LocationService;
import net.fast.lbcs.data.entities.admin.service.ServiceID;

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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		DataSource source = DataSourceFactory.getDataSource();
		return source.editItem(itemId, serviceId, name, description, groupId);
	}

	@Override
	public List<ServiceItemGroup> listItemGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceItemGroup createItemGroup(ServiceID serviceId, String name,
			String description) throws ServiceItemCreationException {
		DataSource source = DataSourceFactory.getDataSource();
		return source.createGroup(serviceId, name, description);
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		DataSource source = DataSourceFactory.getDataSource();
		if(serviceId==null)
			return null;
		return source.getServiceById(serviceId);		
	}


	@Override
	public ServiceItemAttribute createItemAttribute(String name, String type,
			String flag, ServiceID serviceId,
			ServiceItemID itemId) {

		DataSource source = DataSourceFactory.getDataSource();
		return source.createItemAttribute(name, type, flag, serviceId, itemId);
	}

	@Override
	public void deleteItemAttribute(ServiceID serviceId, ServiceItemID itemId, String attributeId) {
		DataSource source = DataSourceFactory.getDataSource();
		source.deleteItemAttribute(serviceId, itemId, attributeId);
	}

	@Override
	public ServiceItemAttribute editAttribute(String AttributeId, String name,
			String type, String flag,
			ServiceID serviceId, ServiceItemID itemId) {
		DataSource source = DataSourceFactory.getDataSource();
		return source.editAttribute(AttributeId, name, type, flag, serviceId, itemId);
	}
	
	

	
}
