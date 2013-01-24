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
<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jquery.simplemodal.js"></script>
<script type="text/javascript" src="../scripts/listing.js"></script>

<link rel="stylesheet" href="../css/style.css" type="text/css" />
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
		<jsp:include page="title_include.jsp">
	 		<jsp:param value="New Object" name="title"/>
	 	</jsp:include>
		<form>
			<div class="form">
				<h1>New Object</h1>
				<label>
					<span>Name:</span>
					<input type="text" value="" class="input_text" name="Service_name" id="Service_name"/>
				</label>
				<label>
					<span>Description</span>
					<textarea class="message" name="discription" id="discription"></textarea>
				</label>
				<label>
					<span>Group:</span>
					<select name="group">
						  <%
						  for(ServiceItemGroup sig : groupList){			  
						  %>
						  <option > <%=sig.getName() %> </option>
						  <%
						  }
						  %>
				 	 </select>
				</label>
				<label class="submit">
					<input type="button" class="button" value="Save" onclick="goToEditObject('<%=serviceId.getId()%>')" />
				</label>
				
			</div>
		</form> 

</body>
</html>