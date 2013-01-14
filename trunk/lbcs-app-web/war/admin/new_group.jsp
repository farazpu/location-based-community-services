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
<title>Insert title here</title>
</head>
<body>

		<jsp:include page="title_include.jsp">
	 		<jsp:param value="New Group :" name="title"/>
	 	</jsp:include>
		
		
		<form>
			<div class="form">
				<h1>New Group</h1>
				<label>
					<span>Name:</span>
					<input type="text" value="" class="input_text" name="Group_name" id="Group_name"/>
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
		

</body>
</html>