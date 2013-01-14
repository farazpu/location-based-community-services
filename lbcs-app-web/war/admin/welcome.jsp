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

<link rel="stylesheet" href="../css/style.css" type="text/css" />

</head>
 <body>
 	<jsp:include page="menu_include.jsp">
 		<jsp:param value="Welcome to LBCS administration!" name="title"/>
 	</jsp:include>
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
 					Listing.popupValue("<img src='../images/edit.png'/>", "edit_service.jsp?serviceId=" + ls.getId().getId(), "400px", "530px"), 
 					Listing.popupValue(ls.getName(), "detail_service.jsp?serviceId=" + ls.getId().getId(), "640px", "90%"), 
 					ls.getCreated(),
 					ls.getLastModified(), 
					  ls.getDesciption()
			);
 		}
 		
 		if(lst.isCreateButton())
 			lst.setCreateClickURL("new_service.jsp");
 	
 		request.setAttribute("listing", lst);
 	%>
 	<jsp:include page="../common/listing.jsp"></jsp:include>
 	
	<div id="footer">
		Location Based Community Service - Administration
	</div>
	   
 </body>
</html>