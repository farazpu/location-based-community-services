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
			int pageNumber=Integer.parseInt(request.getParameter("pageNum"));
			int pagesize=3;
		%>
		<h1> Welcome <%=controller.getCurrentUser().getId() %> </h1>
		<%
//			AdminController controller = new HttpControllerFactory(request).getAdminController();
			List<LocationService> list = controller.listServices(pageNumber*pagesize,(1+pageNumber)*pagesize);
			if(list.size()==0) {
				if(pageNumber!=0)
				{
					%>			
					<a href="welcome.jsp?pageNum=<%= pageNumber-1%>" >Previus Page</a>
					<%
				}
		%>
		no service available
		<%
			}
			else
			{
		%>
		<%
		if(pageNumber!=0)
		{
			%>			
			<a href="welcome.jsp?pageNum=<%= pageNumber-1%>" >Previus Page</a>
			<%
		}
		if(list.size()==pagesize)
		{
		%>	
		
			<a href="welcome.jsp?pageNum=<%= pageNumber+1%>" >Next Page</a>
		<%
		}
		%>			
		<table width="98%" border="1">
		 <tr>
		  <th></th><th>Service Name</th><th>Creation Date</th><th>Last Modified</th><th>Description</th>
		 </tr>
		<%
				for(LocationService ls : list) {
		%>
		 
			 <tr>
			 <td><input type="checkbox" name=" " value=" " /></td>
			  <td><a href="edit_service.jsp?serviceId=<%=ls.getId().getId()%>"><%=ls.getName() %></a></td> <td><%=ls.getCreated()%></td><td><%=ls.getLastModified()%></td><td><%=ls.getDesciption() %></td>
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