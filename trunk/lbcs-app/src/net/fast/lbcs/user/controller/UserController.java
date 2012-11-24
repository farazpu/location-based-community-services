package net.fast.lbcs.user.controller;

import java.util.List;

import net.fast.lbcs.admin.item.ServiceItemID;
import net.fast.lbcs.admin.service.ServiceID;

public abstract class UserController {
	public abstract boolean login(String user, String password, ServiceID serviceId);
	public abstract boolean isLoggedIn();
	
	public abstract List<Product> getProductsAtLocation(Location location);
	public abstract Product createNewProductEntry(ServiceItemID serviceItemId, ProductRawData data);
	public abstract Product editProduct(ProductID productId, ProductRawData data);
	public abstract void deleteProduct(ProductID productId);
	public abstract void postReview(ProductID productId, int rating, int reviewText);
	public abstract void saveSettings(UserSettings settings);
	
}
