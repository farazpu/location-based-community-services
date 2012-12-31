<%@page import="java.util.ArrayList"%>
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
<title>Insert title here</title>
</head>

<script type="text/javascript">

	function goToEditObject(locationService)
	{
		var name=document.getElementById("name").value;
		var description=document.getElementById("description").value;
		var group=document.getElementById("group").value;
		window.location = "edit_object.jsp?objectId=new_object&name=" + name + "&description=" + description + "&group=" + group + "&locationService=" + locationService;
	}

</script>


<%	
	AdminController controller = new HttpControllerFactory(request).getAdminController();
	ServiceID serviceId = new ServiceID();
	serviceId.setId(request.getParameter("locationService"));
	
	LocationService ls = controller.getServiceById(serviceId);
	List<ServiceItemGroup> groupList;
	if(ls!=null) {
		groupList=ls.getGroups();
	}
	else {
		groupList = new ArrayList<ServiceItemGroup>();
	}
%>
<body>
		<h1>Create New Object</h1>
		<TABLE>
		<TR>
		  <TD>Name: </td>
				<td><input id="name" type="text" name="Service_name" value=""></input> </TD>
		  <TD><button type="button"  onclick="goToEditObject('<%=serviceId.getId()%>')">Save</button><button type="button">Cancel</button></TD>
		</TR>
		<TR>
		  <TD>Description:</TD>
		  <td><input id="description" type="text" name="discription" value=""></input></td> 
		</TR>
		<TR>
		  <TD>Group:</TD>
		  <td><select id="group" name="group" value="">
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

</body>
</html>