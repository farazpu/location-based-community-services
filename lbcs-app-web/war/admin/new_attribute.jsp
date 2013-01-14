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
<title>Insert title here</title>
</head>
<body>
<%	
	AdminController controller = new HttpControllerFactory(request).getAdminController();
	ServiceID serviceId = new ServiceID();
	serviceId.setId(request.getParameter("locationService"));
	
	LocationService ls = controller.getServiceById(serviceId);
	ServiceItemGroupID serviceItemGroupId = new ServiceItemGroupID(request.getParameter("groupId"));
	ServiceItemGroup sig= ls.getItemGroupById(serviceItemGroupId);
	
	String title = "New Attribute";
%>
		<jsp:include page="title_include.jsp">
	 		<jsp:param value="<%=title %>" name="title"/>
	 	</jsp:include>
		
		
		<form>
			<div class="form">
				<h1>New Attribute</h1>
				<label>
					<span>Name:</span>
					<input type="text"  class="input_text" name="Group_name" id="Group_name"/>
				</label>
				<label>
					<span>Type</span>
					<select class="input_text" name="type" id="type" >
						<option>number</option>
						<option>string</option>
					</select>
				</label>
				<label>
					<span>Validation</span>
					<select class="input_text" name="validation" id="validation"  >
						<option>None</option>
						<option>Negative</option>
						<option>Non-negative</option>
						<option>Validation 1</option>
						<option>My Validation</option>
					</select>
				</label>
				<label>
					<span>Context</span>
					<select class="input_text" name="context" id="context" >
						<option>Irrelevant</option>
						<option>Greater the Better</option>
						<option>Least Important</option>
					</select>
				</label>
				<label class="submit">
					<input type="button" class="button" value="Save" />
				</label>
				
			</div>
		</form> 
		
</body>
</html>