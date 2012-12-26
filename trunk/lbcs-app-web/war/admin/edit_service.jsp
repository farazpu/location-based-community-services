<%@page import="net.fast.lbcs.admin.service.LocationService"%>
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
		<a href="logout.jsp">logout</a>
		
		<%
			AdminController controller = new HttpControllerFactory(request).getAdminController();
			List<LocationService> list = controller.listServices();
			
			for(LocationService ls : list) {
				%>
				
			
	<b><%=ls %></b> <br>
				<%
			}
		
		%>
		<h1>Managing Service: Service 1</h1>
		<TABLE>
		<TR>
		  <TD>Name: </td>
				<td><input type="text" name="Service_name"></input> </TD>
		  <TD><button type="button">Save</button><button type="button">Cancel</button></TD>
		</TR>
		<TR>
		  <TD>Discription:</TD>
		  <td><input type="text" name="discription"></input></td> 
		</TR>
		</TABLE>
	
	

	
		<h2>Objects</h2>
		<table width="98%" border="1">
		 <tr>
		  <th></th><th>Object Name</th><th>Creation Date</th><th>Last Modified</th><th>Description</th>
		 </tr>
		 <tr>
		 <td><input type="checkbox" name=" " value=" " /></td>
		  <td>T-shirt</td><td></td><td></td><td></td>
		 </tr>
		 <tr>
		 <td><input type="checkbox" name=" " value=" " /></td>
		  <td>Show</td><td></td><td></td><td></td>
		 </tr>
		 
		</table>
		    <table >
	      <tr>	
		<td><button type="button">Create</button></td><td><button type="button">Edit</button></td><td><button type="button">Delete</button></td>
		 </tr>
	   </table>   
	   
	   <h2>Object Group</h2>
		<table width="98%" border="1">
		 <tr>
		  <th></th><th>Object Name</th><th>Creation Date</th><th>Last Modified</th><th>Description</th>
		 </tr>
		 <tr>
		 <td><input type="checkbox" name=" " value=" " /></td>
		  <td>Food</td><td></td><td></td><td></td>
		 </tr>
		 <tr>
		 <td><input type="checkbox" name=" " value=" " /></td>
		  <td>Foot Wear</td><td></td><td></td><td></td>
		 </tr>
		 <td><input type="checkbox" name=" " value=" " /></td>
		  <td>Clothing</td><td></td><td></td><td></td>
		 </tr>
		 
		</table>
		    <table >
	      <tr>	
		<td><button type="button">Create</button></td><td><button type="button">Edit</button></td><td><button type="button">Delete</button></td>
		 </tr>
	   </table>   
 </body>
</html>