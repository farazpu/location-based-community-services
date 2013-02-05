package net.fast.lbcs.data;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import net.fast.lbcs.data.entities.admin.Administrator;
import net.fast.lbcs.data.entities.admin.group.ServiceItemGroup;
import net.fast.lbcs.data.entities.admin.group.ServiceItemGroupID;
import net.fast.lbcs.data.entities.admin.item.ServiceItem;
import net.fast.lbcs.data.entities.admin.item.ServiceItemAttribute;
import net.fast.lbcs.data.entities.admin.item.ServiceItemAttributes;
import net.fast.lbcs.data.entities.admin.item.ServiceItemID;
import net.fast.lbcs.data.entities.admin.service.LocationService;
import net.fast.lbcs.data.entities.admin.service.ServiceID;
import net.fast.lbcs.data.entities.user.Location;
import net.fast.lbcs.data.entities.user.Product;
import net.fast.lbcs.data.entities.user.ProductAttribute;
import net.fast.lbcs.data.entities.user.ProductID;
import net.fast.lbcs.data.entities.user.ProductReview;
import net.fast.lbcs.data.entities.user.User;
import net.fast.lbcs.data.entities.user.UserSettings;
import net.fast.lbcs.memcache.Memcache;

class InMemoryDataSource implements DataSource {
	
	private final static String KIND_SERVICE = "LBCS Location Service";
	private final static String KIND_ITEM = "LBCS Service Object";
	private final static String KIND_GROUP = "LBCS Group";
	private final static String KIND_ATTRIBUTE = "LBCS Object Attribute";
	
	private interface TableService{
		final static String ID = "id";
		final static String NAME = "name";
		final static String DESCRIPTION = "description";
		final static String CREATION_DATE = "creation date";
		final static String MODIFIED_DATE = "modified date";

	}
	
	private interface TableGroup{
		final static String ID = "id";
		final static String SERVICE_ID = "service id";
		final static String NAME = "name";
		final static String DESCRIPTION = "description";
		final static String CREATION_DATE = "creation date";
		final static String MODIFIED_DATE = "modified date";
		
	}

	private interface TableItem{
		final static String ID = "id";
		final static String SERVICE_ID = "service id";
		final static String GROUP_ID = "group id";
		final static String NAME = "name";
		final static String DESCRIPTION = "description";
		final static String MODIFIED_DATE = "modified date";
		
	}
	
	private interface TableAttribute{
		final static String ID = "id";
		final static String SERVICE_ID = "service id";
		final static String ITEM_ID = "item id";
		final static String NAME = "name";
		final static String VALIDATION = "validation";
		final static String CONTEXT = "context";
		final static String TYPE = "type";
		
				
	}
	
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
//		return locationServices;
		List<LocationService> locationServices = new ArrayList<LocationService>();
		DatastoreService service = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query(KIND_SERVICE);
		List<Entity> list = service.prepare(query).asList(FetchOptions.Builder.withDefaults());
		for(Entity serviceEntity : list){
			LocationService ls = entityToLocationService(serviceEntity);
			locationServices.add(ls);
		}
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


    public static boolean test() {
        String query = "select * from location_service";
        ResultSet rs = DataAccessHelper.executeQuery(query);

        try{
        	while(rs.next()){
        		System.out.println(rs.getString(1));
        	}
        }
        catch(Exception ex){
            System.out.println("+++++"+ex.getMessage());

        }

        DataAccessHelper.closeConnection();
        return false;
    }


	private static void createTestData() {
		test();
		createAdmins();
//		createLocationServices();
		
		createUsers();
		
		createProducts();
	}

	private static void createProducts() {
		
		
		int x=2;
		for (LocationService locationService : locationServices) {
			List<ServiceItem> items = locationService.getItems();
			for (ServiceItem serviceItem : items) {
				for (int i = 0; i < x; i++) {
					Location location = null;
					if("Lahore".equals(locationService.getName())) {
						location = new Location(31.575-0.1*i, 74.3269+0.1*i);
					} else if("Karachi".equals(locationService.getName())) {
						location = new Location(24.8508-0.1*i, 67.0181+0.1*i);
					} else if("Pishawar".equals(locationService.getName())) {
						location = new Location(33.9959-0.1*i, 71.5526+0.1*i);
					} else if("Islamabad".equals(locationService.getName())) {
						location = new Location(33.6720-0.1*i, 73.0439+0.1*i);
					} else if("Sahiwal".equals(locationService.getName())) {
						location = new Location(30.6586-0.1*i, 73.0898+0.1*i);
					} else if("Multan".equals(locationService.getName())) {
						location = new Location(30.1832-0.1*i, 71.4805+0.1*i);
						
					}
					products.add(createProduct(serviceItem.getName() + " " + locationService.getName()  + " " + i, serviceItem, (i * 3) + 5, (i * 3) + 3, location));
					
				}
			}
			x++;
		}
	}

	private static Product createProduct(String name, ServiceItem serviceItem, int price, int distance, Location location) {
		List<ProductAttribute> attrs = new ArrayList<ProductAttribute>();
		List<ProductReview> rvs = new ArrayList<ProductReview>();
		Product p = new Product(new ProductID(name + "-id"), name, serviceItem, price, 0, distance, location, attrs, 0, rvs);
		p.initAttributesWithDefaultValues();
		return p;
	}

	private static void createUsers() {
		
		for (int i = 0; i < 10; i++) {
			users.add(new User("user" + i, "pass" + i, new UserSettings(), null));
		}
	}


	@Override
	public LocationService createLocationService(String name, String description) {

		Date currentDate = new Date();
		String id = name + "-ID" + currentDate;
		ServiceID serviceId = new ServiceID(id);
		
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		Key key = KeyFactory.createKey(KIND_SERVICE, id);
		
		// check for duplicates
		Filter duplicateFilter = new FilterPredicate(TableService.NAME, FilterOperator.EQUAL, name);
		Query query = new Query(KIND_SERVICE).setFilter(duplicateFilter);
		Entity entity = datastoreService.prepare(query).asSingleEntity();
		if(entity != null || Memcache.isServiceInMemCache(serviceId,name)){
			return null;
		}
		
		////
		
		Entity serviceEntity = new Entity(key);
		serviceEntity.setProperty(TableService.ID, id);
		serviceEntity.setProperty(TableService.NAME, name);
		serviceEntity.setProperty(TableService.DESCRIPTION, description);
		serviceEntity.setProperty(TableService.CREATION_DATE, currentDate);
		serviceEntity.setProperty(TableService.MODIFIED_DATE, currentDate);
		datastoreService.put(serviceEntity);
		
		Memcache.cacheService(serviceEntity);
		
		LocationService service = new LocationService(
				serviceId, 
				name, description,
				currentDate, currentDate, 
				null, null);
		
		return service;

	}
	
	@Override
	public ServiceItemGroup createGroup(ServiceID serviceID, String name, String description) {

		Date currentDate = new Date();
		String id = name + "-ID (" + serviceID.getId() + ")" + currentDate;
		ServiceItemGroupID groupId=new ServiceItemGroupID(id);
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		Key key = KeyFactory.createKey(KIND_GROUP, id);
		// check for duplicates
		
		Filter duplicateFilterName = new FilterPredicate(TableGroup.NAME, FilterOperator.EQUAL, name);
		Filter duplicateFilterService = new FilterPredicate(TableGroup.SERVICE_ID, FilterOperator.EQUAL, serviceID.getId());
		Filter duplicateFilter = CompositeFilterOperator.and(duplicateFilterName,duplicateFilterService);
		Query query = new Query(KIND_GROUP).setFilter(duplicateFilter);
		Entity entity = datastoreService.prepare(query).asSingleEntity();
		if(entity != null || Memcache.isGroupInMemCache(groupId, name, serviceID)){
			return null;
		}
		
		////
		Entity groupEntity = new Entity(key);
		groupEntity.setProperty(TableGroup.SERVICE_ID, serviceID.getId());
		groupEntity.setProperty(TableGroup.ID, id);
		groupEntity.setProperty(TableGroup.NAME, name);
		groupEntity.setProperty(TableGroup.DESCRIPTION, description);
		groupEntity.setProperty(TableGroup.CREATION_DATE, currentDate);
		groupEntity.setProperty(TableGroup.MODIFIED_DATE, currentDate);
		datastoreService.put(groupEntity);
		
		Memcache.cacheGroup(groupEntity);
		
		ServiceItemGroup group = new ServiceItemGroup(groupId, name, description, currentDate, currentDate);
		return group;
	}

/*	private static List<ServiceItem> createItems(List<ServiceItemGroup> groups) {
		
		List<ServiceItem> items = new ArrayList<ServiceItem>();
		
		items.add(createItem("Burger", groups.get(0)));
		items.add(createItem("Pizza", groups.get(0)));
		items.add(createItem("Chaat", groups.get(0)));
		
		items.add(createItem("Shirt", groups.get(1)));
		items.add(createItem("Pants", groups.get(1)));
		
		return items;
	}
*/
	@Override
	public ServiceItem createItem(ServiceID serviceId, String name, 
			String description, ServiceItemGroupID serviceItemGroupId) {

		Date currentDate = new Date();
		String id = name + "-ID(" + serviceId.getId() + ")" + currentDate;
		ServiceItemID itemId=new ServiceItemID(id);
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		Key key = KeyFactory.createKey(KIND_ITEM, id);
		// check for duplicates
		
		Filter duplicateFilterName = new FilterPredicate(TableItem.NAME, FilterOperator.EQUAL, name);
		Filter duplicateFilterService = new FilterPredicate(TableItem.SERVICE_ID, FilterOperator.EQUAL, serviceId.getId());
		Filter duplicateFilter = CompositeFilterOperator.and(duplicateFilterName,duplicateFilterService);
		Query query = new Query(KIND_ITEM).setFilter(duplicateFilter);
		Entity entity = datastoreService.prepare(query).asSingleEntity();
		if(entity != null || Memcache.isItemInMemCache(itemId, name, serviceId)){
			return null;
		}
		
		////
		Entity itemEntity = new Entity(key);
		itemEntity.setProperty(TableItem.SERVICE_ID, serviceId.getId());
		itemEntity.setProperty(TableItem.GROUP_ID, serviceItemGroupId.getId());
		itemEntity.setProperty(TableItem.ID, id);
		itemEntity.setProperty(TableItem.NAME, name);
		itemEntity.setProperty(TableItem.DESCRIPTION, description);
		itemEntity.setProperty(TableItem.MODIFIED_DATE, currentDate);
		datastoreService.put(itemEntity);
		
		Memcache.cacheItem(itemEntity);
		
		return new ServiceItem(itemId, name, null, null, currentDate, description);
			
	}

/*	private static ServiceItemAttributes createItemAttributes(String name) {
		List<ServiceItemAttribute> list = new ArrayList<ServiceItemAttribute>();
		if("Shirt".equals(name)) {
			list.add(new ServiceItemAttribute("Make", Validation.string));
			list.add(new ServiceItemAttribute("Size", Validation.number));
			list.add(new ServiceItemAttribute("Brand", Validation.string));
		} 
		if("Burger".equals(name)) {
			list.add(new ServiceItemAttribute("Price", Validation.number));
		} 
		if("Pizza".equals(name)) {
			list.add(new ServiceItemAttribute("Price", Validation.number));
			list.add(new ServiceItemAttribute("Size", Validation.number));
			list.add(new ServiceItemAttribute("Flavour", Validation.string));
		} 
		if("Chaat".equals(name)) {
			list.add(new ServiceItemAttribute("Flavour", Validation.string));
			list.add(new ServiceItemAttribute("Price", Validation.string));
		} 
		if("pants".equals(name)) {
			list.add(new ServiceItemAttribute("Make", Validation.string));
			list.add(new ServiceItemAttribute("Length", Validation.number));
			list.add(new ServiceItemAttribute("Waist", Validation.number));
			list.add(new ServiceItemAttribute("Brand", Validation.string));
		} 

		ServiceItemAttributes attrs = new ServiceItemAttributes(list);
		
		return attrs;
	}
*/
	@Override
	public ServiceItemAttribute createItemAttribute(String name, String type, 
			String validation, String context, ServiceID serviceId, ServiceItemID itemId) {
		
		Date currentDate = new Date();
		String id = name + "-ID(" + itemId.getId() + "-" + serviceId.getId() + ")" + currentDate;
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		Key key = KeyFactory.createKey(KIND_ATTRIBUTE, id);
		// check for duplicates
		
		Filter duplicateFilterName = new FilterPredicate(TableAttribute.NAME, FilterOperator.EQUAL, name);
		Filter duplicateFilterItem = new FilterPredicate(TableAttribute.ITEM_ID, FilterOperator.EQUAL, itemId.getId());
		Filter duplicateFilterService = new FilterPredicate(TableAttribute.SERVICE_ID, FilterOperator.EQUAL, serviceId.getId());
		Filter duplicateFilter = CompositeFilterOperator.and(duplicateFilterName,duplicateFilterService,duplicateFilterItem);
		Query query = new Query(KIND_ATTRIBUTE).setFilter(duplicateFilter);
		Entity entity = datastoreService.prepare(query).asSingleEntity();
		if(entity != null || Memcache.isAttributeInMemCache(id, name, serviceId, itemId)){
			return null;
		}
		
		////
		Entity attributeEntity = new Entity(key);
		attributeEntity.setProperty(TableAttribute.SERVICE_ID, serviceId.getId());
		attributeEntity.setProperty(TableAttribute.ITEM_ID, itemId.getId());
		attributeEntity.setProperty(TableAttribute.ID, id);
		attributeEntity.setProperty(TableAttribute.NAME, name);
		attributeEntity.setProperty(TableAttribute.VALIDATION, validation);
		attributeEntity.setProperty(TableAttribute.CONTEXT, context);
		attributeEntity.setProperty(TableAttribute.TYPE, type);
		datastoreService.put(attributeEntity);
		
		Memcache.cacheAttr(attributeEntity);
		
		return new ServiceItemAttribute(id, name, validation, type, context); 
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
	public User queryUserByUserIdAndPassword(String username,
			String password) {
		
		for (User user : users) {
			if(user.getId().equals(username) && 
					user.getPassword().equals(password) ) {
				return user;
			}
		}
		return null;
	}
	
	

	@Override
	public List<LocationService> getAllServices(int startIndex, int endIndex) {
/*		List<LocationService> servicePage=new ArrayList<LocationService>();
		for(int i=startIndex;i<endIndex && i<locationServices.size();i++) {
			servicePage.add(locationServices.get(i));
		}
		return servicePage;
*/
		return getLocationServices();
	}

	@Override
	public LocationService getServiceById(ServiceID serviceId) {
		DatastoreService service = DatastoreServiceFactory.getDatastoreService();
		Filter idFilter = new FilterPredicate(TableService.ID, FilterOperator.EQUAL, serviceId.getId());
		Query query = new Query(KIND_SERVICE).setFilter(idFilter);
		Entity serviceEntity = service.prepare(query).asSingleEntity();
		LocationService ls = entityToLocationService(serviceEntity);
		return ls;
	}
	

	private static List<ServiceItem> getAllServiceItemsByServiceID(ServiceID serviceId){
		List<ServiceItem> itemList = new ArrayList<ServiceItem>();
		DatastoreService service = DatastoreServiceFactory.getDatastoreService();
		Filter idFilter = new FilterPredicate(TableItem.SERVICE_ID, FilterOperator.EQUAL, serviceId.getId());
		Query query = new Query(KIND_ITEM).setFilter(idFilter);
		List<Entity> itemEntityList = service.prepare(query).asList(FetchOptions.Builder.withDefaults());
		for(Entity itemEntity : itemEntityList){
			ServiceItem si = entityToServiceItem(itemEntity);
			itemList.add(si);
		}

		return itemList;
	}
	
	
	private static List<ServiceItemGroup> getAllServiceGroupsByServiceID(ServiceID serviceId){
		List<ServiceItemGroup> groupList = new ArrayList<ServiceItemGroup>();
		DatastoreService service = DatastoreServiceFactory.getDatastoreService();
		Filter idFilter = new FilterPredicate(TableGroup.SERVICE_ID, FilterOperator.EQUAL, serviceId.getId());
		Query query = new Query(KIND_GROUP).setFilter(idFilter);
		List<Entity> groupEntityList = service.prepare(query).asList(FetchOptions.Builder.withDefaults());
		for(Entity groupEntity : groupEntityList){
			ServiceItemGroup sig = entityToServiceItemGroup(groupEntity);
			groupList.add(sig);
		}

		return groupList;
	}
	
	private static LocationService entityToLocationService(Entity entity){
		String name = (String) entity.getProperty(TableService.NAME);
		ServiceID id = new ServiceID((String) entity.getProperty(TableService.ID));
		String description = (String) entity.getProperty(TableService.DESCRIPTION);
		Date creationDate = (Date) entity.getProperty(TableService.CREATION_DATE);
		Date modifiedDate = (Date) entity.getProperty(TableService.MODIFIED_DATE);
	
		List<ServiceItemGroup> groupList = getAllServiceGroupsByServiceID(id);
		List<ServiceItem> itemList = getAllServiceItemsByServiceID(id);
		return new LocationService(id, name, description, creationDate, modifiedDate, itemList, groupList);
	}
	
	private static ServiceItem entityToServiceItem(Entity entity){
		String name = (String) entity.getProperty(TableItem.NAME);
		ServiceItemID id = new ServiceItemID((String) entity.getProperty(TableItem.ID));
		String description = (String)entity.getProperty(TableItem.DESCRIPTION);
		Date modifiedDate = (Date) entity.getProperty(TableItem.MODIFIED_DATE);
		String serviceId = (String)entity.getProperty(TableItem.SERVICE_ID);
		String groupid = (String) entity.getProperty(TableItem.GROUP_ID); 


		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

		ServiceItemGroup sig;
//		get group
		{
			Filter filter= new FilterPredicate(TableGroup.ID, FilterOperator.EQUAL, groupid);
			Query query = new Query(KIND_GROUP).setFilter(filter);
			Entity groupEntity = datastoreService.prepare(query).asSingleEntity();
			sig = entityToServiceItemGroup(groupEntity);
		}
		
		
		List<ServiceItemAttribute> attrList = new ArrayList<ServiceItemAttribute>();
//		fill Attributes
		{
			Filter serviceidFilter = new FilterPredicate(TableAttribute.SERVICE_ID, FilterOperator.EQUAL, serviceId);
			Filter groupidFilter = new FilterPredicate(TableAttribute.ITEM_ID, FilterOperator.EQUAL, id.getId());
			Filter filter = CompositeFilterOperator.and(serviceidFilter, groupidFilter);
			Query query = new Query(KIND_ATTRIBUTE).setFilter(filter);
			List<Entity> attrEntityList = datastoreService.prepare(query).asList(FetchOptions.Builder.withDefaults());
			for(Entity attrEntity : attrEntityList){
				attrList.add(entityToServiceItemAttribute(attrEntity));
			}
		}
		ServiceItemAttributes attributes = new ServiceItemAttributes(attrList) ;
		
		return new ServiceItem(id, name, attributes, sig, modifiedDate, description);
	}
	

	
	private static ServiceItemAttribute entityToServiceItemAttribute(
			Entity entity) {
		String id = (String) entity.getProperty(TableAttribute.ID);
		String name = (String) entity.getProperty(TableAttribute.NAME);
		String type = (String) entity.getProperty(TableAttribute.TYPE);
		String context = (String) entity.getProperty(TableAttribute.CONTEXT);
		String validation = (String) entity.getProperty(TableAttribute.VALIDATION);
		
		return new ServiceItemAttribute(id, name, validation, type, context);
	}

	private static ServiceItemGroup entityToServiceItemGroup(Entity entity){
		String name = (String) entity.getProperty(TableGroup.NAME);
		ServiceItemGroupID id = new ServiceItemGroupID((String) entity.getProperty(TableGroup.ID));
		String description = (String)entity.getProperty(TableGroup.DESCRIPTION);
		Date creationDate = (Date) entity.getProperty(TableGroup.CREATION_DATE);
		Date modifiedDate = (Date) entity.getProperty(TableGroup.MODIFIED_DATE);
		return new ServiceItemGroup(id, name, description, creationDate, modifiedDate);
	}

	@Override
	public ServiceItem getItemById(	ServiceItemID serviceItemId) {

		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		Filter filter = new FilterPredicate(TableItem.ID, FilterOperator.EQUAL, serviceItemId.getId());
		Query query = new Query(KIND_ITEM).setFilter(filter);
		Entity itemEntity = datastoreService.prepare(query).asSingleEntity();
		ServiceItem item = entityToServiceItem(itemEntity);
		
		return item;
	}

	@Override
	public boolean deleteLocationService(ServiceID serviceId) {
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		LocationService ls = getServiceById(serviceId);
		List<ServiceItemGroup> groupList = ls.getGroups();
		for(ServiceItemGroup group : groupList){
			deleteServiceGroup(group.getId());
		}
		Key key = KeyFactory.createKey(KIND_SERVICE, serviceId.getId());
		datastoreService.delete(key);
		return true;
	}

	private static List<ServiceItem> getItemsByGroup(ServiceItemGroupID groupId){
		List<ServiceItem> itemList = new ArrayList<ServiceItem>();
		DatastoreService service = DatastoreServiceFactory.getDatastoreService();
		Filter idFilter = new FilterPredicate(TableItem.GROUP_ID, FilterOperator.EQUAL, groupId.getId());
		Query query = new Query(KIND_ITEM).setFilter(idFilter);
		List<Entity> itemEntityList = service.prepare(query).asList(FetchOptions.Builder.withDefaults());
		for(Entity itemEntity : itemEntityList){
			ServiceItem si = entityToServiceItem(itemEntity);
			itemList.add(si);
		}

		return itemList;
		
	}
	
	@Override
	public boolean deleteServiceGroup(ServiceItemGroupID groupId) {
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		List<ServiceItem> itemList = getItemsByGroup(groupId);
		for(ServiceItem item : itemList){
			deleteServiceItem(item.getId());
		}
		Key key = KeyFactory.createKey(KIND_GROUP, groupId.getId());		
		datastoreService.delete(key);
		return true;
	}

	@Override
	public boolean deleteServiceItem(ServiceItemID itemId) {
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		ServiceItem item = getItemById(itemId);
		List<ServiceItemAttribute> attrList = item.getAttrs().getAttrs();
		for(ServiceItemAttribute attr : attrList){
			Key attrKey = KeyFactory.createKey(KIND_ATTRIBUTE, attr.getId());
			datastoreService.delete(attrKey);
		}
		Key key = KeyFactory.createKey(KIND_ITEM, itemId.getId());
		datastoreService.delete(key);
		return true;
	}

	@Override
	public boolean deleteItemAttribute(String attributeId) {
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		Key key = KeyFactory.createKey(KIND_ATTRIBUTE, attributeId);
		datastoreService.delete(key);
		return true;
	}

	@Override
	public LocationService editService(ServiceID serviceId, String name,
			String description) {
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		
		
		Filter selectionFilter = new FilterPredicate(TableService.ID, FilterOperator.EQUAL, serviceId.getId());
		Query query = new Query(KIND_SERVICE).setFilter(selectionFilter);
		Entity serviceEntity = datastoreService.prepare(query).asSingleEntity();
		String oldname = (String) serviceEntity.getProperty(TableService.NAME);
		if(!(name.equals(oldname))){
			// check for duplicates
			Filter duplicateFilter1 = new FilterPredicate(TableService.NAME, FilterOperator.EQUAL, name);
			Filter duplicateFilter2 = new FilterPredicate(TableService.ID, FilterOperator.NOT_EQUAL, serviceId.getId());
			Filter duplicateFilter = CompositeFilterOperator.and(duplicateFilter1,duplicateFilter2);
			Query duplicatequery = new Query(KIND_SERVICE).setFilter(duplicateFilter);
			Entity entity = datastoreService.prepare(duplicatequery).asSingleEntity();
			if(entity != null || Memcache.isServiceInMemCache(serviceId, name)){
				return null;
			}
			
		}
		
		Date currentDate = new Date();
		serviceEntity.setProperty(TableService.NAME, name);
		serviceEntity.setProperty(TableService.DESCRIPTION, description);
		serviceEntity.setProperty(TableService.MODIFIED_DATE, currentDate);
		datastoreService.put(serviceEntity);
		
		LocationService service = new LocationService(
				serviceId,
				name, description,
				currentDate, currentDate, 
				null, null);
		
		Memcache.cacheService(serviceEntity);
		
		return service;
	}

	@Override
	public ServiceItemGroup editGroup(ServiceItemGroupID itemGroupId,
			ServiceID serviceID, String name, String description) {

		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		Filter selectionFilter= new FilterPredicate(TableGroup.ID, FilterOperator.EQUAL, itemGroupId.getId());
		Query query = new Query(KIND_GROUP).setFilter(selectionFilter);
		Entity groupEntity = datastoreService.prepare(query).asSingleEntity();
		String oldname = (String) groupEntity.getProperty(TableGroup.NAME);

		if(!(name.equals(oldname))){
			// check for duplicates
			Filter duplicateFilterName = new FilterPredicate(TableGroup.NAME, FilterOperator.EQUAL, name);
			Filter duplicateFilterService = new FilterPredicate(TableGroup.SERVICE_ID, FilterOperator.EQUAL, serviceID.getId());
			Filter duplicateFilter = CompositeFilterOperator.and(duplicateFilterName, duplicateFilterService);
			Query duplicatequery = new Query(KIND_SERVICE).setFilter(duplicateFilter);
			Entity entity = datastoreService.prepare(duplicatequery).asSingleEntity();
			if(entity != null || Memcache.isGroupInMemCache(itemGroupId, name, serviceID)){
				return null;
			}
			
		}
		Date currentDate = new Date();
		groupEntity.setProperty(TableGroup.NAME, name);
		groupEntity.setProperty(TableGroup.DESCRIPTION, description);
		groupEntity.setProperty(TableGroup.MODIFIED_DATE, currentDate);
		datastoreService.put(groupEntity);
		
		Memcache.cacheGroup(groupEntity);
		
		ServiceItemGroup group = new ServiceItemGroup(itemGroupId, name, description, currentDate, currentDate);
		return group;
		
		
	}

	@Override
	public ServiceItem editItem(ServiceItemID itemId, ServiceID serviceId,
			String name, String description, ServiceItemGroupID groupId) {
		Date currentDate = new Date();
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		Filter selectionFilter= new FilterPredicate(TableItem.ID, FilterOperator.EQUAL, itemId.getId());
		Query query = new Query(KIND_ITEM).setFilter(selectionFilter);
		Entity itemEntity = datastoreService.prepare(query).asSingleEntity();
		String oldname = (String) itemEntity.getProperty(TableItem.NAME);

		if(!(name.equals(oldname))){
		// check for duplicates
			Filter duplicateFilterName = new FilterPredicate(TableItem.NAME, FilterOperator.EQUAL, name);
			Filter duplicateFilterService = new FilterPredicate(TableItem.SERVICE_ID, FilterOperator.EQUAL, serviceId.getId());
			Filter duplicateFilter = CompositeFilterOperator.and(duplicateFilterName,duplicateFilterService);
			Query duplicatequery = new Query(KIND_ITEM).setFilter(duplicateFilter);
			Entity entity = datastoreService.prepare(duplicatequery).asSingleEntity();
			if(entity != null || Memcache.isItemInMemCache(itemId, name, serviceId)){
				return null;
			}
		}		
		////
		itemEntity.setProperty(TableItem.GROUP_ID, groupId.getId());
		itemEntity.setProperty(TableItem.NAME, name);
		itemEntity.setProperty(TableItem.DESCRIPTION, description);
		itemEntity.setProperty(TableItem.MODIFIED_DATE, currentDate);
		datastoreService.put(itemEntity);
		
		Memcache.cacheItem(itemEntity);
		
		return new ServiceItem(itemId, name, null, null, currentDate, description);

	}


	@Override
	public ServiceItemAttribute editAttribute(String AttributeId, String name,
			String type, String validation, String context,
			ServiceID serviceId, ServiceItemID itemId) {
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		// check for duplicates
		Filter selectionFilter= new FilterPredicate(TableAttribute.ID, FilterOperator.EQUAL, AttributeId);
		Query query = new Query(KIND_ATTRIBUTE).setFilter(selectionFilter);
		Entity attributeEntity = datastoreService.prepare(query).asSingleEntity();
		String oldname = (String) attributeEntity.getProperty(TableAttribute.NAME);

		if(!(name.equals(oldname))){
		// check for duplicates
		
			Filter duplicateFilterName = new FilterPredicate(TableAttribute.NAME, FilterOperator.EQUAL, name);
			Filter duplicateFilterItem = new FilterPredicate(TableAttribute.ITEM_ID, FilterOperator.EQUAL, itemId.getId());
			Filter duplicateFilterService = new FilterPredicate(TableAttribute.SERVICE_ID, FilterOperator.EQUAL, serviceId.getId());
			Filter duplicateFilter = CompositeFilterOperator.and(duplicateFilterName,duplicateFilterService,duplicateFilterItem);
			Query duplicatequery = new Query(KIND_ATTRIBUTE).setFilter(duplicateFilter);
			Entity entity = datastoreService.prepare(duplicatequery).asSingleEntity();
			if(entity != null || Memcache.isAttributeInMemCache(AttributeId, name, serviceId, itemId)){
				return null;
			}
		}	
		
		attributeEntity.setProperty(TableAttribute.NAME, name);
		attributeEntity.setProperty(TableAttribute.VALIDATION, validation);
		attributeEntity.setProperty(TableAttribute.CONTEXT, context);
		attributeEntity.setProperty(TableAttribute.TYPE, type);
		datastoreService.put(attributeEntity);
		
		Memcache.cacheAttr(attributeEntity);
		
		return new ServiceItemAttribute(AttributeId, name, validation, type, context); 
	}
	
}
