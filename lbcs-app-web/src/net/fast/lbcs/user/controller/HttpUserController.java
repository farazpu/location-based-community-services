package net.fast.lbcs.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.fast.lbcs.data.DataSource;
import net.fast.lbcs.data.DataSourceFactory;
import net.fast.lbcs.data.entities.admin.Administrator;
import net.fast.lbcs.data.entities.admin.item.ServiceItemID;
import net.fast.lbcs.data.entities.admin.service.ServiceID;
import net.fast.lbcs.data.entities.user.Location;
import net.fast.lbcs.data.entities.user.Product;
import net.fast.lbcs.data.entities.user.ProductID;
import net.fast.lbcs.data.entities.user.ProductRawData;
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
