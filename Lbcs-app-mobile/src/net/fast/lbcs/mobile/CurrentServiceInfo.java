package net.fast.lbcs.mobile;

import java.util.ArrayList;
import java.util.List;

import net.fast.lbcs.data.entities.admin.group.ServiceItemGroup;
import net.fast.lbcs.data.entities.admin.item.ServiceItem;
import net.fast.lbcs.data.entities.admin.service.LocationService;
import net.fast.lbcs.data.entities.admin.service.ServiceInfo;
import net.fast.lbcs.data.entities.user.Product;

public class CurrentServiceInfo {

	public static ServiceInfo currentServiceInfo;
	public static String XMLServiceInfo;
	

	public static LocationService getLocationService() {
		return currentServiceInfo.getLocationService();	
	}
	
	public static List<Product> getGroupProducts(String groupName) {
		
		List<Product> result = new ArrayList<Product>();
		List<Product> itemList = getProductList();

		for(Product p : itemList) {
			if(p.getServiceItem().getGroup().getName().equals(groupName)) {
				result.add(p);
			}
		}
		
		return result;
	}

	public static List<Product> getObjectProducts(String objectName) {
		
		List<Product> result = new ArrayList<Product>();
		List<Product> itemList = getProductList();

		for(Product p : itemList) {
			if(p.getServiceItem().getName().equals(objectName)) {
				result.add(p);
			}
		}
		
		return result;
	}
	
	public static List<Product> getProduct(String productName) {
		
		List<Product> result = new ArrayList<Product>();
		List<Product> itemList = getProductList();

		for(Product p : itemList) {
			if(p.getName().equals(productName)) {
				result.add(p);
			}
		}
		
		return result;
	}

	
	public static List<Product> getProductList() {
		return currentServiceInfo.getProductResultSet().getProducts();
	}
	
	public static List<ServiceItem> getObjectList() {

		return currentServiceInfo.getLocationService().getItems();
	}

	public static List<ServiceItemGroup> getGroupList() {
		return currentServiceInfo.getLocationService().getGroups();
	}
	

}
