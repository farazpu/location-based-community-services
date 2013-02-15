package net.fast.lbcs.data.entities;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Default;

import net.fast.lbcs.data.entities.admin.item.Validation;
import net.fast.lbcs.data.entities.admin.service.LocationService;

@Default
public class UserLoginData {
	private List<LocationService> locationServices = new ArrayList<LocationService>();
	private List<Validation> validations = new ArrayList<Validation>();
	private boolean error;
	

	public UserLoginData() {
	}

	public UserLoginData(List<LocationService> locationServices, List<Validation> validations,
			boolean error) {
		this.locationServices = locationServices;
		this.validations = validations;
		this.error = error;
	}
	
	
	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public List<LocationService> getLocationServices() {
		return locationServices;
	}

	public void setLocationServices(List<LocationService> locationServices) {
		this.locationServices = locationServices;
	}
	
	public List<Validation> getValidatios() {
		return validations;
	}

	public void setValiations(List<Validation> validations) {
		this.validations = validations;
	}
	
}
