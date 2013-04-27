<%@page import="net.fast.lbcs.data.entities.admin.service.ServiceID"%>
<%@page import="net.fast.lbcs.data.entities.admin.item.ServiceItemID"%>
<%@page import="net.fast.lbcs.data.entities.user.ProductID"%>
<%@page import="net.fast.lbcs.admin.controller.HttpAdminController"%>
<%@page import="net.fast.lbcs.ControllerFactory"%>
<%@page import="net.fast.lbcs.admin.controller.AdminController"%>
<%
	AdminController controller = new HttpAdminController(request);
	String type= request.getParameter("type");
	
	if(type.equals("updateNotificationStatus")){
		String id=request.getParameter("id");
		String value=request.getParameter("value");
		controller.updateNotificationStatus(id, value);
	}
	
	
	else if(type.equals("deleteNotification")){
		String id=request.getParameter("notificationId");
		controller.deleteNotification(id);		
	}
	
	
	else if(type.equals("updateReviewStatus")){
		ProductID productId = new ProductID(request.getParameter("productId"));
		ServiceID serviceId = new ServiceID(request.getParameter("serviceId"));
		ServiceItemID itemId = new ServiceItemID(request.getParameter("itemId"));
		String username = request.getParameter("username");
		String status = request.getParameter("value");
		controller.updateProductReviewStatus(productId, serviceId, itemId, username, status);
	}
	
	
	else if(type.equals("updateValueReviewStatus")){
		ProductID productId = new ProductID(request.getParameter("productId"));
		ServiceID serviceId = new ServiceID(request.getParameter("serviceId"));
		ServiceItemID itemId = new ServiceItemID(request.getParameter("itemId"));
		String attributeId = request.getParameter("attributeId");
		String username = request.getParameter("username");
		String status = request.getParameter("value");
		controller.updateProductValueReviewStatus(productId, serviceId, itemId, attributeId, username, status);		
	}

	
	else if(type.equals("updateRating")){
		ProductID productId = new ProductID(request.getParameter("productId"));
		ServiceID serviceId = new ServiceID(request.getParameter("serviceId"));
		ServiceItemID itemId = new ServiceItemID(request.getParameter("itemId"));
		String status = request.getParameter("value");
		controller.updateProductRating(productId, serviceId, itemId, status);
	}
	
	
	else if(type.equals("adjustProductValueReview")){
		ProductID productId = new ProductID(request.getParameter("productId"));
		ServiceID serviceId = new ServiceID(request.getParameter("serviceId"));
		ServiceItemID itemId = new ServiceItemID(request.getParameter("itemId"));
		String value = request.getParameter("value");
		String attributeId = request.getParameter("attributeId");
		String username = request.getParameter("username");
		System.out.println("--------------------"+value);
		controller.adjustProductValueReview(productId, serviceId, itemId, attributeId, username, value);
		
	}
	
	
	else if(type.equals("updateAttributeValue")){
		ProductID productId = new ProductID(request.getParameter("productId"));
		ServiceID serviceId = new ServiceID(request.getParameter("serviceId"));
		ServiceItemID itemId = new ServiceItemID(request.getParameter("itemId"));
		String attributeId = request.getParameter("attributeId");
		String value = request.getParameter("value");
		controller.updateProductAttributeValue(productId, serviceId, itemId, attributeId, value);
	}
	
%>