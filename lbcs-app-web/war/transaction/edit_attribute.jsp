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
	String attrId = request.getParameter("attributeId");
	String name = request.getParameter("attribute_name");
	String type = request.getParameter("type");
	String validation = request.getParameter("validation");
//	String context = request.getParameter("context");
	String flag = request.getParameter("flag");


	String msg;
	if(name.length()==0){
		msg = "ERROR!! Attribute name must not b empty";
	}
	else{	
		AdminController controller = new HttpControllerFactory(request).getAdminController();
		ServiceItemAttribute attr = controller.editAttribute(attrId, name, validation, type, flag, serviceId, itemId);
		if(attr==null){
			msg = "ERROR!! Attribute name '" + name + "' already exists.";
		}else{
			msg = "Attribute Updated Succesfully!!!";
		}
	}
	
	response.sendRedirect("../admin/edit_attribute.jsp?objectId=" + itemId.getId() + "&serviceId=" + serviceId.getId() + "&attribute=" + attrId + "&msg=" + msg);

%>