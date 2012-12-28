package net.fast.lbcs.data;

import java.util.List;

import net.fast.lbcs.admin.Administrator;
import net.fast.lbcs.admin.service.LocationService;
import net.fast.lbcs.admin.service.ServiceID;

public interface DataSource {

	Administrator queryAdministratorByUserIdAndPassword(String user,
			String password);

	List<LocationService> getAllServices(int startIndex, int endIndex);

	LocationService getServiceById(ServiceID serviceId);

}
