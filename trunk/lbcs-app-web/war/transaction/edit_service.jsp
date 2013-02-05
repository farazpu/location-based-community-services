<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="net.fast.lbcs.admin.controller.HttpAdminController"%>
<%@page import="net.fast.lbcs.data.entities.admin.service.*"%>
<%@page import="java.util.List"%>
<%@page import="net.fast.lbcs.HttpControllerFactory"%>
<%@page import="net.fast.lbcs.admin.controller.AdminController"%>
<%

	ServiceID serviceId = new ServiceID(request.getParameter("serviceId"));
	String name = request.getParameter("service_name");
	String description = request.getParameter("description");
	
	AdminController controller = new HttpControllerFactory(request).getAdminController();
	LocationService ls = controller.editService(serviceId, name, description);
	String msg;
	if(ls==null){
		msg = "ERROR!! Service name '" + name + "' already exists.";
	}else{
		msg = "Service Updated Succesfully!!!";
	}
		
	response.sendRedirect("../admin/edit_service.jsp?serviceId=" + serviceId.getId() + "&msg=" + msg);
%>
