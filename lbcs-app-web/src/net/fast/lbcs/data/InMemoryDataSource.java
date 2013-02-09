package net.fast.lbcs.data;


import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.google.protos.cloud.sql.Client.SqlException;

import net.fast.lbcs.data.entities.MyDate;
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
		
	private interface TblService{
		final static String TABLE_NAME = "location_service";
		final static String C_ID = "service_id";
		final static String C_NAME = "service_name";
		final static String C_DESCRIPTION = "service_description";
		final static String C_CREATION_DATE = "service_date_created";
		final static String C_MODIFIED_DATE = "service_date_modified";

	}
	
	private interface TblGroup{
		
		final static String TABLE_NAME = "groups";
		final static String C_ID = "group_id";
		final static String C_SERVICE_ID = "group_service_id";
		final static String C_NAME = "group_name";
		final static String C_DESCRIPTION = "group_description";
		final static String C_CREATION_DATE = "group_date_created";
		final static String C_MODIFIED_DATE = "group_date_modified";
		
	}

	private interface TblItem{
		final static String TABLE_NAME = "service_item";
		final static String C_ID = "item_id";
		final static String C_SERVICE_ID = "item_service_id";
		final static String C_GROUP_ID = "item_group_id";
		final static String C_NAME = "item_name";
		final static String C_DESCRIPTION = "item_description";
		final static String C_MODIFIED_DATE = "item_date_modified";
		
	}
	
	private interface TblAttribute{
		final static String TABLE_NAME = "attribute";
		final static String C_ID = "attribute_id";
		final static String C_SERVICE_ID = "attribute_service_id";
		final static String C_ITEM_ID = "attribute_item_id";
		final static String C_NAME = "attribute_name";
		final static String C_TYPE = "attribute_type";
		final static String C_FLAG = "attribute_display_flag";
	}

	private interface TblProduct{
		final static String TABLE_NAME = "product";
		final static String C_ID = "product_id";
		final static String C_SERVICE_ID = "product_service_id";
		final static String C_ITEM_ID = "product_item_id";
		final static String C_NAME = "product_name";
		final static String C_X_POSITION = "product_x_position";
		final static String C_Y_POSITION = "product_y_position";
		final static String C_RATING = "product_rating";
		final static String C_COUNT_RATING = "product_count_ratings";
	}

	private interface TblValidityRule{
		final static String TABLE_NAME = "validity_rule";
		final static String C_ID = "val_rule_id";
		final static String C_NAME = "val_rule_name";
		final static String C_DESCRIPTION = "val_rule_description";
		final static String C_TYPE = "val_rule_type";
		final static String C_PARAMS_REQUIRED = "val_rule_param_req";
	}

	private interface TblValidation{
		final static String TABLE_NAME = "attribute_validation";
		final static String C_ATTRIBUTE_ID = "validation_attribute_id";
		final static String C_ITEM_ID = "validation_item_id";
		final static String C_SERVICE_ID = "validation_service_id";
		final static String C_RULE_ID = "validation_rule_id";
		final static String C_PARAM2 = "validation_param1";
		final static String C_PARAM1 = "validation_param2";
	}

	private interface TblValues{
		final static String TABLE_NAME = "values";
		final static String C_ATTRIBUTE_ID = "value_attribute_id";
		final static String C_ITEM_ID = "value_item_id";
		final static String C_SERVICE_ID = "value_service_id";
		final static String C_PRODUCT_ID = "value_product_id";
		final static String C_VALUE = "value";
	}
	

	
	private interface TblReviews{
		final static String TABLE_NAME = "reviews";
		final static String C_ID = "review_id";
		final static String C_ITEM_ID = "review_item_id";
		final static String C_SERVICE_ID = "review_service_id";
		final static String C_PRODUCT_ID = "review_product_id";
		final static String C_USERNAME = "review_username";
		final static String C_RATING = "review_rating";
		final static String C_DATE = "review_date";
		final static String C_COMMENT = "review_comment";
	}
	
	private interface TblReviewValues{
		final static String TABLE_NAME = "review_values";
		final static String C_ITEM_ID = "review_value_item_id";
		final static String C_SERVICE_ID = "review_value_service_id";
		final static String C_PRODUCT_ID = "review_value_product_id";
		final static String C_ATTRIBUTE_ID = "review_value_attribute_id";
		final static String C_REVIEW_ID = "review_value_review_id";
		final static String C_VALUE = "review_value_value";
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
		String sql = "select * from " + TblService.TABLE_NAME;
		ResultSet rs = DataAccessHelper.executeQuery(sql);
		List<LocationService> locationServices = new ArrayList<LocationService>();
		try{
			while(rs!=null && rs.next()){
				LocationService ls = new LocationService();
				ls.setId( new ServiceID( rs.getString(TblService.C_ID)));
				ls.setName( rs.getString(TblService.C_NAME));
				ls.setDesciption( rs.getString(TblService.C_DESCRIPTION));
				ls.setCreated(new MyDate(rs.getString(TblService.C_CREATION_DATE)));
				ls.setLastModified(new MyDate(rs.getString(TblService.C_MODIFIED_DATE)));
				
				locationServices.add(ls);
			}
		}catch (SQLException ex){
			System.out.println(ex.getMessage());
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


	private static void createTestData() {
		
		createUsers();
		createAdmins();
		
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

		String duplicateQuery = "select * from " + TblService.TABLE_NAME + " where " + TblService.C_NAME + " = '" + name +"'";
		ResultSet rs = DataAccessHelper.executeQuery(duplicateQuery);
		try{
			if(rs!=null && rs.next()){
				return null;
			}
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}

		MyDate cDate = new MyDate(new Date());
		
		String id = name + "-ID" + cDate;
		ServiceID serviceId = new ServiceID(id);
				
		String insertSql = "insert into " + TblService.TABLE_NAME + " (" + TblService.C_ID + ","
				+ TblService.C_NAME + "," + TblService.C_CREATION_DATE + "," 
				+ TblService.C_MODIFIED_DATE + "," + TblService.C_DESCRIPTION + ") values ('"
				+ id + "','" + name + "','" + cDate.toString() + "','" + cDate.toString() + "','" 
				+ description + "')";
		
		DataAccessHelper.UpdateQuery(insertSql);
		
		LocationService service = new LocationService(
				serviceId, 
				name, description,
				cDate, cDate, 
				new ArrayList<ServiceItem>(), new ArrayList<ServiceItemGroup>());
		
		return service;

	}
	
	@Override
	public ServiceItemGroup createGroup(ServiceID serviceID, String name, String description) {

		String duplicateQuery = "select * from " + TblGroup.TABLE_NAME + " where " + TblGroup.C_NAME 
				+ " = '" + name +"' and " + TblGroup.C_SERVICE_ID + "='"+serviceID.getId()+"'";
		ResultSet rs = DataAccessHelper.executeQuery(duplicateQuery);
		try{
			if( rs!=null && rs.next()){
				return null;
			}
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}

		MyDate cDate = new MyDate(new Date());
		String id = name + "-ID"+ cDate;
		ServiceItemGroupID groupId=new ServiceItemGroupID(id);

		String insertSql = "insert into " + TblGroup.TABLE_NAME + " (" + TblGroup.C_ID + ","
				+ TblGroup.C_NAME + "," + TblGroup.C_CREATION_DATE + "," + TblGroup.C_MODIFIED_DATE + "," + TblGroup.C_DESCRIPTION + "," 
				+ TblGroup.C_SERVICE_ID + ") values ('" + id + "','" + name + "','" + cDate.toString() + "','" + cDate.toString() + 
				"','" + description + "','" + serviceID.getId() + "')";

		DataAccessHelper.UpdateQuery(insertSql);
		
		ServiceItemGroup group = new ServiceItemGroup(groupId, name, description, cDate, cDate);
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
		
		String duplicateQuery = "select * from " + TblItem.TABLE_NAME + " where " + TblItem.C_NAME 
				+ " = '" + name +"' and " + TblItem.C_SERVICE_ID + "='"+serviceId.getId()+"'";
		ResultSet rs = DataAccessHelper.executeQuery(duplicateQuery);
		try{
			if(rs!=null && rs.next()){
				return null;
			}
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}
		
		MyDate cDate = new MyDate(new Date());
		String id = name + "-ID" + cDate;
		ServiceItemID itemId=new ServiceItemID(id);

		String insertSql = "insert into " + TblItem.TABLE_NAME + " (" + TblItem.C_ID + "," 
				+ TblItem.C_NAME + "," + TblItem.C_MODIFIED_DATE + "," + TblItem.C_DESCRIPTION
				+ "," + TblItem.C_GROUP_ID + "," + TblItem.C_SERVICE_ID + ") values ('"
				+ id + "','" + name + "','" + cDate.toString() + "','" + description + "','" 
				+ serviceItemGroupId.getId() + "','" + serviceId.getId() + "')";

		DataAccessHelper.UpdateQuery(insertSql);
		
		return new ServiceItem(itemId, name, null, null, cDate, description);
			
	}

	@Override
	public ServiceItemAttribute createItemAttribute(String name, String type, 
			String flag, ServiceID serviceId, ServiceItemID itemId) {
		
		MyDate cDate = new MyDate(new Date());
		String id = name + "-ID" + cDate;
		
		String duplicateQuery = "select * from " + TblAttribute.TABLE_NAME + " where " + TblAttribute.C_NAME 
				+ " = '" + name +"' and " + TblAttribute.C_SERVICE_ID + "='"+serviceId.getId() 
				+"' and " + TblAttribute.C_ITEM_ID + "='"+itemId.getId()+"'";
		ResultSet rs = DataAccessHelper.executeQuery(duplicateQuery);
		try{
			if(rs!=null && rs.next()){
				return null;
			}
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}
		
		String insertSql = "insert into " + TblAttribute.TABLE_NAME + " (" + TblAttribute.C_ID + ","
				+ TblAttribute.C_NAME + "," + TblAttribute.C_TYPE + "," + TblAttribute.C_FLAG 
				+ "," + TblAttribute.C_ITEM_ID + "," + TblAttribute.C_SERVICE_ID + ") values ('"
				+ id + "','" + name + "','" + type + "','" + flag + "','" 
				+ itemId.getId() + "','" + serviceId.getId() + "')";

		DataAccessHelper.UpdateQuery(insertSql);
		
		return new ServiceItemAttribute(id, name, "", type, "", flag); 
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
		LocationService ls = new LocationService();
		
		try{
			String serviceQuery = "select * from " + TblService.TABLE_NAME + " where " 
					+ TblService.C_ID + " = '" + serviceId.getId() + "'";
			ResultSet rs = DataAccessHelper.executeQuery(serviceQuery);
			if(rs!=null && rs.next()){
				ls.setId( new ServiceID( rs.getString(TblService.C_ID)));
				ls.setName( rs.getString(TblService.C_NAME));
				ls.setDesciption( rs.getString(TblService.C_DESCRIPTION));
				ls.setCreated(new MyDate(rs.getString(TblService.C_CREATION_DATE)));
				ls.setLastModified(new MyDate(rs.getString(TblService.C_MODIFIED_DATE)));
				ls.setGroups(getAllServiceGroupsByServiceID(serviceId));
				ls.setItems(getAllServiceItemsByServiceID(serviceId, ls.getGroups()));
				
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		
		return ls;
		
	}

	
	private static List<ServiceItemGroup> getAllServiceGroupsByServiceID(ServiceID serviceId){
		List<ServiceItemGroup> groupList = new ArrayList<ServiceItemGroup>();
		
		String query = "select * from " + TblGroup.TABLE_NAME + " where " + TblGroup.C_SERVICE_ID 
				+ " = '" + serviceId.getId() + "'";
		try {
			ResultSet rs = DataAccessHelper.executeQuery(query);
			while(rs!=null && rs.next()){
				ServiceItemGroup group = new ServiceItemGroup();
				group.setId(new ServiceItemGroupID(rs.getString(TblGroup.C_ID)));
				group.setName(rs.getString(TblGroup.C_NAME));
				group.setDescription(rs.getString(TblGroup.C_DESCRIPTION));
				group.setDateCreated(new MyDate(rs.getString(TblGroup.C_CREATION_DATE)));
				group.setDateModified(new MyDate(rs.getString(TblGroup.C_MODIFIED_DATE)));
				groupList.add(group);
			}
		}catch (SQLException ex){
			System.out.println(ex.getMessage());
		}
		
		return groupList;
	}
	
	

	private static List<ServiceItem> getAllServiceItemsByServiceID(ServiceID serviceId, List<ServiceItemGroup> groups){
		List<ServiceItem> itemList = new ArrayList<ServiceItem>();
		try{
			String itemQuery = "select * from " + TblItem.TABLE_NAME + " where " 
					+ TblItem.C_SERVICE_ID + "= '" + serviceId.getId() + "'";
			ResultSet rs = DataAccessHelper.executeQuery(itemQuery);
			while(rs!=null && rs.next()){
				ServiceItem si = new ServiceItem();
				si.setId(new ServiceItemID(rs.getString(TblItem.C_ID)));
				si.setDescription(rs.getString(TblItem.C_DESCRIPTION));
				si.setDateModified(new MyDate(rs.getString(TblItem.C_MODIFIED_DATE)));
				si.setName(rs.getString(TblItem.C_NAME));
				String gid = rs.getString(TblItem.C_GROUP_ID);
				for(ServiceItemGroup group : groups){
					if(gid.equals(group.getId().getId())){
						si.setGroup(group);
						break;
					}
				}
				si.setAttrs(getItemAttributes(serviceId,si.getId()));
				
				itemList.add(si);
				
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}

		return itemList;
	}
	
	private static ServiceItemAttributes getItemAttributes(ServiceID serviceId, ServiceItemID itemId){
		ServiceItemAttributes attributes = new ServiceItemAttributes();
		List<ServiceItemAttribute> attrs = new ArrayList<ServiceItemAttribute>();
		try{
			String query = "select * from " + TblAttribute.TABLE_NAME + " where " + TblAttribute.C_SERVICE_ID
					+ "='" + serviceId.getId() +"' and " + TblAttribute.C_ITEM_ID + "='" + itemId.getId() + "'";
			ResultSet rs = DataAccessHelper.executeQuery(query);
			while(rs!=null && rs.next()){
				ServiceItemAttribute attr = new ServiceItemAttribute();
				attr.setId(rs.getString(TblAttribute.C_ID));
				attr.setName(rs.getString(TblAttribute.C_NAME));
				attr.setType(rs.getString(TblAttribute.C_TYPE));
				attr.setFlag(rs.getString(TblAttribute.C_FLAG));

				attrs.add(attr);
				
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		attributes.setAttrs(attrs);
		return attributes;
	}
	
	
	
	@Override
	public boolean deleteLocationService(ServiceID serviceId) {
		String id = serviceId.getId();

		String query = "delete from " + TblService.TABLE_NAME + " where " +TblService.C_ID +"='"+id +"'";
		DataAccessHelper.UpdateQuery(query);
		
		query = "delete from " + TblItem.TABLE_NAME + " where " +TblItem.C_SERVICE_ID +"='"+id +"'";
		DataAccessHelper.UpdateQuery(query);
		
		query = "delete from " + TblAttribute.TABLE_NAME + " where " +TblAttribute.C_SERVICE_ID +"='"+id +"'";
		DataAccessHelper.UpdateQuery(query);
		
		query = "delete from " + TblGroup.TABLE_NAME + " where " +TblGroup.C_SERVICE_ID +"='"+id +"'";
		DataAccessHelper.UpdateQuery(query);
		
		query = "delete from " + TblProduct.TABLE_NAME + " where " +TblProduct.C_SERVICE_ID +"='"+id +"'";
		DataAccessHelper.UpdateQuery(query);
		
		query = "delete from " + TblReviews.TABLE_NAME + " where " +TblReviews.C_SERVICE_ID +"='"+id +"'";
		DataAccessHelper.UpdateQuery(query);
		
		query = "delete from " + TblReviewValues.TABLE_NAME + " where " +TblReviewValues.C_SERVICE_ID +"='"+id +"'";
		DataAccessHelper.UpdateQuery(query);
		
		query = "delete from " + TblValidation.TABLE_NAME + " where " +TblValidation.C_SERVICE_ID +"='"+id +"'";
		DataAccessHelper.UpdateQuery(query);
				
		query = "delete from " + TblValues.TABLE_NAME + " where " +TblValues.C_SERVICE_ID +"='"+id +"'";
		DataAccessHelper.UpdateQuery(query);
		
		return true;
	}

	@Override
	public boolean deleteServiceGroup(ServiceID serviceId, ServiceItemGroupID groupId) {
		
		String id = groupId.getId();
		List<ServiceItemID> itemList = new ArrayList<ServiceItemID>();
		try{
			String itemQuery = "select " + TblItem.C_ID + " from " + TblItem.TABLE_NAME + " where "
					+ TblItem.C_GROUP_ID + "='" + id + "' and " + TblItem.C_SERVICE_ID + "='"
					+ serviceId.getId() + "'";
			ResultSet rs = DataAccessHelper.executeQuery(itemQuery);
			while(rs!=null && rs.next()){
				itemList.add(new ServiceItemID(rs.getString(TblItem.C_ID)));
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}

		for(ServiceItemID itemId : itemList){
			deleteServiceItem(serviceId, itemId);
		}
		String query = "delete from " + TblGroup.TABLE_NAME + " where " +TblGroup.C_ID +"='"+id +"' and "
				+ TblGroup.C_SERVICE_ID + "='" + serviceId.getId() + "'";
		
		System.out.println(query);
		DataAccessHelper.UpdateQuery(query);
		System.out.println("---"+query);
		
		
		return true;
	}

	@Override
	public boolean deleteServiceItem(ServiceID serviceId, ServiceItemID itemId) {

		String id = itemId.getId();

		String query = "delete from " + TblItem.TABLE_NAME + " where " +TblItem.C_ID +"='"+id +"' and "
				+ TblItem.C_SERVICE_ID + "='" + serviceId.getId() + "'";
		DataAccessHelper.UpdateQuery(query);
		
		query = "delete from " + TblAttribute.TABLE_NAME + " where " + TblAttribute.C_ITEM_ID + "='" + id
				+ "' and " + TblAttribute.C_SERVICE_ID + "='" + serviceId.getId() + "'";
		DataAccessHelper.UpdateQuery(query);
		
		query = "delete from " + TblProduct.TABLE_NAME + " where " + TblProduct.C_ITEM_ID + "='" + id
				+ "' and " + TblProduct.C_SERVICE_ID + "='" + serviceId.getId() + "'";
		DataAccessHelper.UpdateQuery(query);
		
		query = "delete from " + TblReviews.TABLE_NAME + " where " + TblReviews.C_ITEM_ID + "='" + id
				+ "' and " + TblReviews.C_SERVICE_ID + "='" + serviceId.getId() + "'";
		DataAccessHelper.UpdateQuery(query);
		
		query = "delete from " + TblReviewValues.TABLE_NAME + " where " + TblReviewValues.C_ITEM_ID + "='" + id
				+ "' and " + TblReviewValues.C_SERVICE_ID + "='" + serviceId.getId() + "'";
		DataAccessHelper.UpdateQuery(query);
		
		query = "delete from " + TblValidation.TABLE_NAME + " where " + TblValidation.C_ITEM_ID + "='" + id
				+ "' and " + TblValidation.C_SERVICE_ID + "='" + serviceId.getId() + "'";
		DataAccessHelper.UpdateQuery(query);
		
		query = "delete from " + TblValues.TABLE_NAME + " where " + TblValues.C_ITEM_ID + "='" + id
				+ "' and " + TblValues.C_SERVICE_ID + "='" + serviceId.getId() + "'";
		DataAccessHelper.UpdateQuery(query);
		
		
		return true;
	}

	@Override
	public boolean deleteItemAttribute(ServiceID serviceId, ServiceItemID itemId, String attributeId) {
		String id = attributeId;

		String query = "delete from " + TblAttribute.TABLE_NAME + " where " + TblAttribute.C_ID + "='" + id 
				+"' and " + TblAttribute.C_ITEM_ID + "='" + itemId.getId() + "' and " + TblAttribute.C_SERVICE_ID
				+ "='" + serviceId.getId() + "'";
		DataAccessHelper.UpdateQuery(query);
		
		query = "delete from " + TblValidation.TABLE_NAME + " where " +TblValidation.C_ATTRIBUTE_ID + "='"
				+ id + "' and " + TblValidation.C_ITEM_ID + "='" + itemId.getId() + "' and " +
				TblValidation.C_SERVICE_ID + "='" + serviceId.getId() + "'";
		DataAccessHelper.UpdateQuery(query);
		
		query = "delete from " + TblReviewValues.TABLE_NAME + " where " +TblReviewValues.C_ATTRIBUTE_ID + "='"
				+ id + "' and " + TblReviewValues.C_ITEM_ID + "='" + itemId.getId() + "' and " +
				TblReviewValues.C_SERVICE_ID + "='" + serviceId.getId() + "'";
		DataAccessHelper.UpdateQuery(query);
		
		query = "delete from " + TblValues.TABLE_NAME + " where " +TblValues.C_ATTRIBUTE_ID + "='"
				+ id + "' and " + TblValues.C_ITEM_ID + "='" + itemId.getId() + "' and " +
				TblValues.C_SERVICE_ID + "='" + serviceId.getId() + "'";
		DataAccessHelper.UpdateQuery(query);
		
		return true;
	}

	@Override
	public LocationService editService(ServiceID serviceId, String name,
			String description) {
		
		String duplicateQuery = "select * from " + TblService.TABLE_NAME + " where " + TblService.C_NAME + " = '" + name +"'";
		ResultSet rs = DataAccessHelper.executeQuery(duplicateQuery);
		try{
			if(rs!=null){				
				while(rs!=null && rs.next()){
					if(!(serviceId.getId().equals( rs.getString(TblService.C_ID))))
						return null;
				}
			}
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}

		MyDate cDate = new MyDate(new Date());
		
		String query = "update " + TblService.TABLE_NAME + " set " + TblService.C_NAME + " = '" + name
				+ "', " + TblService.C_DESCRIPTION + "='" + description + "',  "+ TblService.C_MODIFIED_DATE
				+ "='" + cDate.toString() + "' where " + TblService.C_ID +"='" + serviceId.getId() + "'";
		
		DataAccessHelper.UpdateQuery(query);
		
		LocationService service = new LocationService(
				serviceId,
				name, description,
				cDate, cDate, 
				new ArrayList<ServiceItem>(), new ArrayList<ServiceItemGroup>());
				
		return service;
	}

	@Override
	public ServiceItemGroup editGroup(ServiceItemGroupID groupId,
			ServiceID serviceID, String name, String description) {

		String duplicateQuery = "select * from " + TblGroup.TABLE_NAME + " where " + TblGroup.C_NAME 
				+ " = '" + name +"' and " + TblGroup.C_SERVICE_ID + "='"+serviceID.getId()+"'";
		ResultSet rs = DataAccessHelper.executeQuery(duplicateQuery);
		try{
			if(rs!=null){				
				while(rs.next()){
					if(!(groupId.getId().equals( rs.getString(TblGroup.C_ID))))
						return null;
				}
			}
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}

		MyDate cDate = new MyDate(new Date());
		
		String query = "update " + TblGroup.TABLE_NAME + " set " + TblGroup.C_NAME + " = '" + name
				+ "', " + TblGroup.C_DESCRIPTION + "='" + description + "',  "+ TblGroup.C_MODIFIED_DATE
				+ "='" + cDate.toString() + "' where " + TblGroup.C_ID +"='" + groupId.getId() + "' and "
				+ TblGroup.C_SERVICE_ID + "='" + serviceID.getId() + "'";
		
		DataAccessHelper.UpdateQuery(query);

		ServiceItemGroup group = new ServiceItemGroup(groupId, name, description, cDate, cDate);
		return group;
		
		
	}

	@Override
	public ServiceItem editItem(ServiceItemID itemId, ServiceID serviceId,
			String name, String description, ServiceItemGroupID groupId) {
		String duplicateQuery = "select * from " + TblItem.TABLE_NAME + " where " + TblItem.C_NAME 
				+ " = '" + name +"' and " + TblItem.C_SERVICE_ID + "='"+serviceId.getId()+"'";
		ResultSet rs = DataAccessHelper.executeQuery(duplicateQuery);
		try{
			if(rs!=null){				
				while(rs.next()){
					if(!(itemId.getId().equals( rs.getString(TblItem.C_ID))))
						return null;
				}
			}
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}
		

		MyDate cDate = new MyDate(new Date());

		String query = "update " + TblItem.TABLE_NAME + " set " + TblItem.C_NAME + " = '" + name
				+ "', " + TblItem.C_DESCRIPTION + "='" + description + "',  " + TblItem.C_GROUP_ID 
				+ "='" + groupId.getId() + "',  "+ TblItem.C_MODIFIED_DATE + "='" + cDate.toString() 
				+ "' where " + TblItem.C_ID +"='" + itemId.getId() + "' and "
				+ TblItem.C_SERVICE_ID + "='" + serviceId.getId() + "'";
		
		DataAccessHelper.UpdateQuery(query);
		
		
		return new ServiceItem(itemId, name, null, null, cDate, description);

	}


	@Override
	public ServiceItemAttribute editAttribute(String attributeId, String name,
			String type, String flag, ServiceID serviceId, ServiceItemID itemId) {

		String duplicateQuery = "select * from " + TblAttribute.TABLE_NAME + " where " + TblAttribute.C_NAME 
				+ " = '" + name +"' and " + TblAttribute.C_SERVICE_ID + "='"+serviceId.getId() 
				+"' and " + TblAttribute.C_ITEM_ID + "='"+itemId.getId()+"'";
		ResultSet rs = DataAccessHelper.executeQuery(duplicateQuery);
		try{
			if(rs!=null){				
				while(rs.next()){
					if(!(attributeId.equals( rs.getString(TblAttribute.C_ID))))
						return null;
				}
			}
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}
		
		String query = "update " + TblAttribute.TABLE_NAME + " set " + TblAttribute.C_NAME + " = '" + name
				+ "', " + TblAttribute.C_TYPE + "='" + type + "',  " + TblAttribute.C_FLAG + "='" + flag
				+ "' where " + TblAttribute.C_ITEM_ID +"='" + itemId.getId() + "' and "
				+ TblAttribute.C_SERVICE_ID + "='" + serviceId.getId() + "' and " + TblAttribute.C_ID 
				+ "='" + attributeId + "'";

		DataAccessHelper.UpdateQuery(query);
		
		return new ServiceItemAttribute(attributeId, name, "", type, "", flag); 
	}
	
}
