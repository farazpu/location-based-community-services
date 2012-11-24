package net.fast.lbcs.serviceProvider.controller;

public abstract class ServiceProviderController {
	public abstract boolean login(String user, String password);
	public abstract boolean isLoggedIn();

}
