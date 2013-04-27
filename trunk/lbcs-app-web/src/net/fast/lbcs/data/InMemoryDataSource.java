package net.fast.lbcs.data;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
import net.fast.lbcs.data.entities.user.Location;
import net.fast.lbcs.data.entities.user.NotificationToAdmin;
import net.fast.lbcs.data.entities.user.Product;
import net.fast.lbcs.data.entities.user.ProductAttribute;
import net.fast.lbcs.data.entities.user.ProductComment;
import net.fast.lbcs.data.entities.user.ProductID;
import net.fast.lbcs.data.entities.user.ProductReview;
import net.fast.lbcs.data.entities.user.ProductValueReview;
import net.fast.lbcs.data.entities.user.User;
import net.fast.lbcs.data.entities.user.UserSettings;

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
		final static String C_VALIDATION_ID = "attribute_validation_id";
		final static String C_CONTEXT_ID = "attribute_context_id";
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
	}

	private interface TblValidityRule{
		final static String TABLE_NAME = "validity_rule";
		final static String C_ID = "val_rule_id";
		final static String C_NAME = "val_rule_name";
		final static String C_TYPE = "val_rule_type";
		final static String C_PARAM1 = "param1";
		final static String C_PARAM2 = "param2";
	}

	private interface TblValues{
		final static String TABLE_NAME = "attr_values";
		final static String C_ATTRIBUTE_ID = "value_attribute_id";
		final static String C_ITEM_ID = "value_item_id";
		final static String C_SERVICE_ID = "value_service_id";
		final static String C_PRODUCT_ID = "value_product_id";
		final static String C_VALUE = "value_value";
	}
	

	
	private interface TblReviews{
		final static String TABLE_NAME = "reviews";
		final static String C_ITEM_ID = "review_item_id";
		final static String C_SERVICE_ID = "review_service_id";
		final static String C_PRODUCT_ID = "review_product_id";
		final static String C_USERNAME = "review_username";
		final static String C_RATING = "review_rating";
		final static String C_DATE = "review_date";
		final static String C_STATUS = "review_status";
	}
	
	private interface TblReviewValues{
		final static String TABLE_NAME = "review_values";
		final static String C_ITEM_ID = "review_value_item_id";
		final static String C_SERVICE_ID = "review_value_service_id";
		final static String C_PRODUCT_ID = "review_value_product_id";
		final static String C_ATTRIBUTE_ID = "review_value_attribute_id";
		final static String C_VALUE = "review_value_value";
		final static String C_USERNAME = "review_value_username";		
		final static String C_STATUS = "review_value_status";
		final static String C_DATE = "review_value_date";
	}
	
	
	private interface TblNotification{
		final static String TABLE_NAME = "notification";
		final static String C_ID = "notification_id";
		final static String C_SERVICE_ID = "notification_service_id";
		final static String C_TEXT = "notification_text";
		final static String C_DATE = "notification_date";
		final static String C_HANDLED = "notification_handled";
		final static String C_USERNAME = "notification_username";
	}

	private interface TblComments{
		final static String TABLE_NAME = "comments";
		final static String C_ID = "comments_id";
		final static String C_SERVICE_ID = "comments_service_id";
		final static String C_ITEM_ID = "comments_item_id";
		final static String C_PRODUCT_ID = "comments_product_id";
		final static String C_TEXT = "comments_text";
		final static String C_DATE = "comments_date";
		final static String C_USERNAME = "comments_username";
	}

	
	private static List<Administrator> admins = new ArrayList<Administrator>();
	private static List<User> users = new ArrayList<User>();
	private static List<Validation> validations = new ArrayList<Validation>();
	private static List<ValidationTypeDetail> validationTypeDetailList = new ArrayList<ValidationTypeDetail>();
	
	
	static {
		createTestData();
	}
	
	
	public static void main(String[] args) {
		System.out.println("========== Admins ==========");
		System.out.println(admins);
		System.out.println("========== Location Services ==========");
		System.out.println("========== Users ==========");
		System.out.println(users);
		System.out.println("========== Products ==========");
		
	}

	
	public static List<User> getUsers() {
		return users;
	}
	
	public static List<Administrator> getAdmins() {
		return admins;
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
	
	
	private static Administrator createAdmin(String id, String password) {
		return new Administrator(id, password);
	}

	private static void createAdmins() {
		admins.add(createAdmin("admin", "pass"));
		admins.add(createAdmin("aizaz", "pass"));
	}
	

	private static void createUsers() {
		
		for (int i = 0; i < 10; i++) {
			users.add(new User("user" + i, "pass" + i, new UserSettings(), null));
		}
	}

	private static void createTestData() {
		
		createUsers();
		createAdmins();
		loadValidations();
		fillValidationTypeDetailList();
	}



	private static void fillValidationTypeDetailList() {
		String validationHTML;
		ValidationTypeDetail vtd = new ValidationTypeDetail();
		vtd.setType("Numeric Equal");
		vtd.setParamsRequired(1);
		validationHTML = "<lable><span>Parameter1:</span><input type='text' value='' class='input_text' name='param1' id='param1'/></lable>";
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);

		vtd = new ValidationTypeDetail();
		vtd.setType("Numeric Greater");
		vtd.setParamsRequired(1);
		validationHTML = "<lable><span>Parameter1:</span><input type='text' value='' class='input_text' name='param1' id='param1'/></lable>";
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);

		vtd = new ValidationTypeDetail();
		vtd.setType("Numeric Smaller");
		vtd.setParamsRequired(1);
		validationHTML = "<lable><span>Parameter1:</span><input type='text' value='' class='input_text' name='param1' id='param1'/></lable>";
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);

		vtd = new ValidationTypeDetail();
		vtd.setType("Numeric Between");
		vtd.setParamsRequired(2);
		validationHTML = "<lable><span>Parameter1:</span><input type='text' value='' class='input_text' name='param1' id='param1'/></lable>";
		validationHTML += "<lable><span>Parameter2:</span><input type='text' value='' class='input_text' name='param2' id='param2'/></lable>";
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);

		vtd = new ValidationTypeDetail();
		vtd.setType("Numeric GreaterEqual");
		vtd.setParamsRequired(1);
		validationHTML = "<lable><span>Parameter1:</span><input type='text' value='' class='input_text' name='param1' id='param1'/></lable>";
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);

		vtd = new ValidationTypeDetail();
		vtd.setType("Numeric LesserEqual");
		vtd.setParamsRequired(1);
		validationHTML = "<lable><span>Parameter1:</span><input type='text' value='' class='input_text' name='param1' id='param1'/></lable>";
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);

		vtd = new ValidationTypeDetail();
		vtd.setType("Numeric NotEqual");
		vtd.setParamsRequired(1);
		validationHTML = "<lable><span>Parameter1:</span><input type='text' value='' class='input_text' name='param1' id='param1'/></lable>";
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);

		vtd = new ValidationTypeDetail();
		vtd.setType("Numeric BetweenInclusive");
		vtd.setParamsRequired(2);
		validationHTML = "<lable><span>Parameter1:</span><input type='text' value='' class='input_text' name='param1' id='param1'/></lable>";
		validationHTML += "<lable><span>Parameter2:</span><input type='text' value='' class='input_text' name='param2' id='param2'/></lable>";
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);

		vtd = new ValidationTypeDetail();
		vtd.setType("Numeric Positive");
		vtd.setParamsRequired(0);
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);

		vtd = new ValidationTypeDetail();
		vtd.setType("Numeric Negative");
		vtd.setParamsRequired(0);
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);

		vtd = new ValidationTypeDetail();
		vtd.setType("Numeric NonNegative");
		vtd.setParamsRequired(0);
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);

		vtd = new ValidationTypeDetail();
		vtd.setType("String StartsWith");
		vtd.setParamsRequired(1);
		validationHTML = "<lable><span>Parameter1:</span><input type='text' value='' class='input_text' name='param1' id='param1'/></lable>";
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);

		vtd = new ValidationTypeDetail();
		vtd.setType("String EndsWith");
		vtd.setParamsRequired(1);
		validationHTML = "<lable><span>Parameter1:</span><input type='text' value='' class='input_text' name='param1' id='param1'/></lable>";
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);

		vtd = new ValidationTypeDetail();
		vtd.setType("String Equals");
		vtd.setParamsRequired(1);
		validationHTML = "<lable><span>Parameter1:</span><input type='text' value='' class='input_text' name='param1' id='param1'/></lable>";
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);

		vtd = new ValidationTypeDetail();
		vtd.setType("String Contains");
		vtd.setParamsRequired(1);
		validationHTML = "<lable><span>Parameter1:</span><input type='text' value='' class='input_text' name='param1' id='param1'/></lable>";
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);

		vtd = new ValidationTypeDetail();
		vtd.setType("String LengthEquals");
		vtd.setParamsRequired(1);
		validationHTML = "<lable><span>Parameter1:</span><input type='text' value='' class='input_text' name='param1' id='param1'/></lable>";
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);

		vtd = new ValidationTypeDetail();
		vtd.setType("String LengthGreater");
		vtd.setParamsRequired(1);
		validationHTML = "<lable><span>Parameter1:</span><input type='text' value='' class='input_text' name='param1' id='param1'/></lable>";
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);

		vtd = new ValidationTypeDetail();
		vtd.setType("String LengthLess");
		vtd.setParamsRequired(1);
		validationHTML = "<lable><span>Parameter1:</span><input type='text' value='' class='input_text' name='param1' id='param1'/></lable>";
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);

		vtd = new ValidationTypeDetail();
		vtd.setType("String LengthBetween");
		vtd.setParamsRequired(2);
		validationHTML = "<lable><span>Parameter1:</span><input type='text' value='' class='input_text' name='param1' id='param1'/></lable>";
		validationHTML += "<lable><span>Parameter2:</span><input type='text' value='' class='input_text' name='param2' id='param2'/></lable>";
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);

		vtd = new ValidationTypeDetail();
		vtd.setType("String RegEx");
		vtd.setParamsRequired(1);
		validationHTML = "<lable><span>Parameter1:</span><input type='text' value='' class='input_text' name='param1' id='param1'/></lable>";
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);
		
		vtd = new ValidationTypeDetail();
		vtd.setType("Logical And");
		vtd.setParamsRequired(2);
		validationHTML = "<lable><span>Parameter1:</span><select name='param1'>";
		for(Validation v : validations){
			validationHTML += "<option value='" + v.getId() + "'>" + v.getName() + "</option>";
		}
		validationHTML+="</select></lable>";
		validationHTML = "<lable><span>Parameter2:</span><select name='param2'>";
		for(Validation v : validations){
			validationHTML += "<option value='" + v.getId() + "'>" + v.getName() + "</option>";
		}
		validationHTML+="</select></lable>";
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);
		
		vtd = new ValidationTypeDetail();
		vtd.setType("Logical Or");
		vtd.setParamsRequired(2);
		validationHTML = "<lable><span>Parameter1:</span><select name='param1'>";
		for(Validation v : validations){
			validationHTML += "<option value='" + v.getId() + "'>" + v.getName() + "</option>";
		}
		validationHTML += "</select></lable>";
		validationHTML += "<lable><span>Parameter2:</span><select name='param2'>";
		for(Validation v : validations){
			validationHTML += "<option value='" + v.getId() + "'>" + v.getName() + "</option>";
		}
		validationHTML+="</select></lable>";
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);
		
		vtd = new ValidationTypeDetail();
		vtd.setType("Logical Not");
		vtd.setParamsRequired(1);
		validationHTML = "<lable><span>Parameter1:</span><select name='param1'>";
		for(Validation v : validations){
			validationHTML += "<option value='" + v.getId() + "'>" + v.getName() + "</option>";
		}
		validationHTML+="</select></lable>";
		vtd.setParamInputHTML(validationHTML);
		validationTypeDetailList.add(vtd);
		



	}

	private static void loadValidations() {
		
		String query = "select * from " + TblValidityRule.TABLE_NAME;
		
		try{
			ResultSet rs = DataAccessHelper.executeQuery(query);
			while(rs!=null && rs.next()){
				List <String> params = new ArrayList<String>();
				Validation v = new Validation();
				v.setId(rs.getString(TblValidityRule.C_ID));
				v.setName(rs.getString(TblValidityRule.C_NAME));
				v.setType(rs.getString(TblValidityRule.C_TYPE));
				params.add(rs.getString(TblValidityRule.C_PARAM1));
				params.add(rs.getString(TblValidityRule.C_PARAM2));
				v.setParams(params);
				String description = v.getName() + " " + v.getType();
				for(int i=0;i<2;i++){
					String param = params.get(i);
					if(param==null || "".equals(param))
						break;
					else
						description = description + ", " + param;
				}
				
				v.setDescription(description);
				validations.add(v);
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
	}

	
	
//******************************************************************************\\
//				************************************************				\\
//							LOCATION SERVICES START								\\
//				************************************************				\\
//******************************************************************************\\
	
	
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
		
		query = "delete from " + TblValues.TABLE_NAME + " where " +TblValues.C_SERVICE_ID +"='"+id +"'";
		DataAccessHelper.UpdateQuery(query);

		query = "delete from " + TblComments.TABLE_NAME + " where " +TblComments.C_SERVICE_ID +"='"+id +"'";
		DataAccessHelper.UpdateQuery(query);

		query = "delete from " + TblNotification.TABLE_NAME + " where " +TblNotification.C_SERVICE_ID +"='"+id +"'";
		DataAccessHelper.UpdateQuery(query);
		
		return true;
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


	@Override
	public List<LocationService> getAllServices(int startIndex, int endIndex) {
		return getLocationServices();
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
	
	

//******************************************************************************\\
//				************************************************				\\
//							LOCATION SERVICES END								\\
//				************************************************				\\
//******************************************************************************\\
	

	
//******************************************************************************\\
//				************************************************				\\
//								GROUPS START									\\
//				************************************************				\\
//******************************************************************************\\
	
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
	
	
//******************************************************************************\\
//				************************************************				\\
//								GROUPS END										\\
//				************************************************				\\
//******************************************************************************\\
	

	
	
//******************************************************************************\\
//				************************************************				\\
//							SERVICE ITEMS START									\\
//				************************************************				\\
//******************************************************************************\\
	
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
				
		query = "delete from " + TblValues.TABLE_NAME + " where " + TblValues.C_ITEM_ID + "='" + id
				+ "' and " + TblValues.C_SERVICE_ID + "='" + serviceId.getId() + "'";
		DataAccessHelper.UpdateQuery(query);
		
		query = "delete from " + TblComments.TABLE_NAME + " where " + TblComments.C_ITEM_ID + "='" + id
				+ "' and " + TblComments.C_SERVICE_ID + "='" + serviceId.getId() + "'";
		DataAccessHelper.UpdateQuery(query);
		
		
		return true;
	}

	public static ServiceItem getItemById(String serviceId, String itemId){
		try{
			String itemQuery = "select * from " + TblItem.TABLE_NAME + " where " + TblItem.C_SERVICE_ID 
					+ "= '" + serviceId + "' and " + TblItem.C_ID + "='" + itemId + "'";
			ResultSet rs = DataAccessHelper.executeQuery(itemQuery);
			while(rs!=null && rs.next()){
				ServiceItem si = new ServiceItem();
				si.setId(new ServiceItemID(rs.getString(TblItem.C_ID)));
				si.setDescription(rs.getString(TblItem.C_DESCRIPTION));
				si.setDateModified(new MyDate(rs.getString(TblItem.C_MODIFIED_DATE)));
				si.setName(rs.getString(TblItem.C_NAME));
				String gid = rs.getString(TblItem.C_GROUP_ID);
				si.setAttrs(getItemAttributes(new ServiceID(serviceId),new ServiceItemID(itemId)));
				
				String query = "select * from " + TblGroup.TABLE_NAME + " where " + TblGroup.C_SERVICE_ID 
						+ " = '" + serviceId + "' and " + TblGroup.C_ID + "='" + gid + "'";
				ResultSet rs1 = DataAccessHelper.executeQuery(query);

				ServiceItemGroup group = new ServiceItemGroup();
				if(rs1!=null && rs1.next()){
					group.setId(new ServiceItemGroupID(rs1.getString(TblGroup.C_ID)));
					group.setName(rs1.getString(TblGroup.C_NAME));
					group.setDescription(rs1.getString(TblGroup.C_DESCRIPTION));
					group.setDateCreated(new MyDate(rs1.getString(TblGroup.C_CREATION_DATE)));
					group.setDateModified(new MyDate(rs1.getString(TblGroup.C_MODIFIED_DATE)));
				}
				
				si.setGroup(group);
				
				return si;
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		return null;
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
	
	
//******************************************************************************\\
//				************************************************				\\
//							SERVICE ITEMS END									\\
//				************************************************				\\
//******************************************************************************\\
	

	
//******************************************************************************\\
//				************************************************				\\
//							ITEM ATTRIBUTES START								\\
//				************************************************				\\
//******************************************************************************\\
	
	
	@Override
	public ServiceItemAttribute createItemAttribute(String name, String type, String ruleId, 
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
				+ "," + TblAttribute.C_ITEM_ID + "," + TblAttribute.C_SERVICE_ID + ","
				+ TblAttribute.C_VALIDATION_ID + ") values ('" + id + "','" + name + "','" 
				+type + "','" + flag + "','" + itemId.getId() + "','" + serviceId.getId() + "','" + ruleId +"')";

		DataAccessHelper.UpdateQuery(insertSql);
		
		return new ServiceItemAttribute(id, name, ruleId, type, " ", flag); 
	}
	


	@Override
	public ServiceItemAttribute editAttribute(String attributeId, String name,
			String type, String flag, ServiceID serviceId, ServiceItemID itemId, String ruleId) {

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
				+ "', " + TblAttribute.C_TYPE + "='" + type + "',  " + TblAttribute.C_FLAG + "='" + flag + "', "
				+ TblAttribute.C_VALIDATION_ID+"='"+ruleId + "' where " + TblAttribute.C_ITEM_ID +"='" + itemId.getId() + "' and "
				+ TblAttribute.C_SERVICE_ID + "='" + serviceId.getId() + "' and " + TblAttribute.C_ID 
				+ "='" + attributeId + "'";

		DataAccessHelper.UpdateQuery(query);
		
		return new ServiceItemAttribute(attributeId, name, ruleId, type, " ", flag); 
	}

	

	@Override
	public boolean deleteItemAttribute(ServiceID serviceId, ServiceItemID itemId, String attributeId) {
		String id = attributeId;

		String query = "delete from " + TblAttribute.TABLE_NAME + " where " + TblAttribute.C_ID + "='" + id 
				+"' and " + TblAttribute.C_ITEM_ID + "='" + itemId.getId() + "' and " + TblAttribute.C_SERVICE_ID
				+ "='" + serviceId.getId() + "'";
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
				attr.setValidation(rs.getString(TblAttribute.C_VALIDATION_ID));
				if(attr.getValidation()==null || "".equals(attr.getValidation())){
					attr.setValidation("None");
				}

				attrs.add(attr);
				
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		attributes.setAttrs(attrs);
		return attributes;
	}
	
	
//******************************************************************************\\
//				************************************************				\\
//							ITEM ATTRIBUTES END									\\
//				************************************************				\\
//******************************************************************************\\
	

	
//******************************************************************************\\
//				************************************************				\\
//							VALIDATIONS START									\\
//				************************************************				\\
//******************************************************************************\\

	
	
	@Override
	public Validation createValidation(String name, String type, List<String> params) {
		
		String duplicateQuery = "select * from " + TblValidityRule.TABLE_NAME + " where "
				+ TblValidityRule.C_NAME + " = '" + name + "'";
		
		try{
			ResultSet rs = DataAccessHelper.executeQuery(duplicateQuery);
			if(rs!=null){
				if(rs.next()){
					return null;
				}
			}
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}
		
		for(int i=params.size(); i<2;i++)
			params.add("");
		
		String id = name+ "-ID" + (new MyDate(new Date()));
		String sql = "insert into "+ TblValidityRule.TABLE_NAME + "(" + TblValidityRule.C_ID + "," 
				+ TblValidityRule.C_NAME + "," + TblValidityRule.C_TYPE + "," + TblValidityRule.C_PARAM1
				+ "," + TblValidityRule.C_PARAM2+ ") values ('" + id + "','" + name + "','" 
				+ type + "','" + params.get(0) + "','" + params.get(1) + "')";
		
		DataAccessHelper.UpdateQuery(sql);
		
		String description = name + "=" + type+"(";
		for(int i=0;i<2;i++){
			String param = params.get(i);
			if(param==null || "".equals(param))
				break;
			else
				description = description + "," + param;
		}
		Validation v = new Validation(id, name, type, description, params);
		validations.add(v);
		return v;
	}

	@Override
	public boolean deleteValidation(String validationId) {
		
		String checkQuery = "select * from " + TblAttribute.TABLE_NAME + " where " 
		+ TblAttribute.C_VALIDATION_ID + "='" + validationId + "'";
		try{
			ResultSet rs = DataAccessHelper.executeQuery(checkQuery);
			if(rs!=null && rs.next())
				return false;
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		String query = "delete from " + TblValidityRule.TABLE_NAME + " where " 
		+ TblValidityRule.C_ID + "='" + validationId +"'" ;
		DataAccessHelper.UpdateQuery(query);
		
		for(int i=0;i<validations.size();i++){
			if(validations.get(i).getId().equals(validationId)){
				validations.remove(i);
			}
		}
		return true;
		
	}

	@Override
	public Validation updateValidation(String validationId, String name,
			String type, List<String> params) {
		
		String duplicateQuery = "select * from " + TblValidityRule.TABLE_NAME + " where " +
		TblValidityRule.C_NAME + " = '" + name +"'";
		ResultSet rs = DataAccessHelper.executeQuery(duplicateQuery);
		try{
			if(rs!=null){				
				while(rs!=null && rs.next()){
					if(!(validationId.equals( rs.getString(TblValidityRule.C_ID))))
						return null;
				}
			}
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}

		String sql = "update "+ TblValidityRule.TABLE_NAME+ " set " + TblValidityRule.C_NAME + "='" 
				+ name + "'," +TblValidityRule.C_TYPE  + "='" + type 
				+ "'," + TblValidityRule.C_PARAM1 + "='" + params.get(0) 
				+ "'," + TblValidityRule.C_PARAM2 + "='" + params.get(1) 
				+ "' where " + TblValidityRule.C_ID + "='" + validationId + "'";
		
		DataAccessHelper.UpdateQuery(sql);
		
		String description = name + " " + type;
		for(int i=0;i<2;i++){
			String param = params.get(i);
			if(param==null || "".equals(param))
				break;
			else
				description = description + ", " + param;
		}
		
		for(int i=0 ; i<validations.size();i++){
			if(validations.get(i).getId().equals(validationId)){
				validations.remove(i);
				break;
			}
		}

		Validation v = new Validation(validationId, name, type, description, params);
		validations.add(v);
		
		return v;
	}

	@Override
	public List<Validation> getAllValidations() {
		return validations;
	}

	@Override
	public Validation getValidationById(String validationId) {
		for(Validation v : validations){
			if(v.getId().equals(validationId))
				return v;
		}
		return null;
	}

	@Override
	public List<ValidationTypeDetail> getValidationTypeDetails() {
		return validationTypeDetailList;
	}


//******************************************************************************\\
//				************************************************				\\
//								VALIDATIONS END									\\
//				************************************************				\\
//******************************************************************************\\



//******************************************************************************\\
//				************************************************				\\
//								PRODUCTS START									\\
//				************************************************				\\
//******************************************************************************\\
	
	@Override
	public Product createProduct(ServiceID serviceId,
			ServiceItemID serviceItemId, ProductID productId,
			Location location, List<ProductAttribute> attrList, String productName) {
		
		String query = "insert into " + TblProduct.TABLE_NAME + " (" + TblProduct.C_ID + ", "
				+ TblProduct.C_ITEM_ID + ", " + TblProduct.C_SERVICE_ID + ", " + TblProduct.C_NAME
				+ ", " + TblProduct.C_X_POSITION + ", " + TblProduct.C_Y_POSITION + ", "
				+ TblProduct.C_RATING + ") values ('" + productId.getId()
				+ "','" + serviceItemId.getId() + "','" + serviceId.getId() + "','" + productName
				+ "','" + location.getLon() + "','" + location.getLat() + "','0')";
		DataAccessHelper.UpdateQuery(query);
		
		
		for(ProductAttribute pa : attrList) {
			String valQuery = "insert into "+ TblValues.TABLE_NAME + " (" + TblValues.C_SERVICE_ID
				+ ", " + TblValues.C_ITEM_ID + ", " +TblValues.C_ATTRIBUTE_ID + ", "
				+ TblValues.C_PRODUCT_ID + ", " + TblValues.C_VALUE + ") values ('" +serviceId.getId()
				+ "','" + serviceItemId.getId() + "','" + pa.getKey() + "','" + productId.getId()
				+ "','" + pa.getValue() + "')";
			DataAccessHelper.UpdateQuery(valQuery);
		}
		
		return new Product();
	}
	
	
	@Override
	public List<Product> getServiceProducts(ServiceID serviceId){
		return getProducts(serviceId);
	}
	
	
	@Override
	public void updateProductRating(ProductID productId, ServiceID serviceId,
			ServiceItemID itemId, String rating){
		String query = "update " + TblProduct.TABLE_NAME + " set " + TblProduct.C_RATING + "='"
				+ rating + "' where " + TblProduct.C_ID + "='" + productId.getId() + "'AND "
				+ TblProduct.C_SERVICE_ID + "='" + serviceId.getId() + "'AND "
				+ TblProduct.C_ITEM_ID + "='" + itemId.getId()+"'";
		DataAccessHelper.UpdateQuery(query);
	}	
	
	@Override
	public void updateProductAttributeValue(ProductID productId, ServiceID serviceId,
			ServiceItemID itemId, String attributeId, String value){
		String query = "update " + TblValues.TABLE_NAME + " set " + TblValues.C_VALUE+ "='"
				+ value + "' where " + TblValues.C_ATTRIBUTE_ID + "='" + attributeId + "'AND "
				+ TblValues.C_SERVICE_ID + "='" + serviceId.getId() + "'AND "
				+ TblValues.C_ITEM_ID + "='" + itemId.getId() + "'AND "
				+ TblValues.C_PRODUCT_ID + "='" + productId.getId()+"'";
		DataAccessHelper.UpdateQuery(query);
	}	
	

	
	
	public static List<Product> getProducts(ServiceID serviceId){
		List<Product> products = new ArrayList<Product>();
		
		String query = "select * from " + TblProduct.TABLE_NAME + " where " + TblProduct.C_SERVICE_ID + "='" + serviceId.getId()+ "'";
		try{
			ResultSet rs = DataAccessHelper.executeQuery(query);
			while(rs!=null && rs.next()){
				Product p = new Product();
				p.setId(new ProductID(rs.getString(TblProduct.C_ID)));
				p.setName(rs.getString(TblProduct.C_NAME));
				p.setLocation(new Location(Float.parseFloat(rs.getString(TblProduct.C_X_POSITION)),
						Float.parseFloat(rs.getString(TblProduct.C_Y_POSITION))));
				String itemId = rs.getString(TblProduct.C_ITEM_ID);
				p.setServiceItem(getItemById(serviceId.getId(), itemId));
				p.setRating(rs.getString(TblProduct.C_RATING));
				List<ProductAttribute> productAttrList = new ArrayList<ProductAttribute>();
				String attrquery = "select * from " + TblValues.TABLE_NAME + " where "
					+ TblValues.C_SERVICE_ID + "='" + serviceId.getId() + "' and " + TblValues.C_ITEM_ID 
					+ "='" + itemId + "' and " + TblValues.C_PRODUCT_ID + "='" +p.getId().getId() + "'";
				ResultSet valResult = DataAccessHelper.executeQuery(attrquery);
				while(valResult!=null && valResult.next()){
					ProductAttribute attr = new ProductAttribute(valResult.getString(TblValues.C_ATTRIBUTE_ID), valResult.getString(TblValues.C_VALUE));
					productAttrList.add(attr);
				}
				
				
				p.setAttrs(productAttrList);
				p.setReviews(getProductReviews(serviceId, itemId, p.getId()));
				p.setComments(getProductComments(serviceId, itemId, p.getId()));
				p.setValueReviews(getProductValueReviews(serviceId, itemId, p.getId()));
				products.add(p);
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		return products;
	}
	
//******************************************************************************\\
//				************************************************				\\
//								PRODUCTS END									\\
//				************************************************				\\
//******************************************************************************\\
	
	
	
//******************************************************************************\\
//				************************************************				\\
//							PRODUCT REVIEWS START								\\
//				************************************************				\\
//******************************************************************************\\


	@Override
	public ProductReview addProductReview(ProductID productId, ServiceID serviceId,
			ServiceItemID itemId, String username, MyDate date, String rating) {
		
		String query = "select * from " + TblReviews.TABLE_NAME + " where " + TblReviews.C_ITEM_ID + "='"
				+ itemId.getId() + "' and " + TblReviews.C_PRODUCT_ID + "='" + productId.getId() + "' and "
				+ TblReviews.C_SERVICE_ID + "='" + serviceId.getId() +"' and " + TblReviews.C_USERNAME
				+ "='" + username + "'";
		try{
			ResultSet rs = DataAccessHelper.executeQuery(query);
			if(rs!=null && rs.next()){
				String updateQuery = "update " + TblReviews.TABLE_NAME + " set " + TblReviews.C_RATING + "='"
						+ rating + "'," + TblReviews.C_STATUS + "='unhandled'," + TblReviews.C_DATE + "='"
						+ date + "'" + " where " + TblReviews.C_ITEM_ID + "='"
						+ itemId.getId() + "' and " + TblReviews.C_PRODUCT_ID + "='" + productId.getId() + "' and "
						+ TblReviews.C_SERVICE_ID + "='" + serviceId.getId() +"' and " + TblReviews.C_USERNAME
						+ "='" + username + "'";
				DataAccessHelper.UpdateQuery(updateQuery);		
			}
			else {				
				String insertQuery = "insert into "+ TblReviews.TABLE_NAME + "(" + TblReviews.C_ITEM_ID + "," 
						+ TblReviews.C_PRODUCT_ID + "," + TblReviews.C_SERVICE_ID + "," + TblReviews.C_USERNAME
						+ "," + TblReviews.C_DATE+"," + TblReviews.C_RATING+"," + TblReviews.C_STATUS+ 
						") values ('" + itemId.getId() + "','" + productId.getId() + "','" 
						+ serviceId.getId() + "','" + username + "','" + date + "','" + rating + "','unhandled')";
				DataAccessHelper.UpdateQuery(insertQuery);		
			}
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	

	@Override
	public void updateProductReviewStatus(ProductID productId, ServiceID serviceId,
			ServiceItemID itemId, String username, String status) {
			String updateQuery = "update " + TblReviews.TABLE_NAME + " set " 
					+ TblReviews.C_STATUS + "='" + status + "' where "
					+ TblReviews.C_ITEM_ID + "='" + itemId.getId() + "' and "
					+ TblReviews.C_SERVICE_ID + "='" + serviceId.getId() + "' and "
					+ TblReviews.C_PRODUCT_ID + "='" + productId.getId() + "' and "
					+ TblReviews.C_USERNAME + "='" + username + "'";
			DataAccessHelper.UpdateQuery(updateQuery);		
	}
	
	
	
	static private List<ProductReview> getProductReviews(ServiceID serviceId, String itemId, ProductID productId){		
		
		List<ProductReview> result = new ArrayList<ProductReview>();
		try{
		String reviewquery = "select * from " + TblReviews.TABLE_NAME + " where "
				+ TblReviews.C_SERVICE_ID + "='" + serviceId.getId() + "' and " + TblReviews.C_ITEM_ID 
				+ "='" + itemId + "' and " + TblReviews.C_PRODUCT_ID + "='" +productId.getId() + "'";
			ResultSet revResult = DataAccessHelper.executeQuery(reviewquery);
			while(revResult!=null && revResult.next()){
				ProductReview rev = new ProductReview();
				rev.setReviewRating(revResult.getString(TblReviews.C_RATING));
				rev.setDate(new MyDate(revResult.getString(TblReviews.C_DATE)));
				rev.setUsername(revResult.getString(TblReviews.C_USERNAME));
				rev.setProduct(revResult.getString(TblReviews.C_PRODUCT_ID));
				rev.setStatus(revResult.getString(TblReviews.C_STATUS));
				result.add(rev);
			}
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return result;
	}
	

	
//******************************************************************************\\
//				************************************************				\\
//								PRODUCT REVIEWS END								\\
//				************************************************				\\
//******************************************************************************\\

	
	
//******************************************************************************\\
//				************************************************				\\
//							PRODUCT VALUE REVIEWS START							\\
//				************************************************				\\
//******************************************************************************\\

	
	@Override
	public ProductValueReview addProductValueReview(ProductID productId, ServiceID serviceId,
			ServiceItemID itemId,String attributeId , String username, MyDate date, String value) {
		
		String query = "select * from " + TblReviewValues.TABLE_NAME + " where " 
				+ TblReviewValues.C_ITEM_ID + "='" + itemId.getId() + "' and " 
				+ TblReviewValues.C_PRODUCT_ID + "='" + productId.getId() + "' and "
				+ TblReviewValues.C_SERVICE_ID + "='" + serviceId.getId() +"' and "
				+ TblReviewValues.C_ATTRIBUTE_ID + "='" + attributeId +"' and "
				+ TblReviewValues.C_USERNAME+ "='" + username + "'";
		try{
			ResultSet rs = DataAccessHelper.executeQuery(query);
			if(rs!=null && rs.next()){
				String updateQuery = "update " + TblReviewValues.TABLE_NAME + " set " 
						+ TblReviewValues.C_VALUE + "='" + value + "'," 
						+ TblReviewValues.C_STATUS + "='unhandled'," 
						+ TblReviewValues.C_DATE + "='" + date + "'" + " where " 
						+ TblReviewValues.C_ITEM_ID + "='" + itemId.getId() + "' and " 
						+ TblReviewValues.C_PRODUCT_ID + "='" + productId.getId() + "' and "
						+ TblReviewValues.C_SERVICE_ID + "='" + serviceId.getId() +"' and "
						+ TblReviewValues.C_ATTRIBUTE_ID + "='" + attributeId +"' and "
						+ TblReviewValues.C_USERNAME+ "='" + username + "'";
				DataAccessHelper.UpdateQuery(updateQuery);		
			}
			else {				
				String insertQuery = "insert into "+ TblReviewValues.TABLE_NAME + "(" + TblReviewValues.C_ITEM_ID + "," 
						+ TblReviewValues.C_PRODUCT_ID + "," + TblReviewValues.C_SERVICE_ID + "," + TblReviewValues.C_USERNAME
						+ "," + TblReviewValues.C_DATE+"," + TblReviewValues.C_VALUE+"," + TblReviewValues.C_STATUS
						+ ","+TblReviewValues.C_ATTRIBUTE_ID + 
						") values ('" + itemId.getId() + "','" + productId.getId() + "','" 
						+ serviceId.getId() + "','" + username + "','" + date + "','" + value + "','unhandled','"+attributeId+"')";
				DataAccessHelper.UpdateQuery(insertQuery);		
			}
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	

	@Override
	public void updateProductValueReviewStatus(ProductID productId, ServiceID serviceId,
			ServiceItemID itemId, String attributeId, String username, String status) {
			String updateQuery = "update " + TblReviewValues.TABLE_NAME + " set " 
					+ TblReviewValues.C_STATUS + "='" + status + "' where "
					+ TblReviewValues.C_ATTRIBUTE_ID + "='" + attributeId + "' and "
					+ TblReviewValues.C_ITEM_ID + "='" + itemId.getId() + "' and "
					+ TblReviewValues.C_SERVICE_ID + "='" + serviceId.getId() + "' and "
					+ TblReviewValues.C_PRODUCT_ID + "='" + productId.getId() + "' and "
					+ TblReviewValues.C_USERNAME + "='" + username + "'";
			DataAccessHelper.UpdateQuery(updateQuery);		
	}
	
	@Override
	public void adjustProductValueReview(ProductID productId, ServiceID serviceId,
			ServiceItemID itemId, String attributeId, String username, String value) {
			String updateQuery = "update " + TblReviewValues.TABLE_NAME + " set " 
					+ TblReviewValues.C_VALUE + "='" + value + "' where "
					+ TblReviewValues.C_ATTRIBUTE_ID + "='" + attributeId + "' and "
					+ TblReviewValues.C_ITEM_ID + "='" + itemId.getId() + "' and "
					+ TblReviewValues.C_SERVICE_ID + "='" + serviceId.getId() + "' and "
					+ TblReviewValues.C_PRODUCT_ID + "='" + productId.getId() + "' and "
					+ TblReviewValues.C_USERNAME + "='" + username + "'";
			System.out.println(updateQuery);
			DataAccessHelper.UpdateQuery(updateQuery);		
	}
	
	
	static private List<ProductValueReview> getProductValueReviews(ServiceID serviceId, String itemId, ProductID productId){		
		List<ProductValueReview> result = new ArrayList<ProductValueReview>();
		try{
		String reviewquery = "select * from " + TblReviewValues.TABLE_NAME + " where "
				+ TblReviewValues.C_SERVICE_ID + "='" + serviceId.getId() + "' and " + TblReviewValues.C_ITEM_ID 
				+ "='" + itemId + "' and " + TblReviewValues.C_PRODUCT_ID + "='" +productId.getId() + "'";
			ResultSet revResult = DataAccessHelper.executeQuery(reviewquery);
			while(revResult!=null && revResult.next()){
				ProductValueReview rev = new ProductValueReview();
				rev.setAttributeId(revResult.getString(TblReviewValues.C_ATTRIBUTE_ID));
				rev.setProductId(revResult.getString(TblReviewValues.C_PRODUCT_ID));
				rev.setStatus(revResult.getString(TblReviewValues.C_STATUS));
				rev.setUsername(revResult.getString(TblReviewValues.C_USERNAME));
				rev.setValue(revResult.getString(TblReviewValues.C_VALUE));
				rev.setDate(new MyDate(revResult.getString(TblReviewValues.C_DATE)));
				result.add(rev);
			}
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	

//******************************************************************************\\
//				************************************************				\\
//							PRODUCT VALUE REVIEWS END							\\
//				************************************************				\\
//******************************************************************************\\

//******************************************************************************\\
//				************************************************				\\
//							PRODUCT COMMENTS START								\\
//				************************************************				\\
//******************************************************************************\\

	
	
	@Override
	public void addProductComment(ProductID productId, ServiceID serviceId,
			ServiceItemID itemId,String username, MyDate date, String text) {
		
			String insertQuery = "insert into "+ TblComments.TABLE_NAME + "(" + TblComments.C_ITEM_ID + "," 
					+ TblComments.C_PRODUCT_ID + "," + TblComments.C_SERVICE_ID + "," + TblComments.C_USERNAME
					+ "," + TblComments.C_DATE+"," + TblComments.C_TEXT+ 
					") values ('" + itemId.getId() + "','" + productId.getId() + "','" 
					+ serviceId.getId() + "','" + username + "','" + date + "','" + text + "')";
			DataAccessHelper.UpdateQuery(insertQuery);		
	}
	
	
	static private List<ProductComment> getProductComments(ServiceID serviceId, String itemId, ProductID productId){		
		List<ProductComment> result = new ArrayList<ProductComment>();
		try{
		String query = "select * from " + TblComments.TABLE_NAME + " where "
				+ TblComments.C_SERVICE_ID + "='" + serviceId.getId() + "' and " + TblComments.C_ITEM_ID 
				+ "='" + itemId + "' and " + TblComments.C_PRODUCT_ID + "='" +productId.getId() + "'";
			ResultSet revResult = DataAccessHelper.executeQuery(query);
			while(revResult!=null && revResult.next()){
				ProductComment rev = new ProductComment();
				rev.setProductId(revResult.getString(TblComments.C_PRODUCT_ID));
				rev.setUsername(revResult.getString(TblComments.C_USERNAME));
				rev.setDate(new MyDate(revResult.getString(TblComments.C_DATE)));
				rev.setComment(revResult.getString(TblComments.C_TEXT));
				result.add(rev);
			}
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return result;
	}
	
//******************************************************************************\\
//				************************************************				\\
//							PRODUCT COMMENTS END								\\
//				************************************************				\\
//******************************************************************************\\

	
	
//******************************************************************************\\
//				************************************************				\\
//							NOTIFICATIONS START									\\
//				************************************************				\\
//******************************************************************************\\

	

	@Override
	public void addNotification(String notification, String username,
			ServiceID serviceId) {
		String query = "insert into " + TblNotification.TABLE_NAME + " (" + TblNotification.C_SERVICE_ID + ", "
				+ TblNotification.C_TEXT + ", " + TblNotification.C_DATE + ", " + TblNotification.C_HANDLED + 
				", " + TblNotification.C_USERNAME +") values ('" + serviceId.getId() + "','" + notification + "','"
				+ new MyDate(new Date()) + "','unhandled','" + username + "')";
		DataAccessHelper.UpdateQuery(query);
	}

	

	@Override
	public void updateNotificationStatus(String id, String status) {
		String updateQuery = "update " + TblNotification.TABLE_NAME + " set " 
				+ TblNotification.C_HANDLED + "='" + status + "' where "
				+ TblNotification.C_ID + "='" + id + "'";
		DataAccessHelper.UpdateQuery(updateQuery);				
	}
	
	@Override
	public void deleteNotification(String id){
		String deleteQuery = "delete from " + TblNotification.TABLE_NAME + " where "
				+ TblNotification.C_ID + "='" + id + "'";
		DataAccessHelper.UpdateQuery(deleteQuery);		

	}
	
	
	@Override
	public List<NotificationToAdmin> getNotifications(){		
		List<NotificationToAdmin> result = new ArrayList<NotificationToAdmin>();
		try{
		String query = "select * from " + TblNotification.TABLE_NAME;
			ResultSet revResult = DataAccessHelper.executeQuery(query);
			while(revResult!=null && revResult.next()){
				NotificationToAdmin rev = new NotificationToAdmin();
				rev.setUsername(revResult.getString(TblNotification.C_USERNAME));
				rev.setDate(new MyDate(revResult.getString(TblNotification.C_DATE)));
				rev.setText(revResult.getString(TblNotification.C_TEXT));
				rev.setStatus(revResult.getString(TblNotification.C_HANDLED));
				rev.setService(revResult.getString(TblNotification.C_SERVICE_ID));
				rev.setId(revResult.getString(TblNotification.C_ID));
				result.add(rev);
			}
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return result;
	}


		
//******************************************************************************\\
//				************************************************				\\
//							NOTIFICATIONS END									\\
//				************************************************				\\
//******************************************************************************\\

	
	
}
