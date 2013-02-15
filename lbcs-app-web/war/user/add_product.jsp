<%@page import="net.fast.lbcs.ControllerFactory"%>
<%@page import="net.fast.lbcs.user.controller.UserController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="net.fast.lbcs.data.entities.admin.service.LocationService"%>
<%@page import="net.fast.lbcs.HttpControllerFactory"%>
<%@page import="net.fast.lbcs.admin.controller.AdminController"%>
<%@page import="net.fast.lbcs.data.entities.user.*"%>
<%@page import="net.fast.lbcs.data.entities.admin.item.*"%>
<%@page import="net.fast.lbcs.data.entities.admin.service.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String productName = request.getParameter("name");
ProductID productId = new ProductID(request.getParameter("productId"));
ServiceID serviceId = new ServiceID(request.getParameter("serviceId"));
ServiceItemID itemId = new ServiceItemID(request.getParameter("itemId"));
AdminController controller = new HttpControllerFactory(request).getAdminController();
LocationService ls = controller.getServiceById(serviceId);

ServiceItem si = ls.getItemById(itemId);
List<ServiceItemAttribute> attrList = si.getAttrs().getAttrs();
List<ProductAttribute> paList = new ArrayList<ProductAttribute>();
Location l = new Location();

for(ServiceItemAttribute sia : attrList){
	paList.add(new ProductAttribute(sia.getId(), request.getParameter(sia.getName())));
}

UserController userController = new HttpControllerFactory(request).getUserController();
Product p = userController.createNewProductEntry(serviceId, itemId, productId, l, paList, productName);

if(p==null)
	response.getWriter().write("Failure!");
else
	response.getWriter().write("Product Added Successfully.");

%>