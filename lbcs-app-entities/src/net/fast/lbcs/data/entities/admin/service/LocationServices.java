package net.fast.lbcs.data.entities.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Default;

@Default
public class LocationServices {
	private List<LocationService> locationServices = new ArrayList<LocationService>();
	private boolean validation;
	

	public LocationServices() {
	}

	public LocationServices(List<LocationService> locationServices, boolean validation) {
		this.locationServices = locationServices;
		this.validation = validation;
	}
	
	

	public boolean isValidation() {
		return validation;
	}

	public void setValidation(boolean validation) {
		this.validation = validation;
	}

	public List<LocationService> getLocationServices() {
		return locationServices;
	}

	public void setLocationServices(List<LocationService> locationServices) {
		this.locationServices = locationServices;
	}
	
	
}
