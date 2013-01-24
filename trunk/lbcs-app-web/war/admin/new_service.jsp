<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Service</title>
<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jquery.simplemodal.js"></script>
<script type="text/javascript" src="../scripts/listing.js"></script>

<link rel="stylesheet" href="../css/style.css" type="text/css" />
</head>

<script type="text/javascript">

	function goToEditService()
	{
		var name=document.getElementById("name").value;
		var description=document.getElementById("description").value;
		window.location = "edit_service.jsp?serviceId=New_Service1&name=" + name + "&description=" + description;
	}

</script>


<body>
		<jsp:include page="title_include.jsp">
	 		<jsp:param value="New Service" name="title"/>
	 	</jsp:include>
		
		
		<form>
			<div class="form">
				<h1>New Service</h1>
				<label>
					<span>Name:</span>
					<input type="text" value="" class="input_text" name="Service_name" id="Service_name"/>
				</label>
				<label>
					<span>Description</span>
					<textarea class="message" name="discription" id="discription"></textarea>
				</label>
				<label class="submit">
					<input type="button" class="button" value="Save" />
				</label>
				
			</div>
		</form> 
 		
		
<!--  		
		<TABLE>
		<TR>
		  <TD>Name: </td>
				<td><input id="name" type="text" name="service_name" value=""></input> </TD>
		  <TD><button type="button" onclick="goToEditService()">Save</button><button type="button">Cancel</button></TD>
		</TR>
		<TR>
		  <TD>Description:</TD>
		  <td><input id="description"  type="text" name="discription" value=""></input></td> 
		</TR>
		</TABLE>
-->
</body>
</html>