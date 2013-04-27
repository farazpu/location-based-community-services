package net.fast.lbcs.mobile;

import java.util.ArrayList;
import java.util.List;

import net.fast.lbcs.data.entities.admin.group.ServiceItemGroup;
import net.fast.lbcs.data.entities.admin.item.ServiceItem;
import net.fast.lbcs.data.entities.admin.item.Validation;
import net.fast.lbcs.data.entities.admin.service.LocationService;
import net.fast.lbcs.data.entities.admin.service.ServiceInfo;
import net.fast.lbcs.data.entities.user.Product;
import net.fast.lbcs.data.entities.user.ProductResultSet;

public class CurrentServiceInfo {

	public static String currentUser;
	public static ServiceInfo currentServiceInfo;
	public static List<Validation> validations;
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
	
	public static Product getProduct(String id) {
		
		List<Product> itemList = getProductList();

		for(Product p : itemList) 
			if(p.getId().getId().equals(id)) 
				return p;
		return null;
	}

	public static Product getProductById(String productId){
		List<Product> itemList = getProductList();		
		for(Product p : itemList) {
			if(p.getName().equals(productId)) {
				return p;
			}
		}	
		return null;
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
	
	public static List<Validation> getValidations(){
		return validations;
	}
	
	public static Validation getValidationById(String validationId){
		for(Validation v : validations){
			if(v.getId().equals(validationId)){
				return v;
			}
		}
		return null;
	}
	
	public static void addProduct(Product product){
		List<Product> plist = currentServiceInfo.getProductResultSet().getProducts();
		for(int i=0;i<plist.size();i++){
			if(plist.get(i).getId().getId().equals(product.getId().getId()) && plist.get(i).getServiceItem().getId().getId().equals(product.getServiceItem().getId().getId())){
				plist.remove(i);
				break;
			}	
		}
				
		plist.add(product);
		ProductResultSet prs = new ProductResultSet(plist, currentServiceInfo.getProductResultSet().getLocation());
		currentServiceInfo.setProductResultSet(prs);
	}
	
	
	

}
