<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="net.fast.lbcs.data.entities.admin.service.*"%>
<%@page import="net.fast.lbcs.HttpControllerFactory"%>
<%@page import="net.fast.lbcs.admin.controller.AdminController"%>


<%
AdminController controller = new HttpControllerFactory(request).getAdminController();
String serviceId = request.getParameter("serviceId");
ServiceID id = new ServiceID(serviceId);
controller.deleteService(id);
response.sendRedirect("deletion_successfull.jsp");

%>