package net.fast.lbcs.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.fast.lbcs.admin.Administrator;
import net.fast.lbcs.admin.group.ServiceItemGroup;
import net.fast.lbcs.admin.group.ServiceItemGroupID;
import net.fast.lbcs.admin.item.ServiceItem;
import net.fast.lbcs.admin.item.ServiceItemAttribute;
import net.fast.lbcs.admin.item.ServiceItemAttributes;
import net.fast.lbcs.admin.item.ServiceItemID;
import net.fast.lbcs.admin.item.Validation;
import net.fast.lbcs.admin.service.LocationService;
import net.fast.lbcs.admin.service.ServiceID;
import net.fast.lbcs.user.User;
import net.fast.lbcs.user.controller.Location;
import net.fast.lbcs.user.controller.Product;
import net.fast.lbcs.user.controller.ProductID;
import net.fast.lbcs.user.controller.UserSettings;

class InMemoryDataSource implements DataSource {
	private static List<Administrator> admins = new ArrayList<Administrator>();
	private static List<LocationService> locationServices = new ArrayList<LocationService>();

	private static List<User> users = new ArrayList<User>();
	private static List<Product> products = new ArrayList<Product>();
	
	static {
		createTestData();
	}
	
	

	public static List<Administrator> getAdmins() {
		return admins;
	}

	public static List<LocationService> getLocationServices() {
		return locationServices;
	}

	public static List<User> getUsers() {
		return users;
	}

	public static List<Product> getProducts() {
		return products;
	}

	private static Administrator createAdmin(String id, String password) {
		return new Administrator(id, password);
	}

	private static void createTestData() {
		createAdmins();
		createLocationServices();
		
		createUsers();
		
		createProducts();
	}

	private static void createProducts() {
		
		
		for (LocationService locationService : locationServices) {
			List<ServiceItem> items = locationService.getItems();
			for (ServiceItem serviceItem : items) {
				for (int i = 0; i < 5; i++) {
					Location location = null;
					if("Lahore".equals(locationService.getName())) {
						location = new Location(31.5758, 74.3269);
					} else if("Karachi".equals(locationService.getName())) {
						location = new Location(24.8508, 67.0181);
						
					}
					products.add(createProduct(serviceItem.getName() + " " + i, serviceItem, (i * 3) + 5, (i * 3) + 3, location));
				}
			}
		}
	}

	private static Product createProduct(String name, ServiceItem serviceItem, int price, int distance, Location location) {
		Product p = new Product(new ProductID(), name, serviceItem, price, 0, distance, location, null, 0, null);
		return p;
	}

	private static void createUsers() {
		
		for (int i = 0; i < 10; i++) {
			users.add(new User("user" + i, "pass" + i, new UserSettings(), null));
		}
	}

	private static void createLocationServices() {
		locationServices.add(createLocationService("Lahore"));	
		locationServices.add(createLocationService("Karachi"));	
	}

	private static LocationService createLocationService(String name) {
		List<ServiceItemGroup> groups = createGroups();
		LocationService service = new LocationService(
				new ServiceID(name + "-id"), 
				name, "Desciption of " + name, 
				new Date(), new Date(), 
				createItems(groups), groups);
		return service;
		
	}

	private static List<ServiceItemGroup> createGroups() {
		List<ServiceItemGroup> groups = new ArrayList<ServiceItemGroup>();
		groups.add(createGroup("Food"));
		groups.add(createGroup("Clothing"));
		return groups;
	}

	private static ServiceItemGroup createGroup(String name) {
		ServiceItemGroup group = new ServiceItemGroup(new ServiceItemGroupID(name + "-id"), name, "Description of " + name + " group.");
		return group;
	}

	private static List<ServiceItem> createItems(List<ServiceItemGroup> groups) {
		
		List<ServiceItem> items = new ArrayList<ServiceItem>();
		
		items.add(createItem("Burger", groups.get(0)));
		items.add(createItem("Pizza", groups.get(0)));
		items.add(createItem("Chaat", groups.get(0)));
		
		items.add(createItem("Shirt", groups.get(0)));
		items.add(createItem("Pants", groups.get(0)));
		
		return items;
	}

	private static ServiceItem createItem(String name,
			ServiceItemGroup serviceItemGroup) {
		return new ServiceItem(new ServiceItemID(name + "-id"), name, createItemAttributes(name), serviceItemGroup);
	}

	private static ServiceItemAttributes createItemAttributes(String name) {
		List<ServiceItemAttribute> list = new ArrayList<ServiceItemAttribute>();
		if("Shirt".equals(name)) {
			list.add(new ServiceItemAttribute("Make", Validation.string));
			list.add(new ServiceItemAttribute("Size", Validation.string));
		} 

		ServiceItemAttributes attrs = new ServiceItemAttributes(list);
		
		return attrs;
	}

	private static void createAdmins() {
		admins.add(createAdmin("admin", "pass"));
		admins.add(createAdmin("aizaz", "pass"));
	}
	
	
	public static void main(String[] args) {
		System.out.println("========== Admins ==========");
		System.out.println(admins);
		System.out.println("========== Location Services ==========");
		System.out.println(locationServices);
		System.out.println("========== Users ==========");
		System.out.println(users);
		System.out.println("========== Products ==========");
		System.out.println(products);
		
	}

	@Override
	public Administrator queryAdministratorByUserIdAndPassword(String user,
			String password) {
		
		for (Administrator administrator : admins) {
			if(administrator.getId().equals(user) && 
					administrator.getPassword().equals(password) ) {
				return administrator;
			}
		}
		return null;
	}

	@Override
	public List<LocationService> getAllServices() {
		return locationServices;
	}
	
}
