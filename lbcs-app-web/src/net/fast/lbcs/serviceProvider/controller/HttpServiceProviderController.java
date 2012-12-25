package net.fast.lbcs.serviceProvider.controller;

import javax.servlet.http.HttpServletRequest;

import net.fast.lbcs.serviceProvider.ServiceProvider;

public class HttpServiceProviderController extends ServiceProviderController{

	private HttpServletRequest request;

	public HttpServiceProviderController(HttpServletRequest request) {
		this.request = request;
		
	}
	
	@Override
	public boolean login(String user, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLoggedIn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ServiceProvider getCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
