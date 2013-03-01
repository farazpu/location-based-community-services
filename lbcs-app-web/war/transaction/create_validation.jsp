<%@page import="net.fast.lbcs.data.entities.admin.item.Validation"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="net.fast.lbcs.admin.controller.HttpAdminController"%>
<%@page import="net.fast.lbcs.data.entities.admin.service.*"%>
<%@page import="java.util.List"%>
<%@page import="net.fast.lbcs.HttpControllerFactory"%>
<%@page import="net.fast.lbcs.admin.controller.AdminController"%>
<%

	String name = request.getParameter("validation_name");
	String type = request.getParameter("validation_type");
	List<String> params = new ArrayList<String>();
	

	String msg;
	if(name.length()==0){
		msg = "ERROR!! Validation name must not b empty";
	}
	else{
	
		if(type.equals("Numeric Positive") || type.equals("Numeric Negative") || type.equals("Numeric NonNegative")){}
		else if(type.equals("Logical And") || type.equals("Logical Or") || type.equals("Numeric Between")
				|| type.equals("Numeric BetweenInclusive") || type.equals("String LengthBetween")){
			params.add(request.getParameter("param1"));
			params.add(request.getParameter("param2"));
		} else {
			params.add(request.getParameter("param1"));
		}
		
		AdminController controller = new HttpControllerFactory(request).getAdminController();
		Validation v = controller.createValidation(name, type, params);
		
		if(v==null){
			msg = "ERROR!! Validation Rule name '" + name + "' already exists.";
		}else{
			msg = "Service Created Succesfully!!!";
		}
	}		
	response.sendRedirect("../admin/new_validation.jsp?msg=" + msg);
%>
