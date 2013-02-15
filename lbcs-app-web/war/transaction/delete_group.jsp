<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="net.fast.lbcs.data.entities.admin.item.*"%>
<%@page import="net.fast.lbcs.data.entities.admin.group.*"%>
<%@page import="net.fast.lbcs.data.entities.admin.service.*"%>
<%@page import="net.fast.lbcs.HttpControllerFactory"%>
<%@page import="net.fast.lbcs.admin.controller.AdminController"%>


<%
	AdminController controller = new HttpControllerFactory(request).getAdminController();
	String groupId = request.getParameter("groupId");
	String sId = request.getParameter("serviceId");
	ServiceID serviceId = new ServiceID(sId);
	ServiceItemGroupID id = new ServiceItemGroupID(groupId);
	controller.deleteItemGroup(serviceId, id);
	response.sendRedirect("deletion_successfull.jsp?status=true");

%>