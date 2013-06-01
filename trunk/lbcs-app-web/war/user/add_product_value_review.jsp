<%@page import="net.fast.lbcs.data.entities.MyDate"%>
<%@page import="net.fast.lbcs.ControllerFactory"%>
<%@page import="net.fast.lbcs.user.controller.UserController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="net.fast.lbcs.data.entities.admin.service.LocationService"%>
<%@page import="net.fast.lbcs.HttpControllerFactory"%>
<%@page import="net.fast.lbcs.admin.controller.AdminController"%>
<%@page import="net.fast.lbcs.data.entities.user.*"%>
<%@page import="net.fast.lbcs.data.entities.admin.item.*"%>
<%@page import="net.fast.lbcs.data.entities.admin.service.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

ProductID productId = new ProductID(request.getParameter("productId"));
ServiceID serviceId = new ServiceID(request.getParameter("serviceId"));
ServiceItemID itemId = new ServiceItemID(request.getParameter("itemId"));
String attributeId = request.getParameter("attributeId");
String username = request.getParameter("username");
MyDate date = new MyDate(request.getParameter("date"));
String value = request.getParameter("value");

UserController userController = new HttpControllerFactory(request).getUserController();
ProductValueReview pr = userController.addProductValueReview(productId, serviceId, itemId, attributeId, username, date, value);

	response.getWriter().write("Success."); 

%>