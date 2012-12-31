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
  <title>Edit Service</title>
  </head>
 <body>
 		<%
			AdminController controller = new HttpControllerFactory(request).getAdminController();
 			LocationService ls;
 			ServiceID serviceId = new ServiceID();
 			serviceId.setId(request.getParameter("serviceId"));
 			if(serviceId.getId().equals("New_Service1")){
 				ls = new LocationService(serviceId , request.getParameter("name"), 
 						request.getParameter("description"), new Date(), new Date(), 
 						null, null);
 			}
 			else{
	 			ls= controller.getServiceById(serviceId); 				
 			}
 			
 						
			
 		%>
		
		<h1>Managing Service: <%=ls.getId().getId() %></h1>
		<TABLE>
		<TR>
		  <TD>Name: </td>
				<td><input type="text" name="Service_name" value="<%=ls.getName() %>"></input> </TD>
		  <TD><button type="button">Save</button><button type="button">Cancel</button></TD>
		</TR>
		<TR>
		  <TD>Discription:</TD>
		  <td><input type="text" name="discription" value="<%=ls.getDesciption() %>"></input></td> 
		</TR>
		</TABLE>
	
	
	
		<h2>Objects</h2>
		<table width="98%" border="1">
		 <tr>
		  <th></th><th>Object Name</th><th>Object Group</th><th>Last Modified</th><th>Description</th>
		 </tr>
		 <%
		 List<ServiceItem> itemList=ls.getItems();
		 if(itemList!=null) {
			for(ServiceItem si : itemList) {
		 %>
			<tr>
			<td><input type="checkbox" name=" " value=" " /></td>
			<td><a href="edit_object.jsp?objectId=<%=si.getId().getId() %>&locationService=<%=ls.getId().getId() %>" ><%=si.getName() %></a></td><td><%=si.getGroup().getName() %></td><td><%=si.getDateModified() %></td><td><%=si.getDescription()%></td>
			</tr>
		<%
			}
		}
		%>
		</table>
		    <table >
	      <tr>	
		<td><button type="button"  onclick="window.location = 'new_object.jsp?locationService=<%=serviceId.getId()%>'">Create</button></td><td><button type="button">Edit</button></td><td><button type="button">Delete</button></td>
		 </tr>
	   </table>   
	   
	   <h2>Object Group</h2>
		<table width="98%" border="1">
		 <tr>
		  <th></th><th>Object Name</th><th>Creation Date</th><th>Last Modified</th><th>Description</th>
		 </tr>
		 <%
		 List<ServiceItemGroup> groupList=ls.getGroups();
		 if(groupList!=null) {
			 for(ServiceItemGroup sig : groupList) {
		 %>
			 <tr>
			 <td><input type="checkbox" name=" " value=" " /></td>
			  <td><%=sig.getName() %></td><td><%=sig.getDateCreated() %></td><td><%=sig.getDateModified()%></td><td><%=sig.getDescription() %></td>
			 </tr>
		 <%
			 }
		 }
		 %>
		</table>
		    <table >
	      <tr>	
		<td><button type="button" onclick="window.location = 'new_group.jsp?serviceId=<%=serviceId.getId() %>'">Create</button></td><td><button type="button">Edit</button></td><td><button type="button">Delete</button></td>
		 </tr>
	   </table>   
 </body>
</html>
