<%@page import="net.fast.lbcs.web.listing.Listing"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.reflect.Array"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jquery.simplemodal.js"></script>
<script type="text/javascript" src="../scripts/listing.js"></script>

<link rel="stylesheet" href="../css/style.css" type="text/css" />
<title>Delete Object</title>
</head>
<body>
<%	
	AdminController controller = new HttpControllerFactory(request).getAdminController();
	ServiceID serviceId = new ServiceID();
	serviceId.setId(request.getParameter("locationService"));
	
	LocationService ls = controller.getServiceById(serviceId);
	ServiceItemID serviceItemId = new ServiceItemID(request.getParameter("objectId"));
	ServiceItem si= ls.getItemById(serviceItemId);
	
	
	String service = ls.getName() + " (" + ls.getId().getId() + ")";
	String object = si.getName() + " (" + si.getId().getId() + ")";
	String title = "Delete Object: " + object;
 %>




	  	<jsp:include page="title_include.jsp">
	 		<jsp:param value="<%=title %>" name="title"/>
	 	</jsp:include>
		
	<div class="popup-wrapper">
 	<form>
		<div class="delform">
			<label>
				<span>Are you sure you want to delete object '<%=object %>' from service '<%=service %>'?</span>
			</label>
			<label class="submit">
				<input type="button" class="button" value="Yes" />
			</label>
			
		</div>
	</form>
	</div>

</body>
</html>