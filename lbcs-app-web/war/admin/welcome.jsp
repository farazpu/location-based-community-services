<%@page import="net.fast.lbcs.data.entities.admin.item.Validation"%>
<%@page import="net.fast.lbcs.web.listing.Listing"%>
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
<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jquery.simplemodal.js"></script>
<script type="text/javascript" src="../scripts/listing.js"></script>
<script>

$(document).ready(function(){
		$("#validationList").hide();
		$("#switcher1").hide();
		$("#switcherButton").click(function(){
			$("#serviceList").hide();
			$("#switcher").hide();
			$("#validationList").show();
			$("#switcher1").show();			
		});
		$("#switcherButton1").click(function(){
			$("#serviceList").show();
			$("#switcher").show();
			$("#validationList").hide();
			$("#switcher1").hide();			
		});
	});

</script>

<link rel="stylesheet" href="../css/style.css" type="text/css" />

</head>


 <body>
 	<jsp:include page="menu_include.jsp">
 		<jsp:param value="Welcome to LBCS administration!" name="title"/>
 	</jsp:include>
 	

	<div id="switcher">
		<button id="switcherButton" >View Validation</button> 	
	</div> 	
	<div id="switcher1">
		<button id="switcherButton1" >View Sevices</button> 	
	</div> 	
 	
 	<div id="serviceList">
 	<%
 		Listing lst = new Listing();
 		
		lst.getColumns().add(">delete");
		lst.getColumns().add(">edit");
		
		lst.getColumns().add("Service Name");
 		lst.getColumns().add("Creation Date");
 		lst.getColumns().add("Last Modified");
 		lst.getColumns().add("Description");
 		
 		lst.getFocusColumns().add("Service Name");
 		
 		lst.setFooterNote("List of available services.");
 		lst.setCreateSelectionColumn(false);
 		lst.setDeleteButton(false);
 		lst.setEditButton(false);
 

 		int pageNumber=0;
		try {
			pageNumber = Integer.parseInt(request.getParameter("pageNum"));
		}catch(Exception exp){}
		lst.setCurrentPage(pageNumber);
		
		int pagesize=3;
		AdminController controller = new HttpControllerFactory(request).getAdminController();
		List<LocationService> list = controller.listServices(pageNumber*pagesize,(1+pageNumber)*pagesize);
		
 		lst.setCanGoNext(list.size() > 0);
 		
 		for(LocationService ls : list) {
 			lst.addRow(
 					Listing.popupValue("<img src='../images/delete.png'/>", "delete_service.jsp?serviceId=" + ls.getId().getId(), "140px", "330px"), 
 					Listing.popupValue("<img src='../images/edit.png'/>", "edit_service.jsp?serviceId=" + ls.getId().getId() + "&msg=a" , "400px", "530px"), 
 					Listing.anchorValue(ls.getName(), "detail_service.jsp?serviceId=" + ls.getId().getId()), 
 					ls.getCreated(),
 					ls.getLastModified(), 
					  ls.getDesciption()
			);
 		}
 		
 		if(lst.isCreateButton())
 			lst.setCreateClickURL("new_service.jsp?msg=a");
 	
 		request.setAttribute("listing", lst);
 	%>
 	<jsp:include page="../common/listing.jsp"></jsp:include>
	</div>

	<div id="validationList">
 	<%
 		lst = new Listing();
 		
		lst.getColumns().add(">delete");
		lst.getColumns().add(">edit");
		
		lst.getColumns().add("Validation Name");
 		lst.getColumns().add("Validation Type");
 		lst.getColumns().add("Validation Parameters");
 		lst.getColumns().add("Description");
 		
 		lst.getFocusColumns().add("Validation Name");
 		
 		lst.setFooterNote("List of available Validation Rules.");
 		lst.setCreateSelectionColumn(false);
 		lst.setDeleteButton(false);
 		lst.setEditButton(false);
 		lst.setCanGoNext(false);
 

		try {
			pageNumber = Integer.parseInt(request.getParameter("pageNum"));
		}catch(Exception exp){}
		lst.setCurrentPage(pageNumber);
		
		controller = new HttpControllerFactory(request).getAdminController();
		List<Validation> validationList = controller.getAllValidations();
		
 		for(Validation val : validationList) {
 			String params = val.getParams().get(0);
 			for(int i=1;i<val.getParams().size(); i++){
 				params = params + ", " + val.getParams().get(i);
 			}
 			lst.addRow(
 					Listing.popupValue("<img src='../images/delete.png'/>", "delete_validation.jsp?validationId=" + val.getId()  , "140px", "330px"), 
 					Listing.popupValue("<img src='../images/edit.png'/>", "edit_validation.jsp?validationId=" + val.getId() + "&msg=a" , "400px", "530px"), 
 					val.getName(),
 					val.getType(),
 					params,
 					val.getDescription()
			);
 		}
 		
 		if(lst.isCreateButton())
 			lst.setCreateClickURL("new_validation.jsp?msg=a");
 	
 		request.setAttribute("listing", lst);
 	%>
 	<jsp:include page="../common/listing.jsp"></jsp:include>
	</div>
 	
	<div id="footer">
		Location Based Community Service - Administration
	</div>
	   
 </body>
</html>