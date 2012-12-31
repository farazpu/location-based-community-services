package net.fast.lbcs.data;

import java.util.List;

import net.fast.lbcs.data.entities.admin.Administrator;
import net.fast.lbcs.data.entities.admin.service.LocationService;
import net.fast.lbcs.data.entities.admin.service.ServiceID;
import net.fast.lbcs.data.entities.user.User;

public interface DataSource {

	Administrator queryAdministratorByUserIdAndPassword(String user,
			String password);

	List<LocationService> getAllServices(int startIndex, int endIndex);

	LocationService getServiceById(ServiceID serviceId);

	User queryUserByUserIdAndPassword(String user, String password);

}
