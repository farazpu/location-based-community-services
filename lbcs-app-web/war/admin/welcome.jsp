<%@page import="net.fast.lbcs.admin.controller.HttpAdminController"%>
<%@page import="net.fast.lbcs.data.entities.admin.service.*"%>
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

<link rel="stylesheet" href="../css/style.css" type="text/css" />

<head>
  <title>Edit Service</title>
  </head>
 <body>
 	<jsp:include page="menu_include.jsp">
 		<jsp:param value="Welcome to LBCS administration!" name="title"/>
 	</jsp:include>
		<%
			AdminController controller = new HttpControllerFactory(request).getAdminController();

			int pageNumber=0;
			try {
				pageNumber = Integer.parseInt(request.getParameter("pageNum"));
			}catch(Exception exp){}
			int pagesize=3;
			List<LocationService> list = controller.listServices(pageNumber*pagesize,(1+pageNumber)*pagesize);
			if(list.size()==0) {
				if(pageNumber!=0) {
					%>			
					<a href="welcome.jsp?pageNum=<%= pageNumber-1%>" >Previous Page</a>
					<%
				}
		%>
		no service available
				<%
					} else {
						%>
		<div class="listing-table">
		<table>
		<thead>
		 <tr>
		  <th></th><th class="focus">Service Name</th><th>Creation Date</th><th>Last Modified</th><th>Description</th>
		 </tr>
		</thead>
		<tfoot>
			<tr>
				
				<td colspan="100%">
					<span class="message">
					Please select or click on a service to perform desired action.
					</span>
					<span class="page-buttons">
						<%
						
						if(pageNumber!=0) {
					%>			
					<a href="welcome.jsp?pageNum=<%= pageNumber-1%>" ><img alt="Previous Page" src="../images/prev_page.png"></a>
					<%
						}
						
						if(list.size()==pagesize) {
				%>	
				
					<a href="welcome.jsp?pageNum=<%= pageNumber+1%>" ><img alt="Next Page" src="../images/next_page.png"></a>
				<%
						}
				%>			
					</span>
				</td>
			</tr>
		</tfoot>
		<tbody>
		<%
				for(LocationService ls : list) {
		%>
		 
			 <tr>
			 <td><input type="checkbox" name=" " value=" " /></td>
			  <td class="focus"><a href="edit_service.jsp?serviceId=<%=ls.getId().getId()%>"><%=ls.getName() %></a></td> 
			  <td><%=ls.getCreated()%></td>
			  <td><%=ls.getLastModified()%></td>
			  <td><%=ls.getDesciption() %></td>
			 </tr>
		<%
				}
		
		
		
			}
		%>
		 </tbody>
		</table>
	</div>

	<table>
		<tr>	
			<td>
				<input class="button" type="button" onclick="window.location = 'new_service.jsp'" value="Create"/>
			</td>
			<td>
				<input class="button" type="button" value="Edit"/>
			</td>
			<td>
				<input class="button" type="button" value="Delete"/>
			</td>
		</tr>
	</table>
	<div id="footer">
		Location Based Community Service - Administration
	</div>
	   
 </body>
</html>