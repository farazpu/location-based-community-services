<%@page import="org.simpleframework.xml.core.Persister"%>
<%@page import="org.simpleframework.xml.Serializer"%>
<%@page import="net.fast.lbcs.data.entities.admin.group.ServiceItemGroupID"%>
<%@page import="net.fast.lbcs.user.controller.ProductFilters"%>
<%@page import="net.fast.lbcs.data.entities.MyDate"%>
<%@page import="net.fast.lbcs.ControllerFactory"%>
<%@page import="net.fast.lbcs.user.controller.UserController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="net.fast.lbcs.data.entities.admin.service.LocationService"%>
<%@page import="net.fast.lbcs.HttpControllerFactory"%>
<%@page import="net.fast.lbcs.admin.controller.AdminController"%>
<%@page import="net.fast.lbcs.data.entities.user.*"%>
<%@page import="net.fast.lbcs.data.entities.admin.item.*"%>
<%@page import="net.fast.lbcs.data.entities.admin.service.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

ServiceID serviceId = new ServiceID(request.getParameter("serviceId"));
double longitude=Double.parseDouble(request.getParameter("gpslong"));
double latitude=Double.parseDouble(request.getParameter("gpslat"));
ServiceItemGroupID groupId = new ServiceItemGroupID(request.getParameter("groupId"));
String username = request.getParameter("username");
Location location = new Location(longitude,latitude);
UserController ucontroller = new HttpControllerFactory(request).getUserController();
List<Product> products = ucontroller.getProductsByServiceId(serviceId);
products = ProductFilters.DistanceFilter(products, location, 2500);
String type = request.getParameter("type");
if(type.equals("item")){
	ServiceItemID itemId = new ServiceItemID(request.getParameter("itemId"));
	products = ProductFilters.ItemFilter(products, itemId.getId(), groupId.getId(), username);
}
else{
	products = ProductFilters.GroupFilter(products, groupId);
}
products = ProductFilters.RatingFilter(products, username, 50);


ProductResultSet prs = new ProductResultSet(products, location);

AdminController controller = new HttpControllerFactory(request).getAdminController();
LocationService locationService = controller.getServiceById(serviceId);

ServiceInfo serviceInfo = new ServiceInfo(locationService, prs);

Serializer serializer = new Persister();
serializer.write(serviceInfo, response.getWriter());




%>