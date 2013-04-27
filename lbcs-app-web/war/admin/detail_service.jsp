<%@page import="net.fast.lbcs.web.listing.Listing"%>
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
<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jquery.simplemodal.js"></script>
<script type="text/javascript" src="../scripts/listing.js"></script>
<script>

$(document).ready(function(){
		$("#itemList").hide();
		$("#switcher1").hide();
		$("#switcherButton").click(function(){
			$("#groupList").hide();
			$("#switcher").hide();
			$("#itemList").show();
			$("#switcher1").show();			
		});
		$("#switcherButton1").click(function(){
			$("#groupList").show();
			$("#switcher").show();
			$("#itemList").hide();
			$("#switcher1").hide();			
		});
	});

</script>

<link rel="stylesheet" href="../css/style.css" type="text/css" />
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
 			
 						
		String title = "Service Detail: " + ls.getName() + " (" + ls.getId().getId() + ")";
 		%>
	  	<jsp:include page="menu_include.jsp">
	 		<jsp:param value="<%=title %>" name="title"/>
	 	</jsp:include>
		
	<div class="popup-wrapper">
 	
	<div id="switcher">
		<button id="switcherButton" >View Items</button> 	
	</div> 	
	<div id="switcher1">
		<button id="switcherButton1" >View Groups</button> 	
	</div> 	

	<div id="itemList">	
	<%
		Listing lst = new Listing();
		
		lst.setTitle("Objects");
		
		lst.getColumns().add(">delete");
		lst.getColumns().add(">edit");
		lst.getColumns().add("Object Name");
		lst.getColumns().add("Object Group");
		lst.getColumns().add("Last Modified");
		lst.getColumns().add("Description");

		lst.getFocusColumns().add("Object Name");
		
 		lst.setFooterNote("List of available Objects.");
		lst.setCreateSelectionColumn(false);
 		lst.setDeleteButton(false);
 		lst.setEditButton(false);
 		
 		lst.setCanGoNext(false);

		List<ServiceItem> itemList=ls.getItems();
		if(itemList!=null) {
			for(ServiceItem si : itemList) {
				lst.addRow(
						Listing.popupValue("<img src='../images/delete.png'/>", "delete_object.jsp?objectId=" + si.getId().getId() + "&serviceId=" + ls.getId().getId(), "140px", "330px"),
						Listing.popupValue("<img src='../images/edit.png'/>", "edit_object.jsp?objectId=" + si.getId().getId() + "&serviceId=" + ls.getId().getId() + "&msg=a", "445px", "525px"),
						Listing.anchorValue(si.getName(), "detail_object.jsp?objectId=" + si.getId().getId() + "&serviceId=" + ls.getId().getId()),
						si.getGroup().getName(), si.getDateModified(), si.getDescription());
			}
		}
		lst.setCreateClickURL("new_object.jsp?serviceId="+ls.getId().getId() + "&msg=a");

 		request.setAttribute("listing", lst);
 	%>
 	<jsp:include page="../common/listing.jsp"></jsp:include>
	</div>

		<div id="groupList">
	<%
		lst = new Listing();
		
		lst.setTitle("Object Group");
		
		lst.getColumns().add(">delete");
		lst.getColumns().add(">edit");
		lst.getColumns().add("Group Name");
		lst.getColumns().add("Creation Date");
		lst.getColumns().add("Last Modified");
		lst.getColumns().add("Description");
		

		lst.getFocusColumns().add("Group Name");
		
 		lst.setFooterNote("List of available groups.");
 		lst.setCreateSelectionColumn(false);
 		lst.setDeleteButton(false);
 		lst.setEditButton(false);
 		
 		lst.setCanGoNext(false);

		 List<ServiceItemGroup> groupList=ls.getGroups();
		 if(groupList!=null) {
			 for(ServiceItemGroup sig : groupList) {
					lst.addRow(
							Listing.popupValue("<img src='../images/delete.png'/>", "delete_group.jsp?groupId=" + sig.getId().getId() + "&serviceId=" + ls.getId().getId(), "140px", "330px"),
							Listing.popupValue("<img src='../images/edit.png'/>", "edit_group.jsp?groupId=" + sig.getId().getId() + "&serviceId=" + ls.getId().getId() + "&msg=a", "385px", "525px"),
//							Listing.popupValue(sig.getName(), "edit_group.jsp?objectId=" + sig.getId().getId() + "&locationService=" + ls.getId().getId(), "90%", "90%"),
							sig.getName(),sig.getDateCreated(), sig.getDateModified(), sig.getDescription());
			 }
		 }

		lst.setCreateClickURL("new_group.jsp?serviceId="+ls.getId().getId() + "&msg=a");
 		request.setAttribute("listing", lst);
 	%>
 	<jsp:include page="../common/listing.jsp"></jsp:include>
	</div>
	<div id="footer">
		Location Based Community Service - Administration
	</div>
	</div>
 </body>
</html>
