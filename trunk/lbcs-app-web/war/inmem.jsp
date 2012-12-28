<%@page import="net.fast.lbcs.user.controller.Product"%>
<%@page import="net.fast.lbcs.user.User"%>
<%@page import="net.fast.lbcs.debug.BeanToHtml"%>
<%@page import="net.fast.lbcs.admin.service.LocationService"%>
<%@page import="net.fast.lbcs.data.InMemoryDebugFacade"%>
<%@page import="net.fast.lbcs.admin.Administrator"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String command = request.getParameter("cmd");
if(command == null) {
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Data</title>
<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript">

function select(targetId) {
	$(".typeContainer").removeClass("selected");
	
	$("#id" + targetId).addClass("selected");
	
}
</script>
<style type="text/css">

ul .typeContainer {

}

ul .typeContainer.selected {
  background: #EBAD99;  
}

table .typeContainer {
  border: 2px solid black;
}

table .typeContainer.selected {
  border: 4px solid red;
}

table .typeContainer.selected td {
  background: #EBAD99;  
}

table
{
  width: 100%;
  margin: 0 auto;
  border-collapse: separate;
  border-spacing: 0;
  text-shadow: 0 1px 0 #fff;
  color: #2a2a2a;
  background: #fafafa;  
  background-image: -moz-linear-gradient(top, #fff, #eaeaea, #fff); /* Firefox 3.6 */
  background-image: -webkit-gradient(linear,center bottom,center top,from(#fff),color-stop(0.5, #eaeaea),to(#fff)); 
}

table td
{
  height: 50px;
  line-height: 50px;
  padding: 0 20px;
  border-bottom: 1px solid #cdcdcd;
  border-right: 1px solid #cdcdcd;
  box-shadow: 0 1px 0 white;
  -moz-box-shadow: 0 1px 0 white;
  -webkit-box-shadow: 0 1px 0 white;
  white-space: nowrap;
  text-align: center;
}

/*Body*/
table tbody td
{
  text-align: left;
  font: normal 12px Verdana, Arial, Helvetica;
  width: 150px;
  background: #e7f3d4;  
  background: rgba(184,243,85,0.3);
}


/*Header*/
table tr th
{
  font: bold 1.3em 'trebuchet MS', 'Lucida Sans', Arial;  
  -moz-border-radius-topright: 10px;
  -moz-border-radius-topleft: 10px; 
  border-top-right-radius: 10px;
  border-top-left-radius: 10px;
  border-top: 1px solid #eaeaea; 
}

h1 {
	font-family: 'Droid Sans', serif;
	font-size: 22px;
	border-bottom: 1px dashed #aaa;
	border-left: 7px solid #aaa;		
	border-left: 7px solid rgba(0,0,0,.2);
	margin: 0 -15px 15px -22px;
	padding: 15px 15px 5px 15px;
}
</style>
</head>
<body>
<h1>Admins</h1>
<%=new BeanToHtml(InMemoryDebugFacade.getAdmins(), Administrator.class, "a").createPropertiesIndentedList() %>

<h1>Location Services</h1>
<%=new BeanToHtml(InMemoryDebugFacade.getLocationServices(), LocationService.class, "b").createPropertiesIndentedList() %>

<h1>Users</h1>
<%=new BeanToHtml(InMemoryDebugFacade.getUsers(), User.class, "c").createPropertiesIndentedList() %>

<h1>Products</h1>
<%=new BeanToHtml(InMemoryDebugFacade.getProducts(), Product.class, "d").createPropertiesIndentedList() %>


</body>
</html>
<%}%>