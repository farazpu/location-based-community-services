<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="net.fast.lbcs.admin.controller.HttpAdminController"%>
<%@page import="net.fast.lbcs.data.entities.admin.service.*"%>
<%@page import="net.fast.lbcs.data.entities.admin.group.*"%>
<%@page import="net.fast.lbcs.data.entities.admin.item.*"%>
<%@page import="java.util.List"%>
<%@page import="net.fast.lbcs.HttpControllerFactory"%>
<%@page import="net.fast.lbcs.admin.controller.AdminController"%>
<%

	ServiceID serviceId = new ServiceID(request.getParameter("serviceId"));
	ServiceItemGroupID groupid = new ServiceItemGroupID(request.getParameter("group"));
	String description = request.getParameter("description");
	String name = request.getParameter("object_name");


	String msg;
	if(name.length()==0){
		msg = "ERROR!! Object name must not b empty";
	}
	else{
		AdminController controller = new HttpControllerFactory(request).getAdminController();
		ServiceItem si = controller.createItem(serviceId, name, description, groupid);
		if(si==null){
			msg = "ERROR!! Object name '" + name + "' already exists.";
		}else{
			msg = "Object Created Succesfully!!!";
		}
		
	}

	
	response.sendRedirect("../admin/new_object.jsp?serviceId="+serviceId.getId() + "&msg=" + msg);

%>