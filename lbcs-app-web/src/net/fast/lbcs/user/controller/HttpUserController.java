package net.fast.lbcs.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.fast.lbcs.admin.item.ServiceItemID;
import net.fast.lbcs.admin.service.ServiceID;
import net.fast.lbcs.user.User;

public class HttpUserController extends UserController{

	private HttpServletRequest request;

	public HttpUserController(HttpServletRequest request) {
		this.request = request;
		
	}
	
	@Override
	public boolean login(String user, String password, ServiceID serviceId) {
		// TODO Auto-generated method stub
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
	public Product createNewProductEntry(ServiceItemID serviceItemId,
			ProductRawData data) {
		// TODO Auto-generated method stub
		return null;
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

}
