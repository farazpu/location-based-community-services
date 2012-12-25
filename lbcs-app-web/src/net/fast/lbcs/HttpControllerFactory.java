package net.fast.lbcs;

import javax.servlet.http.HttpServletRequest;

import net.fast.lbcs.admin.controller.AdminController;
import net.fast.lbcs.admin.controller.HttpAdminController;
import net.fast.lbcs.serviceProvider.controller.HttpServiceProviderController;
import net.fast.lbcs.serviceProvider.controller.ServiceProviderController;
import net.fast.lbcs.user.controller.HttpUserController;
import net.fast.lbcs.user.controller.UserController;

public class HttpControllerFactory extends ControllerFactory{

	private HttpAdminController adminController;
	private HttpUserController userController;
	private HttpServiceProviderController serviceProviderController;

	public HttpControllerFactory(HttpServletRequest request) {
		adminController = new HttpAdminController(request);
		userController = new HttpUserController(request);
		serviceProviderController = new HttpServiceProviderController(request);
	}
	
	@Override
	public AdminController getAdminController() {
		return adminController;
	}

	@Override
	public UserController getUserController() {
		return userController;
	}

	@Override
	public ServiceProviderController getServiceProviderController() {
		return serviceProviderController;
	}

}
