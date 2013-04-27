<%@page import="java.util.ArrayList"%>
<%@page import="net.fast.lbcs.data.InMemoryDebugFacade"%>
<%@page import="net.fast.lbcs.data.entities.user.NotificationToAdmin"%>
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
<script type="text/javascript" src="../scripts/statusfunctions.js"></script>

<link rel="stylesheet" href="../css/style.css" type="text/css" />

</head>
 <body>
 	<jsp:include page="../admin/menu_include.jsp">
 		<jsp:param value="Welcome to LBCS administration!" name="title"/>
 	</jsp:include>
 	<%
 		Listing lst = new Listing();
 		
		lst.getColumns().add(">delete");
		
		lst.getColumns().add("Detail");
 		lst.getColumns().add("Text"); 
 		lst.getColumns().add("Status");

 		
 		lst.getFocusColumns().add("Detail");
 		
 		lst.setFooterNote("List of available notification.");
 		lst.setCreateSelectionColumn(false);
 		lst.setDeleteButton(false);
 		lst.setEditButton(false);
 		lst.setCanGoNext(false);
 		lst.setCreateButton(false);


		
		AdminController controller = new HttpControllerFactory(request).getAdminController();
		List<LocationService> list = InMemoryDebugFacade.getLocationServices();
		List<NotificationToAdmin> notificationList = controller.getNotifications();
 		
 		List<String> statuses = new ArrayList<String>();
		statuses.add("unhandled");
		statuses.add("in process");
		 		
 		
 		int i=0;
 		for(NotificationToAdmin notification : notificationList) {
 			for(LocationService ls : list){
 				if(ls.getId().getId().equals(notification.getService()))
 					notification.setService(ls.getName());
 			}
 			lst.addRow(
 					Listing.popupValue("<img src='../images/delete.png'/>", "delete_notification.jsp?notificationId=" + notification.getId(), "140px", "330px"), 
 					"Service : "+ notification.getService()+ "</br>Username : "+notification.getUsername() + "</br>Date : " + notification.getDate(),
 					notification.getText(), Listing.selectTag(statuses, notification.getStatus(), "updateNotificationStatus(\""+notification.getId()+"\",this.value)")
			);
 			i++;
 		} 	
 		request.setAttribute("listing", lst);
 	%>
 	<jsp:include page="../common/listing.jsp"></jsp:include>
 	
	<div id="footer">
		Location Based Community Service - Administration
	</div>
	   
 </body>
</html>