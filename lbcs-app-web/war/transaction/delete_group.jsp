<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="net.fast.lbcs.data.entities.admin.item.*"%>
<%@page import="net.fast.lbcs.data.entities.admin.group.*"%>
<%@page import="net.fast.lbcs.HttpControllerFactory"%>
<%@page import="net.fast.lbcs.admin.controller.AdminController"%>


<%
AdminController controller = new HttpControllerFactory(request).getAdminController();
String groupId = request.getParameter("groupId");
ServiceItemGroupID id = new ServiceItemGroupID(groupId);
controller.deleteItemGroup(id);
response.sendRedirect("deletion_successfull.jsp");

%>