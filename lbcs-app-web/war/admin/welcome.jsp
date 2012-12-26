<%@page import="net.fast.lbcs.admin.controller.HttpAdminController"%>
<%@page import="net.fast.lbcs.admin.service.LocationService"%>
<%@page import="java.util.List"%>
<%@page import="net.fast.lbcs.HttpControllerFactory"%>
<%@page import="net.fast.lbcs.admin.controller.AdminController"%>
<%@page import="org.omg.PortableInterceptor.USER_EXCEPTION"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
<head>
  <title>Edit Service</title>
  </head>
 <body>
		<a href="logout.jsp">logout</a>
		<%
			AdminController controller = new HttpControllerFactory(request).getAdminController();
		%>
		<h1> Welcome <%=controller.getCurrentUser().getId() %>
		<%
//			AdminController controller = new HttpControllerFactory(request).getAdminController();
			List<LocationService> list = controller.listServices();
			if(list.size()==0) {
		%>
		no service available
		<%
			}
			else
			{
		%>
				
			
	
		<h2>Objects</h2>
		<table width="98%" border="1">
		 <tr>
		  <th></th><th>Service Name</th><th>Creation Date</th><th>Last Modified</th><th>Description</th>
		 </tr>
		<%
				for(LocationService ls : list) {
		%>
		 
			 <tr>
			 <td><input type="checkbox" name=" " value=" " /></td>
			  <td><%=ls.getName() %></td> <td><%=ls.getCreated()%></td><td><%=ls.getLastModified()%></td><td><%=ls.getDesciption() %></td>
			 </tr>
		<%
				}
			}
		%>
		 
		</table>


    <table >
	      <tr>	
		<td><button type="button">Create</button></td><td><button type="button">Edit</button></td><td><button type="button">Delete</button></td>
		 </tr>
	   </table>   
 </body>
</html>