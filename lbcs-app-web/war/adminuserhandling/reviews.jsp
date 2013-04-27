<%@page import="net.fast.lbcs.data.entities.admin.item.ServiceItemAttribute"%>
<%@page import="net.fast.lbcs.data.entities.user.ProductValueReview"%>
<%@page import="net.fast.lbcs.data.entities.user.ProductAttribute"%>
<%@page import="net.fast.lbcs.data.entities.user.ProductReview"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.fast.lbcs.data.entities.user.Product"%>
<%@page import="net.fast.lbcs.data.InMemoryDebugFacade"%>
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
<script>

$(document).ready(function(){
		$("#reviewValueList").hide();
		$("#switcher1").hide();
		$("#switcherButton").click(function(){
			$("#reviewList").hide();
			$("#switcher").hide();
			$("#reviewValueList").show();
			$("#switcher1").show();			
		});
		$("#switcherButton1").click(function(){
			$("#reviewList").show();
			$("#switcher").show();
			$("#reviewValueList").hide();
			$("#switcher1").hide();			
		});
	});

</script>

<link rel="stylesheet" href="../css/style.css" type="text/css" />

</head>


 <body>
 	<jsp:include page="../admin/menu_include.jsp">
 		<jsp:param value="Welcome to LBCS administration!" name="title"/>
 	</jsp:include>
 	

	<div id="switcher">
		<button id="switcherButton" >View Value Reviews</button> 	
	</div> 	
	<div id="switcher1">
		<button id="switcherButton1" >View Rating Reviews</button> 	
	</div> 	
 	
 	<div id="reviewList">
 	<%
 		Listing lst = new Listing();
 				
		lst.getColumns().add("Main Detail");
 		lst.getColumns().add("Internal Datail");
 		lst.getColumns().add("Review Rating");
 		lst.getColumns().add("Status");
 		lst.getColumns().add("Take Action");
 		
 		lst.getFocusColumns().add("Main Detail");
 		
 		lst.setFooterNote("List of reviews.");
 		lst.setCreateSelectionColumn(false);
 		lst.setDeleteButton(false);
 		lst.setEditButton(false);
 		lst.setCanGoNext(false);
 		lst.setCreateButton(false);
 
		
		AdminController controller = new HttpControllerFactory(request).getAdminController();
		List<LocationService> list = InMemoryDebugFacade.getLocationServices();
		List<String> statuses = new ArrayList<String>();
		statuses.add("unhandled");
		statuses.add("in process");
		statuses.add("action taken");
 		
 		for(LocationService ls : list) {
 			List<Product> productList = InMemoryDebugFacade.getProducts(ls.getId());		
 			for(Product p : productList){
 				List<ProductReview> reviewList = p.getReviews();
// 				float pRating=0;
// 				if(reviewList.size()!=0){
//	 				for(ProductReview review : reviewList){
//	 					pRating+=Integer.parseInt(review.getReviewRating());
//	 				}
//	 				pRating /=reviewList.size();
// 				}
 				for(ProductReview review : reviewList){
 					if("unhandled".equals(review.getStatus()) || "in process".equals(review.getStatus()) )
		 			lst.addRow(
		 					"Service = "+ ls.getName() + " , Item = "+ p.getServiceItem().getName() + "</br>Product = " + p.getName() + " , Username = " + review.getUsername() + "</br> Date = "+review.getDate(),
		 					"Admin Rating = " + p.getRating() + "</br>Public Rating = "+p.getPublicRating() , 
		 					review.getReviewRating()+"", 
		 					Listing.selectTag(statuses, review.getStatus(), "updateReviewStatus(\""+ls.getId().getId()+"\",\"" + p.getServiceItem().getId().getId()+"\",\"" + p.getId().getId()+"\",\"" + review.getUsername() + "\",this.value)"),
		 					Listing.popupValue("Take Action", "updateRating.jsp?serviceId="+ls.getId().getId()+"&itemId="+p.getServiceItem().getId().getId()+"&productId="+p.getId().getId(), "140px", "330px")
 					);
 				}
 			}
 		}
 		
 		
 		request.setAttribute("listing", lst);
 	%>
 	<jsp:include page="../common/listing.jsp"></jsp:include>
	</div>

	<div id="reviewValueList">
 	<%
 		lst = new Listing();
 		
		
		lst.getColumns().add("Main Detail");
 		lst.getColumns().add("Internal Detail");
 		lst.getColumns().add("Review Value");
 		lst.getColumns().add("Status");
 		lst.getColumns().add("Take Action");
 		lst.getColumns().add("Adujst Value");
 		
 		lst.getFocusColumns().add("Main Detail");
 		
 		lst.setFooterNote("List of Value Reviews.");
 		lst.setCreateSelectionColumn(false);
 		lst.setDeleteButton(false);
 		lst.setEditButton(false);
 		lst.setCanGoNext(false);
 		lst.setCreateButton(false);

		
 		for(LocationService ls : list) {
 			List<Product> productList = InMemoryDebugFacade.getProducts(ls.getId());		
 			for(Product p : productList){
 				List<ProductValueReview> reviewList = p.getValueReviews();
 				for(ProductValueReview review : reviewList){
 					if("unhandled".equals(review.getStatus()) || "in process".equals(review.getStatus()) ){
			 			lst.addRow(
			 					"Service = "+ ls.getName() + " , Item = "+ p.getServiceItem().getName() + "</br>Product = " + p.getName() + " , Username = " + review.getUsername() + "</br> Date = "+review.getDate(),
			 					"Admin Value = " + p.getAttributeValue(review.getAttributeId()) + "</br>Public Rating = "+p.getAttributePublicValue(review.getAttributeId()), 
			 					""+review.getValue(),
			 					Listing.selectTag(statuses, review.getStatus(), "updateValueReviewStatus(\""+ls.getId().getId()+"\",\"" + p.getServiceItem().getId().getId()+"\",\"" + p.getId().getId()+"\",\"" + review.getAttributeId()+"\",\"" + review.getUsername() + "\",this.value)"),
			 					Listing.popupValue("Take Action", "updateAttributeValue.jsp?serviceId="+ls.getId().getId()+"&itemId="+p.getServiceItem().getId().getId()+"&productId="+p.getId().getId()+"&attributeId="+review.getAttributeId(), "140px", "330px"),
			 					Listing.popupValue("Adjust Value", "adjustProductValueReview.jsp?serviceId="+ls.getId().getId()+"&itemId="+p.getServiceItem().getId().getId()+"&productId="+p.getId().getId()+"&attributeId="+review.getAttributeId()+"&username="+review.getUsername(), "140px", "330px")
	 					);
 					}
 				}
 			}
 		}
 				
 			
 		request.setAttribute("listing", lst);
 	%>
 	<jsp:include page="../common/listing.jsp"></jsp:include>
	</div>
 	
	<div id="footer">
		Location Based Community Service - Administration
	</div>
	   
 </body>
</html>