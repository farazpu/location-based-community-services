<%@page import="net.fast.lbcs.data.entities.admin.service.LocationService"%>
<%@page import="net.fast.lbcs.web.listing.Listing"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
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
			String serviceId = request.getParameter("serviceId");
 			String productId = request.getParameter("productId");
 			String attributeId = request.getParameter("attributeId");
 			String username = request.getParameter("username");
 			String itemId = request.getParameter("itemId");
 			String title="Adjust Review Value";
 		%>
	  	<jsp:include page="../admin/title_include.jsp">
	 		<jsp:param value="<%=title %>" name="title"/>
	 	</jsp:include>
		
	<div class="popup-wrapper">
 	<form action = "datasourceinteracter.jsp" method="get">
		<div class="delform">
				<input type="hidden" name="type" value="adjustProductValueReview" />
				<input type="hidden" name="serviceId" value="<%=serviceId %>" />
				<input type="hidden" name="productId" value="<%=productId %>" />
				<input type="hidden" name="attributeId" value="<%=attributeId %>" />
				<input type="hidden" name="username" value="<%=username %>" />
				<input type="hidden" name="itemId" value="<%=itemId %>" />
				<label>
					<span>Adjust Value : </span>
					<input style="background: #262626;; color: white;" type="text" name="value" value="" />
				</label>
			<label class="submit">
				<input type="submit" class="button" value="Save" />
			</label>
			
		</div>
	</form>
	</div>
 </body>
</html>
