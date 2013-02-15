<%@page import="net.fast.lbcs.admin.controller.HttpAdminController"%>
<%@page import="net.fast.lbcs.data.entities.UserLoginData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.fast.lbcs.data.InMemoryDebugFacade"%>
<%@page import="net.fast.lbcs.*"%>
<%@page import="net.fast.lbcs.user.controller.UserController"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="net.fast.lbcs.data.entities.admin.item.*"%>
<%@page import="net.fast.lbcs.data.entities.admin.group.*"%>
<%@page import="net.fast.lbcs.data.entities.admin.service.*"%>
<%@page import="java.util.List"%>
<%@page import="net.fast.lbcs.HttpControllerFactory"%>
<%@page import="net.fast.lbcs.admin.controller.AdminController"%>
<%@page import="org.simpleframework.xml.core.Persister"%>
<%@page import="org.simpleframework.xml.Serializer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
	Serializer serializer = new Persister();
	List<LocationService> serviceList;
	List<Validation> validations;
	UserLoginData userLoginData = new UserLoginData();
	
	AdminController adminController = new HttpAdminController(request);
	UserController userController = new HttpControllerFactory((HttpServletRequest) request).getUserController();
	if(userController.login(request.getParameter("username"), request.getParameter("password"), null)) {
		serviceList= InMemoryDebugFacade.getLocationServices();
		validations = adminController.getAllValidations();
 		userLoginData.setError(false);
	}
	else {
		serviceList=new ArrayList<LocationService>();
		validations = new ArrayList<Validation>();
		userLoginData.setError(true);
	}
	userLoginData.setLocationServices(serviceList);
	userLoginData.setValiations(validations);
	serializer.write(userLoginData, response.getWriter());
	
%>
