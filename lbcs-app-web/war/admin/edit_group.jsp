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
</head>
<body>
<%	
	AdminController controller = new HttpControllerFactory(request).getAdminController();
	ServiceID serviceId = new ServiceID();
	serviceId.setId(request.getParameter("serviceId"));
	
	LocationService ls = controller.getServiceById(serviceId);
	ServiceItemGroupID serviceItemGroupId = new ServiceItemGroupID(request.getParameter("groupId"));
	ServiceItemGroup sig= ls.getItemGroupById(serviceItemGroupId);
	
	

	String title = "Edit Group: " + sig.getName() + " (" + sig.getId().getId() + ")";
%>
		<jsp:include page="title_include.jsp">
	 		<jsp:param value="<%=title %>" name="title"/>
	 	</jsp:include>
		
		<%
			String msg = request.getParameter("msg");
			if(!(msg.equals("a"))){
		%>
		<div class="statusMessage">
			<%=msg %>
		</div>
		<%
			}
		%>
		
		<form action="../transaction/edit_group.jsp" method="get">
			<div class="form">
				<h1>Edit Group '<%=sig.getName() %>'</h1>
				<label>
					<span>Name:</span>
					<input type="text" value="<%=sig.getName() %>" class="input_text" name="group_name" id="Group_name"/>
				</label>
				<label>
					<span>Description</span>
					<textarea class="message" name="description" id="description" ><%=sig.getDescription() %></textarea>
				</label>
				<input type="hidden" name="groupId" value="<%=serviceItemGroupId.getId() %>" />
				<input type="hidden" name=serviceId value="<%=request.getParameter("serviceId") %>">
				<label class="submit">
					<input type="submit" class="button" value="Save" />
				</label>
				
			</div>
		</form> 
		
</body>
</html>