<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<body>
		<h1>Create New Service</h1>
		
		<TABLE>
		<TR>
		  <TD>Name: </td>
				<td><input id="name" type="text" name="service_name" value=""></input> </TD>
		  <TD><button type="button" onclick="window.location = 'edit_service?serviceId=<%=request.getParameter("serviceId")%>'">Save</button><button type="button">Cancel</button></TD>
		</TR>
		<TR>
		  <TD>Description:</TD>
		  <td><input id="description"  type="text" name="discription" value=""></input></td> 
		</TR>
		</TABLE>

</body>
</body>
</html>