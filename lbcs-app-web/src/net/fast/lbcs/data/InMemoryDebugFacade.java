package net.fast.lbcs.data;

import java.util.List;

import net.fast.lbcs.data.entities.admin.Administrator;
import net.fast.lbcs.data.entities.admin.service.LocationService;
import net.fast.lbcs.data.entities.admin.service.ServiceID;
import net.fast.lbcs.data.entities.user.Product;
import net.fast.lbcs.data.entities.user.User;


public class InMemoryDebugFacade {
	public static List<Administrator> getAdmins() {
		return InMemoryDataSource.getAdmins();
	}

	public static List<LocationService> getLocationServices() {
		return InMemoryDataSource.getLocationServices();
	}

	public static List<User> getUsers() {
		return InMemoryDataSource.getUsers();
	}

	public static List<Product> getProducts(ServiceID serviceId) {
		return InMemoryDataSource.getProducts(serviceId);
	}
}
