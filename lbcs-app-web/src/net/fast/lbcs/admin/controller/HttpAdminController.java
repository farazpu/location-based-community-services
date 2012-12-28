package net.fast.lbcs.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.fast.lbcs.admin.Administrator;
import net.fast.lbcs.admin.group.ServiceItemGroup;
import net.fast.lbcs.admin.group.ServiceItemGroupID;
import net.fast.lbcs.admin.item.ServiceItem;
import net.fast.lbcs.admin.item.ServiceItemAttributes;
import net.fast.lbcs.admin.item.ServiceItemCreationException;
import net.fast.lbcs.admin.item.ServiceItemDeleteException;
import net.fast.lbcs.admin.item.ServiceItemID;
import net.fast.lbcs.admin.service.LocationService;
import net.fast.lbcs.admin.service.ServiceCreationException;
import net.fast.lbcs.admin.service.ServiceDeleteException;
import net.fast.lbcs.admin.service.ServiceEditException;
import net.fast.lbcs.admin.service.ServiceID;
import net.fast.lbcs.data.DataSource;
import net.fast.lbcs.data.DataSourceFactory;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteService(ServiceID serviceId)
			throws ServiceDeleteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LocationService editService(ServiceID serviceId, String description)
			throws ServiceEditException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ServiceItem> listItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceItem createItem(ServiceID serviceId, String name,
			String description, ServiceItemAttributes attributes)
			throws ServiceItemCreationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteItem(ServiceItemID itemId)
			throws ServiceItemDeleteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServiceItem editItem(ServiceItemID itemId,
			ServiceItemAttributes attributes) throws ServiceEditException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ServiceItemGroup> listItemGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceItemGroup createItemGroup(ServiceID serviceId, String name,
			String description) throws ServiceItemCreationException {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteItemGroup(ServiceItemGroupID itemGroupId)
			throws ServiceItemDeleteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServiceItem editGroup(ServiceItemGroupID itemGroupId,
			String description) throws ServiceEditException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocationService getServiceById(ServiceID serviceId) {
		// TODO Auto-generated method stub
		DataSource source = DataSourceFactory.getDataSource();
		if(serviceId==null)
			return null;
		return source.getServiceById(serviceId);		
	}
	
	

	
}
