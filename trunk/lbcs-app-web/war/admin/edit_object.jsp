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
<title>Edit Object</title>
<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jquery.simplemodal.js"></script>
<script type="text/javascript" src="../scripts/listing.js"></script>

<link rel="stylesheet" href="../css/style.css" type="text/css" />

</head>
<body>
<%	
	AdminController controller = new HttpControllerFactory(request).getAdminController();
	ServiceID serviceId = new ServiceID();
	serviceId.setId(request.getParameter("locationService"));
	
	LocationService ls = controller.getServiceById(serviceId);
	List<ServiceItemGroup> groupList=ls.getGroups();
	ServiceItemID serviceItemId = new ServiceItemID(request.getParameter("objectId"));
	ServiceItem si= ls.getItemById(serviceItemId);
	
	
	if(si==null && "new_object".equals(request.getParameter("objectId"))){
		ServiceItemAttributes attr = new ServiceItemAttributes(new ArrayList<ServiceItemAttribute>());
		String str = request.getParameter("group");
		for(ServiceItemGroup sig : groupList) {
			if (sig.getName().equals(str)) {
				si=new ServiceItem(serviceItemId, request.getParameter("name"), attr, 
						sig, new Date(), request.getParameter("description"));
			}
		}
	}

	String title = "Edit Object: " + si.getName() + " (" + si.getId().getId() + ")";
 %>

		<form>
			<div class="form">
				<h1>Edit Object</h1>
				<label>
					<span>Name:</span>
					<input type="text" value="<%=si.getName() %>" class="input_text" name="Service_name" id="Service_name"/>
				</label>
				<label>
					<span>Description</span>
					<textarea class="message" name="discription" id="discription"><%=si.getDescription() %></textarea>
				</label>
				<label>
					<span>Group:</span>
					<select name="group">
						  <%
						  for(ServiceItemGroup sig : groupList){			  
						  %>
						  <option <%=si.getGroup().getName().equals(sig.getName()) ? "selected='selected'" : "" %> > <%=sig.getName() %> </option>
						  <%
						  }
						  %>
				 	 </select>
				</label>
				<label class="submit">
					<input type="button" class="button" value="Save" />
				</label>
				
			</div>
		</form> 
 		
</body>
</html>