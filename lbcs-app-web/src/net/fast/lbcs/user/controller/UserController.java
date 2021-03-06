package net.fast.lbcs.user.controller;

import java.util.List;

import net.fast.lbcs.data.entities.MyDate;
import net.fast.lbcs.data.entities.admin.Administrator;
import net.fast.lbcs.data.entities.admin.item.ServiceItemID;
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

public abstract class UserController {
	public abstract boolean login(String user, String password, ServiceID serviceId);
	public abstract boolean isLoggedIn();
	public abstract User getCurrentUser();
	

	public abstract List<Product> getProductsByServiceId(ServiceID serviceId);
	public abstract List<Product> getProductsAtLocation(Location location);
	public abstract Product createNewProductEntry(ServiceID serviceId, ServiceItemID serviceItemId,
			ProductID productId, Location location, List<ProductAttribute> attrList, String productName);
	public abstract Product editProduct(ProductID productId, ProductRawData data);
	public abstract void deleteProduct(ProductID productId);
	public abstract void postReview(ProductID productId, int rating, int reviewText);
	public abstract void saveSettings(UserSettings settings);
	public abstract ProductReview addProductReview(ProductID productId,ServiceID serviceId,
			ServiceItemID itemId,String username,MyDate date,String rating);
	
	public abstract void addNotification(String notification, String username, ServiceID serviceId);
	public abstract void addProductComment(ProductID productId, ServiceID serviceId,
			ServiceItemID itemId, String username, MyDate date, String text);
	public abstract ProductValueReview addProductValueReview(ProductID productId,
			ServiceID serviceId, ServiceItemID itemId, String attributeId,
			String username, MyDate date, String value);
}
