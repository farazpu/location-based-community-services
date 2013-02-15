<%@page import="net.fast.lbcs.data.entities.admin.item.Validation"%>
<%@page import="java.util.List"%>
<%@page import="net.fast.lbcs.data.entities.ValidationTypeDetail"%>
<%@page import="net.fast.lbcs.admin.controller.HttpAdminController"%>
<%@page import="net.fast.lbcs.ControllerFactory"%>
<%@page import="net.fast.lbcs.admin.controller.AdminController"%>
<%@page import="net.fast.lbcs.data.DataSource"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <script type="text/javascript" src="../scripts/jquery.js"></script>
  <script type="text/javascript" src="../scripts/jquery.simplemodal.js"></script>
  <script type="text/javascript" src="../scripts/listing.js"></script>
	
  <link rel="stylesheet" href="../css/style.css" type="text/css" />
  <script type="text/javascript">

	function changeFunc() {
		var selectBox = document.getElementById("Validation_type");
		var selectedValue = selectBox.options[selectBox.selectedIndex].value;
		if(selectedValue=="Numeric Positive" || selectedValue=="Numeric Negative" || selectedValue=="Numeric NonNegative"){
				document.getElementById("params").innerHTML="</br></br></br></br>";
		} else if(selectedValue=="Logical Not"){
				document.getElementById("params").innerHTML = document.getElementById("logicparam1").innerHTML;
		} else if(selectedValue=="Logical And" || selectedValue=="Logical Or" ){
				var html=document.getElementById("logicparam1").innerHTML;
				html+=document.getElementById("logicparam2").innerHTML;
				document.getElementById("params").innerHTML=html;
		} else if(selectedValue=="Numeric Between" || selectedValue=="Numeric BetweenInclusive" ||
				selectedValue=="String LengthBetween"){
					var html = "<lable><span>Parameter1:</span><input type='text' value='' class='input_text' name='param1' id='param1'/></lable></br>";
					html += "<lable><span>Parameter2:</span><input type='text' value='' class='input_text' name='param2' id='param2'/></lable>";
					document.getElementById("params").innerHTML = html;
		} else {
				document.getElementById("params").innerHTML = "<lable><span>Parameter1:</span><input type='text' value='' class='input_text' name='param1' id='param1'/></lable></br></br>";
		}
}

  </script>
 </head>
 <body>
 
	<jsp:include page="title_include.jsp">
 		<jsp:param value="New Validation Rule" name="title"/>
 	</jsp:include>
	
	<%
		AdminController controller = new HttpAdminController(request);
		List<ValidationTypeDetail> typeList = controller.getValidationTypeDetails();
		List<Validation> validationList = controller.getAllValidations();
		String msg = request.getParameter("msg");
		if(!(msg.equals("a"))){
	%>
	<div class="statusMessage">
		<%=msg %>
	</div>
	<%
		}
	%>

	<form action="../transaction/create_validation.jsp" method="get">
		<div class="form">
			<h1>New Validation Rule</h1>
			<label>
				<span>Name:</span>
				<input type="text" value="" class="input_text" name="validation_name" id="Validation_name"/>
			</label>
			
			<label>
				<span>Type</span>
			  <select name="validation_type" id="Validation_type" onchange="changeFunc();">
			  <%for(ValidationTypeDetail vtd : typeList){ %>
			  	<option><%=vtd.getType() %></option>
			  <%
			  }
			  %>
			  </select>
			</label>
			
			<div id = 'params'>
			</div>
			
			<label class="submit">
				<input type="submit" class="button" value="Save" />
			</label>
			
		</div>
	</form> 
		
 
 
  
<!--  <div id="option1" style="display: none;">
  <form>Password: <input type="text" name="inp2">
  likho: <input type="text" name="inp3"></form>
  </div>
  
 <div id="option2" style="display: none;">
  <form>Input: <input type="text" name="inp1"></form>
  </div>
  
    <div id="option3" style="display: none;">
  <select id="selectBox1">
   <option value="1">Option #1</option>
   <option value="2">Option #2</option>
   <option value="3">Option #3</option>
   <option value="4">Option #4</option>
  </select>
  </div>
 
 <div id="option4" style="display: none;">
  <select id="selectBox2">
   <option value="1">Option #1</option>
   <option value="2">Option #2</option>
  </select>
  <select id="selectBox3">
   <option value="1">Option #1</option>
   <option value="2">Option #2</option>
  </select>
  <select id="selectBox4">
   <option value="1">Option #1</option>
   <option value="2">Option #2</option>
  </select>
  <select id="selectBox5">
   <option value="1">Option #1</option>
   <option value="2">Option #2</option>
  </select>
  <select id="selectBox6">
   <option value="1">Option #1</option>
   <option value="2">Option #2</option>
  </select>
  </div>
 -->
 
<div id="logicparam1" style="display: none;">
		<lable>
		<span>Parameter1:</span>
		<select name='param1'>
		<%for(Validation v : validationList){ %>
			<option value='<%=v.getId() %>'> <%=v.getName() %> </option>
		<% } %>
		</select></lable>
		</br>
</div>

<div id="logicparam2" style="display: none;">
		<lable>
		<span>Parameter2:</span>
		<select name='param2'>
		<%for(Validation v : validationList){ %>
			<option value='<%=v.getId() %>'> <%=v.getName() %> </option>
		<% } %>
		</select></lable>
</div>


 </body>
</html>