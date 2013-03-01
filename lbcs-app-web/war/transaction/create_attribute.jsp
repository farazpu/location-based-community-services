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
	ServiceItemID itemId = new ServiceItemID(request.getParameter("objectId"));
	String name = request.getParameter("attribute_name");
	String type = request.getParameter("type");
	String validation = request.getParameter("validation");
//	String context = request.getParameter("context");
	String flag = request.getParameter("flag");
	
	String msg;
	if(name.length()==0){
		msg = "ERROR!! Attribute name must not be empty";
	}
	else{
		AdminController controller = new HttpControllerFactory(request).getAdminController();
		ServiceItemAttribute attr = controller.createItemAttribute(name, type, flag, serviceId, itemId, validation);
		
		if(attr==null){
			msg = "ERROR!! Attribute name '" + name + "' already exists.";
		}else{
			msg = "Attribute Created Succesfully!!!";
		}

		
	}
	
	response.sendRedirect("../admin/new_attribute.jsp?objectId=" + itemId.getId() + "&serviceId=" + serviceId.getId() + "&msg=" + msg);

%>