<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="net.fast.lbcs.data.entities.admin.service.*"%>
<%@page import="net.fast.lbcs.HttpControllerFactory"%>
<%@page import="net.fast.lbcs.admin.controller.AdminController"%>


<%
AdminController controller = new HttpControllerFactory(request).getAdminController();
String validationId = request.getParameter("validationId");
String status="";
if(controller.deleteValidation(validationId))
	status="true";
else
	status="false";
	response.sendRedirect("deletion_successfull.jsp?status="+status);
%>