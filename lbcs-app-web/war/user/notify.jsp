<%@page import="net.fast.lbcs.user.controller.UserController"%>
<%@page import="net.fast.lbcs.HttpControllerFactory"%>
<%@page import="net.fast.lbcs.admin.controller.AdminController"%>
<%@page import="net.fast.lbcs.data.entities.admin.service.ServiceID"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

String notification = request.getParameter("notification");
String username = request.getParameter("username");
ServiceID serviceId = new ServiceID(request.getParameter("serviceId"));


UserController userController = new HttpControllerFactory(request).getUserController();
userController.addNotification(notification, username, serviceId);

	response.getWriter().write("Success.");

%>