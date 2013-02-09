<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="net.fast.lbcs.data.entities.admin.item.*"%>
<%@page import="net.fast.lbcs.data.entities.admin.service.*"%>
<%@page import="net.fast.lbcs.HttpControllerFactory"%>
<%@page import="net.fast.lbcs.admin.controller.AdminController"%>
    
<%

	AdminController controller = new HttpControllerFactory(request).getAdminController();
	String attributeId = request.getParameter("attributeId");
	String sId = request.getParameter("serviceId");
	String iId = request.getParameter("itemId");
	ServiceID serviceId = new ServiceID(sId);
	ServiceItemID itemId = new ServiceItemID(iId);
	controller.deleteItemAttribute(serviceId, itemId, attributeId);
	response.sendRedirect("deletion_successfull.jsp");
%>