package net.fast.lbcs.user.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.fast.lbcs.data.DataSource;
import net.fast.lbcs.data.DataSourceFactory;
import net.fast.lbcs.data.InMemoryDebugFacade;
import net.fast.lbcs.data.entities.MyDate;
import net.fast.lbcs.data.entities.admin.Administrator;
import net.fast.lbcs.data.entities.admin.item.ServiceItem;
import net.fast.lbcs.data.entities.admin.item.ServiceItemID;
import net.fast.lbcs.data.entities.admin.service.LocationService;
import net.fast.lbcs.data.entities.admin.service.ServiceID;
import net.fast.lbcs.data.entities.user.Location;
import net.fast.lbcs.data.entities.user.Product;
import net.fast.lbcs.data.entities.user.ProductAttribute;
import net.fast.lbcs.data.entities.user.ProductID;
import net.fast.lbcs.data.entities.user.ProductRawData;
import net.fast.lbcs.data.entities.user.ProductReview;
import net.fast.lbcs.data.entities.user.ProductValueReview;
import net.fast.lbcs.data.entities.user.User;
import net.fast.lbcs.data.entities.user.UserSettings;

public class HttpUserController extends UserController{

	private HttpServletRequest request;

	public HttpUserController(HttpServletRequest request) {
		this.request = request;
		
	}
	
	@Override
	public boolean login(String username, String password, ServiceID serviceId) {
		DataSource source = DataSourceFactory.getDataSource();
		User user = source.queryUserByUserIdAndPassword(username, password);
		if(user != null) {
			return true;
		}

		return false;

	}

	@Override
	public boolean isLoggedIn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsAtLocation(Location location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product createNewProductEntry(ServiceID serviceId, ServiceItemID serviceItemId,
			ProductID productId, Location location, List<ProductAttribute> attrList, String productName) {
		
		DataSource source = DataSourceFactory.getDataSource();
		return source.createProduct(serviceId, serviceItemId, productId, location, attrList, productName);
	}

	@Override
	public Product editProduct(ProductID productId, ProductRawData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduct(ProductID productId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postReview(ProductID productId, int rating, int reviewText) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveSettings(UserSettings settings) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> getProductsByServiceId(ServiceID serviceId) {
		return InMemoryDebugFacade.getProducts(serviceId);
	}

	@Override
	public ProductReview addProductReview(ProductID productId,
			ServiceID serviceId, ServiceItemID itemId, String username,
			MyDate date, String rating) {

		DataSource source = DataSourceFactory.getDataSource();
		return source.addProductReview(productId, serviceId, itemId, username, date, rating);
	}

	@Override
	public void addNotification(String notification, String username,
			ServiceID serviceId) {
		DataSource source = DataSourceFactory.getDataSource();
		source.addNotification(notification, username, serviceId);
		
	}

	
	@Override
	public void addProductComment(ProductID productId, ServiceID serviceId,
			ServiceItemID itemId, String username, MyDate date, String text){
		DataSource source = DataSourceFactory.getDataSource();
		source.addProductComment(productId, serviceId, itemId, username, date, text);
	}

	@Override
	public ProductValueReview addProductValueReview(ProductID productId,
			ServiceID serviceId, ServiceItemID itemId, String attributeId,
			String username, MyDate date, String value){
		DataSource source = DataSourceFactory.getDataSource();
		source.addProductValueReview(productId, serviceId, itemId, attributeId, username, date, value);
		
		return null;
	}


}
