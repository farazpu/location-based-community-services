<%@page import="net.fast.lbcs.HttpControllerFactory"%>
<%@page import="net.fast.lbcs.admin.controller.AdminController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div class="top-menu">
	<%
		AdminController controller = new HttpControllerFactory(request).getAdminController();
	%>
	<span class="menu-title"><%=request.getParameter("title") %></span>
	<span class="menu-item"> <img src="../images/user.png"><%=controller.getCurrentUser().getId() %></span> 
	<a class="menu-item" href="../admin/welcome.jsp?pageNum=0">Home</a>
	<a class="menu-item" href="../adminuserhandling/notifications.jsp?pageNum=0">Notifications</a>
	<a class="menu-item" href="../adminuserhandling/reviews.jsp?pageNum=0">Reviews</a>
	<a class="menu-item" href="../admin/logout.jsp">logout</a>
</div>
