package net.fast.lbcs.admin.controller;

import java.util.ArrayList;
import java.util.Date;
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
	private static final Map<String,List<ServiceID>> ADMIN_SERVICES = new HashMap<String,List<ServiceID>>();
	private static List<LocationService> services;
	
	private HttpServletRequest request;
	
	static {
		USERS.put("admin", "password123");
		USERS.put("aizaz", "password");
		USERS.put("awain", "awain");
	}
	
	
	
	
	public HttpAdminController(HttpServletRequest request) {
		this.request = request;
		//temporary method;
		staticServices();
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
		List<LocationService> service_list=new ArrayList<LocationService>();
		List<ServiceID> idList=ADMIN_SERVICES.get(getCurrentUser().getId());
		if(idList!=null)
		{
			for(int i = 0 ; i < services.size() ; i++) {
				LocationService service=services.get(i);
				boolean found=false;
				for(int j = 0 ; j < idList.size() && !found ; j++) {
					if( idList.get(j).getId().equals(service.getId().getId()) ) {
						service_list.add(service);
						found=true;
					}
				}
			}
		}		
		return service_list;
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
	
	
	
/*
 * temp methods for prototype	
 */
	private void staticServices() {
		services=new ArrayList<LocationService>();
		LocationService service;
		for(int i=1;i<=8;i++) {			
			service = new LocationService();
			service.setId(new ServiceID(""+i));
			service.setName("service"+i);
			service.setCreated(new Date(2011, i, 10+i));
			service.setLastModified(new Date(2012, i, i));
			service.setDesciption("Discription "+i);
			services.add(service);
		}
		List list1=new ArrayList<ServiceID>();
		for(int i=1;i<=6;i++)
			list1.add(new ServiceID(""+i));
		ADMIN_SERVICES.put("admin",list1);
		
		List list2=new ArrayList<ServiceID>();
		for(int i=4;i<=8;i++)
			list2.add(new ServiceID(""+i));
		ADMIN_SERVICES.put("aizaz",list2);
		
		

	}
	
}
