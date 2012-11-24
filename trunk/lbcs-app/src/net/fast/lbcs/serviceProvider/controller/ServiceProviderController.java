package net.fast.lbcs.serviceProvider.controller;

import net.fast.lbcs.serviceProvider.ServiceProvider;

public abstract class ServiceProviderController {
	public abstract boolean login(String user, String password);
	public abstract boolean isLoggedIn();
	public abstract ServiceProvider getCurrentUser();

}
