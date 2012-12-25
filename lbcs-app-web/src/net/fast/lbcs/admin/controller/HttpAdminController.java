package net.fast.lbcs.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class HttpAdminController extends AdminController{


	private static final String USER_ATTR = HttpAdminController.class.getName() + ".user";
	private static final Map<String,String> USERS = new HashMap<String,String>();
	private HttpServletRequest request;
	
	static {
		USERS.put("admin", "password123");
		USERS.put("aizaz", "password");
	}
	
	public HttpAdminController(HttpServletRequest request) {
		this.request = request;
		
	}
	
	@Override
	public boolean login(String user, String password) {
		if(USERS.containsKey(user) && password != null && password.equals(USERS.get(user))){
			Administrator admin=new Administrator(user,password);
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
	public List<LocationService> listServices() {
		// TODO Auto-generated method stub
		return null;
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
	
}
