<%@page import="net.fast.lbcs.web.listing.Listing"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="net.fast.lbcs.data.entities.admin.item.*"%>
<%@page import="net.fast.lbcs.data.entities.admin.group.*"%>
<%@page import="net.fast.lbcs.data.entities.admin.service.*"%>
<%@page import="java.util.List"%>
<%@page import="net.fast.lbcs.HttpControllerFactory"%>
<%@page import="net.fast.lbcs.admin.controller.AdminController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jquery.simplemodal.js"></script>
<script type="text/javascript" src="../scripts/listing.js"></script>

<link rel="stylesheet" href="../css/style.css" type="text/css" />
  </head>
 <body>
 
 		<%
			AdminController controller = new HttpControllerFactory(request).getAdminController();
 			LocationService ls;
 			ServiceID serviceId = new ServiceID();
 			serviceId.setId(request.getParameter("serviceId"));
 			ls= controller.getServiceById(serviceId); 				
 			
 		String msg = ls.getName();
		String title = "Delete Service: " + msg;
 		%>
	  	<jsp:include page="title_include.jsp">
	 		<jsp:param value="<%=title %>" name="title"/>
	 	</jsp:include>
		
	<div class="popup-wrapper">
 	<form action = "../transaction/delete_service.jsp" method="get">
		<div class="delform">
			<label>
				<span>Are you sure you want to delete service '<%=msg %>'?</span>
			</label>
				<input type="hidden" name="serviceId" value="<%=serviceId.getId() %>" />
			<label class="submit">
				<input type="submit" class="button" value="Yes" />
			</label>
			
		</div>
	</form>
	</div>
 </body>
</html>
