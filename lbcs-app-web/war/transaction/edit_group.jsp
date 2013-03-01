<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="net.fast.lbcs.admin.controller.HttpAdminController"%>
<%@page import="net.fast.lbcs.data.entities.admin.service.*"%>
<%@page import="net.fast.lbcs.data.entities.admin.group.*"%>
<%@page import="java.util.List"%>
<%@page import="net.fast.lbcs.HttpControllerFactory"%>
<%@page import="net.fast.lbcs.admin.controller.AdminController"%>
<%

ServiceID serviceId = new ServiceID(request.getParameter("serviceId"));
ServiceItemGroupID groupId = new ServiceItemGroupID(request.getParameter("groupId"));
	String name = request.getParameter("group_name");
	String description = request.getParameter("description");
	

	String msg;
	if(name.length()==0){
		msg = "ERROR!! Group name must not b empty";
	}
	else{
	
		AdminController controller = new HttpControllerFactory(request).getAdminController();
		ServiceItemGroup sig = controller.editGroup(groupId, serviceId, name, description);
		if(sig==null){
			msg = "ERROR!! Group name '" + name + "' already exists.";
		}else{
			msg = "Group Created Succesfully!!!";
		}
	}	
	response.sendRedirect("../admin/edit_group.jsp?groupId=" + groupId.getId() + "&serviceId=" + serviceId.getId() + "&msg="+msg);
%>
    