<%@page import="net.fast.lbcs.user.controller.ProductFilters"%>
<%@page import="net.fast.lbcs.user.controller.UserController"%>
<%@page import="net.fast.lbcs.data.entities.user.ProductResultSet"%>
<%@page import="net.fast.lbcs.data.entities.user.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.fast.lbcs.data.entities.user.Location"%>
<%@page import="net.fast.lbcs.data.InMemoryDebugFacade"%>
<%@page import="java.util.List"%>
<%@page import="net.fast.lbcs.data.entities.admin.item.*"%>
<%@page import="net.fast.lbcs.data.entities.admin.group.*"%>
<%@page import="net.fast.lbcs.data.entities.admin.service.*"%>
<%@page import="net.fast.lbcs.HttpControllerFactory"%>
<%@page import="net.fast.lbcs.admin.controller.AdminController"%>
<%@page import="org.simpleframework.xml.core.Persister"%>
<%@page import="org.simpleframework.xml.Serializer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
Serializer serializer = new Persister();
ServiceID serviceId=new ServiceID(request.getParameter("serviceID"));
String username=request.getParameter("username");
double longitude=Double.parseDouble(request.getParameter("gpslong"));
double latitude=Double.parseDouble(request.getParameter("gpslat"));
AdminController controller = new HttpControllerFactory(request).getAdminController();
LocationService locationService = controller.getServiceById(serviceId);
ServiceInfo serviceInfo = new ServiceInfo();
serviceInfo.setLocationService(locationService);
Location location = new Location(longitude, latitude);


ProductResultSet prs = new ProductResultSet();

UserController ucontroller = new HttpControllerFactory(request).getUserController();
List<Product> serviceProducts = ucontroller.getProductsByServiceId(serviceId);

serviceProducts = ProductFilters.RatingFilter(serviceProducts, username, 75);



prs.setProducts(serviceProducts);
prs.setLocation(location);
serviceInfo.setProductResultSet(prs);
serializer.write(serviceInfo, response.getWriter());

%>