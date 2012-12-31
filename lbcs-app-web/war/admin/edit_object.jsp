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
	
 %>
		<h1>Edit Object: <%=si.getId().getId() %></h1>
		<TABLE>
		<TR>
		  <TD>Name: </td>
				<td><input type="text" name="Service_name" value="<%=si.getName() %>"></input> </TD>
		  <TD><button type="button">Save</button><button type="button">Cancel</button></TD>
		</TR>
		<TR>
		  <TD>Discription:</TD>
		  <td><input type="text" name="discription" value="<%=si.getDescription() %>"></input></td> 
		</TR>
		<TR>
		  <TD>Group:</TD>
		  <td><select name="group" value="<%=si.getGroup().getName() %>">
		  <%
		  for(ServiceItemGroup sig : groupList){			  
		  %>
		  <option> <%=sig.getName() %> </option>
		  <%
		  }
		  %>
		  </select></td> 
		</TR>
		
		</TABLE>
		
		<h2>Attributes</h2>
		<table width="98%" border="1">
		 <tr>
		  <th></th><th>Attribute Name</th><th>Type</th><th>Validation</th><th>Context</th>
		 </tr>
		 <%
		 List<ServiceItemAttribute> attributeList = si.getAttrs().getAttrs();
		 for(ServiceItemAttribute sia : attributeList) {
		 %>
		 <tr>
		 <td><input type="checkbox" name=" " value=" " /></td>
		  <td><%=sia.getName() %></a></td><td>String</td><td><%=sia.getValidation() %></td><td>none</td>
		 </tr>
		 <%
		 }
		 %>
		
 		
</body>
</html>