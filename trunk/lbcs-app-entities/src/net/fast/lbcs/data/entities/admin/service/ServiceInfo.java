package net.fast.lbcs.data.entities.admin.service;

import net.fast.lbcs.data.entities.user.ProductResultSet;

import org.simpleframework.xml.Default;

@Default
public class ServiceInfo {
	private LocationService locationService;
	private ProductResultSet productResultSet;
	public LocationService getLocationService() {
		return locationService;
	}
	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}
	public ProductResultSet getProductResultSet() {
		return productResultSet;
	}
	public void setProductResultSet(ProductResultSet productResultSet) {
		this.productResultSet = productResultSet;
	}
	public ServiceInfo(LocationService locationService,
			ProductResultSet productResultSet) {
		this.locationService = locationService;
		this.productResultSet = productResultSet;
	}
	public ServiceInfo() {
	}
	
	
}
