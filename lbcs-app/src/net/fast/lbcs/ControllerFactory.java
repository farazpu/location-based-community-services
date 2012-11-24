package net.fast.lbcs;

import net.fast.lbcs.admin.controller.AdminController;
import net.fast.lbcs.serviceProvider.controller.ServiceProviderController;
import net.fast.lbcs.user.controller.UserController;

public abstract class ControllerFactory {
	public abstract AdminController getAdminController();
	public abstract UserController getUserController();
	public abstract ServiceProviderController getServiceProviderController();
}
