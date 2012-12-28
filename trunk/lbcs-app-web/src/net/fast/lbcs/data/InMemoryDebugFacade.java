package net.fast.lbcs.data;

import java.util.List;

import net.fast.lbcs.admin.Administrator;
import net.fast.lbcs.admin.service.LocationService;
import net.fast.lbcs.user.User;
import net.fast.lbcs.user.controller.Product;

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

	public static List<Product> getProducts() {
		return InMemoryDataSource.getProducts();
	}
}
