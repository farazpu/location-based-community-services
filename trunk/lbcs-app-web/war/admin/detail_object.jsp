<%@page import="net.fast.lbcs.web.listing.Listing"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.reflect.Array"%>
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
<title>Edit Object</title>
<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jquery.simplemodal.js"></script>
<script type="text/javascript" src="../scripts/listing.js"></script>

<link rel="stylesheet" href="../css/style.css" type="text/css" />

</head>
<body>
<%	
	AdminController controller = new HttpControllerFactory(request).getAdminController();
	ServiceID serviceId = new ServiceID();
	serviceId.setId(request.getParameter("serviceId"));
	
	LocationService ls = controller.getServiceById(serviceId);
	List<ServiceItemGroup> groupList=ls.getGroups();
	ServiceItemID serviceItemId = new ServiceItemID(request.getParameter("objectId"));
	ServiceItem si= ls.getItemById(serviceItemId);
	
	
	if(si==null && "new_object".equals(request.getParameter("objectId"))){
		ServiceItemAttributes attr = new ServiceItemAttributes(new ArrayList<ServiceItemAttribute>());
		String str = request.getParameter("group");
		for(ServiceItemGroup sig : groupList) {
			if (sig.getName().equals(str)) {
				si=new ServiceItem(serviceItemId, request.getParameter("name"), attr, 
						sig, new Date(), request.getParameter("description"));
			}
		}
	}

	String title = "Edit Object: " + si.getName() + " (" + si.getId().getId() + ")";
 %>
 	  	<jsp:include page="menu_include.jsp">
	 		<jsp:param value="<%=title %>" name="title"/>
	 	</jsp:include>

			<%
		Listing lst = new Listing();
		
		lst.setTitle("Attributes");
		
		lst.getColumns().add(">delete");
		lst.getColumns().add(">edit");
		lst.getColumns().add("Attribute Name");
		lst.getColumns().add("Type");
		lst.getColumns().add("Validation");
		lst.getColumns().add("Context");

		lst.getFocusColumns().add("Attribute Name");
		
 		lst.setCreateSelectionColumn(false);
 		lst.setDeleteButton(false);
 		lst.setEditButton(false);
 		
 		lst.setCanGoNext(false);

		 List<ServiceItemAttribute> attributeList = si.getAttrs().getAttrs();
		if(attributeList!=null) {
			 for(ServiceItemAttribute sia : attributeList) {
				lst.addRow(
						Listing.popupValue("<img src='../images/delete.png'/>", "delete_attribute.jsp?objectId=" + si.getId().getId() + "&serviceId=" + ls.getId().getId() + "&attribute=" + sia.getId(), "140px", "330px"),
						Listing.popupValue("<img src='../images/edit.png'/>", "edit_attribute.jsp?objectId=" + si.getId().getId() + "&serviceId=" + ls.getId().getId() + "&attribute=" + sia.getName()  + "&msg=a", "420px", "525px"),
//						Listing.popupValue(sia.getName(), "edit_attribute.jsp", "90%", "90%"),
						sia.getName(), sia.getType(), sia.getValidation(), sia.getContext());
			}
		}
		lst.setCreateClickURL("new_attribute.jsp?objectId=" + si.getId().getId() + "&serviceId=" + ls.getId().getId() + "&msg=a");
 		request.setAttribute("listing", lst);
 	%>
 	<jsp:include page="../common/listing.jsp"></jsp:include>
		
 		
</body>
</html>