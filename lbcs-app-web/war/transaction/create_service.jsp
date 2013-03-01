<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="net.fast.lbcs.admin.controller.HttpAdminController"%>
<%@page import="net.fast.lbcs.data.entities.admin.service.*"%>
<%@page import="java.util.List"%>
<%@page import="net.fast.lbcs.HttpControllerFactory"%>
<%@page import="net.fast.lbcs.admin.controller.AdminController"%>
<%

	String name = request.getParameter("service_name");
	String description = request.getParameter("description");

	String msg;
	if(name.length()==0){
		msg = "ERROR!! Service name must not b empty";
	}
	else{
		AdminController controller = new HttpControllerFactory(request).getAdminController();
		LocationService ls = controller.createService(name, description);
		if(ls==null){
			msg = "ERROR!! Service name '" + name + "' already exists.";
		}else{
			msg = "Service Created Succesfully!!!";
		}
	}		
	response.sendRedirect("../admin/new_service.jsp?msg=" + msg);
%>
